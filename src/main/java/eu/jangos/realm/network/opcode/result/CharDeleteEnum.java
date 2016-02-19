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
 * CharDeleteEnum is an enumeration of all possible command that the server may send back to the client for character deletion.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public enum CharDeleteEnum {    
    
    /**
     * Character deletion is in progress.
     */
    CHAR_DELETE_IN_PROGRESS((short) 0x38),
    
    /**
     * Characater deletion is done succesfully.
     */
    CHAR_DELETE_SUCCESS((short) 0x39),
    
    /**
     * Character deletion has failed (guild master case).
     */
    CHAR_DELETE_FAILED((short) 0x3A);            
    
    private final short result;

    private CharDeleteEnum(short result)
    {
        this.result = result;
    }
    
    public short getValue(){
        return this.result;
    }
    
    public static CharDeleteEnum convert(short value) {
        for(CharDeleteEnum cmd : CharDeleteEnum.values()){
            if(cmd.getValue() == value)
                return cmd;
        }            
        return null;
    } 
}
