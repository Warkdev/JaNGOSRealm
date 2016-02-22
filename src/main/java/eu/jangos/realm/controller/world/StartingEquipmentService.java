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

import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.world.Professions;
import eu.jangos.realm.model.world.Gender;
import eu.jangos.realm.model.world.Item;
import eu.jangos.realm.model.world.Race;
import eu.jangos.realm.model.world.Startingequipment;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StartingEquipment Service manages the starting items list for a given race and class.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class StartingEquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(StartingEquipmentService.class);     
    
    /**
     * Return the list of items for a given race and a given class and a given gender.
     * @param race The race of the character.
     * @param profession The profession of the character.
     * @param gender The gender of the character.
     * @return The list of starting items for this combination.
     */
    public List<Startingequipment> getStartingEquipment(Race race, Professions profession, Gender gender){
        List<Startingequipment> listStartingEquipment = null;
        try (Session session = HibernateUtil.getWorldSession().openSession()){
            listStartingEquipment = (List<Startingequipment>) session.createCriteria(Startingequipment.class)
                    .setFetchMode("item", FetchMode.JOIN)
                    .setFetchMode("item.itemsubclass", FetchMode.JOIN)
                    .setFetchMode("item.itemclass", FetchMode.JOIN)
                    .add(Restrictions.and(
                            Restrictions.eq("race", race), 
                            Restrictions.eq("professions", profession), 
                            Restrictions.eq("gender", gender))).list();                    
        } catch (Exception e) {
            logger.debug("Exception raised while querying the database.");
        }
        
        return listStartingEquipment;
    }        
}
