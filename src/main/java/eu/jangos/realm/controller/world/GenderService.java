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
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.world.Gender;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GenderService manages the genders in the database. It checks the business
 * rules.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class GenderService {

    private static final Logger logger = LoggerFactory.getLogger(GenderService.class);   

    /**
     * Returns all genders available in database.
     *
     * @return a List of genders.
     */
    public List<Gender> getAllGenders() {
        List<Gender> listGenders = null;

        try  (Session session = HibernateUtil.getWorldSession().openSession()) {
            listGenders = (List<Gender>) session.createCriteria(Gender.class).list();
        } catch (Exception e) {
            logger.debug("No gender found.");
        }
        return listGenders;
    }

    /**
     * Returns the gender which has the given id.
     *
     * @param id The id to be looked for.
     * @return The corresponding gender.
     */
    public Gender getGenderById(byte id) {
        
        try  (Session session = HibernateUtil.getWorldSession().openSession()) {
            Gender gender = (Gender) session.createCriteria(Gender.class)
                    .add(Restrictions.eq("id", id)).uniqueResult();
            return gender;
        } catch (Exception e) {
            System.out.println(e);
            logger.debug("Gender ID " + id + " not found, returning null.");
            return null;
        }
    }
    
    /**
     * Returns  the gender which has the given value (coming from the game).
     * @param value The GenderEnum value (MALE, FEMALE, NONE)
     * @return The entity object Gender corresponding to the value, null otherwise.
     */
    public Gender getGenderByValue(GenderEnum value) {
        try  (Session session = HibernateUtil.getWorldSession().openSession()) {
            Gender gender = (Gender) session.createCriteria(Gender.class)
                    .add(Restrictions.eq("gender", value.getValue())).uniqueResult();
            return gender;
        } catch (HibernateException he) {            
            logger.debug("Gender Value " + value + " not found, returning null.");
            return null;
        }
    }
}
