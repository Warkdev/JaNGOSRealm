package eu.jangos.realm.enums.items;

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
 * Slots represents the type of slots existing in the game.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum SlotEnum {

    /**
     * A forgiven slot.
     */
    NONE(-1),
    /**
     * Head slot.
     */
    HEAD(0),
    
    /**
     * Neck slot.
     */
    NECK(1),
    
    /**
     * Shoulders slot.
     */
    SHOULDERS(2),
    
    /**
     * Shirt slot.
     */
    SHIRT(3),
    
    /**
     * Chest slot.
     */
    CHEST(4),
    
    /**
     * Waist slot.
     */
    WAIST(5),
    
    /**
     * Legs slot.
     */
    LEGS(6),
    
    /**
     * Feet slot.
     */
    FEET(7),
    
    /**
     * Wrists slot.
     */
    WRISTS(8),
    
    /**
     * Hands slot.
     */
    HANDS(9),
    
    /**
     * Finger slot.
     */
    FINGER1(10),
    
    /**
     * Finder slot.
     */
    FINGER2(11),
    
    /**
     * Trinket slot.
     */
    TRINKET1(12),
    
    /**
     * Trinket slot.
     */
    TRINKET2(13),
    
    /**
     * Back slot.
     */
    BACK(14),
    
    /**
     * Main hand slot.
     */
    MAIN_HAND(15),
    
    /**
     * Off hand slot.
     */
    OFF_HAND(16),
    
    /**
     * Ranged slot.
     */
    RANGED(17),
    
    /**
     * Tabard slot.
     */
    TABARD(18),
    
    /**
     * Bag1 slot.
     */
    BAG1(19);

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private SlotEnum(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this SlotEnum.
     *
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a SlotEnum value.
     *
     * @param value
     * @return The SlotEnum corresponding to that value, null if there is no
 match.
     */
    public static SlotEnum convert(int value) {
        for (SlotEnum v : SlotEnum.values()) {
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
        for (SlotEnum g : SlotEnum.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
