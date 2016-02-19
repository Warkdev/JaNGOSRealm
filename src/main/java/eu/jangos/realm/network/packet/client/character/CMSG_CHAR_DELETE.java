package eu.jangos.realm.network.packet.client.character;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
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
 * CMSG_CHAR_DELETE represents a packet sent by the client whenever it tries to delete a character.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_CHAR_DELETE extends AbstractRealmClientPacket {

    /**
     * Packet size.
     */
    private short size;    

    /**
     * Player ID.
     */
    private long id;
    
    /**
     * Constructor with opcode.
     *
     * @param opcode
     * @param size
     */
    public CMSG_CHAR_DELETE(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }           
    
    public String toString() {
        String toString = "[CMSG_CHAR_DELETE [ "
                + " id: " + this.id                
                + " ]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {               
        if((buf.readableBytes() + 4) < this.size)
        {
            throw new Exception();
        }                                
        
        this.id = buf.readLong();
    }

}
