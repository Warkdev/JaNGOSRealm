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
 * Visual represents the visual that the Player can have (especially at logon screen).
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum Visual {

    /**
     * Default State.
     */
    NONE(0x00000000),
    
    /**
     * This player is a group leader.
     */
    GROUP_LEADER(0x00000001),
    
    /**
     * This player is AFK.
     */
    AFK(0x00000002),
    
    /**
     * This player shows "Do Not Disturb".
     */
    DND(0x00000004),
    
    /**
     * This player is a GM.
     */
    GM(0x00000008),
    
    /**
     * This player is in GHOST form.
     */
    GHOST(0x00000010),
    
    /**
     * This player is resting.
     */
    RESTING(0x00000020),
    
    /**
     * Unknown usage.
     */
    UNK7(0x00000040), // admin?
    
    /**
     * This player is in PvP.
     * TODO: Check usage.
     */
    FFA_PVP(0x00000080),
    
    /**
     * This player has been attacked by guards and is now in PvP.
     */
    CONTESTED_PVP(0x00000100), // Player has been involved in a PvP combat and will be attacked by contested guards
    
    /**
     * This player is in PvP (too).
     * TODO: Check usage.
     */
    IN_PVP(0x00000200),
    
    /**
     * This player is hiding helm.
     */
    HIDE_HELM(0x00000400),
    
    /**
     * This player is hiding cloak.
     */
    HIDE_CLOAK(0x00000800),
    
    /**
     * This player has played long time.
     * TODO: Check usage.
     */
    PARTIAL_PLAY_TIME(0x00001000),
    
    /**
     * This player has played long time
     * TODO: Check usage.
     */
    NO_PLAY_TIME(0x00002000),
    
    /**
     * Unknown usage.
     */
    UNK15(0x00004000),
    
    /**
     * Weird visual effect, looks like GHOST flag.
     */
    UNK16(0x00008000),
    
    /**
     * Player has entered sanctuary.
     * TODO: Check usage.
     */
    SANCTUARY(0x00010000),
    
    /**
     * Player taxi mode.
     * TODO: Check usage.
     */
    TAXI_BENCHMARK(0x00020000),
    
    /**
     * PvP Timer active for this player, after being disabled manually
     * TODO: Check usage.
     */
    PVP_TIMER(0x00040000);

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private Visual(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this State.
     *
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a State value.
     *
     * @param value
     * @return The State corresponding to that value, null if there is no match.
     */
    public static Visual convert(int value) {
        for (Visual v : Visual.values()) {
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
        for (Visual g : Visual.values()) {
            if (g.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
