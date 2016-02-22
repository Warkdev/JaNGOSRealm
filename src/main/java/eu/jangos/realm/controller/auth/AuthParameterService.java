package eu.jangos.realm.controller.auth;

/*
 * Copyright 2016 Warkdev.
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
import eu.jangos.realm.model.auth.Parameter1;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ParameterService is an abstraction layer providing access to Parameters stored in the database.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class AuthParameterService {
    private static final Logger logger = LoggerFactory.getLogger(AuthParameterService.class);
        
    /**
     * Returns the parameter corresponding to the asked key.
     *
     * @param key The key to retrieve from the database.
     * @return The parameter corresponding to the parameter key in a String
     * format or null if the key is empty/not found.
     */
    public String getParameter(String key) {
        if (key == null || key.isEmpty()) {
            logger.debug("Key parameter is empty, returning null.");
            return null;
        }

        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            Parameter1 parameter = (Parameter1) session.createCriteria(Parameter1.class).add(Restrictions.eq("param", key)).uniqueResult();

            if (parameter == null) {
                return null;
            }

            return parameter.getVal();
        } catch (HibernateException he) {
            return null;
        }
    }  
}
