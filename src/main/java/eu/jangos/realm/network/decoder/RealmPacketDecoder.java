package eu.jangos.realm.network.decoder;

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
import eu.jangos.realm.authstep.AuthStep;
import static eu.jangos.realm.network.handler.RealmAuthHandler.AUTH;
import static eu.jangos.realm.network.handler.RealmAuthHandler.CRYPT;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
import eu.jangos.realm.network.packet.client.CMSG_PING;
import eu.jangos.realm.network.packet.client.auth.CMSG_AUTH_SESSION;
import eu.jangos.realm.network.packet.client.auth.CMSG_PLAYER_LOGIN;
import eu.jangos.realm.network.packet.client.character.CMSG_CHAR_CREATE;
import eu.jangos.realm.network.packet.client.character.CMSG_CHAR_DELETE;
import eu.jangos.realm.network.packet.client.character.CMSG_CHAR_ENUM;
import eu.jangos.realm.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.ByteOrder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AuthPacketDecoder is an extension of the ByteMessageDecoder of Netty. It is
 * in charge of translation incoming byte network packets into WoW
 * understandable packet manageable by the authentication server.
 *
 * The decoder currently supports the following WoW packets: -
 * CMD_AUTH_LOGON_CHALLENGE - CMD_AUTH_LOGON_PROOF - CMD_REALM_LIST
 *
 * @author Warkdev
 * @version v0.1 BETA.
 * @see AuthClientCmd for defined opcode.
 */
public class RealmPacketDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(RealmPacketDecoder.class);
    private static final int HEADER_LENGTH = 6;
    private short opcode = 0;
    private short size = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        logger.debug("Packet received: " + in.readableBytes());

        // We should at least get the header.
        if (in.readableBytes() < HEADER_LENGTH) {
            logger.debug("Packet received but less than header size.");
            return;
        }

        ByteBuf msg = in.order(ByteOrder.LITTLE_ENDIAN);

        // We should decrypt the header only once per packet.
        if (opcode == 0) {
            byte[] header = new byte[HEADER_LENGTH];
            int readBytes = (ctx.channel().attr(AUTH).get() == AuthStep.STEP_AUTHED ? HEADER_LENGTH : 4);

            for (int i = 0; i < readBytes; i++) {
                header[i] = msg.readByte();
            }

            header = ctx.channel().attr(CRYPT).get().decrypt(header);

            size = (short) ((header[0] << 8 | header[1]) & 0xFF);
            opcode = (short) ((header[3] << 8 | header[2] & 0xFF));

            logger.debug("Opcode received: " + opcode + ", with size: " + size + " (readable bytes: " + in.readableBytes() + ") ");
        }

        if ((in.readableBytes() + 4) < size) {
            logger.debug("Packet size is higher than the available bytes. (" + in.readableBytes() + ", " + size + ")");
            return;
        }

        final Opcodes code = Opcodes.convert(opcode);

        if (code == null) {
            return;
        }

        AbstractRealmClientPacket packet = null;

        switch (code) {
            case CMSG_PING:
                packet = new CMSG_PING(code, size);
                break;
            case CMSG_AUTH_SESSION:
                packet = new CMSG_AUTH_SESSION(code, (short) 0);
                break;
            case CMSG_CHAR_ENUM:
                packet = new CMSG_CHAR_ENUM(code, size);
                break;
            case CMSG_CHAR_CREATE:
                packet = new CMSG_CHAR_CREATE(code, size);
                break;
            case CMSG_CHAR_DELETE:
                packet = new CMSG_CHAR_DELETE(code, size);
                break;
            case CMSG_PLAYER_LOGIN:
                packet = new CMSG_PLAYER_LOGIN(code, size);
                break;
            default:
                logger.debug("Context: " + ctx.name() + "Packet received, opcode not supported: " + code);
                msg.clear();
                ctx.close();
                break;
        }

        if (packet != null) {
            try {
                logger.debug("Context: " + ctx.name() + "Packet received, opcode: " + code);
                logger.debug("Packet content: \n"+StringUtils.toPacketString(ByteBufUtil.hexDump(msg).toUpperCase(), size, code));
                packet.decode(msg);
                opcode = 0;
                size = 0;
            } catch (Exception e) {
                return;
            }
            out.add(packet);
            msg.clear();
        }
    }

}
