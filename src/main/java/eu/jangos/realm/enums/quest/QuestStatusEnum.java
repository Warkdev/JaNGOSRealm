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
 * QuestStatusEnum represents the possible status of a quest.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestStatusEnum {

    
    QUEST_STATUS_NONE((byte) 0),
    
    QUEST_STATUS_COMPLETE((byte) 1),
        
    QUEST_STATUS_UNAVAILABLE((byte) 2),
       
    QUEST_STATUS_INCOMPLETE((byte) 3),
      
    QUEST_STATUS_AVAILABLE((byte) 4),

    QUEST_STATUS_FAILED((byte) 5);  

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestStatusEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestStatusEnum.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestStatusEnum value.
     * @param value
     * @return The QuestFailedReasons corresponding to that value, null if there is no match.
     */
    public static QuestStatusEnum convert(byte value) {
        for (QuestStatusEnum v : QuestStatusEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
