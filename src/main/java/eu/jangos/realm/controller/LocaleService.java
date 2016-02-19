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

import eu.jangos.realm.model.auth.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LocaleService is an abstract implementation for interaction with Locale entity.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class LocaleService {     
    private static final Logger logger = LoggerFactory.getLogger(LocaleService.class);
    
    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WAuthPU");;
    private EntityManager em = emf.createEntityManager();        
    
    private AuthParameterService ps = new AuthParameterService();
    
    /**
     * Returns the corresponding Locale entity for a given String (e.g. "enGB" will return the locale "English").
     * If the locale doesn't exist in database, the default locale will be returned.
     * @param locale The short name of the locale to retrieve from the database (e.g. "enGB" or "frFR").
     * @return The locale corresponding to the locale parameter or the default locale if not found.
     */
    public Locale getLocaleForString(String locale){
        if(locale == null || locale.isEmpty()){
            logger.debug("Locale string is empty.");
            return getDefaultLocale();
        }        
        
        try{
            Locale l = (Locale) this.em.createNamedQuery("Locale.findByLocaleString").setParameter("localeString", locale).getSingleResult();
            logger.debug("Locale found, returning "+l.getLocale());
            return l;
        } catch (NoResultException nre) {
            logger.debug("Locale not found.");
            return getDefaultLocale();
        }
    }
    
    /**
     * Provides the default locale according to the parameter defaultLocale in database.
     * @return the default locale.
     */
    private Locale getDefaultLocale() {
        logger.debug("Locale not supported, providing default.");
        Locale l = (Locale) this.em.createNamedQuery("Locale.findByLocaleString").setParameter("localeString", this.ps.getParameter("defaultLocale")).getSingleResult();
        return l;
    }
}
