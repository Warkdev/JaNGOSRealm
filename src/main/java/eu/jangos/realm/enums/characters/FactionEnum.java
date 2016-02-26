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
package eu.jangos.realm.enums.characters;

import java.util.ArrayList;
import java.util.List;

/**
 * FactionEnum is the enumeration handling both factions.
 *
 * @author Warkdev
 * @version 1.0
 */
public enum FactionEnum {

    UNKNOWN((byte) 0),
    ALLY((byte) 1),
    HORDE((byte) 2);

    private final byte value;
    private static final List<RaceEnum> listAllyRaces = new ArrayList<>();
    private static final List<RaceEnum> listHordeRaces = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @param value
     */
    private FactionEnum(byte value) {
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
     * @return The Races corresponding to that value, null if there is no match.
     */
    public static FactionEnum convert(byte value) {
        for (FactionEnum v : FactionEnum.values()) {
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
        for (FactionEnum g : FactionEnum.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the corresponding faction for a given race.
     *
     * @param race The race for which the faction needs to be found.
     * @return The faction of the race.
     */
    public static FactionEnum getFactionForRace(RaceEnum race) {
        switch (race) {
            case DWARF:
            case GNOME:
            case HUMAN:
            case NIGHTELF:
                return ALLY;
            case ORC:
            case TAUREN:
            case TROLL:
            case UNDEAD:
                return HORDE;
        }
        return UNKNOWN;
    }

    /**
     * Returns the corresponding enemy faction for a given race.
     *
     * @param race The race for which the faction needs to be found.
     * @return The faction of the race.
     */
    public static FactionEnum getEnemyFactionForRace(RaceEnum race) {
        switch (race) {
            case DWARF:
            case GNOME:
            case HUMAN:
            case NIGHTELF:
                return HORDE;
            case ORC:
            case TAUREN:
            case TROLL:
            case UNDEAD:
                return ALLY;
        }
        return UNKNOWN;
    }

    /**
     * Returns the list of races for the given faction.
     *
     * @param faction The faction for which the race needs to be found.
     * @return
     */
    public static List<RaceEnum> getRacesForFactions(FactionEnum faction) {
        switch (faction) {
            case ALLY:
                if (listAllyRaces.isEmpty()) {
                    listAllyRaces.add(RaceEnum.DWARF);
                    listAllyRaces.add(RaceEnum.GNOME);
                    listAllyRaces.add(RaceEnum.HUMAN);
                    listAllyRaces.add(RaceEnum.NIGHTELF);
                }
                return listAllyRaces;
            case HORDE:
                if (listHordeRaces.isEmpty()) {
                    listHordeRaces.add(RaceEnum.ORC);
                    listHordeRaces.add(RaceEnum.TROLL);
                    listHordeRaces.add(RaceEnum.UNDEAD);
                    listHordeRaces.add(RaceEnum.TAUREN);
                }
                return listHordeRaces;
            default:
                return null;
        }
    }
}
