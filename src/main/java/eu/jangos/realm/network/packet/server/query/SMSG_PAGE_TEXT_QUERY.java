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
 * SMSG_PAGE_TEXT_QUERY represents a message sent by the server as the result of a CMSG_PAGE_TEXT_QUERY packet.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_PAGE_TEXT_QUERY extends AbstractRealmServerPacket {   
    
    /**
     * The ID of the page.
     */
    private int id;
    
    /**
     * The text of the page.
     */
    private String text;
    
    public SMSG_PAGE_TEXT_QUERY(Opcodes code) {
        super(code, 5);
    }           

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }                
    
    @Override
    public String toString() {
        String toString = "[SMSG_PAGE_TEXT_QUERY [ "
                + " id: " + this.id
                + " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:        
        // 2b - 2b - 4b - ?b
        // Size (Little Endian) - Opcode (Big Endian) - Page ID - Page Text             
        
        buf.writeShort(this.size + this.text.length());
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.id);
        ByteBufUtil.writeAscii(buf, this.text);
        buf.writeByte((byte) 0); // End of string        
    }
}
