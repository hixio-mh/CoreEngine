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

import de.coreengine.asset.meta.MetaModel;

/**Class for loading model files<br>
 * <br>
 * Supported Formats:<br>
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

    /**Loading
     *
     * @param file
     * @param asResource
     * @return
     */
    public static MetaModel loadModelFile(String file, boolean asResource){
        return null;
    }
}
