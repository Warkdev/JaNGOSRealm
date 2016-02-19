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
 * AuthResultEnum is an enumeration of all possible result that the server may send back to the client for the authentication process.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public enum AuthEnum {    
    
    /**
     * Account is banned.
     * "This game account has been closed and is no longer available for use".
     */
    AUTH_FAIL_BANNED((short) 3),                
    
    /**
     * Account is unknown.
     * "The information you have entered is not valid. Please check the spelling".
     */
    AUTH_FAIL_UNKNOWN_ACCOUNT((short) 4),                 
    
    /**
     * Password is incorrect.
     * "The information you have entered is not valid. Please check the spelling".
     * The client reject next login attempts after this error, so in code used WOW_FAIL_UNKNOWN_ACCOUNT for both cases.
     */
    AUTH_FAIL_INCORRECT_PASSWORD((short) 5),                 
    
    /**
     * Account is already logged in.
     * This account is already logged into game. Please check the spelling and try again.
     */    
    AUTH_FAIL_ALREADY_ONLINE((short) 6),   
    
    /**
     * Account prepaid time is over.
     * You have used up your prepaid time for this account. Please purchase more to continue playing.
     */
    AUTH_FAIL_NO_TIME((short) 7),
    
    /**
     * Database is in maintenance.
     * Could not log in to game at this time. Please try again later.
     */
    AUTH_FAIL_DB_BUSY((short) 8),
    
    /**
     * Version is invalid.
     * Unable to validate game version. This may be caused by file corruption or interference of another program. 
     * Please visit site for more information and possible solutions to this issue.
     */
    AUTH_FAIL_VERSION_INVALID((short) 9),                 
    
    /**
     * Version needs to be updated. * Not supported by jE4W *
     * Downloading.
     */
    AUTH_FAIL_VERSION_UPDATE((short) 10),                 
    
    /**
     * Server is invalid.
     * Unable to connect.
     */
    AUTH_FAIL_INVALID_SERVER((short) 11),
    
    /**
     * Account is authenticated succesfully.     
     */
    AUTH_SUCCESS((short) 12),
    
    /**
     * No access for this account.
     * Unable to connect.
     */
    AUTH_FAIL_FAIL_NOACCESS((short) 13),
    
    /**
     * Connected (used in Vanilla ? Not sure..)
     * Connected.
     */
    AUTH_SUCCESS_SURVEY((short) 14),
    
    /**
     * Parent control has restricted access on this account.
     * Access to this account has been blocked by parental controls. Your settings may be changed in your account preferences at site.
     */
    AUTH_FAIL_PARENTCONTROL((short) 15);        
    
    private final short result;

    private AuthEnum(short result)
    {
        this.result = result;
    }
    
    public short getValue(){
        return this.result;
    }
    
    public static AuthEnum convert(short value) {
        for(AuthEnum cmd : AuthEnum.values()){
            if(cmd.getValue() == value)
                return cmd;
        }            
        return null;
    } 
}
