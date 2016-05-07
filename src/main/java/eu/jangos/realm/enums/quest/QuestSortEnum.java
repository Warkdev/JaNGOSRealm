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
 * QuestSortEnum represents the possible sorts of quest.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum QuestSortEnum {

    QUEST_SORT_EPIC((short) 1),
    QUEST_SORT_WAILING_CAVERNS_OLD((short) 21),
    QUEST_SORT_SEASONAL((short) 22),
    QUEST_SORT_UNDERCITY_OLD((short) 23),
    QUEST_SORT_HERBALISM((short) 24),
    QUEST_SORT_SCARLET_MONASTERY_OLD((short) 25),
    QUEST_SORT_ULDAMAN_OLD((short) 41),
    QUEST_SORT_WARLOCK((short) 61),
    QUEST_SORT_WARRIOR((short) 81),
    QUEST_SORT_SHAMAN((short) 82),
    QUEST_SORT_FISHING((short) 101),
    QUEST_SORT_BLACKSMITHING((short) 121),
    QUEST_SORT_PALADIN((short) 141),
    QUEST_SORT_MAGE((short) 161),
    QUEST_SORT_ROGUE((short) 162),
    QUEST_SORT_ALCHEMY((short) 181),
    QUEST_SORT_LEATHERWORKING((short) 182),
    QUEST_SORT_ENGINEERING((short) 201),
    QUEST_SORT_TREASURE_MAP((short) 221),
    QUEST_SORT_SUNKEN_TEMPLE_OLD((short) 241),
    QUEST_SORT_HUNTER((short) 261),
    QUEST_SORT_PRIEST((short) 262),
    QUEST_SORT_DRUID((short) 263),
    QUEST_SORT_TAILORING((short) 264),
    QUEST_SORT_SPECIAL((short) 284),
    QUEST_SORT_COOKING((short) 304),
    QUEST_SORT_FIRST_AID((short) 324),
    QUEST_SORT_LEGENDARY((short) 344),
    QUEST_SORT_DARKMOON_FAIRE((short) 364),
    QUEST_SORT_AHN_QIRAJ_WAR((short) 365),
    QUEST_SORT_LUNAR_FESTIVAL((short) 366),
    QUEST_SORT_REPUTATION((short) 367),
    QUEST_SORT_INVASION((short) 368),
    QUEST_SORT_MIDSUMMER((short) 369),
    QUEST_SORT_BREWFEST((short) 370);
    
    private final short value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private QuestSortEnum(short value) {
        this.value = value;
    }

    /**
     * Return the int value of this QuestTypesEnum.
     * @return the backing int value.
     */
    public short getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a QuestSortEnum value.
     * @param value
     * @return The QuestSortEnum corresponding to that value, null if there is no match.
     */
    public static QuestSortEnum convert(short value) {
        for (QuestSortEnum v : QuestSortEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
