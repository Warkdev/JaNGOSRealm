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

import eu.jangos.realm.model.realm.Parameters;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RealmParameterService is an abstraction layer providing access to Parameters stored in the realm database.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class RealmParameterService {
    private static final Logger logger = LoggerFactory.getLogger(RealmParameterService.class);
    
    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WRealmPU");
    private static final EntityManager em = emf.createEntityManager();   
    
    /**
     * Returns the parameter corresponding to the asked key.
     * @param key The key to retrieve from the database.
     * @return The parameter corresponding to the parameter key in a String format or null if the key is empty/not found.
     */
    public String getParameter(String key) {
        if(key == null || key.isEmpty()){
            logger.debug("Key parameter is empty, returning null.");
            return null;
        }                
        
        try{            
            String value = ((Parameters) em.createNamedQuery("Parameters.findByParam").setParameter("param", key).getSingleResult()).getVal();        
            logger.debug("Key parameter "+key+" found, value: "+value);
            return value;
        } catch (NoResultException nre) {
            logger.error("Key parameter "+key+" not found. Check database !");
            return null;
        }
    }    
}
