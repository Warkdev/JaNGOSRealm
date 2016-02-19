package eu.jangos.realm.controller;

import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
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
 * WorldService is used to manage the World running into this realm. It saves
 * current World state and load shared components.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class WorldService {

    private static final Logger logger = LoggerFactory.getLogger(WorldService.class);
    private static Map<Integer, ChannelHandlerContext> mapSessions;

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
    }
}
