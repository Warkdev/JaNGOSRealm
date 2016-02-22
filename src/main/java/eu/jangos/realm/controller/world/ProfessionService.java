package eu.jangos.realm.controller.world;

/*
 * Copyright 2016 Talendrys.
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
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.world.Professions;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProfessionService manages the classes in the database. It checks the business
 * rules.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class ProfessionService {

    private static final Logger logger = LoggerFactory.getLogger(ProfessionService.class);

    /**
     * Return all classes available in the database.
     * @return A List of classes.
     */
    public List<Professions> getAllClass() {
        List<Professions> listProfessions = null;
        
        try (Session session = HibernateUtil.getWorldSession().openSession()){
            listProfessions = (List<Professions>) session.createCriteria(Professions.class).list();
        } catch (Exception e) {
            logger.debug("No class found.");
        }
        
        return listProfessions;
    }
    
    /**
     * Returns the class which has the given id.
     *
     * @param id The id to be looked for.
     * @return The corresponding class.
     */
    public Professions getClassById(byte id) {
        try (Session session = HibernateUtil.getWorldSession().openSession()){
            Professions profession = (Professions) session.createCriteria(Professions.class).add(Restrictions.eq("id",id)).uniqueResult();
            return profession;
        } catch (Exception e) {
            logger.debug("Class ID " + id + " not found, returning null.");
            return null;
        }
    }

    /**
     * Returns the class which has the given game value.
     * @param value The ProfessionsEnum value (WARRIOR, PALADIN, WARLOCK, ...)
     * @return The corresponding entity object to the given value, null otherwise.
     */
    public Professions getClassByValue(ProfessionsEnum value) {
        try (Session session = HibernateUtil.getWorldSession().openSession()){
            Professions profession = (Professions) session.createCriteria(Professions.class).add(Restrictions.like("professions",value)).uniqueResult();
            return profession;
        } catch (HibernateException he) {
            logger.debug("Class value " + value + " not found, returning null.");
            return null;
        }
    }
}
