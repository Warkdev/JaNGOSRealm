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
public enum InventoryTypeEnum {

    NON_EQUIPPED(0),
    HEAD(1),
    NECK(2),
    SHOULDERS(3),
    BODY(4),
    CHEST(5),
    WAIST(6),
    LEGS(7),
    FEET(8),
    WRISTS(9),
    HANDS(10),
    FINGER(11),
    TRINKET(12),
    WEAPON(13),
    SHIELD(14),
    RANGED(15),
    CLOAK(16),
    TWOHANDS(17),
    BAG(18),
    TABARD(19),
    ROBE(20),
    MAINHAND(21),
    OFFHAND(22),
    HOLDABLE(23),
    AMMO(24),
    THROWN(25),
    RANGEDRIGHT(26),
    QUIVER(27);

    private final int value;

    private InventoryTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Convert an int value into a Gender value.
     *
     * @param value
     * @return The Gender corresponding to that value, null if there is no
     * match.
     */
    public static InventoryTypeEnum convert(int value) {
        for (InventoryTypeEnum v : InventoryTypeEnum.values()) {
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
    public static boolean exists(int value) {
        for (InventoryTypeEnum g : InventoryTypeEnum.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }

}
