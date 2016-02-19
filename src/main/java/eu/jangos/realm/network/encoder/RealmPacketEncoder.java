package eu.jangos.realm.network.encoder;

import static eu.jangos.realm.network.handler.RealmAuthHandler.CRYPT;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import eu.jangos.realm.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * RealmPacket encoder is responsible to encode the actual business packets into
 * byte packets.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class RealmPacketEncoder extends MessageToByteEncoder<AbstractRealmServerPacket> {

    private static final Logger logger = LoggerFactory.getLogger(RealmPacketEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractRealmServerPacket msg, ByteBuf out) throws Exception {
        logger.debug("Context: " + ctx.name() + ", packet: " + msg.toString());        
        msg.encode(out);       
        
        logger.debug("Packet content: \n"+StringUtils.toPacketString(ByteBufUtil.hexDump(out).toUpperCase(), msg.getSize() ,msg.getCode()));
        
        // Reversing header.                              
        reverseHeader(ctx, out);
    }

    private void reverseHeader(ChannelHandlerContext ctx, ByteBuf out){
        // Retaining the actual index.
        int index = out.writerIndex();
        out.resetReaderIndex();

        // Reading the header.
        byte[] header = out.readBytes(4).array(); 

        // Resetting the reader index to send it.
        out.resetReaderIndex();
        
        // Swapping the header.
        byte temp = header[2];
        header[2] = header[3];
        header[3] = temp;      
        
        // Encrypting the header (if applicable).
        header = ctx.channel().attr(CRYPT).get().encrypt(header);                
        
        // Reset the writer index.
        out.resetWriterIndex();

        // Write the header.
        out.writeBytes(header);
        
        // Reset the writer index to the default one.
        out.writerIndex(index);           
    }
}
