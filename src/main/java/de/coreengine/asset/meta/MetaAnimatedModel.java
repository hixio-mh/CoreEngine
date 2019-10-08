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

package de.coreengine.asset.meta;

import de.coreengine.animation.Animation;
import de.coreengine.animation.Joint;
import de.coreengine.rendering.model.AnimatedModel;
import de.coreengine.rendering.model.Mesh;
import de.coreengine.util.Logger;

import java.util.HashMap;

public class MetaAnimatedModel extends MetaModel{

    //Data
    public Joint skeleton;
    public HashMap<String, Animation> animations;

    /**Creates new model instance of the meta model
     *
     * @param texPath Path to get models textures from
     * @param asResource Load model textures from resources
     * @return Create model instance
     */
    @Override
    public AnimatedModel getInstance(String texPath, boolean asResource){
        if(this.meshes == null){
            Logger.warn("Error by creating animated model instance",
                    "The meshes array of the meta animated model is null! Returning null");
            return null;
        }

        Mesh[] meshes = new Mesh[this.meshes.length];

        //Create all mesh instances
        for(int i = 0; i < this.meshes.length; i++){
            meshes[i] = this.meshes[i].getInstance(texPath, asResource, true);
        }

        return new AnimatedModel(meshes, skeleton, animations);
    }
}