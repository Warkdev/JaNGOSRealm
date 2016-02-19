package eu.jangos.realm.enums.characters;

/**
 * jE4W is a featured server emulator for World of Warcraft 1.12.x.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * World of Warcraft, and all World of Warcraft or Warcraft art, images, and
 * lore are copyrighted by Blizzard Entertainment, Inc.
 *
 * A lot of credits goes to MaNGOS project from which several ideas (but not
 * all) were included in this project.
 *
 * Copyright (C) 2015-2015 jE4W project Copyright (C) 2005-2014 MaNGOS project
 * <http://getmangos.eu>
 */
/**
 * Gender represents the gender that the characters can have.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum GenderEnum {

    MALE(0),
    FEMALE(1),
    NONE(2);

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private GenderEnum(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this Gender.
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a Gender value.
     * @param value
     * @return The Gender corresponding to that value, null if there is no match.
     */
    public static GenderEnum convert(int value) {
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
    public static boolean exists(int value){
        for(GenderEnum g : GenderEnum.values())
        {
            if(g.getValue() == value)
                return true;
        }
        return false;
    }
}
