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
package eu.jangos.realm.enums.items;

/**
 *
 * @author Warkdev
 */
public enum ItemStorageEnum {

    EQUIPPED((byte) 0),
    BAG1((byte) 19),
    BAG2((byte) 20),
    BAG3((byte) 21),
    BAG4((byte) 22),
    BANKBAG1((byte) 63),
    BANKBAG2((byte) 64),
    BANKBAG3((byte) 65),
    BANKBAG4((byte) 66),
    BANKBAG5((byte) 67),
    BANKBAG6((byte) 68),
    STORED((byte) 255);

    private final byte value;

    private ItemStorageEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    /**
     * Convert an byte value into a Gender value.
     *
     * @param value
     * @return The Gender corresponding to that value, null if there is no
     * match.
     */
    public static ItemStorageEnum convert(byte value) {
        for (ItemStorageEnum v : ItemStorageEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }

    /**
     * Method to check whether the given value exists within this enum.
     *
     * @param value The value to be checked.
     * @return True if the value exists.
     */
    public static boolean exists(byte value) {
        for (ItemStorageEnum g : ItemStorageEnum.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }

}
