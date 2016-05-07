package eu.jangos.realm.enums.party;

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
