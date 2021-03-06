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
package eu.jangos.realm.utils;

/**
 *
 * @author Warkdev
 * @version v1.0
 * @since 12-02-2016
 */
public class WorldParameterConstants {
    /**
     * Parameter indicating the World Name in Database.
     */
    public static final String KEY_WORLD_NAME = "worldName";
    
    /**
     * Parameter indicating the timeout of the connection for a WorldSession.
     */
    public static final String KEY_WORLD_TIMEOUT = "worldTimeout";    
    
    /**
     * Parameter indicating the World version.
     */
    public static final String KEY_WORLD_VERSION = "worldVersion";
    
    /**
     * Parameter indicating the maximum amount of active characters that an account can have.
     */
    public static final String KEY_WORLD_MAX_CHARACTERS = "worldMaxCharacters";
    
    /**
     * Parameter indicating the starting level of the characters on this realm.
     */
    public static final String KEY_WORLD_START_LEVEL = "worldStartLevel";
    
    /**
     * Parameter indicating the maximum level of the characters on this realm.
     */
    public static final String KEY_WORLD_MAX_LEVEL = "worldMaxLevel";
    
    /**
     * Parameter indicating the minimum length of a character name. (Default client = 3).
     */
    public static final String KEY_WORLD_MIN_LENGTH_NAME = "worldMinLengthName";
    
    /**
     * Parameter indicating the maximum length of a character name. (Default client = 12).
     */
    public static final String KEY_WORLD_MAX_LENGTH_NAME = "worldMaxLengthName";
            
    public static final String COMMAND_SEPARATOR = ":";
    
    /**
     * QUEST CONSTANTS
     */
    
    /**
     * Maximum number of quests in the log.
     */
    public static final int QUEST_MAX_QUEST_LOG_SIZE = 20;
    
    /**
     * Maximum number of objectives to complete a quest.
     */
    public static final int QUEST_OBJECTIVES_COUNT = 4;
    
    /**
     * Maximum number of items to achieve an objective for a quest.
     */
    public static final int QUEST_ITEM_OBJECTIVES_COUNT = 4;
    
    public static final int QUEST_SOURCE_ITEM_IDS_COUNT = 4;
    
    public static final int QUEST_REWARD_CHOICES_COUNT = 6;
    
    public static final int QUEST_REWARDS_COUNT = 4;
    
    public static final int QUEST_DEPLINK_COUNT = 10;
    
    public static final int QUEST_REPUTATIONS_COUNT = 5;
    
    public static final int QUEST_EMOTE_COUNT = 4;            
}
