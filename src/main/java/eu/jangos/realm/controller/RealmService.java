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

import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.auth.Realm;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RealmService is an abstraction layer to interact with Realm entities.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class RealmService {
    private static final Logger logger = LoggerFactory.getLogger(RealmService.class);
            
    /**
     * Provides all realms stored in the database.
     * @return A List of Realms corresponding to all realms stored in the database.
     */
    public List<Realm> getAllRealms(){
        logger.debug("Returning all realms");
        try(Session session = HibernateUtil.getAuthSession().openSession())
        {
            return session.createCriteria(Realm.class).list();
        } catch (HibernateException he) {
            logger.debug("Issue while connecting to the database.");            
        }
        return null;
    }
}
