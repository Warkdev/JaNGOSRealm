package eu.jangos.realm.enums.spell;

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
 * AttributesEx represents the various extra attribues of spell existing in the
 * game.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum AttributesEx {

    /**
     * Unknown
     */
    UNK0(0x00000001),
    
    /**
     * Use all power.
     */
    DRAIN_ALL_POWER(0x00000002),
    
    /**
     * Channeled (1) Spell
     */
    CHANNELED_1(0x00000004),
    
    /**
     * Unknown
     */
    UNK1(0x00000008),
    
    /**
     * Unknown
     */
    UNK2(0x00000010),
    
    /**
     * Doesn't break the stealth.
     */
    NOT_BREAK_STEALTH(0x00000020),
    
    /**
     * Channeled (2) Spell
     */
    CHANNELED_2(0x00000040),
    
    /**
     * Negative ??
     */
    NEGATIVE(0x00000080),
    
    /**
     * Requires that the target is not in combat.
     */
    NOT_IN_COMBAT_TARGET(0x00000100),
    
    /**
     * Unknown.
     */
    UNK3(0x00000200),
    
    /**
     * Doesn't generates threat.
     */
    NO_THREAT(0x00000400),
    
    /**
     * Unknown.
     */
    UNK4(0x00000800),
    
    /**
     * Unknown.
     */
    UNK5(0x00001000),
    
    /**
    * Far sight ?
    */
    FARSIGHT(0x00002000),
    
    /**
     * Unknown.
     */
    UNK6(0x00004000),
    
    /**
     * Removes aura on immunity.
     */
    DISPEL_AURAS_ON_IMMUNITY(0x00008000),
    
    /**
     * Unaffected by immune.
     */
    UNAFFECTED_BY_SCHOOL_IMMUNE(0x00010000),
    
    /**
     * Unknown.
     */
    UNK7(0x00020000),
    
    /**
     * Unknown.
     */
    UNK8(0x00040000),
    
    /**
     * Unknown.
     */
    UNK9(0x00080000),
    
    /**
     * Requires combo points on target.
     */
    REQ_TARGET_COMBO_POINTS(0x00100000),
    
    /**
     * Unknown.
     */
    UNK10(0x00200000),
    
    /**
     * Use combo points.
     */
    REQ_COMBO_POINTS(0x00400000),
    
    /**
     * Unknown.
     */
    UNK11(0x00800000),
    
    /**
     * Unknown.
     */
    UNK12(0x01000000),
    
    /**
     * Unknown.
     */
    UNK13(0x02000000),
    
    /**
     * Unknown.
     */
    UNK14(0x04000000),
    
    /**
     * Unknown.
     */
    UNK15(0x08000000),
    
    /**
     * Unknown.
     */
    UNK16(0x10000000),
    
    /**
     * Unknown.
     */
    UNK17(0x20000000),
    
    /**
     * Unknown.
     */
    UNK18(0x40000000),
    
    /**
     * Unknown.
     */
    UNK19(0x80000000);//31

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private AttributesEx(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this AttributesEx.
     *
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a AttributesEx value.
     *
     * @param value
     * @return The AttributesEx corresponding to that value, null if there is no
     * match.
     */
    public static AttributesEx convert(int value) {
        for (AttributesEx v : AttributesEx.values()) {
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
        for (AttributesEx g : AttributesEx.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
