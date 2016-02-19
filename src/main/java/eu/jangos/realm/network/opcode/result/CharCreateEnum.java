package eu.jangos.realm.network.opcode.result;

/**
 * jE4W is a featured server emulator for World of Warcraft 1.12.x.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * World of Warcraft, and all World of Warcraft or Warcraft art, images, and
 * lore are copyrighted by Blizzard Entertainment, Inc.
 *
 * A lot of credits goes to MaNGOS project from which several ideas (but not
 * all) were included in this project.
 *
 * Copyright (C) 2015-2015 jE4W project Copyright (C) 2005-2014 MaNGOS project
 * <http://getmangos.eu>
 */

/**
 * CharCreateResultEnum is an enumeration of all possible command that the server may send back to the client for character creation.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public enum CharCreateEnum {    
    
    /**
     * Character creation is in progress.     
     */
    CHAR_CREATE_IN_PROGRESS((short) 0x2D),                
    
    /**
     * Character has been created succesfully.     
     */
    CHAR_CREATE_SUCCESS((short) 0x2E),                 
    
    /**
     * There was an error during the character creation.         
     */
    CHAR_CREATE_ERROR((short) 0x2F),                 
    
    /**
     * Server failed to create the character.    
     */    
    CHAR_CREATE_FAILED((short) 0x30),   
    
    /**
     * The name of the character is already in use.     
     */
    CHAR_CREATE_NAME_IN_USE((short) 0x31),
    
    /**
     * The character creation option is disabled.    
     */
    CHAR_CREATE_DISABLED((short) 0x3A),
    
    /**
     * The character creation doesn't fit the realm specification (no dual-faction on PvP realms).     
     */
    CHAR_CREATE_PVP_TEAMS_VIOLATION((short) 0x33),                 
    
    /**
     * The server can't handle more characters.   
     */
    CHAR_CREATE_SERVER_LIMIT((short) 0x34),                 
    
    /**
     * The account can't have more characters for this realm.     
     */
    CHAR_CREATE_ACCOUNT_LIMIT((short) 0x35),
    
    /**
     * The character name is empty.     
     */
    CHAR_NAME_NO_NAME((short) 0x43),
    
    /**
     * The character name is too short.     
     */
    CHAR_NAME_TOO_SHORT((short) 0x44),
    
    /**
     * The character name is too long.     
     */
    CHAR_NAME_TOO_LONG((short) 0x45),
    
    /**
     * The character name has invalid characters.    
     */
    CHAR_NAME_INVALID_CHARACTERS((short) 0x46),
    
    /**
     * The character name has mixed languages.    
     */
    CHAR_NAME_MIXED_LANGUAGES((short) 0x47),
    
    /**
     * The character name is profane.    
     */
    CHAR_NAME_PROFANE((short) 0x48),
    
    /**
     * The character name is reserved.    
     */
    CHAR_NAME_RESERVED((short) 0x49),
    
    /**
     * The character name has invalid apostrophe.    
     */
    CHAR_NAME_INVALID_APOSTROPHE((short) 0x4A),
    
    /**
     * The character name has multiple apostrophes.    
     */
    CHAR_NAME_MULTIPLE_APOSTROPHES((short) 0x4B),
    
    /**
     * The character name has three consecutive apostrophes.
     */
    CHAR_NAME_THREE_CONSECUTIVE((short) 0x4C),
    
    /**
     * The character name has invalid space.    
     */
    CHAR_NAME_INVALID_SPACE((short) 0x4D),
    
    /**
     * The character name has consecutive spaces.    
     */
    CHAR_NAME_CONSECUTIVE_SPACES((short) 0x4E),    
    
    /**
     * The character name doesn't respect the rules.
     */
    CHAR_NAME_FAILURE((short) 0x4F),        
    
    /**
     * The character name is ok regarding the rules.     
     */
    CHAR_NAME_SUCCESS((short) 0x50);        
    
    private final short result;

    private CharCreateEnum(short result)
    {
        this.result = result;
    }
    
    public short getValue(){
        return this.result;
    }
    
    public static CharCreateEnum convert(short value) {
        for(CharCreateEnum cmd : CharCreateEnum.values()){
            if(cmd.getValue() == value)
                return cmd;
        }            
        return null;
    } 
}
