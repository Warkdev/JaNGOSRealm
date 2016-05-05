package eu.jangos.realm.network.packet.client.login;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
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
 * CMSG_PLAYER_LOGOUT represents a packet sent by the client when it tries to logout a character.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_PLAYER_LOGOUT extends AbstractRealmClientPacket {

    /**
     * Packet size.
     */
    private short size;        
    
    /**
     * Constructor with opcode.
     *
     * @param opcode
     */
    public CMSG_PLAYER_LOGOUT(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }
    
    public String toString() {
        String toString = "[CMSG_PLAYER_LOGOUT ["                 
                +"]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {           
        if((buf.readableBytes()+4) < this.size)
        {
            throw new Exception();
        }                
    }

}
