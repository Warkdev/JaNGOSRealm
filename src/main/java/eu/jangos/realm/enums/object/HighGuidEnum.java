/*
 * Copyright 2016 Warkdev.
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
package eu.jangos.realm.enums.object;

/**
 * HighGuidEnum is the enum for the HighGuid values
 * @author Warkdev
 */
public enum HighGuidEnum {
           
    PLAYER(0),
    NOTRANSPORT(8128),
    ITEM(16384),
    CONTAINER(16384),
    GAMEOBJECT(61712),
    TRANSPORT(61728),
    UNIT(61744),
    PET(61760),
    DYNAMICOBJECT(61696),
    CORPSE(61697);
    
    private final int value;

    private HighGuidEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
        /**
     * Convert an int value into a Gender value.
     * @param value
     * @return The Gender corresponding to that value, null if there is no match.
     */
    public static HighGuidEnum convert(int value) {
        for (HighGuidEnum v : HighGuidEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * Method to check whether the given value exists within this enum.
     * @param value The value to be checked.
     * @return True if the value exists.
     */
    public static boolean exists(int value){
        for(HighGuidEnum g : HighGuidEnum.values())
        {
            if(g.getValue() == value)
                return true;
        }
        return false;
    }
               
}
