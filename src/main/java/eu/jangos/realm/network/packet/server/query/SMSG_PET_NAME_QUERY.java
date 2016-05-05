package eu.jangos.realm.network.packet.server.query;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

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
 * SMSG_PET_NAME_QUERY represents a message sent by the server as the result of a CMSG_PET_NAME_QUERY packet.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_PET_NAME_QUERY extends AbstractRealmServerPacket {

    private int number;
    
    private String name;
    
    // Is equal to UNIT_FIELD_PET_NAME_TIMESTAMP
    private int field;   

    public SMSG_PET_NAME_QUERY(Opcodes code) {
        super(code, 8);
    }   

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }  
    
    public String toString() {
        String toString = "[SMSG_PET_NAME_QUERY [ "
                + " number: " + this.number
                + ", name: " + this.name
                + ", field: " + this.field                
                + " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:
        // 2b - 2b - 4b - ?b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - Pet number - Pet name - Pet Field                
        
        buf.writeShort(this.size + this.name.length() + 1);
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.number);
        ByteBufUtil.writeAscii(buf, this.name);
        buf.writeByte((byte) 0); // End of string
        buf.writeInt(this.field);        
    }
}
