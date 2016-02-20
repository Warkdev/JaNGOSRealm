package eu.jangos.realm.controller;

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

import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.world.Race;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
    
    /**
     * Return all races available in the database.
     * @return A List of race.
     */
    public List<Race> getAllRaces() {
        List<Race> listRaces = null;
        
        try (Session session = HibernateUtil.getWorldSession().openSession()) {
            listRaces = (List<Race>) session
                    .createCriteria(Race.class)
                    .list();
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
        try (Session session = HibernateUtil.getWorldSession().openSession()) {
            Race race = (Race) session.createCriteria(Race.class)
                    .add(Restrictions.like("id", id)).uniqueResult();
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
        try (Session session = HibernateUtil.getWorldSession().openSession()) {
            Race race = (Race) session.createCriteria(Race.class)
                    .add(Restrictions.like("value", value)).uniqueResult();
            return race;
        } catch (Exception e) {
            logger.debug("Race value " + value + " not found, returning null.");
            return null;   
        }
    }
}
