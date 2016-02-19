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
 * AttributesEx3 represents the various extra attribues of spell existing in the
 * game.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum AttributesEx3 {

    /**
     * Unknown.
     */
    UNK0(0x00000001),
    
    /**
     * Unknown.
     */
    UNK1(0x00000002),
    
    /**
     * Unknown.
     */
    UNK2(0x00000004),
    
    /**
     * Unknown.
     */
    UNK3(0x00000008),
    
    /**
     * Unknown.
     */
    UNK4(0x00000010),
    
    /**
     * Unknown.
     */
    UNK5(0x00000020),
    
    /**
     * Unknown.
     */
    UNK6(0x00000040),
    
    /**
     * Unknown.
     */
    UNK7(0x00000080),
    
    /**
     * Can only target player.
     */
    TARGET_ONLY_PLAYER(0x00000100),
    
    /**
     * Unknown.
     */
    UNK8(0x00000200),
    
    /**
     * Main hand weapon required.
     */
    MAIN_HAND(0x00000400),
    
    /**
     * Can only be casted in battleground.
     */
    BATTLEGROUND(0x00000800),
    
    /**
     * Target is a dead player.
     */
    CAST_ON_DEAD(0x00001000),
    
    /**
     * Unknown.
     */
    UNK9(0x00002000),
    
    /**
     * Unknown. ("Honorless target" only this spells have this flag).
     */
    UNK10(0x00004000),
    
    /**
     * Autoshoot flag.
     */
    AUTO_SHOOT(0x00008000),
    
    /**
     * Unknown. (no triggers effects that trigger on casting a spell.
     */
    UNK12(0x00010000),
    
    /**
     * Causes no aggro if not missed.
     */
    NO_INITIAL_AGGRO(0x00020000),
    
    /**
     * Spell should always hit its target.
     */
    CANT_MISS(0x00040000),
    
    /**
     * Unknown.
     */
    UNK13(0x00080000),
    
    /**
     * Spell persistent to death.
     */
    DEATH_PERSISTENT(0x00100000),
    
    /**
     * Unknown. (Only "Emprise de la nature".
     */
    UNK14(0x00200000),
    
    /**
     * Requires wand.
     */
    REQ_WAND(0x00400000),
    
    /**
     * Unknown.
     */
    UNK15(0x00800000),
    
    /**
     * Spell that requires an off-hand weapon.
     */
    REQ_OFFHAND(0x01000000),    
    
    /**
     * Only zzzOLD spells, deprecated.
     */
    @Deprecated
    UNK16(0x02000000),
    
    /**
     * Unknown.
     */
    UNK17(0x04000000),
    
    /**
     * Unknown. ("Mind View" and "fishing" has this flag.
     */
    UNK18(0x08000000),
    
    /**
     * Unknown.
     */
    UNK19(0x10000000),
    
    /**
     * Unknown.
     */
    UNK20(0x20000000),
    
    /**
     * Unknown. (Only "debit" from the rogue)
     */
    UNK21(0x40000000),
    
    /**
     * Unknown.
     */
    UNK22(0x80000000);

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private AttributesEx3(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this AttributesEx3.
     *
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a AttributesEx3 value.
     *
     * @param value
     * @return The AttributesEx3 corresponding to that value, null if there is no
     * match.
     */
    public static AttributesEx3 convert(int value) {
        for (AttributesEx3 v : AttributesEx3.values()) {
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
        for (AttributesEx3 g : AttributesEx3.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
