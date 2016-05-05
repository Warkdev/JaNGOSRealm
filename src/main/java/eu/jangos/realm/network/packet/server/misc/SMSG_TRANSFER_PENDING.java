package eu.jangos.realm.network.packet.server.misc;

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
 * SMSG_TRANSFER_PENDING represents a message sent by the server to indicates that the player is changing of World.
 * Used as an answer to CMSG_WORLD_TELEPORT
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_TRANSFER_PENDING extends AbstractRealmServerPacket {
        
    /**
     * The map on which the player is being teleported to.
     */
    private int mapID;
    
    /**
     * The ID of the transport on which the player is located (only if the player is on a transport).
     */
    private int transportID;
    
    /**
     * The ID of the current map of the player.
     */
    private int currentMapID;
    
    /**
     * SMSG_TRANSFER_PENDING can be 4 bytes length or 12 (if the player is on a transport).
     * @param code 
     */
    public SMSG_TRANSFER_PENDING(Opcodes code) {
        super(code, 4);        
        
        this.transportID = 0;
        this.currentMapID = 0;
    }    

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public int getTransportID() {
        return transportID;
    }

    public void setTransportID(int transportID) {
        this.transportID = transportID;
    }

    public int getCurrentMapID() {
        return currentMapID;
    }

    public void setCurrentMapID(int currentMapID) {
        this.currentMapID = currentMapID;
    }        
    
    public String toString(){        
        String toString = "[SMSG_TRANSFER_PENDING [ "
                + ", mapID: " + this.mapID
                + ", Transport ID: "+this.transportID
                + ", current map: "+this.currentMapID                
                + " ]]";
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {        
        // Packet structure:
        // 2b - 2b - 4b (- 4b - 4b)
        // Size (Little Endian) - Opcode (Big Endian) - mapID (- transportID - currentMapID)                
        
        buf.writeShort(this.size + (this.transportID != 0 ? 8 : 0));
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.mapID);       
        
        if(this.transportID != 0)
        {        
            buf.writeInt(this.transportID);
            buf.writeInt(this.currentMapID);        
        }
    }

}
