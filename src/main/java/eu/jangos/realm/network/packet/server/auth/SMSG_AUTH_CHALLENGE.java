package eu.jangos.realm.network.packet.server.auth;

import eu.jangos.realm.network.opcode.Opcodes;
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
 * SMSG_AUTH_CHALLENGE represents a server packet with the realm auth challenge.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_AUTH_CHALLENGE extends AbstractRealmServerPacket {

    /**
     * Seed is used for the encryption
     */
    private byte[] seed;

    public SMSG_AUTH_CHALLENGE(Opcodes opcode) {
        super(opcode, 6);
    }

    public byte[] getSeed() {
        return seed;
    }

    public void setSeed(byte[] seed) {
        this.seed = seed;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // SMSG_AUTH_CHALLENGE happens whenever a client is connecting to a realm.
        // Packet structure:
        // 2b - 2b - 4b
        // Size (BigEndian) - Opcode (Little Endian) - Seed
        
        buf.writeShort(this.size);        
        buf.writeShort(this.code.getValue());
        buf.writeBytes(this.seed, 0, 4);
    }

}
