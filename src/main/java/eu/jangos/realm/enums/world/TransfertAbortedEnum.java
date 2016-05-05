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
 * TransfertAbortedEnum is an enum representing all the reasons for which a transfer could have been aborted.
 * @author Warkdev
 * @version 0.1BETA
 */
public enum TransfertAbortedEnum {

    TRANSFER_ABORT_NONE((byte) 0),
    TRANSFER_ABORT_MAX_PLAYERS((byte) 1),     // Transfer Aborted: instance is full
    TRANSFER_ABORT_NOT_FOUND((byte) 2),     // Transfer Aborted: instance not found
    TRANSFER_ABORT_TOO_MANY_INSTANCES((byte) 3),     // You have entered too many instances recently.
    TRANSFER_ABORT_ZONE_IN_COMBAT((byte) 5);     // Unable to zone in while an encounter is in progress.
            
    private final byte value;
    
    /**
     * Default constructor.
     *
     * @param value
     */
    private TransfertAbortedEnum(byte value) {
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
    public static TransfertAbortedEnum convert(int value) {
        for (TransfertAbortedEnum v : TransfertAbortedEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
