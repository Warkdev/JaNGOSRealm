package eu.jangos.realm.enums.quest;

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
 * QuestGiverStatusEnum represents the possible status of a quest giver.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestGiverStatusEnum {

    
    DIALOG_STATUS_NONE((byte) 0),
    
    DIALOG_STATUS_UNAVAILABLE((byte) 1),
        
    DIALOG_STATUS_CHAT((byte) 2),
       
    DIALOG_STATUS_INCOMPLETE((byte) 3),
      
    DIALOG_STATUS_REWARD_REP((byte) 4),

    DIALOG_STATUS_AVAILABLE((byte) 5),
    
    DIALOG_STATUS_REWARD_OLD((byte) 6),  
    
    DIALOG_STATUS_REWARD((byte) 7);

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestGiverStatusEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestGiverStatusEnum.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestGiverStatusEnum value.
     * @param value
     * @return The QuestFailedReasons corresponding to that value, null if there is no match.
     */
    public static QuestGiverStatusEnum convert(byte value) {
        for (QuestGiverStatusEnum v : QuestGiverStatusEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
