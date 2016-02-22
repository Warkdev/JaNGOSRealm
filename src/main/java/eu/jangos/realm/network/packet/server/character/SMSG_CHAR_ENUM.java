package eu.jangos.realm.network.packet.server.character;

import eu.jangos.realm.controller.characters.ItemInstanceService;
import eu.jangos.realm.controller.factory.ItemServiceFactory;
import eu.jangos.realm.controller.world.ItemService;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.model.characters.ItemInstance;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

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
    private List<Characters> listChars;

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
    public void addCharacter(Characters character) {
        if (this.listChars.add(character)) {
            this.size += getCharacterLength(character);
            this.numChars++;
        }
    }

    /**
     * Add characters to the actual list of characters.
     *
     * @param listChars The list of characters to be added.
     */
    public void addCharacters(List<Characters> listChars) {
        for (Characters c : listChars) {
            addCharacter(c);
        }
    }

    /**
     * Remove the character from the character list.
     *
     * @param character The character to be removed.
     */
    public void remCharacter(Characters character) {
        if (this.listChars.remove(character)) {
            this.size -= getCharacterLength(character);
            this.numChars--;
        }
    }

    private int getCharacterLength(Characters character) {
        return 159 + character.getName().length();
    }

    public String toString() {
        String toString = "[SMSG_CHAR_ENUM [ " + this.numChars + " character(s), size: " + this.size;

        for (Characters c : this.listChars) {
            toString += "[ id: " + c.getGuid()
                    + ", name: " + c.getName()
                    + ", race: " + RaceEnum.convert(c.getRace())
                    + ", class: " + ProfessionsEnum.convert(c.getFkDbcClass())
                    + ", gender: " + GenderEnum.convert(c.getGender());
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

        ItemService itemService = ItemServiceFactory.getInstance();
        ItemInstanceService iiService = new ItemInstanceService();

        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeByte(this.numChars);

        for (Characters c : this.listChars) {
            buf = buf.order(ByteOrder.LITTLE_ENDIAN);
            buf.writeLong(c.getGuid());
            //buf = buf.order(ByteOrder.BIG_ENDIAN);
            ByteBufUtil.writeAscii(buf, c.getName());
            buf.writeByte((byte) 0);
            buf.writeByte(c.getRace());
            buf.writeByte(c.getFkDbcClass());
            buf.writeByte(c.getGender());
            buf.writeByte(c.getSkin());
            buf.writeByte(c.getFace());
            buf.writeByte(c.getHairstyle());
            buf.writeByte(c.getHaircolor());
            buf.writeByte(c.getFacialhair());
            buf.writeByte((byte) c.getLevel());
            buf.writeInt(c.getFkDbcZone());
            buf.writeInt(c.getFkDbcMap());
            buf.writeFloat((float) c.getPositionX()); // X
            buf.writeFloat((float) c.getPositionY()); // Y
            buf.writeFloat((float) c.getPositionZ()); // Z
            buf.writeInt(0); // Guild ID
            buf.writeInt(0); // Char Flags
            buf.writeByte((byte) 1); // First login
            buf.writeInt(0); // Pet ID
            buf.writeInt(0); // Pet Level
            buf.writeInt(0); // Pet family

            int count = 0;
            for (ItemInstance item : iiService.getEquipment(c)) {
                int displayID = 0;
                byte inventoryType = 0;
                
                for(int i=count; i<item.getSlot(); i++)
                {
                    buf.writeInt(displayID);
                    buf.writeByte(inventoryType);
                    count++;
                }
                                
                displayID = itemService.getItemByID(item.getFkObjectEntry()).getDisplayid();
                inventoryType = itemService.getItemByID(item.getFkObjectEntry()).getInventorytype().getId();                                
                
                buf.writeInt(displayID);
                buf.writeByte(inventoryType);
                
                count++;
            }

            for(int i=count; i<=EQUIPMENT_SLOT_END; i++)
            {
                buf.writeInt(0);
                buf.writeByte(0);
            }
            
        }

    }
}
