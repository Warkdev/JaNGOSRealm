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
package eu.jangos.realm.enums.world;

/**
 * LogoutRequestEnum is an enum representing all the reasons the server sends back to the client while he's trying to logout.
 * @author Warkdev
 * @version 0.1BETA
 */
public enum LogoutRequestEnum {

    LOGOUT_OK((byte) 0), // Logout is accepted by server
    LOGOUT_ERROR_COMBAT((byte) 1),     // Logout can't be performed while being in combat.
    LOGOUT_ERROR_DUEL((byte) 2),     // Logout can't be performed while being in duel or frozen - not the correct value ?
    LOGOUT_ERROR_MOVING((byte) 3);     // Logout can't be performed while the player is moving (jump/fall).    
            
    private final byte value;
    
    /**
     * Default constructor.
     *
     * @param value
     */
    private LogoutRequestEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the byte value.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an byte in a 
     * @param value
     * @return The PartyOperation corresponding to that value, null if there is no match.
     */
    public static LogoutRequestEnum convert(int value) {
        for (LogoutRequestEnum v : LogoutRequestEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
