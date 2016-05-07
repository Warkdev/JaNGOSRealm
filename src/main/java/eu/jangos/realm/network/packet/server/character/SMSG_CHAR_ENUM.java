package eu.jangos.realm.network.packet.server.character;

import eu.jangos.realm.controller.characters.ItemInstanceService;
import eu.jangos.realm.controller.factory.ItemServiceFactory;
import eu.jangos.realm.controller.world.ItemService;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.model.characters.Characters;
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

    /**
     * Build the field which represents the login flag.
     * @param c The character for which the flags need to be created.
     * @return An integer corresponding to the value of the character flag.
     */
    private byte convertLoginFlagsToByte(Characters c) {
        byte flags = 0; // NONE
        
        flags+=(c.getChangename() ? 1 : 0); // RENAME
        flags+=(c.getResetspell() ? 2 : 0); // RESET SPELLS
        flags+=(c.getResettalents() ? 4 : 0); // RESET TALENTS
        //flags+=(c.isCharcustom()? 8 : 0); // CUSTOMIZE -- Not used
        //flags+=(c.isre() ? 16 : 0); // RESET PET TALENTS -- Not used
        flags+=(c.getTotaltime() == 0 ? 32 : 0); // FIRST LOGIN        
        
        return flags;
    }
    
    /**
     * Build the field which represents the character flags.
     *
     * @param c The character for which the flags needs to be created.
     * @return An integer corresponding to the value of the character flag.
     */
    private int convertPlayersFlagsToInt(Characters c) {
        int flags = 0;

        // flags+= 1; //UNKNOWN FLAG
        // flags+= (0*2) // UNKNOWN FLAG
        // flags+= (0*4) // UNKNOWN
        // flags+= (0*8) // UNKNOWN
        // flags+= (0*16) // UNKNOWN
        // flags+= (0*32) // UNKNOWN
        // flags+= (0*64) // UNKNOWN
        // flags+= (0*128) // UNKNOWN
        // flags+= (0*256) // UNKNOWN
        // flags+= (0*512) // UNKNOWN        
        flags += (c.getHidehelm() ? 1024 : 0); // HIDE_HELM
        flags += (c.getHidecloak() ? 2048 : 0); // HIDE_CLOAK
        // flags+= (0*4096) // UNKNOWN
        flags += (c.getGhost() ? 8192 : 0); // GHOST
        flags += (c.getChangename() ? 16384 : 0); // RENAME
        // flags+= (0*32768) // UNKNOWN
        // flags+= (0*65536) // UNKNOWN
        // flags+= (0*131072) // UNKNOWN
        // flags+= (0*262144) // UNKNOWN
        // flags+= (0*524288) // UNKNOWN
        // flags+= (0*1048576) // UNKNOWN
        // flags+= (0*2097152) // UNKNOWN
        // flags+= (0*4194304) // UNKNOWN
        // flags+= (0*8388608) // UNKNOWN        
        // flags+= (0*16777216) // LOCKED_BY_BILLING        
        // flags+= (0*33554432) // DECLINED     
        // flags+= (0*67108864) // UNKNOWN
        // flags+= (0*134217728) // UNKNOWN
        // flags+= (0*268435456) // UNKNOWN
        // flags+= (0*536870912) // UNKNOWN
        // flags+= (0*1073741824) // UNKNOWN
        // flags+= (0*2147483648) // UNKNOWN        
        
        return flags;
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

        // TODO: 
        // - Guild membership
        // - Pet ID
        // - Pet Level
        // - Pet Family
        
        
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
            buf.writeByte((byte) 0); // End of string
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
            buf.writeInt(convertPlayersFlagsToInt(c)); // Char Flags            
            buf.writeByte(convertLoginFlagsToByte(c)); // First login
            buf.writeInt(0); // Pet ID
            buf.writeInt(0); // Pet Level
            buf.writeInt(0); // Pet family

            int count = 0;

            // We get the list of data for the equipment.
            for (Object data : iiService.getEquipmentCharEnum(c)) {
                Object[] itemInstance = (Object[]) data;
                int displayID = 0;
                byte inventoryType = 0;

                // We fill-in the data for the unoccupied slots.
                for (int i = count; i < Integer.parseInt(itemInstance[0].toString()); i++) {
                    buf.writeInt(displayID);
                    buf.writeByte(inventoryType);
                    count++;
                }

                // We add the occupied slot.
                Object[] item = itemService.getItemByIDCharEnum(Integer.parseInt(itemInstance[1].toString()));

                displayID = Integer.parseInt(item[0].toString());
                inventoryType = Byte.parseByte(item[1].toString());

                buf.writeInt(displayID);
                buf.writeByte(inventoryType);

                count++;
            }

            // We fill in the data for the end of the equipment slot.
            for (int i = count; i <= EQUIPMENT_SLOT_END; i++) {
                buf.writeInt(0);
                buf.writeByte(0);
            }

        }

    }
}
