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
 * QuestTypesEnum represents the possible types of quest.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestTypesEnum {

    
    QUEST_TYPE_ELITE((byte) 1),
    
    QUEST_TYPE_LIFE((byte) 21),
        
    QUEST_TYPE_PVP((byte) 42),
       
    QUEST_TYPE_RAID((byte) 62),
      
    QUEST_TYPE_DUNGEON((byte) 81),

    QUEST_TYPE_WORLD_EVENT((byte) 82),
    
    QUEST_TYPE_LEGENDARY((byte) 83),
    
    QUEST_TYPE_ESCORT((byte) 84);

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestTypesEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestTypesEnum.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestTypesEnum value.
     * @param value
     * @return The QuestTypesEnum corresponding to that value, null if there is no match.
     */
    public static QuestTypesEnum convert(byte value) {
        for (QuestTypesEnum v : QuestTypesEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
