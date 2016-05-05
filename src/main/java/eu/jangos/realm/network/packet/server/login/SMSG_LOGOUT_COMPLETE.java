package eu.jangos.realm.network.packet.server.login;

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
 * SMSG_LOGOUT_COMPLETE represents a message sent by the server to indicates that the player logout is complete.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_LOGOUT_COMPLETE extends AbstractRealmServerPacket {       
        
    public SMSG_LOGOUT_COMPLETE(Opcodes code) {
        super(code, 4);           
    }            
    
    public String toString(){        
        String toString = "[SMSG_LOGOUT_COMPLETE]";                
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {        
        // Packet structure:
        // 2b - 2b
        // Size (Little Endian) - Opcode (Big Endian)
        
        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());          
    }

}
