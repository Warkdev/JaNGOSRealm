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
