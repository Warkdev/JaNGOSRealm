package eu.jangos.realm.network.packet.client.misc;

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
 * CMSG_WORLD_TELEPORT represents a packet sent by the client to teleport an unit to another location.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_WORLD_TELEPORT extends AbstractRealmClientPacket {

    /**
     * Packet size.
     */
    private short size;    
    
    /**
     * The time of the packet
     */
    private int time;
            
    /**
     * The map on which the player needs to be teleported to.
     */
    private int mapID;
    
    /**
     * The position X on which the player needs to be teleported.
     */
    private float posX;
    
    /**
     * The position Y on which the player needs to be teleported.
     */
    private float posY;
    
    /**
     * The position Z on which the player needs to be teleported.
     */
    private float posZ;
    
    /**
     * The orientation on which the player needs to be teleported.
     */
    private float orientation;
    
    /**
     * Constructor with opcode.
     *
     * @param opcode
     * @param size
     */
    public CMSG_WORLD_TELEPORT(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
    
    public String toString() {
        String toString = "[CMSG_WORLD_TELEPORT [ "
                + " time: " + this.time
                + ", mapID: " + this.mapID
                + ", PosX: "+this.posX
                + ", PosY: "+this.posY
                + ", PosZ: "+this.posZ
                + ", Orientation: "+this.orientation
                + " ]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {               
        if((buf.readableBytes() + 4) < this.size)
        {
            throw new Exception();
        }            
        
        this.time = buf.readInt();
        this.mapID = buf.readInt();
        this.posX = buf.readFloat();
        this.posY = buf.readFloat();
        this.posZ = buf.readFloat();
        this.orientation = buf.readFloat();
    }

}
