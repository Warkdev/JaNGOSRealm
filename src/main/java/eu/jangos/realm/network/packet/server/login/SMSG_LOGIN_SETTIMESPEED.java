package eu.jangos.realm.network.packet.server.login;

import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
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
 * SMSG_LOGIN_SETTIMESPEED represents a message sent by the server to define the
 * world timespeed.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class SMSG_LOGIN_SETTIMESPEED extends AbstractRealmServerPacket {

    /**
     * The world actual time.
     */
    private int time;

    /**
     * The current world time speed.
     */
    private static final float WORLD_TIME_SPEED = (float) 0.01666667;

    public SMSG_LOGIN_SETTIMESPEED(Opcodes code) {
        super(code, 8);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String toString() {
        String toString = "[SMSG_LOGIN_SETTIMESPEED [ "
                + " time: " + this.time
                + ", time speed: " + WORLD_TIME_SPEED
                + " ]]";
        return toString;
    }

    @Override
    public void encode(ByteBuf buf) throws Exception {
        // Packet structure:
        // 2b - 2b - 4b - 4b
        // Size (Little Endian) - Opcode (Big Endian) - current time - world time speed
        
        buf.writeShort(this.size);
        buf.writeShort(this.code.getValue());
        buf.writeInt(this.time);       
        buf.writeFloat(WORLD_TIME_SPEED);        
    }
}
