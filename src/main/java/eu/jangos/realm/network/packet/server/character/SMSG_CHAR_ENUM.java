package eu.jangos.realm.network.packet.server.character;

import eu.jangos.realm.model.world.Equipment;
import eu.jangos.realm.model.realm.Items;
import eu.jangos.realm.model.realm.Pchars;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import java.nio.ByteOrder;
import java.util.ArrayList;
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
 * SMSG_CHAR_ENUM represents a message sent by the server a client is
 * authenticated for this realm.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_CHAR_ENUM extends AbstractRealmServerPacket {

    /**
     * List of characters.
     */
    private List<Pchars> listChars;

    /**
     * Number of characters.
     */
    private int numChars;

    private static final int EQUIPMENT_SLOT_END = 19;

    public SMSG_CHAR_ENUM(Opcodes code) {
        super(code, 3);
        this.listChars = new ArrayList<>();
        this.numChars = 0;
    }

    /**
     * Add one character to the list of characters.
     *
     * @param character The character to be added.
     */
    public void addCharacter(Pchars character) {
        if (this.listChars.add(character)) {
            this.size += character.getSize();
            this.numChars++;
        }
    }

    /**
     * Add characters to the actual list of characters.
     *
     * @param listChars The list of characters to be added.
     */
    public void addCharacters(List<Pchars> listChars) {
        for (Pchars c : listChars) {
            addCharacter(c);
        }
    }

    /**
     * Remove the character from the character list.
     *
     * @param character The character to be removed.
     */
    public void remCharacter(Pchars character) {
        if (this.listChars.remove(character)) {
            this.size -= character.getSize();
            this.numChars--;
        }
    }

    public String toString() {
        String toString = "[SMSG_CHAR_ENUM [ " + this.numChars + " character(s), size: " + this.size;

        for (Pchars c : this.listChars) {
            toString += "[ id: " + c.getId()
                    + ", name: " + c.getName()
                    + ", race: " + c.getFkRace().getValue()
                    + ", class: " + c.getFkClass().getValue()
                    + ", gender: " + c.getFkGender().getValue();
        }

        toString += " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:
        // 2b - 2b - 1b
        // Size (Little Endian) - Opcode (Big Endian) - number of characters 
        // If any character:
        // 8b - ?b - 1b - 1b - 1b - 1b - 1b - 1b - 1b - 1b - 1b - 4b - 4b - 4b - 4b - 4b - 4b - 1b - 4b - 4b - 4b 
        // GUID (Little Endian) - Name - Race - Class - Gender - Skin - Face - HairStyle - HairColor - FacialHair - level - zone - map - x - y - z - guild
        // char flags - first login - Pet id - Pet level - Pet Family
        // Then equipment: (19 pieces)
        // 4b - 1b
        // Item Display ID - Item Inventory Type
        // Then the bag
        // 4b - 1b
        // First bag display ID
        // First bag inventory type

        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeByte(this.numChars);

        for (Pchars c : this.listChars) {
            buf = buf.order(ByteOrder.LITTLE_ENDIAN);
            buf.writeLong(c.getId());
            //buf = buf.order(ByteOrder.BIG_ENDIAN);
            ByteBufUtil.writeAscii(buf, c.getName());
            buf.writeByte((byte) 0);
            buf.writeByte(c.getFkRace().getValue().getValue());
            buf.writeByte(c.getFkClass().getValue().getValue());
            buf.writeByte(c.getFkGender().getValue().getValue());
            buf.writeByte(c.getSkin());
            buf.writeByte(c.getFace());
            buf.writeByte(c.getHairStyle());
            buf.writeByte(c.getHairColor());
            buf.writeByte(c.getFacialHair());
            buf.writeByte((byte) c.getLevel());
            buf.writeInt(c.getZone());
            buf.writeInt(c.getMap());
            buf.writeFloat((float) c.getPosx()); // X
            buf.writeFloat((float) c.getPosy()); // Y
            buf.writeFloat((float) c.getPosz()); // Z
            buf.writeInt(0); // Guild ID
            buf.writeInt(0); // Char Flags
            buf.writeByte((byte) 1); // First login
            buf.writeInt(0); // Pet ID
            buf.writeInt(0); // Pet Level
            buf.writeInt(0); // Pet family

             // This can be optimized.
            for(int i=0; i<=EQUIPMENT_SLOT_END; i++)
            {
                int displayID = 0;
                byte inventoryType = 0;
                for(Equipment e : c.getEquipmentCollection())
                {
                    if(e.getFkSlot().getId() == i && e.getFkItem().getFkInventorytype().getId() != 0)
                    {
                        // There's an equipment for this item slot.
                        displayID = e.getFkItem().getDisplayid();
                        inventoryType = e.getFkItem().getFkInventorytype().getId().byteValue();                        
                    }
                }
                
                buf.writeInt(displayID);
                buf.writeByte(inventoryType);
            }
        }
    }

}
