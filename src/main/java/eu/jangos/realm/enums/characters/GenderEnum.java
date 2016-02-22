package eu.jangos.realm.enums.characters;

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

/**
 * Gender represents the gender that the characters can have.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum GenderEnum {

    MALE((byte) 0),
    FEMALE((byte) 1),
    NONE((byte) 2);

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private GenderEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this Gender.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a Gender value.
     * @param value
     * @return The Gender corresponding to that value, null if there is no match.
     */
    public static GenderEnum convert(byte value) {
        for (GenderEnum v : GenderEnum.values()) {
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
    public static boolean exists(byte value){
        for(GenderEnum g : GenderEnum.values())
        {
            if(g.getValue() == value)
                return true;
        }
        return false;
    }
}
