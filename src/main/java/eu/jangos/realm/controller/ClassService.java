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
import eu.jangos.realm.enums.characters.ClassEnum;
import eu.jangos.realm.model.realm.Cclass;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassService manages the classes in the database. It checks the business
 * rules.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class ClassService {

    private static final Logger logger = LoggerFactory.getLogger(ClassService.class);

    private static final EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jE4WRealmPU");

    private EntityManager em = emf.createEntityManager();

    /**
     * Return all classes available in the database.
     * @return A List of classes.
     */
    public List<Cclass> getAllClass() {
        List<Cclass> listClass = null;
        
        try {
            listClass = (List<Cclass>) this.em.createNamedQuery("Cclass.findAll").getResultList();
        } catch (Exception e) {
            logger.debug("No class found.");
        }
        
        return listClass;
    }
    
    /**
     * Returns the class which has the given id.
     *
     * @param id The id to be looked for.
     * @return The corresponding class.
     */
    public Cclass getClassById(Integer id) {
        try {
            Cclass charClass = (Cclass) this.em.createNamedQuery("Cclass.findById").setParameter("id", id).getSingleResult();
            return charClass;
        } catch (Exception e) {
            logger.debug("Class ID " + id + " not found, returning null.");
            return null;
        }
    }

    /**
     * Returns the class which has the given game value.
     * @param value The ClassEnum value (WARRIOR, PALADIN, WARLOCK, ...)
     * @return The corresponding entity object to the given value, null otherwise.
     */
    public Cclass getClassByValue(ClassEnum value) {
        try {
            Cclass charClass = (Cclass) this.em.createNamedQuery("Cclass.findByValue").setParameter("value", value).getSingleResult();
            return charClass;
        } catch (Exception e) {
            logger.debug("Class value " + value + " not found, returning null.");
            return null;
        }
    }
}
