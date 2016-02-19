package eu.jangos.realm.controller;

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

import eu.jangos.realm.model.auth.Bannedip;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BannedAccountService is an abstraction layer to perform activities on a BannedIP entity.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class BannedIPService {
    private static final Logger logger = LoggerFactory.getLogger(BannedIPService.class);
    
    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WAuthPU");;
    private EntityManager em = emf.createEntityManager();
    
    private Bannedip bannedip;

    /**
     * This method checks whether an IP is banned or not in the database.
     * @param ip The IP to be verified.
     * @return true if the IP is banned, false otherwise. true if the IP is empty.
     */
    public boolean isIPBanned(String ip) {
        if(ip == null || ip.isEmpty()) {
            logger.debug("IP parameter is empty, exiting. ban = true.");
            return true;
        }
              
        try{
            this.bannedip = (Bannedip) this.em.createNamedQuery("Bannedip.findActiveBan").setParameter("id", ip).getSingleResult();            
        } catch(NoResultException nre) {
            logger.debug("IP "+ip+" is not banned.");
            return false;
        }        
        
        logger.error("Account using IP "+ip+" tried to log in but the IP is banned.");
        return true;
    }
}
