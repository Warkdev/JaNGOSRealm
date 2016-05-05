package eu.jangos.realm.network.packet.client.query;

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
 * CMSG_CREATURE_QUERY represents a packet sent by the client when it wants to retrieve creature information.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_CREATURE_QUERY extends AbstractRealmClientPacket {

    /**
     * Packet size.
     */
    private short size;
    
    /**
     * Creature entry.
     */
    private int entry;
    
    /**
     * Creature guid.
     */
    private long guid;
    
    /**
     * Constructor with opcode.
     *
     * @param opcode
     */
    public CMSG_CREATURE_QUERY(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }    
    
    public String toString() {
        String toString = "[CMSG_CREATURE_QUERY [entry: "+this.entry
                +", guid: "+this.guid
                +"]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {           
        if((buf.readableBytes()+4) < this.size)
        {
            throw new Exception();
        }
        
        this.entry = buf.readInt();   
        this.guid = buf.readLong();
    }

}
