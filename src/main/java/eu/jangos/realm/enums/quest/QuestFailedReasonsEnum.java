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
 * QuestFailedReasonsEnum represents the possible failed reasons for which a quest could fail.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestFailedReasonsEnum {

    /**
     * The requirements are missing.
     */
    INVALIDREASON_DONT_HAVE_REQ((byte) 0),
    
    /**
     * The player must have a higher level to take this quest.
     */
    INVALIDREASON_QUEST_FAILED_LOW_LEVEL((byte) 1),
    
    /**
     * The quest is not available for the player's race.
     */
    INVALIDREASON_QUEST_FAILED_WRONG_RACE((byte) 6),
    
    /**
     * The quest has already been completed.
     */
    INVALIDREASON_QUEST_ALREADY_DONE((byte) 7),
    
    /**
     * The player can only do one timed quest at a time.
     */
    INVALIDREASON_QUEST_ONLY_ONE_TIMED((byte) 12),
    
    /**
     * The player is already executing that quest.
     */
    INVALIDREASON_QUEST_ALREADY_ON((byte) 13),
    
    /**
     * The player is already executing that quest.
     */
    INVALIDREASON_QUEST_ALREADY_ON2((byte) 18),
    
    /**
     * The player does not have the required items with him.
     */
    INVALIDREASON_QUEST_FAILED_MISSING_ITEMS((byte) 21),
    
    /**
     * The player does not have enough money to end this quest.
     */
    INVALIDREASON_QUEST_FAILED_NOT_ENOUGH_MONEY((byte) 23);      

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestFailedReasonsEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestFailedReasonsEnum.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestFailedReasonsEnum value.
     * @param value
     * @return The QuestFailedReasonsEnum corresponding to that value, null if there is no match.
     */
    public static QuestFailedReasonsEnum convert(byte value) {
        for (QuestFailedReasonsEnum v : QuestFailedReasonsEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
