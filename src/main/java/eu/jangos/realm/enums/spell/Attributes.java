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
 * Attributes represents the various attribues of spell existing in the game..
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum Attributes {

    /**
     * Unknown.
     */
    UNKNOWN0(0x00000001),
    
    /**
     * Ranged ability.
     */
    RANGED(0x00000002),
    
    /**
     * On next swing.
     */
    ON_NEXT_SWING_1(0x00000004),
    
    /**
     * Unknown.
     */
    UNKNOWN1(0x00000008),
    
    /**
     * Unknown
     */
    UNKNOWN2(0x00000010),
    
    /**
     * Trade spell
     */
    TRADE(0x00000020),
    
    /**
     * Passive.
     */
    PASSIVE(0x00000040),
    
    /**
     * Shows no spell icon.
     */
    HIDE(0x00000080),
    
    /**
     * Unknown
     */
    UNKNOWN3(0x00000100),
    
    /**
     * Unknown
     */
    UNKNOWN4(0x00000200),
    
    /**
     * On next swing
     */
    ON_NEXT_SWING_2(0x00000400),
    
    /**
     * Unknown
     */
    UNK5(0x00000800),
    
    /**
     * Only during the day.
     */
    DAYTIME_ONLY(0x00001000),
    
    /**
     * Only during the night.
     */
    NIGHT_ONLY(0x00002000),
    
    /**
     * Only indoor.
     */
    INDOORS_ONLY(0x00004000),
    
    /**
     * Only outdoor.
     */
    OUTDOORS_ONLY(0x00008000),
    
    /**
     * Not when shapeshifted
     */
    NOT_SHAPESHIFT(0x00010000),
    
    /**
     * Only when stealth.
     */
    ONLY_STEALTHED(0x00020000),
    
    /**
     * Unknown.
     */
    UNK6(0x00040000),
    
    /**
     * Spell damage depends on caster level.
     */
    LEVEL_DAMAGE_CALCULATION(0x00080000),
    
    /**
     * Stop attacking target after using this spell.
     */
    STOP_ATTACK_TARGET(0x00100000),
    
    /**
     * Can't be dodged/parried/blocked.
     */
    IMPOSSIBLE_DODGE_PARRY_BLOCK(0x00200000),
    
    /**
     * Set tracking target.
     */
    SET_TRACKING_TARGET(0x00400000),
    
    /**
     * Unknown.
     */
    UNK7(0x00800000),
    
    /**
     * Castable when mounted.
     */
    CASTABLE_WHILE_MOUNTED(0x01000000),
    
    /**
     * Activate and start cooldown after aura fade or remove summoned creature or go.
     */
    DISABLED_WHILE_ACTIVE(0x02000000),
    
    /**
     * Unknown.
     */
    UNK8(0x04000000),
    
    /**
     * Castable while sitting.
     */
    CASTABLE_WHILE_SITTING(0x08000000),
    
    /**
     * Can't be used in combat.
     */
    CANT_USED_IN_COMBAT(0x10000000),
    
    /**
     * Unaffected by invulnerability (maybe not).
     */
    UNAFFECTED_BY_INVULNERABILITY(0x20000000),
    
    /**
     * Unknown (breakable by damage ?)
     */
    UNK30(0x40000000),
    
    /**
     * Can't be cancelled (aura).
     */
    CANT_CANCEL(0x80000000);        

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private Attributes(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this School.
     *
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a School value.
     *
     * @param value
     * @return The School corresponding to that value, null if there is no
     * match.
     */
    public static Attributes convert(int value) {
        for (Attributes v : Attributes.values()) {
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
        for (Attributes g : Attributes.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
