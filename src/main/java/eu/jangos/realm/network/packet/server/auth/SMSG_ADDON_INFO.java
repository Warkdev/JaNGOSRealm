package eu.jangos.realm.network.packet.server.auth;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import eu.jangos.realm.network.packet.client.auth.AddonInfo;
import io.netty.buffer.ByteBuf;
import java.util.List;

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
 * SMSG_ADDON_INFO represents a server packet with the addon info.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_ADDON_INFO extends AbstractRealmServerPacket {

    /**
     * List of add-ons accepted by the server.
     */
    private List<AddonInfo> listAddons;  

    public SMSG_ADDON_INFO() {
        super(Opcodes.SMSG_ADDON_INFO, 2);
    }

    public SMSG_ADDON_INFO(Opcodes opcode) {
        super(opcode, 2);
    }

    public List<AddonInfo> getListAddons() {
        return listAddons;
    }

    public void setListAddons(List<AddonInfo> listAddons) {
        this.listAddons = listAddons;

        this.size += (8 * this.listAddons.size());

    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // SMSG_ADDON_INFO happens whenever a client is connecting to a realm.
        // Packet structure:
        // 2b - 2b - [ 1b - 1b - 1b - (256b) - 4b - 1b ]
        // Size (BigEndian) - Opcode (Little Endian) - AddOn Type - Unknown - Unknown - Public key (optional)
        // - Unknown - Unknown

        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());

        for (AddonInfo info : this.listAddons) {
            buf.writeByte(2); // Blizzard Add-ons (2)
            buf.writeByte(1); // Unknown.

            // In vanilla, all-addons are Blizzard add-ons, don't know why.            
            buf.writeByte(0);

            buf.writeInt(0);
            buf.writeByte(0);
        }

    }

}
