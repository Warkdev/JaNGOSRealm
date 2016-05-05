package eu.jangos.realm.network.packet.server.query;

import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
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
 * SMSG_NAME_QUERY represents a message sent by the server as the result of a CMSG_NAME_QUERY packet.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_NAME_QUERY extends AbstractRealmServerPacket {

    /**
     * Represents the guid of the player for which the client sent a query.
     */
    private long guid;
    
    /**
     * Represents the name of the player.
     */
    private String name;
    
    /**
     * Represents  the realm name for cross-realm BG usage.
     */
    private String realm;
    
    /**
     * Represents the race of the player.
     */
    private RaceEnum race;
    
    /**
     * Represents the gender of the player.
     */
    private GenderEnum gender;
    
    /**
     * Represents the class of the player.
     */
    private ProfessionsEnum prof;       

    public SMSG_NAME_QUERY(Opcodes code) {
        super(code, 12);
    }   

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public RaceEnum getRace() {
        return race;
    }

    public void setRace(RaceEnum race) {
        this.race = race;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public ProfessionsEnum getProf() {
        return prof;
    }

    public void setProf(ProfessionsEnum prof) {
        this.prof = prof;
    }    
    
    public String toString() {
        String toString = "[SMSG_NAME_QUERY [ "
                + " name: " + this.name
                + ", realm: " + this.realm
                + ", race: " + this.race
                + ", gender: " + this.gender
                + ", class: " + this.prof
                + " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:
        // If player exists:
        // 2b - 2b - ?b - ?b - 4b - 4b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - Player name - Realm name - race - gender - class                
        
        buf.writeShort(this.size + this.name.length() + this.realm.length() + 2);
        buf.writeShort(this.code.getValue());
        ByteBufUtil.writeAscii(buf, this.name);
        buf.writeByte((byte) 0); // End of string
        ByteBufUtil.writeAscii(buf, this.realm);
        buf.writeByte((byte) 0); // End of string
        buf.writeInt(this.race.getValue());
        buf.writeInt(this.gender.getValue());
        buf.writeInt(this.prof.getValue());        
    }
}
