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
 * QuestTradeSkillEnum represents the required skills to perform a quest.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestTradeSkillEnum {

    
    QUEST_TRSKILL_NONE((byte) 0),
   
    QUEST_TRSKILL_ALCHEMY((byte) 1),
        
    QUEST_TRSKILL_BLACKSMITHING((byte) 2),
        
    QUEST_TRSKILL_COOKING((byte) 3),
        
    QUEST_TRSKILL_ENCHANTING((byte) 4),
        
    QUEST_TRSKILL_ENGINEERING((byte) 5),
        
    QUEST_TRSKILL_FIRSTAID((byte) 6),
        
    QUEST_TRSKILL_HERBALISM((byte) 7),
    
    QUEST_TRSKILL_LEATHERWORKING((byte) 8),
    
    QUEST_TRSKILL_POISONS((byte) 9),
    
    QUEST_TRSKILL_TAILORING((byte) 10),
    
    QUEST_TRSKILL_MINING((byte) 11),
    
    QUEST_TRSKILL_FISHING((byte) 12),
    
    QUEST_TRSKILL_SKINNING((byte) 13);

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestTradeSkillEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestShareMessages.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestShareMessages value.
     * @param value
     * @return The QuestFailedReasons corresponding to that value, null if there is no match.
     */
    public static QuestTradeSkillEnum convert(byte value) {
        for (QuestTradeSkillEnum v : QuestTradeSkillEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
