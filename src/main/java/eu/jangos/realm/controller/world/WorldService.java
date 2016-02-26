package eu.jangos.realm.controller.world;

import eu.jangos.realm.controller.auth.AccountService;
import eu.jangos.realm.model.auth.Account;
import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * WorldService is used to manage the World running into this realm. It saves
 * current World state and load shared components.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class WorldService {

    private static final Logger logger = LoggerFactory.getLogger(WorldService.class);
    private static Map<Integer, ChannelHandlerContext> mapSessions;
    private static final AccountService accountService = new AccountService();

    public WorldService() {
        mapSessions = new HashMap<>();
    }
    
    /**
     * Add a new session to this realm. This method also disconnects other users with the same account name.
     * @param account The ID of the account to be added to this realm.
     * @param ctx The actual TCP context for the new client.
     */
    public synchronized void addSession(int account, ChannelHandlerContext ctx)
    {
        if(mapSessions.containsKey(account)){
            // Actually, we should kick that user and authorize the new one.
            mapSessions.get(account).close();
            logger.error("User account "+account+" is already logged in, kicking him.");
        }
                    
        // Adding that session.
        mapSessions.put(account, ctx);
        logger.debug("User account "+account+" added to the sessions map.");
        
        // TODO: Update the session counters + population.
    }
    
    /**
     * Remove the session for the account with the ID account from the mapSessions. 
     * @param account The ID of the account to be removed.
     */
    public synchronized void removeSession(int account)
    {
        logger.debug("User account "+account+" is being removed from the sessions map.");
        mapSessions.remove(account);
        Account acc = accountService.getAccount(account);
        if(acc != null)
        {
            acc.setOnline(false);
            accountService.update(acc);
        }
    }
}
