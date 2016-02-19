package eu.jangos.realm.network.packet.server.auth;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.opcode.result.AuthEnum;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;

/**
 * jE4W is a featured server emulator for World of Warcraft 1.12.x.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * World of Warcraft, and all World of Warcraft or Warcraft art, images, and
 * lore are copyrighted by Blizzard Entertainment, Inc.
 *
 * A lot of credits goes to MaNGOS project from which several ideas (but not
 * all) were included in this project.
 *
 * Copyright (C) 2015-2015 jE4W project Copyright (C) 2005-2014 MaNGOS project
 * <http://getmangos.eu>
 */
/**
 * SMSG_LOGIN_VERIFY_WORLD represents a message sent by the server whenever a
 * CMSG_PLAYER_LOGIN message is sent.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_LOGIN_VERIFY_WORLD extends AbstractRealmServerPacket {

    /**
     * The map to which the player belongs.
     */
    private int map;

    /**
     * The position X of the player.
     */
    private float posX;

    /**
     * The position Y of the player.
     */
    private float posY;

    /**
     * The position Z of the player.
     */
    private float posZ;

    /**
     * The orientation of the player.
     */
    private float orientation;

    public SMSG_LOGIN_VERIFY_WORLD() {
        super(Opcodes.SMSG_LOGIN_VERIFY_WORLD, 22);
    }
    
    public SMSG_LOGIN_VERIFY_WORLD(Opcodes code) {
        super(code, 22);
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
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
        String toString = "[SMSG_LOGIN_VERIFY_WORLD [Map:" + this.map
                + ", X: " + this.posX
                + ", Y: " + this.posY
                + ", Z: " + this.posZ
                + ", Orientation: " + this.orientation
                + "]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:
        // 2b - 2b - 4b - 4b - 4b - 4b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - map - posX - posY - posZ - orientation

        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.map);
        buf.writeFloat(this.posX);
        buf.writeFloat(this.posY);
        buf.writeFloat(this.posZ);
        buf.writeFloat(this.orientation);
    }

}
