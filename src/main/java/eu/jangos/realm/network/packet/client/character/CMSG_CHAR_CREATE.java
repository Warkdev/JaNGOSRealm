package eu.jangos.realm.network.packet.client.character;

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
 * CMSG_CHAR_CREATE represents a packet sent by the client whenever it tries to create a character.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_CHAR_CREATE extends AbstractRealmClientPacket {

    /**
     * Packet size.
     */
    private short size;    
    
    /**
     * Name of the character.
     */
    private String name;
    
    /**
     * The race of the character, indicated as a key.
     * (See DBC files).
     */
    private byte race;
    
    /**
     * The class of the character, indicated as a key.
     * (See DBC files).
     */
    private byte charClass;
    
    /**
     * The gender of the character, indicated as a key.
     */
    private byte gender;
    
    /**
     * The skin of the character, indicated as a key.
     */
    private byte skin;
    
    /**
     * The face of the character, indicated as a key.
     */
    private byte face;
    
    /**
     * The hairStyle of the character, indicated as a key.
     */
    private byte hairStyle;
    
    /**
     * The hairColor of the character, indicated as a key.
     */
    private byte hairColor;
    
    /**
     * The facial hair of the character, indicated as a key.
     */
    private byte facialHair;
    
    /**
     * Constructor with opcode.
     *
     * @param opcode
     * @param size
     */
    public CMSG_CHAR_CREATE(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getRace() {
        return race;
    }

    public void setRace(byte race) {
        this.race = race;
    }

    public byte getCharClass() {
        return charClass;
    }

    public void setCharClass(byte charClass) {
        this.charClass = charClass;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public byte getSkin() {
        return skin;
    }

    public void setSkin(byte skin) {
        this.skin = skin;
    }

    public byte getFace() {
        return face;
    }

    public void setFace(byte face) {
        this.face = face;
    }

    public byte getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(byte hairStyle) {
        this.hairStyle = hairStyle;
    }

    public byte getHairColor() {
        return hairColor;
    }

    public void setHairColor(byte hairColor) {
        this.hairColor = hairColor;
    }

    public byte getFacialHair() {
        return facialHair;
    }

    public void setFacialHair(byte facialHair) {
        this.facialHair = facialHair;
    }
            
    public String toString() {
        String toString = "[CMSG_CHAR_CREATE [ "
                + " name: " + this.name
                + ", race: " + this.race
                + ", charClass: "+this.charClass
                + " ]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {               
        if((buf.readableBytes() + 4) < this.size)
        {
            throw new Exception();
        }                                
        
        StringBuilder b = new StringBuilder();
        byte c;        
        while((c = buf.readByte()) != 0)
        {
            b.append((char) c);
        }                

        this.name = b.toString();
        this.race = buf.readByte();
        this.charClass = buf.readByte();
        this.gender = buf.readByte();
        this.skin = buf.readByte();
        this.face = buf.readByte();
        this.hairStyle = buf.readByte();
        this.hairColor = buf.readByte();
        this.facialHair = buf.readByte();        
    }

}
