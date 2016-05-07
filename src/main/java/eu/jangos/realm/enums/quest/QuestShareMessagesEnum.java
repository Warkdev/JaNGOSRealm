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
 * QuestShareMessagesEnum represents the possible message returned to the client while sharing a quest.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestShareMessagesEnum {

    /**
     * Sharing quest...
     */
    QUEST_PARTY_MSG_SHARING_QUEST((byte) 0),
    
    /**
     * Invalid sharing.
     */
    QUEST_PARTY_MSG_CANT_TAKE_QUEST((byte) 1),
    
    /**
     * Member has accepted the quest.
     */
    QUEST_PARTY_MSG_ACCEPT_QUEST((byte) 2),
    
    /**
     * Member has declined the quest.
     */
    QUEST_PARTY_MSG_DECLINE_QUEST((byte) 3),
    
    /**
     * Member is too far to accept the quest.
     */
    QUEST_PARTY_MSG_TOO_FAR((byte) 4),
    
    /**
     * Member is busy, can't receive the share.
     */
    QUEST_PARTY_MSG_BUSY((byte) 5),
    
    /**
     * Member's quest log is full.
     */
    QUEST_PARTY_MSG_LOG_FULL((byte) 6),
    
    /**
     * Member has already the quest.
     */
    QUEST_PARTY_MSG_HAVE_QUEST((byte) 7),
    
    /**
     * Member has accomplished the quest.
     */
    QUEST_PARTY_MSG_FINISH_QUEST((byte) 8);      

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestShareMessagesEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestShareMessagesEnum.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestShareMessagesEnum value.
     * @param value
     * @return The QuestFailedReasons corresponding to that value, null if there is no match.
     */
    public static QuestShareMessagesEnum convert(byte value) {
        for (QuestShareMessagesEnum v : QuestShareMessagesEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
