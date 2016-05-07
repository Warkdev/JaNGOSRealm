package eu.jangos.realm.network.packet.server.query;

import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
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
 * SMSG_GUILD_QUERY represents a message sent by the server as the result of a CMSG_GUILD_QUERY packet.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_GUILD_QUERY extends AbstractRealmServerPacket {
    
    private int id;
    
    private String name;
    
    private List<String> listRanks;
    
    private int emblemStyle;
    
    private int emblemColor;
    
    private int borderlStyle;
    
    private int borderColor;
    
    private int backgroundColor;       
    
    private static final int GUILD_RANKS_MAX_COUNT = 10;      

    public SMSG_GUILD_QUERY(Opcodes code) {
        super(code, 34);
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListRanks() {
        return listRanks;
    }

    public void setListRanks(List<String> listRanks) {
        this.listRanks = listRanks;
    }

    public int getEmblemStyle() {
        return emblemStyle;
    }

    public void setEmblemStyle(int emblemStyle) {
        this.emblemStyle = emblemStyle;
    }

    public int getEmblemColor() {
        return emblemColor;
    }

    public void setEmblemColor(int emblemColor) {
        this.emblemColor = emblemColor;
    }

    public int getBorderlStyle() {
        return borderlStyle;
    }

    public void setBorderlStyle(int borderlStyle) {
        this.borderlStyle = borderlStyle;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }    
    
    public String toString() {
        String toString = "[SMSG_GUILD_QUERY [ "
                + " name: " + this.name                
                + " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:        
        // 2b - 2b - 4b - ?b - [?b*10] - 4b - 4b - 4b - 4b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - Guild ID - Guild name - [x Ranks name] - emblem style
        // - emblem color - border style - border color - background color
        
        int strSize = 0;
        strSize += this.name.length();        
        for(String rankName : this.listRanks)
        {
            strSize += rankName.length();
        }        
        
        buf.writeShort(this.size + strSize);
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.id);
        ByteBufUtil.writeAscii(buf, this.name);
        buf.writeByte((byte) 0); // End of string
        
        // We add ranks name.
        for(int i=0;i<GUILD_RANKS_MAX_COUNT;i++)
        {
            if(i<this.listRanks.size())
                ByteBufUtil.writeAscii(buf, this.listRanks.get(i));                            
            buf.writeByte((byte) 0); // End of string
        }
        
        buf.writeInt(this.emblemStyle);
        buf.writeInt(this.emblemColor);
        buf.writeInt(this.borderlStyle);
        buf.writeInt(this.borderColor);
        buf.writeInt(this.backgroundColor);
    }
}
