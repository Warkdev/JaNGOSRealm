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
 *
 * @author Warkdev
 */
public enum RealmTypeEnum {
        
    PVE(0),
    PVP(1),
    RP_PVE(6),
    RP_PVP(8);
    
    private final int value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private RealmTypeEnum(int value) {
        this.value = value;
    }

    /**
     * Return the int value of this PartyOperation.
     * @return the backing int value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a PartyOperation value.
     * @param value
     * @return The PartyOperation corresponding to that value, null if there is no match.
     */
    public static RealmTypeEnum convert(int value) {
        for (RealmTypeEnum v : RealmTypeEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
    
}
