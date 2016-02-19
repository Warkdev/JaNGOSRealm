package eu.jangos.realm.network.packet.server.auth;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.opcode.result.AuthEnum;
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
 * SMSG_AUTH_RESPONSE represents a message sent by the server whenever a CMSG_AUTH_SESSION message is sent.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_AUTH_RESPONSE extends AbstractRealmServerPacket {

    /**
     * Result command from the server.
     */
    private AuthEnum result;
    
    public SMSG_AUTH_RESPONSE(Opcodes code) {
        super(code, 0);
    }

    public AuthEnum getResult() {
        return result;
    }

    public void setResult(AuthEnum result) {
        this.result = result;
        this.size = (this.result != AuthEnum.AUTH_SUCCESS ? 5 : 12);
    }    
    
    public String toString(){
        String toString = "[SMSG_AUTH_RESPONSE [Result: "+this.result+"]]";
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {
        // SMSG_AUTH_RESPONSE happens after a client requested to login.
        // Packet structure: (Failed case)
        // 2b - 2b - 1b
        // Size (Little Endian) - Opcode (Big Endian) - result
        
        if(this.result != AuthEnum.AUTH_SUCCESS){
            buf.writeShort(this.size);
            buf.writeShort(this.code.getValue());
            buf.writeByte(this.result.getValue());
        } else {
            
        // Packet structure: (Success case)
        // 2b - 1b - 1b - 4b - 1b - 1b
        // Size - Opcode - Result - BillingTimeRemaining - BillingPlanFlags - BillingTimeRested
            
            buf.writeShort(this.size); // Size
            buf.writeShort(this.code.getValue());
            buf.writeByte((byte) this.result.getValue());
            buf.writeInt(0);
            buf.writeByte(0);
            buf.writeInt(0);            
        }
    }

}
