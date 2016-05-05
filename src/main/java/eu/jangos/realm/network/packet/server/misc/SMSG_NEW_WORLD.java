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
 * SMSG_NEW_WORLD represents a message sent by the server to indicates that the player has changed of World.
 * Used as an answer to CMSG_WORLD_TELEPORT
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_NEW_WORLD extends AbstractRealmServerPacket {
        
    /**
     * The map on which the player has been to be teleported to.
     */
    private int mapID;
    
    /**
     * The position X on which the player has been to be teleported.
     */
    private float posX;
    
    /**
     * The position Y on which the player has been to be teleported.
     */
    private float posY;
    
    /**
     * The position Z on which the player has been to be teleported.
     */
    private float posZ;
    
    /**
     * The orientation on which the player has been to be teleported.
     */
    private float orientation;
                
    public SMSG_NEW_WORLD(Opcodes code) {
        super(code, 20);
    }    

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }

    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }        
    
    public String toString(){        
        String toString = "[SMSG_NEW_WORLD [ "
                + ", mapID: " + this.mapID
                + ", PosX: "+this.posX
                + ", PosY: "+this.posY
                + ", PosZ: "+this.posZ
                + ", Orientation: "+this.orientation
                + " ]]";
        return toString;
    }
    
    @Override
    public void encode(ByteBuf buf) throws Exception {        
        // Packet structure:
        // 2b - 2b - 4b - 4b - 4b - 4b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - mapID - posX - posY - posZ - orientation        
        
        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.mapID);
        buf.writeFloat(this.posX);
        buf.writeFloat(this.posY);
        buf.writeFloat(this.posZ);
        buf.writeFloat(this.orientation);
    }

}
