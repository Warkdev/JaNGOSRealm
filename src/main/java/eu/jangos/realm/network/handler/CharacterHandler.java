package eu.jangos.realm.network.handler;

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

import eu.jangos.realm.authstep.AuthStep;
import eu.jangos.realm.controller.characters.CharacterService;
import eu.jangos.realm.controller.world.WorldService;
import eu.jangos.realm.controller.factory.WorldServiceFactory;
import static eu.jangos.realm.network.handler.RealmAuthHandler.ACCOUNT;
import static eu.jangos.realm.network.handler.RealmAuthHandler.AUTH;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import eu.jangos.realm.network.packet.client.CMSG_PING;
import eu.jangos.realm.network.packet.client.login.CMSG_PLAYER_LOGIN;
import eu.jangos.realm.network.packet.client.character.CMSG_CHAR_CREATE;
import eu.jangos.realm.network.packet.client.character.CMSG_CHAR_DELETE;
import eu.jangos.realm.network.packet.server.character.SMSG_CHAR_CREATE;
import eu.jangos.realm.network.packet.server.character.SMSG_CHAR_DELETE;
import eu.jangos.realm.network.packet.server.character.SMSG_CHAR_ENUM;
import eu.jangos.realm.network.packet.server.SMSG_PING;
import eu.jangos.realm.network.packet.server.auth.SMSG_ACCOUNT_DATA_TIMES;
import eu.jangos.realm.network.packet.server.auth.SMSG_LOGIN_VERIFY_WORLD;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CharacterHandler is responsible to handle all the business logic for auth
 network packets. Managing requests and responses.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CharacterHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CharacterHandler.class);

    private static WorldService worldService;
    private final CharacterService characterService;

    /**
     * Constructor of RealmServerHandler.
     */
    public CharacterHandler() {
        super();
        worldService = WorldServiceFactory.getInstance();
        characterService = new CharacterService();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Let's refuse any packed for non-authed clients.
        if (ctx.channel().attr(AUTH).get() != AuthStep.STEP_AUTHED) {
            return;
        }

        AbstractRealmClientPacket request = (AbstractRealmClientPacket) msg;

        AbstractRealmServerPacket response = null;

        logger.info(msg.toString());

        switch (request.getOpcode()) {
            case CMSG_PING:
                response = new SMSG_PING(Opcodes.SMSG_PING);

                int ping = ((CMSG_PING) request).getPing();

                ((SMSG_PING) response).setPing(ping);

                break;
            case CMSG_CHAR_ENUM:
                response = new SMSG_CHAR_ENUM(Opcodes.SMSG_CHAR_ENUM);

                // Adding character list.
                ((SMSG_CHAR_ENUM) response).addCharacters(characterService.getCharactersForAccount(ctx.channel().attr(ACCOUNT).get()));

                break;
            case CMSG_CHAR_CREATE:
                response = new SMSG_CHAR_CREATE(Opcodes.SMSG_CHAR_CREATE);

                ((SMSG_CHAR_CREATE) response).setResult(
                        characterService.createChar(
                                ((CMSG_CHAR_CREATE) request).getName(),
                                ((CMSG_CHAR_CREATE) request).getRace(),
                                ((CMSG_CHAR_CREATE) request).getCharClass(),
                                ((CMSG_CHAR_CREATE) request).getGender(),
                                ((CMSG_CHAR_CREATE) request).getSkin(),
                                ((CMSG_CHAR_CREATE) request).getFace(),
                                ((CMSG_CHAR_CREATE) request).getHairStyle(),
                                ((CMSG_CHAR_CREATE) request).getHairColor(),
                                ((CMSG_CHAR_CREATE) request).getFacialHair(),
                                ctx.pipeline().get(RealmAuthHandler.class).getAccount()
                        )
                );

                break;
            case CMSG_CHAR_DELETE:
                response = new SMSG_CHAR_DELETE(Opcodes.SMSG_CHAR_DELETE);

                ((SMSG_CHAR_DELETE) response).setResult(
                        characterService.deleteChar(
                                ((CMSG_CHAR_DELETE) request).getId(),
                                ctx.pipeline().get(RealmAuthHandler.class).getAccount(),
                                false
                        )
                );

                break;

            case CMSG_PLAYER_LOGIN:
                if (characterService.loginChar(((CMSG_PLAYER_LOGIN) request).getId(), 
                        ctx.pipeline().get(RealmAuthHandler.class).getAccount())) {
                    SMSG_LOGIN_VERIFY_WORLD packet = new SMSG_LOGIN_VERIFY_WORLD();
                    
                    /**packet.setMap(characterService.getLoggedCharacter().getFkDbcMap());
                    packet.setPosX(characterService.getLoggedCharacter().getPositionX());
                    packet.setPosY(characterService.getLoggedCharacter().getPositionY());
                    packet.setPosZ(characterService.getLoggedCharacter().getPositionZ());
                    packet.setOrientation(characterService.getLoggedCharacter().getOrientation());*/
                    
                    ctx.write(packet);
                    
                    SMSG_ACCOUNT_DATA_TIMES data = new SMSG_ACCOUNT_DATA_TIMES();
                    
                    ctx.write(data);
                    
                    
                } else {
                    // Kick unknown client.                    
                    ctx.close();
                }

                break;

            default:
                logger.error("Packet received, opcode not handled: " + request.getOpcode());
                break;
        }

        if(response != null){            
            ctx.writeAndFlush(response);
        } else {
            // Let pass this to other handlers.
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.pipeline().get(RealmAuthHandler.class).getAccount() != null) {
            worldService.removeSession(ctx.pipeline().get(RealmAuthHandler.class).getAccount().getId());
        }
    }
}
