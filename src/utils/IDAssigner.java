/*
 * Copyright 2016 Matthew Rogers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package utils;

/**
 * <strong>Project:</strong> Firestorm
 * <strong>File:</strong> IDAssigner.java
 * 
 * @author Matthew Rogers
 */
public class IDAssigner {
    
    private int baseID;
    
    public IDAssigner(int baseID){
        this.baseID = baseID;
    }
    
    public int next(){
        return baseID++;
    }
    
    public int getCurrentID(){
        return baseID;
    }

}
