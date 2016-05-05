package eu.jangos.realm.network.packet.server.login;

import eu.jangos.realm.enums.world.LogoutRequestEnum;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;

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
 * SMSG_LOGOUT_RESPONSE represents a message sent by the server to indicates that the player logout is accepted or refused.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_LOGOUT_RESPONSE extends AbstractRealmServerPacket {
        
    /**
     * The reason for which the player logout is accepted or refused.
     */
    private LogoutRequestEnum reason;
    
    /**
     * Indicates whether the player can logout instantly or not.
     */
    private boolean instant;
    
    /**
     * SMSG_TRANSFER_PENDING can be 4 bytes length or 12 (if the player is on a transport).
     * @param code 
     */
    public SMSG_LOGOUT_RESPONSE(Opcodes code) {
        super(code, 5);           
    }        

    public LogoutRequestEnum getReason() {
        return reason;
    }

    public void setReason(LogoutRequestEnum reason) {
        this.reason = reason;
    }

    public boolean isInstant() {
        return instant;
    }

    public void setInstant(boolean instant) {
        this.instant = instant;
    }         
    
    public String toString(){        
        String toString = "[SMSG_LOGOUT_RESPONSE [ "                
                + ", reason: "+this.reason
                + ", instant: "+this.instant
                + " ]]";
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {        
        // Packet structure:
        // 2b - 2b - 1b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - reason - instant
        // There are uncertainty, Mangos claims structure is 1b + 4b
        // TC claims it's 4b + 1b.
        
        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());          
        buf.writeByte(this.reason.getValue());
        buf.writeInt(this.instant ? 1 : 0);
    }

}
