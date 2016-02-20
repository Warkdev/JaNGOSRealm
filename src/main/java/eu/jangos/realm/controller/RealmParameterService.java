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

//import eu.jangos.realm.model.world.Parameters;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RealmParameterService is an abstraction layer providing access to Parameters stored in the realm database.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class RealmParameterService {
    //private static final Logger logger = LoggerFactory.getLogger(RealmParameterService.class);
            
    /**
     * Returns the parameter corresponding to the asked key.
     * @param key The key to retrieve from the database.
     * @return The parameter corresponding to the parameter key in a String format or null if the key is empty/not found.
     */
   /** public String getParameter(String key) {
        if(key == null || key.isEmpty()){
            logger.debug("Key parameter is empty, returning null.");
            return null;
        }                
        
        try{            
            String value = ((Parameters) em.createNamedQuery("Parameters.findByParam").setParameter("param", key).getSingleResult()).getVal();        
            logger.debug("Key parameter "+key+" found, value: "+value);
            return value;
        } catch (NoResultException nre) {
            logger.error("Key parameter "+key+" not found. Check database !");
            return null;
        }
    }    */
}
