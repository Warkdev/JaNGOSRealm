package eu.jangos.realm.network.packet.server.character;

import eu.jangos.realm.network.opcode.result.CharCreateEnum;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;

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
 * SMSG_CHAR_CREATE represents a message sent by the server to confirm or invalidate a character creation.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_CHAR_CREATE extends AbstractRealmServerPacket {
        
    /**
     * The character result sent by the server.
     */
    private CharCreateEnum result;
            
    public SMSG_CHAR_CREATE(Opcodes code) {
        super(code, 3);
    }    

    public CharCreateEnum getResult() {
        return result;
    }

    public void setResult(CharCreateEnum result) {
        this.result = result;
    }        
    
    public String toString(){        
        String toString = "[SMSG_CHAR_CREATE [ Result: "+this.result+" ]]";
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {        
        // Packet structure:
        // 2b - 2b - 1b
        // Size (Little Endian) - Opcode (Big Endian) - result        
        
        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeByte(this.result.getValue());                       
    }

}
