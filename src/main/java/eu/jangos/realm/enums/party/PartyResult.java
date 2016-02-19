package eu.jangos.realm.enums.party;

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
 * PartyResult represents the results actions within a group.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum PartyResult {

    /**
     * The result for the given command is OK.
     */
    PARTY_RESULT_OK(0),
    
    /**
     * The name of the player is incorrect, only used server side.
     */
    ERR_BAD_PLAYER_NAME_S(1),
    
    /**
     * The target is not in the group, only used server side.
     */
    ERR_TARGET_NOT_IN_GROUP_S(2),
    
    /**
     * The target is not in the instance, only used server side.
     */
    ERR_TARGET_NOT_IN_INSTANCE_S(3),
    
    /**
     * The group is full.
     */
    ERR_GROUP_FULL(4),
    
    /**
     * Target is already in a group, only used server side.
     */
    ERR_ALREADY_IN_GROUP_S(5),
    
    /**
     * You are not in a group.
     */
    ERR_NOT_IN_GROUP(6),
    
    /**
     * You are not the leader of the group.
     */
    ERR_NOT_LEADER(7),
    
    /**
     * The target is is the other faction.
     */
    ERR_PLAYER_WRONG_FACTION(8),
    
    /**
     * The target is ignoring you, only used server side.
     */
    ERR_IGNORING_YOU_S(9),
    
    /**
     * Unknown.
     */
    ERR_INVITE_RESTRICTED(13);        

    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private PartyResult(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this PartyResult.
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a PartyResult value.
     * @param value
     * @return The PartyResult corresponding to that value, null if there is no match.
     */
    public static PartyResult convert(int value) {
        for (PartyResult v : PartyResult.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
