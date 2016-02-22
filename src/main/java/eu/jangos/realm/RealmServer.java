package eu.jangos.realm;

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
import eu.jangos.realm.controller.auth.RealmService;
import eu.jangos.realm.controller.world.WorldParameterService;
import eu.jangos.realm.model.auth.Realm;
import eu.jangos.realm.network.decoder.RealmPacketDecoder;
import eu.jangos.realm.network.encoder.RealmPacketEncoder;
import eu.jangos.realm.network.handler.RealmAuthHandler;
import eu.jangos.realm.network.handler.RealmWorldHandler;
import eu.jangos.realm.utils.WorldParameterConstants;
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
import io.netty.handler.timeout.ReadTimeoutHandler;
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
    
    static private WorldParameterService wps = new WorldParameterService();
    static private RealmService rs = new RealmService();
    
    static final boolean SSL = false;
    static final Realm realm = rs.getRealmByName(wps.getParameter(WorldParameterConstants.KEY_WORLD_NAME));
    static final int PORT = realm.getPort();        
    
    static InetAddress HOST;    
    static final String VERSION = wps.getParameter(WorldParameterConstants.KEY_WORLD_VERSION);
    static final int TIMEOUT = Integer.parseInt(wps.getParameter(WorldParameterConstants.KEY_WORLD_TIMEOUT));

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

        realm.setOffline(false);
        rs.save(realm);
        
        logger.info("Realm configuration: ");
        logger.info("Name: "+realm.getName());
        logger.info("Address: "+realm.getAddress()+":"+realm.getPort());
        logger.info("Type: "+realm.getRealmtype().getType());
        logger.info("Timezone: "+realm.getRealmtimezone().getName());
        logger.info("Population: "+realm.getPopulation());
        logger.info("Players: "+realm.getCountPlayers()+"/"+realm.getMaxPlayers());                
        
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
                            p.addLast(new RealmPacketDecoder(), new RealmPacketEncoder(), new RealmAuthHandler(), new ReadTimeoutHandler(TIMEOUT), new RealmWorldHandler());                            
                        }
                    });

            ChannelFuture f;

            // Start the server.   
            try {
                HOST = InetAddress.getByAddress(realm.getAddress().getBytes());                
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
            
            // Indicating that this realm is offline.
            realm.setOffline(true);
            rs.save(realm);
            
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();                        
        }
    }
}
