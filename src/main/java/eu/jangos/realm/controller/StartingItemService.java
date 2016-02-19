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

import eu.jangos.realm.model.realm.Cclass;
import eu.jangos.realm.model.realm.Gender;
import eu.jangos.realm.model.realm.Items;
import eu.jangos.realm.model.realm.Race;
import eu.jangos.realm.model.realm.Startingitem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StartingItem Service manages the starting items list for a given race and class.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class StartingItemService {

    private static final Logger logger = LoggerFactory.getLogger(StartingItemService.class);

    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WRealmPU");
    
    private EntityManager em = emf.createEntityManager();        
    
    /**
     * Return the list of items for a given race and a given class and a given gender.
     * @param race The race of the character.
     * @param cclass The class of the character.
     * @param gender The gender of the character.
     * @return The list of starting items for this combination.
     */
    public List<Startingitem> getStartingItems(Race race, Cclass cclass, Gender gender){
        List<Startingitem> listStartingItems = null;
        try{
            listStartingItems = this.em.createNamedQuery("Startingitem.findByRaceAndClassAndGender")
                    .setParameter("fkRace", race.getId())
                    .setParameter("fkClass", cclass.getId())
                    .setParameter("fkGender", gender.getId()).getResultList();
        } catch (Exception e) {
            logger.debug("Exception raised while querying the database.");
        }
        
        return listStartingItems;
    }        
}
