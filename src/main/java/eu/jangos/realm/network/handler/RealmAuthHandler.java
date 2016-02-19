package eu.jangos.realm.network.handler;

import eu.jangos.realm.authstep.AuthStep;
import eu.jangos.realm.controller.AccountService;
import eu.jangos.realm.controller.CharacterService;
import eu.jangos.realm.controller.AuthParameterService;
import eu.jangos.realm.controller.WorldService;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.network.opcode.Opcodes;
import eu.jangos.realm.network.opcode.result.AuthEnum;
import eu.jangos.realm.network.packet.AbstractRealmClientPacket;
import eu.jangos.realm.network.packet.AbstractRealmServerPacket;
import eu.jangos.realm.network.packet.client.auth.CMSG_AUTH_SESSION;
import eu.jangos.realm.network.packet.server.auth.SMSG_ADDON_INFO;
import eu.jangos.realm.network.packet.server.auth.SMSG_AUTH_CHALLENGE;
import eu.jangos.realm.network.packet.server.auth.SMSG_AUTH_RESPONSE;
import eu.jangos.realm.utils.AuthUtils;
import eu.jangos.realm.utils.BigNumber;
import eu.jangos.realm.utils.GenericCrypt;
import eu.jangos.realm.utils.VanillaCrypt;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RealmServerHandler is responsible to handle all the business logic for auth
 * network packets. Managing requests and responses.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class RealmAuthHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RealmAuthHandler.class);

    public static final AttributeKey<GenericCrypt> CRYPT
            = AttributeKey.valueOf("CRYPT");

    public static final AttributeKey<AuthStep> AUTH
            = AttributeKey.valueOf("AUTH");

    public static final AttributeKey<Account> ACCOUNT
            = AttributeKey.valueOf("ACCOUNT");

    private static WorldService worldService;
    private final AccountService accountService;
    private static final AuthParameterService parameterService = new AuthParameterService();

    private CMSG_AUTH_SESSION cAuthSession;

    // Seed is used for auth challenge request.
    private final byte[] seed;

    /**
     * Constructor of RealmServerHandler.
     */
    public RealmAuthHandler() {
        super();
        this.seed = new SecureRandom().generateSeed(4);
        this.accountService = new AccountService();
        worldService = WorldServiceFactory.getInstance();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("A new client is attempting to connect, sending challenge");

        SMSG_AUTH_CHALLENGE response = new SMSG_AUTH_CHALLENGE(Opcodes.SMSG_AUTH_CHALLENGE);
        response.setSeed(seed);

        // Generating a new crypt.
        GenericCrypt crypt = new VanillaCrypt();
        ctx.channel().attr(CRYPT).set(crypt);

        ctx.channel().attr(AUTH).set(AuthStep.STEP_AUTHING);

        ctx.writeAndFlush(response);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        AbstractRealmClientPacket request = (AbstractRealmClientPacket) msg;

        // By default, response is no access for a login activity.
        AbstractRealmServerPacket response = new SMSG_AUTH_RESPONSE(Opcodes.SMSG_AUTH_RESPONSE);

        logger.info(msg.toString());

        switch (request.getOpcode()) {
            case CMSG_AUTH_SESSION:
                if (ctx.channel().attr(AUTH).get() != AuthStep.STEP_AUTHING) {
                    logger.error("Client is sending a CMSG_AUTH_SESSION packet again while being authenticating.");
                    ctx.close();
                    return;
                }

                this.cAuthSession = (CMSG_AUTH_SESSION) request;

                // Checking build number.
                if (this.cAuthSession.getBuild() < Integer.parseInt(parameterService.getParameter("minSupportedBuild")) || this.cAuthSession.getBuild() > Integer.parseInt(parameterService.getParameter("maxSupportedBuild"))) {
                    logger.debug("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Build is not supported.");
                    ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_VERSION_INVALID);
                    break;
                }

                // Checking account existence.
                if (!this.accountService.checkExistence(this.cAuthSession.getAccount().toUpperCase())) {
                    logger.debug("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Account does not exist.");
                    ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_UNKNOWN_ACCOUNT);
                    break;
                }

                // Checking if this account is the one logging into the authentication server.
                if (!this.accountService.getAccount().getLastIP().equals(((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress())) {
                    logger.debug("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : IP is not the same one than the one used to authenticate.");
                    ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_FAIL_NOACCESS);
                    break;
                }

                // Checking if account is locked.
                if (this.accountService.isLocked()) {
                    logger.debug("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Account is locked.");
                    ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_BANNED);
                    break;
                }

                // Checking if account is banned -- Includes IP & Account.
                if (this.accountService.isBanned(((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress())) {
                    logger.debug("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Account is banned.");
                    ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_BANNED);
                    break;
                }

                // At this step, we can start making the calculation.
                try {
                    BigNumber K = new BigNumber(this.accountService.getAccount().getSessionkey(), 16);

                    if (AuthUtils.checkClientDigest(this.cAuthSession.getAccount(), seed, this.cAuthSession.getSeed(), K, this.cAuthSession.getDigest())) {
                        // We are happy that client could login.
                        logger.info("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Account is logged in.");

                        worldService.addSession(this.accountService.getAccount().getId(), ctx);

                        // Initializing the crypt.
                        ctx.channel().attr(CRYPT).get().init(K.asByteArray());
                        ctx.channel().attr(AUTH).set(AuthStep.STEP_AUTHED);
                        ctx.channel().attr(ACCOUNT).set(accountService.getAccount());

                        ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_SUCCESS);

                        // We send add-on data as well.
                        SMSG_ADDON_INFO packet = new SMSG_ADDON_INFO();
                        packet.setListAddons(((CMSG_AUTH_SESSION) request).getListAddon());
                        ctx.write(packet);                        
                    } else {
                        // Well, the calculation went wrong.
                        logger.info("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Can't verify the hash.");
                        ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_FAIL_NOACCESS);
                        break;
                    }
                } catch (NoSuchAlgorithmException nsa) {
                    // Something went wrong, aborting connection.
                    logger.info("Context: " + ctx.name() + ", account: " + this.cAuthSession.getAccount() + " : Problem  to find an algorithm.");
                    ((SMSG_AUTH_RESPONSE) response).setResult(AuthEnum.AUTH_FAIL_FAIL_NOACCESS);
                    break;
                }

                break;
            default:
                logger.error("Packet received, opcode not handled: " + request.getOpcode());
                response = null;
                break;
        }

        if (response != null) {
            ctx.writeAndFlush(response);
        } else {
            // Let pass this to other handlers.
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (this.accountService.getAccount() != null) {
            worldService.removeSession(this.accountService.getAccount().getId());
        }
    }

    public AccountService getAccountService() {
        return this.accountService;
    }
}
