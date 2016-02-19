package eu.jangos.realm.network.packet;

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

import eu.jangos.realm.network.opcode.Opcodes;
import io.netty.buffer.ByteBuf;

/**
 * AbstractrealmServerPacket is the parent of all packets sent by the server.
 * 
 * @author Warkdev
 * @version v0.1 BETA.
 */
public abstract class AbstractRealmServerPacket {
    protected Opcodes code;
    protected int size;
    
    public AbstractRealmServerPacket(final Opcodes code, final int size){
        this.code = code;
        this.size = size;
    }

    public Opcodes getCode() {
        return code;
    }

    public void setCode(Opcodes code) {
        this.code = code;
    }        

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }        
    
    public abstract void encode(ByteBuf buf) throws Exception;
}
