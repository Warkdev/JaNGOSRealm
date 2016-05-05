package eu.jangos.realm.network.packet.client.query;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
import io.netty.buffer.ByteBuf;
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
 * CMSG_WHO represents a packet sent by the client when it wants to retrieve who
 * information.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_WHO extends AbstractRealmClientPacket {

    private int levelMin;

    private int levelMax;

    private String playerName;

    private String guildName;

    private int raceMask;

    private int classMask;

    private int zonesCount;
    private List<Integer> listZones;

    private int strCount;
    private List<String> listStrings;

    private static final int MAX_ZONES = 10;
    private static final int MAX_STRINGS = 4;

    /**
     * Packet size.
     */
    private short size;

    /**
     * Character ID.
     */
    private long id;

    /**
     * Constructor with opcode.
     *
     * @param opcode
     */
    public CMSG_WHO(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
        this.listStrings = new ArrayList<>();
        this.listZones = new ArrayList<>();
    }

    public int getLevelMin() {
        return levelMin;
    }

    public void setLevelMin(int levelMin) {
        this.levelMin = levelMin;
    }

    public int getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(int levelMax) {
        this.levelMax = levelMax;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public int getRaceMask() {
        return raceMask;
    }

    public void setRaceMask(int raceMask) {
        this.raceMask = raceMask;
    }

    public int getClassMask() {
        return classMask;
    }

    public void setClassMask(int classMask) {
        this.classMask = classMask;
    }

    public int getZonesCount() {
        return zonesCount;
    }

    public void setZonesCount(int zonesCount) {
        this.zonesCount = zonesCount;
    }

    public List<Integer> getListZones() {
        return listZones;
    }

    public void setListZones(List<Integer> listZones) {
        this.listZones = listZones;
    }

    public int getStrCount() {
        return strCount;
    }

    public void setStrCount(int strCount) {
        this.strCount = strCount;
    }

    public List<String> getListStrings() {
        return listStrings;
    }

    public void setListStrings(List<String> listStrings) {
        this.listStrings = listStrings;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        String toString = "[CMSG_WHO ["
                + " level min: " + this.levelMin
                + ", level max: " + this.levelMax
                + ", player name: " + this.playerName
                + ", guild name: " + this.guildName
                + ", race mask: " + this.raceMask
                + ", class mask: " + this.classMask
                + ", zones count: " + this.zonesCount
                + ", string count: " + this.strCount
                + "]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {
        if ((buf.readableBytes() + 4) < this.size) {
            throw new Exception();
        }

        this.levelMin = buf.readInt();
        this.levelMax = buf.readInt();
        this.raceMask = buf.readInt();
        this.classMask = buf.readInt();
        this.zonesCount = buf.readInt();

        for (int i = 0; i < this.zonesCount; i++) {
            this.listZones.add(buf.readInt());
        }

        this.strCount = buf.readInt();

        StringBuilder b;
        byte c;
        for (int i = 0; i < this.strCount; i++) {
            b = new StringBuilder();
            while ((c = buf.readByte()) != 0) {
                b.append((char) c);
            }

            this.listStrings.add(b.toString());
        }
        
    }

}
