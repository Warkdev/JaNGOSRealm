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

import eu.jangos.realm.enums.items.InventoryTypeEnum;
import eu.jangos.realm.enums.items.SlotEnum;

/**
 * This class handles the utils function for the Items.
 * @author Warkdev
 */
public class ItemUtils {
    
    public static SlotEnum getSlotForInventoryType(InventoryTypeEnum invType)
    {
        switch(invType)
        {
            case HEAD:
                return SlotEnum.HEAD;
            case NECK:
                return SlotEnum.NECK;
            case SHOULDERS:
                return SlotEnum.SHOULDERS;
            case BODY:            
                return SlotEnum.SHIRT;
            case CHEST:                
            case ROBE:
                return SlotEnum.CHEST;
            case TABARD:
                return SlotEnum.TABARD;
            case WAIST:
                return SlotEnum.WAIST;
            case LEGS:
                return SlotEnum.LEGS;
            case FEET:
                return SlotEnum.FEET;
            case WRISTS:
                return SlotEnum.WRISTS;
            case HANDS:
                return SlotEnum.HANDS;
            case FINGER:
                return SlotEnum.FINGER1;
            case TRINKET:
                return SlotEnum.TRINKET1;
            case CLOAK:
                return SlotEnum.BACK;
            case WEAPON:
            case MAINHAND:
            case TWOHANDS:
                return SlotEnum.MAIN_HAND;
            case OFFHAND:
            case SHIELD:
            case HOLDABLE:
                return SlotEnum.OFF_HAND;
            case RANGED:
            case RANGEDRIGHT:
            case THROWN:
                return SlotEnum.RANGED;            
            default:
                return SlotEnum.NONE;
        }
    }
}
