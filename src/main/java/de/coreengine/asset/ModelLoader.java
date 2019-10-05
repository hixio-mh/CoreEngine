/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2019, Suuirad
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.coreengine.asset;

import com.bulletphysics.collision.shapes.CollisionShape;
import de.coreengine.asset.meta.MetaMaterial;
import de.coreengine.asset.meta.MetaMesh;
import de.coreengine.asset.meta.MetaModel;
import de.coreengine.asset.modelLoader.MaterialData;
import de.coreengine.asset.modelLoader.MeshData;
import de.coreengine.rendering.model.Material;
import de.coreengine.rendering.model.Mesh;
import de.coreengine.rendering.model.Model;
import de.coreengine.util.Logger;
import javafx.util.Pair;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMaterial;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;

import static org.lwjgl.assimp.Assimp.*;

/**Class for loading model files<br>
 * <br>
 * Supported Formats:<br>
 * <br>
 * COMMON INTERCHANGE FORMATS (An asterisk indicates limited support)<br>
 * Autodesk ( .fbx )<br>
 * Collada ( .dae )<br>
 * glTF ( .gltf, .glb )<br>
 * Blender 3D ( .blend )<br>
 * 3ds Max 3DS ( .3ds )<br>
 * 3ds Max ASE ( .ase )<br>
 * Wavefront Object ( .obj )<br>
 * Industry Foundation Classes (IFC/Step) ( .ifc )<br>
 * XGL ( .xgl,.zgl )<br>
 * Stanford Polygon Library ( .ply )<br>
 * *AutoCAD DXF ( .dxf )<br>
 * LightWave ( .lwo )<br>
 * LightWave Scene ( .lws )<br>
 * Modo ( .lxo )<br>
 * Stereolithography ( .stl )<br>
 * DirectX X ( .x )<br>
 * AC3D ( .ac )<br>
 * Milkshape 3D ( .ms3d )<br>
 * * TrueSpace ( .cob,.scn )<br>
 * *OpenGEX ( .ogex )<br>
 * *X3D ( .x3d )<br>
 * *3MF ( .3mf )<br>
 *<br>
 * MOTION CAPTURE FORMATS<br>
 * Biovision BVH ( .bvh )<br>
 * * CharacterStudio Motion ( .csm )<br>
 *<br>
 * GRAPHICS ENGINE FORMATS<br>
 * Ogre XML ( .xml )<br>
 * Irrlicht Mesh ( .irrmesh )<br>
 * * Irrlicht Scene ( .irr )<br>
 * <br>
 * GAME FILE FORMATS<br>
 * Quake I ( .mdl )<br>
 * Quake II ( .md2 )<br>
 * Quake III Mesh ( .md3 )<br>
 * Quake III Map/BSP ( .pk3 )<br>
 * * Return to Castle Wolfenstein ( .mdc )<br>
 * Doom 3 ( .md5* )<br>
 * *Valve Model ( .smd,.vta )<br>
 * *Open Game Engine Exchange ( .ogex )<br>
 * *Unreal ( .3d )<br>
 *<br>
 * OTHER FILE FORMATS<br>
 * BlitzBasic 3D ( .b3d )<br>
 * Quick3D ( .q3d,.q3s )<br>
 * Neutral File Format ( .nff )<br>
 * Sense8 WorldToolKit ( .nff )<br>
 * Object File Format ( .off )<br>
 * PovRAY Raw ( .raw )<br>
 * Terragen Terrain ( .ter )<br>
 * 3D GameStudio (3DGS) ( .mdl )<br>
 * 3D GameStudio (3DGS) Terrain ( .hmp )<br>
 * Izware Nendo ( .ndo )<br>
 * <br>
 * @author Darius
 */
public class ModelLoader {

    /**Loading a model from a file into asset database
     *
     * @param file Model file to load
     * @param texPath Location of the texture files
     * @param shape Collision shape, or ConvexHullShape/TriangleMeshShape to auto generate
     * @param asResource Load model from resources
     */
    public static void loadModelFile(String file, String texPath, boolean asResource, CollisionShape shape){
        if(AssetDatabase.models.containsKey(file)) return;
        loadModelFileMeta(file, texPath, asResource, shape);
    }

    /**Loading a model from a file into asset database and storing data into meta model
     *
     * @param file Model file to load
     * @param texPath Location of the texture files
     * @param asResource Load model from resources
     * @param shape Collision shape, or ConvexHullShape/TriangleMeshShape to auto generate
     * @return Meta model with raw model data
     */
    public static MetaModel loadModelFileMeta(String file, String texPath, boolean asResource, CollisionShape shape){

        //Ensure tex path is a directory
        if(!texPath.endsWith("/")) texPath += "/";

        //Load and parse model
        int flags = aiProcess_Triangulate | aiProcess_FixInfacingNormals | aiProcess_JoinIdenticalVertices |aiProcess_CalcTangentSpace;
        AIScene aiScene = aiImportFile(file, flags);
        if(aiScene == null){
            Logger.warn("Error by loading model", "The model file " + file + " could not be loaded! " +
                    "Returning null!");
            return null;
        }

        //Get data
        Pair<Material, MetaMaterial>[] materials = getMaterials(aiScene, texPath);
        Pair<Mesh, MetaMesh>[] meshs = getMeshs(aiScene, materials, shape);

        //Mesh data
        Mesh[] meshes = new Mesh[meshs.length];
        MetaMesh[] metaMeshes = new MetaMesh[meshs.length];
        for(int i = 0; i < meshs.length; i++){
            meshes[i] = meshs[i].getKey();
            metaMeshes[i] = meshs[i].getValue();
        }

        //Create and store models
        MetaModel metaModel = new MetaModel();
        metaModel.setMeshes(metaMeshes);
        Model model = new Model(meshes);
        AssetDatabase.models.put(file, model);

        return metaModel;
    }

    /**Loading meshes and meta meshes from an aiScene
     *
     * @param aiScene AIScene to load meshes from
     * @param materials Materials of the AIScene
     * @return Array of the scene meshes
     */
    private static Pair<Mesh, MetaMesh>[] getMeshs(AIScene aiScene, Pair<Material, MetaMaterial>[] materials,
                                                   CollisionShape shape){
        int meshCount = aiScene.mNumMeshes();

        //Create mesh data structures
        Pair<Mesh, MetaMesh>[] meshes = new Pair[meshCount];

        //Iterate through meshes and parse them
        for(int i = 0; i < meshCount; i++){
            AIMesh aiMesh = AIMesh.create(aiScene.mMeshes().get(i));
            MeshData meshData = new MeshData(aiMesh, materials);
            meshData.parse(shape);
            meshes[i] = new Pair<>(meshData.getMesh(), meshData.getMetaMesh());
        }

        return meshes;
    }

    /**Loading materials and meta materials from an aiScene
     *
     * @param aiScene AIScene to load materials from
     * @param texPath Location to load textures from
     * @return Array of the scene materials
     */
    private static Pair<Material, MetaMaterial>[] getMaterials(AIScene aiScene, String texPath){
        int matCount = aiScene.mNumMaterials();

        //Create material data structures
        Pair<Material, MetaMaterial>[] materials = new Pair[matCount];

        //Iterate through materials and parse them
        for(int i = 0; i < matCount; i++){
            AIMaterial aiMaterial = AIMaterial.create(aiScene.mMaterials().get(i));
            MaterialData materialData = new MaterialData(aiMaterial);
            materialData.parse(texPath);
            materials[i] = new Pair<>(materialData.getMaterial(), materialData.getMetaMaterial());
        }

        return materials;
    }
}