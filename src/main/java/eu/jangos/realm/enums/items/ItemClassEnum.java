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
public enum ItemClassEnum {
 
    CONSUMABLE((byte) 0),
    CONTAINER((byte) 1),
    WEAPON((byte) 2),
    GEM((byte) 3),
    ARMOR((byte) 4),
    REAGENT((byte) 5),
    PROJECTILE((byte) 6),
    TRADEGOODS((byte) 7),
    GENERIC((byte) 8),
    BOOK((byte) 9),
    MONEY((byte) 10),
    QUIVER((byte) 11),
    QUEST((byte) 12),
    KEY((byte) 13),
    PERMANENT((byte) 14),
    JUNK((byte) 15);    

    private final byte value;

    private ItemClassEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    /**
     * Convert an int value into a Gender value.
     *
     * @param value
     * @return The Gender corresponding to that value, null if there is no
     * match.
     */
    public static ItemClassEnum convert(byte value) {
        for (ItemClassEnum v : ItemClassEnum.values()) {
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
        for (ItemClassEnum g : ItemClassEnum.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
    
}
