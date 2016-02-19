package eu.jangos.realm;

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
import eu.jangos.realm.controller.RealmParameterService;
import eu.jangos.realm.network.decoder.RealmPacketDecoder;
import eu.jangos.realm.network.encoder.RealmPacketEncoder;
import eu.jangos.realm.network.handler.RealmAuthHandler;
import eu.jangos.realm.network.handler.RealmWorldHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RealmServer is the main class of the realm server for jE4W. This class is
 * responsible to startup the listener server.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class RealmServer {

    private static final Logger logger = LoggerFactory.getLogger(RealmServer.class);
    
    static private RealmParameterService rps = new RealmParameterService();     
    
    static final boolean SSL = false;
    static final int PORT = Integer.parseInt(rps.getParameter("realmPort"));    

    static InetAddress HOST;    
    static final String VERSION = rps.getParameter("realmVersion");

    /**
     * Main of the RealmServer program.
     *
     * @param args the command line arguments
     * @throws InterruptedException in case of interruption of the running
     * process.
     */
    public static void main(String[] args) throws InterruptedException {

        logger.info("Starting JaNGOS realm server version " + VERSION + ".");
        logger.info("JaNGOS is an opensource project, check-out : https://github.com/Warkdev/JaNGOSRealm !");

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new RealmPacketDecoder(), new RealmPacketEncoder(), new RealmAuthHandler(), new RealmWorldHandler());                            
                        }
                    });

            ChannelFuture f;

            // Start the server.   
            try {
                HOST = InetAddress.getByAddress(rps.getParameter("realmAddress").getBytes());                
                f = b.bind(HOST, PORT).sync();
                logger.info("JaNGOS realm server started listening on " + HOST.getHostAddress() + ":" + PORT);
            } catch (UnknownHostException ex) {
                f = b.bind(PORT);
                logger.info("JaNGOS realm server started listening on port " + PORT);
            }

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            logger.info("JaNGOS realm server shutting down.");
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
