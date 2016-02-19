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

import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.model.realm.Race;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RaceService manages the races in the database. It checks the
 * business rules.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class RaceService {

    private static final Logger logger = LoggerFactory.getLogger(RaceService.class);

    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WRealmPU");
    
    private EntityManager em = emf.createEntityManager();        
    
    /**
     * Return all races available in the database.
     * @return A List of race.
     */
    public List<Race> getAllRaces() {
        List<Race> listRaces = null;
        
        try {
            listRaces = (List<Race>) this.em.createNamedQuery("Race.findAll").getResultList();
        } catch (Exception e) {
            logger.debug("No race found.");
        }
        
        return listRaces;
    }
    
    /**
     * Returns the race which has the given id.
     * @param id The id to be looked for.
     * @return The corresponding race.
     */
    public Race getRaceById(Integer id) {
        try{
            Race race = (Race) this.em.createNamedQuery("Race.findById").setParameter("id", id).getSingleResult();
            return race;
        } catch (Exception e) {
            logger.debug("Race ID " + id + " not found, returning null.");
            return null;   
        }                        
    }
    
    /**
     * Returns the race which has the given value.
     * @param value The RaceEnum value corresponding to a game Race (HUMAN, ORC, ...)
     * @return The corresponding race entity object which has this RaceEnum value.
     */
    public Race getRaceByValue(RaceEnum value) {
        try{
            Race race = (Race) this.em.createNamedQuery("Race.findByValue").setParameter("value", value).getSingleResult();
            return race;
        } catch (Exception e) {
            logger.debug("Race value " + value + " not found, returning null.");
            return null;   
        } 
    }
}
