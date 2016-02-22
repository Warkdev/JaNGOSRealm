package eu.jangos.realm.enums.characters;

/*
 * Copyright 2016 Talendrys.
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
 * Race represents the races that the characters can have.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum RaceEnum {

    HUMAN((byte) 1),
    ORC((byte) 2),
    DWARF((byte) 3),
    NIGHTELF((byte) 4),
    UNDEAD((byte) 5),
    TAUREN((byte) 6),
    GNOME((byte) 7),
    TROLL((byte) 8);

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private RaceEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this Races.
     *
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a Races value.
     *
     * @param value
     * @return The Races corresponding to that value, null if there is no
     * match.
     */
    public static RaceEnum convert(byte value) {
        for (RaceEnum v : RaceEnum.values()) {
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
        for (RaceEnum g : RaceEnum.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
