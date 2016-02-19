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

import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.auth.Bannedaccount;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BannedAccountService is an abstraction layer to perform activities on a BannedAccount entity.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class BannedAccountService {
    private static final Logger logger = LoggerFactory.getLogger(BannedAccountService.class);
    
    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WAuthPU");;
    private EntityManager em = emf.createEntityManager();
    
    private Bannedaccount bannedAccount;

    /**
     * This method checks whether an account is banned or not into the database.
     * @param account The account to be checked.
     * @return true if the account is banned, false otherwise.
     */
    public boolean isAccountBanned(Account account) {
        if(account == null){
            logger.debug("Account parameter is null. ban = true.");
            return true;
        }         
        
        try{
            this.bannedAccount = (Bannedaccount) this.em.createNamedQuery("Bannedaccount.findActiveBan").setParameter("account", account).getSingleResult();            
        } catch (NoResultException nre) {            
            logger.debug("Account "+account.getName()+" is not banned.");
            return false;
        }
        
        logger.error("Account "+account.getName()+" tried to log in but is banned.");
        return true;            
    }
    
}
