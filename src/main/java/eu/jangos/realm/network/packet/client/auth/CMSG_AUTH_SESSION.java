package eu.jangos.realm.network.packet.client.auth;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
import eu.jangos.realm.utils.BigNumber;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

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
 * CMSG_AUTH_SESSION represents a packet sent by the client after the server has
 * sent the challenge.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CMSG_AUTH_SESSION extends AbstractRealmClientPacket {

    /**
     * Packet size.
     */
    private short size;

    /**
     * Client seed.
     */
    private byte[] seed;

    /**
     * Client build.
     */
    private int build;

    /**
     * Unknown usage.
     */
    private int unknown;

    /**
     * Account name.
     */
    private String account;

    /**
     * The client calculation.
     */
    private byte[] digest;

    /**
     * List of addons.
     */
    private List<AddonInfo> listAddon;        

    /**
     * Constructor with opcode.
     *
     * @param opcode
     */
    public CMSG_AUTH_SESSION(Opcodes opcode, short size) {
        super(opcode);
        this.size = size;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public byte[] getSeed() {
        return seed;
    }

    public void setSeed(byte[] seed) {
        this.seed = seed;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    public int getUnknown() {
        return unknown;
    }

    public void setUnknown(int unknown) {
        this.unknown = unknown;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public byte[] getDigest() {
        return digest;
    }

    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    public List<AddonInfo> getListAddon() {
        return listAddon;
    }

    public void setListAddon(List<AddonInfo> listAddon) {
        this.listAddon = listAddon;
    }

    public String toString() {
        String toString = "[CMSG_AUTH_SESSION [Build: " + this.build
                + ", unknown: " + this.unknown
                + ", account: " + this.account
                + ", seed: " + new BigNumber(this.seed).toHexString()
                + ", digest: " + new BigNumber(this.digest).toHexString()
                + ", addon: [";
        for(AddonInfo info : this.listAddon)
        {
            toString += "Name: "+info.getName()
                    + ", CRC: "+info.getCRC()
                    + ", Enabled: "+info.getEnabled()
                    + ", Unk7: "+info.getUnk7()
                    + ", ";
        }
                
                toString += "]]]";

        return toString;
    }

    @Override
    public void decode(ByteBuf buf) throws Exception {
        if (buf.readableBytes() < this.size) {
            throw new Exception();
        }

        buf.readShort();
        this.build = buf.readInt();
        this.unknown = buf.readInt();

        StringBuilder b = new StringBuilder();

        byte c;

        while ((c = buf.readByte()) != 0) {
            b.append((char) c);
        }

        this.account = b.toString();
        this.seed = buf.readBytes(4).array();
        this.digest = buf.readBytes(20).array();

        int sizeAddons = buf.readInt();

        byte[] compressedAddons = buf.readBytes(buf.readableBytes()).array();

        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedAddons);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedAddons.length);

        final byte[] buffer = new byte[1024];
        while (!decompressor.finished()) {
            try {
                final int count = decompressor.inflate(buffer);
                bos.write(buffer, 0, count);
            } catch (final DataFormatException e) {
            }
        }
        try {
            bos.close();
        } catch (final IOException e) {
        }

        final byte[] decompressedAddons = bos.toByteArray();

        if (sizeAddons != decompressedAddons.length) {
            System.out.println("Something went wrong");
            return;
        }

        ByteBuf addonBuffer = Unpooled.wrappedBuffer(decompressedAddons).order(ByteOrder.LITTLE_ENDIAN);

        this.listAddon = new ArrayList<>();
        
        while (addonBuffer.isReadable()) {            
            String name;
            byte unk6;
            int crc, unk7;
            
            b = new StringBuilder();
            while ((c = addonBuffer.readByte()) != 0) {
                b.append((char) c);
            }

            name = b.toString();                            
            
            crc = addonBuffer.readInt();
            unk7 = addonBuffer.readInt();
            unk6 = addonBuffer.readByte();           
            
            AddonInfo info = new AddonInfo(name, unk6, crc, unk7);
            this.listAddon.add(info);
        }

    }

}
