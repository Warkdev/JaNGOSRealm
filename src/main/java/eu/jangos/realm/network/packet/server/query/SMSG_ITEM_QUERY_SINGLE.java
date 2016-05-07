package eu.jangos.realm.network.packet.server.query;

import eu.jangos.realm.controller.factory.ItemServiceFactory;
import eu.jangos.realm.controller.world.ItemService;
import eu.jangos.realm.model.world.Item;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

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
 * SMSG_ITEM_QUERY_SINGLE represents a message sent by the server as the result of a CMSG_ITEM_QUERY_SINGLE packet.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_ITEM_QUERY_SINGLE extends AbstractRealmServerPacket {

    /**
     * The item for which the query has been raised.
     */
    private Item item;                       

    private static final int MAX_ITEM_PROTO_STATS = 10;
    private static final int MAX_ITEM_PROTO_DAMAGES = 5;
    private static final int MAX_ITEM_PROTO_SPELLS = 5;
    
    
    public SMSG_ITEM_QUERY_SINGLE(Opcodes code) {
        super(code, 453);
    }   

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }    
    
    public String toString() {
        String toString = "[SMSG_ITEM_QUERY_SINGLE [ "
                + " item: " + this.item.getName()                
                + " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:        
        // 2b - 2b - 
        // Size (Little Endian) - Opcode (Big Endian) -                 
        
        ItemService itemService = ItemServiceFactory.getInstance();
        
        buf.writeShort(this.size + this.item.getName().length() + this.item.getDescription().length());
        buf.writeShort(this.code.getValue());
        
        buf.writeInt(this.item.getEntry());
        buf.writeInt(this.item.getItemsubclass().getItemclass().getId());
        buf.writeInt(this.item.getItemsubclass().getId().getId()); 
        // 12
        ByteBufUtil.writeAscii(buf, this.item.getName());
        buf.writeByte(0); // End of string
        buf.writeByte(0); // End of string 2
        buf.writeByte(0); // End of string 3
        buf.writeByte(0); // End of string 4
        // 16 + ?
        buf.writeInt(this.item.getDisplayid());
        buf.writeInt(this.item.getItemquality().getId());
        buf.writeInt(this.item.getFlags());
        buf.writeInt(this.item.getBuyprice());
        buf.writeInt(this.item.getSellprice());
        buf.writeInt(this.item.getInventorytype().getId());
        buf.writeInt(this.item.getAllowableclass());
        buf.writeInt(this.item.getAllowablerace());
        buf.writeInt(this.item.getItemlevel());
        buf.writeInt(this.item.getRequiredlevel());
        buf.writeInt(this.item.getRequiredskill());
        buf.writeInt(this.item.getRequiredskillrank());
        buf.writeInt(this.item.getRequiredhonorrank());
        buf.writeInt(this.item.getRequiredcityrank());
        buf.writeInt(this.item.getRequiredreputationfaction());
        buf.writeInt((this.item.getRequiredreputationfaction() > 0 ? this.item.getRequiredreputationrank() : 0));
        buf.writeInt(this.item.getMaxcount());
        buf.writeInt(this.item.getStackable());
        buf.writeInt(this.item.getContainerslots());
        // 92        
        
        buf.writeInt(this.item.getStatType1());
        buf.writeInt(this.item.getStatValue1());
        buf.writeInt(this.item.getStatType2());
        buf.writeInt(this.item.getStatValue2());
        buf.writeInt(this.item.getStatType3());
        buf.writeInt(this.item.getStatValue3());
        buf.writeInt(this.item.getStatType4());
        buf.writeInt(this.item.getStatValue4());
        buf.writeInt(this.item.getStatType5());
        buf.writeInt(this.item.getStatValue5());
        buf.writeInt(this.item.getStatType6());
        buf.writeInt(this.item.getStatValue6());
        buf.writeInt(this.item.getStatType7());
        buf.writeInt(this.item.getStatValue7());
        buf.writeInt(this.item.getStatType8());
        buf.writeInt(this.item.getStatValue8());
        buf.writeInt(this.item.getStatType9());
        buf.writeInt(this.item.getStatValue9());
        buf.writeInt(this.item.getStatType10());
        buf.writeInt(this.item.getStatValue10());
        // 172
        
        buf.writeFloat(this.item.getDmgMin1());
        buf.writeFloat(this.item.getDmgMax1());
        buf.writeInt(this.item.getDmgType1());
        buf.writeFloat(this.item.getDmgMin2());
        buf.writeFloat(this.item.getDmgMax2());
        buf.writeInt(this.item.getDmgType2());
        buf.writeFloat(this.item.getDmgMin3());
        buf.writeFloat(this.item.getDmgMax3());
        buf.writeInt(this.item.getDmgType3());
        buf.writeFloat(this.item.getDmgMin4());
        buf.writeFloat(this.item.getDmgMax4());
        buf.writeInt(this.item.getDmgType4());
        buf.writeFloat(this.item.getDmgMin5());
        buf.writeFloat(this.item.getDmgMax5());
        buf.writeInt(this.item.getDmgType5());
        // 232
        
        // resistances
        buf.writeInt(this.item.getArmor());
        buf.writeInt(this.item.getHolyRes());
        buf.writeInt(this.item.getFireRes());
        buf.writeInt(this.item.getNatureRes());
        buf.writeInt(this.item.getFrostRes());
        buf.writeInt(this.item.getShadowRes());
        buf.writeInt(this.item.getArcaneRes());
        // 260
        
        buf.writeInt(this.item.getDelay());
        buf.writeInt(this.item.getAmmoType());
        buf.writeFloat(this.item.getRangedModRange());
        // 272
        
        // spells
        buf.writeInt(this.item.getSpellid1());
        buf.writeInt(this.item.getSpelltrigger1());
        buf.writeInt(this.item.getSpellcharges1());
        buf.writeInt(this.item.getSpellcooldown1());
        buf.writeInt(this.item.getSpellcategory1());
        buf.writeInt(this.item.getSpellcategorycooldown1());
        buf.writeInt(this.item.getSpellid2());
        buf.writeInt(this.item.getSpelltrigger2());
        buf.writeInt(this.item.getSpellcharges2());
        buf.writeInt(this.item.getSpellcooldown2());
        buf.writeInt(this.item.getSpellcategory2());
        buf.writeInt(this.item.getSpellcategorycooldown2());
        buf.writeInt(this.item.getSpellid3());
        buf.writeInt(this.item.getSpelltrigger3());
        buf.writeInt(this.item.getSpellcharges3());
        buf.writeInt(this.item.getSpellcooldown3());
        buf.writeInt(this.item.getSpellcategory3());
        buf.writeInt(this.item.getSpellcategorycooldown3());
        buf.writeInt(this.item.getSpellid4());
        buf.writeInt(this.item.getSpelltrigger4());
        buf.writeInt(this.item.getSpellcharges4());
        buf.writeInt(this.item.getSpellcooldown4());
        buf.writeInt(this.item.getSpellcategory4());
        buf.writeInt(this.item.getSpellcategorycooldown4());
        buf.writeInt(this.item.getSpellid5());
        buf.writeInt(this.item.getSpelltrigger5());
        buf.writeInt(this.item.getSpellcharges5());
        buf.writeInt(this.item.getSpellcooldown5());
        buf.writeInt(this.item.getSpellcategory5());
        buf.writeInt(this.item.getSpellcategorycooldown5());     
        // 392
        
        buf.writeInt(this.item.getBonding());
        // 396
        ByteBufUtil.writeAscii(buf, this.item.getDescription());
        buf.writeByte(0); // end of string
        // 397 + ?                    
        buf.writeInt(this.item.getPagetext().getId());
        buf.writeInt(this.item.getLanguageid());
        buf.writeInt(this.item.getPagematerial());
        buf.writeInt(this.item.getStartquest());
        buf.writeInt(this.item.getLockid());
        buf.writeInt(this.item.getMaterial());
        buf.writeInt(this.item.getSheath());
        buf.writeInt(this.item.getRandomproperty());
        buf.writeInt(this.item.getBlock());
        buf.writeInt(this.item.getItemset());
        buf.writeInt(this.item.getMaxdurability());
        buf.writeInt(this.item.getArea());
        buf.writeInt(this.item.getMap());
        buf.writeInt(this.item.getBagfamily());                     
        // 453
    }
}
