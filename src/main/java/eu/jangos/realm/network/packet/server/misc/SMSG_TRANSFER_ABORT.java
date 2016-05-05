package eu.jangos.realm.network.packet.server.misc;

import eu.jangos.realm.enums.world.TransfertAbortedEnum;
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
 * SMSG_TRANSFER_ABORT represents a message sent by the server to indicates that the player teleport to another world has been aborted.
 * Used as an answer to CMSG_WORLD_TELEPORT
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_TRANSFER_ABORT extends AbstractRealmServerPacket {
        
    /**
     * The map on which the player was being teleported to.
     */
    private int mapID;
    
    /**
     * The reason for which the player transfer has been aborted.
     */
    private TransfertAbortedEnum reason;
    
    /**
     * This argument is never used.
     */
    private byte unknown;
    
    /**
     * SMSG_TRANSFER_PENDING can be 4 bytes length or 12 (if the player is on a transport).
     * @param code 
     */
    public SMSG_TRANSFER_ABORT(Opcodes code) {
        super(code, 6);   
        this.unknown = 0;
    }    

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public TransfertAbortedEnum getReason() {
        return reason;
    }

    public void setReason(TransfertAbortedEnum reason) {
        this.reason = reason;
    }

    public byte getUnknown() {
        return unknown;
    }

    public void setUnknown(byte unknown) {
        this.unknown = unknown;
    }        
    
    public String toString(){        
        String toString = "[SMSG_TRANSFER_ABORT [ "
                + ", mapID: " + this.mapID
                + ", reason: "+this.reason
                + ", unknown: "+this.unknown
                + " ]]";
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {        
        // Packet structure:
        // 2b - 2b - 4b - 1b - 1b
        // Size (Little Endian) - Opcode (Big Endian) - mapID - reason - unknown
        
        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.mapID);       
        buf.writeByte(this.reason.getValue());
        buf.writeByte(this.unknown);
    }

}
