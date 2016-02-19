package eu.jangos.realm.network.handler;

import eu.jangos.realm.authstep.AuthStep;
import eu.jangos.realm.controller.CharacterService;
import eu.jangos.realm.controller.WorldService;
import eu.jangos.realm.controller.factory.WorldServiceFactory;
import static eu.jangos.realm.network.handler.RealmAuthHandler.ACCOUNT;
import static eu.jangos.realm.network.handler.RealmAuthHandler.AUTH;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import eu.jangos.realm.network.packet.client.CMSG_PING;
import eu.jangos.realm.network.packet.client.auth.CMSG_PLAYER_LOGIN;
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
 * RealmWorldHandler is responsible to handle all the business logic for auth
 * network packets. Managing requests and responses.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class RealmWorldHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RealmWorldHandler.class);

    private static WorldService worldService;
    private final CharacterService characterService;

    /**
     * Constructor of RealmServerHandler.
     */
    public RealmWorldHandler() {
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
                                ctx.pipeline().get(RealmAuthHandler.class).getAccountService().getAccount()
                        )
                );

                break;
            case CMSG_CHAR_DELETE:
                response = new SMSG_CHAR_DELETE(Opcodes.SMSG_CHAR_DELETE);

                ((SMSG_CHAR_DELETE) response).setResult(
                        characterService.deleteChar(
                                ((CMSG_CHAR_DELETE) request).getId(),
                                ctx.pipeline().get(RealmAuthHandler.class).getAccountService().getAccount()
                        )
                );

                break;

            case CMSG_PLAYER_LOGIN:
                if (characterService.loginChar(((CMSG_PLAYER_LOGIN) request).getId(), 
                        ctx.pipeline().get(RealmAuthHandler.class).getAccountService().getAccount())) {
                    SMSG_LOGIN_VERIFY_WORLD packet = new SMSG_LOGIN_VERIFY_WORLD();
                    
                    packet.setMap(characterService.getLoggedCharacter().getMap());
                    packet.setPosX(characterService.getLoggedCharacter().getPosx());
                    packet.setPosY(characterService.getLoggedCharacter().getPosy());
                    packet.setPosZ(characterService.getLoggedCharacter().getPosz());
                    packet.setOrientation(characterService.getLoggedCharacter().getOrientation());
                    
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
        if (ctx.pipeline().get(RealmAuthHandler.class).getAccountService().getAccount() != null) {
            worldService.removeSession(ctx.pipeline().get(RealmAuthHandler.class).getAccountService().getAccount().getId());
        }
    }
}
