package eu.jangos.realm.controller.auth;

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
import eu.jangos.realm.model.auth.Bannedip;
import eu.jangos.realm.utils.Utils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BannedAccountService is an abstraction layer to perform activities on a BannedIP entity.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class BannedIPService {
    private static final Logger logger = LoggerFactory.getLogger(BannedIPService.class);                

    /**
     * This method checks whether an IP is banned or not in the database.
     * @param ip The IP to be verified.
     * @return true if the IP is banned, false otherwise. true if the IP is empty.
     */
    public boolean isIPBanned(String ip) {
        if(ip == null || ip.isEmpty()) {
            logger.error("IP parameter is empty, exiting. ban = true.");
            return true;
        }
                      
        if(!Utils.isValidIP4Address(ip)) {
            logger.error("A valid IPv4 address must be provided.");
            return true;
        }        
        
        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            return (session.createCriteria(Bannedip.class)
                    .add(Restrictions.and(
                            Restrictions.like("ip", ip),
                            Restrictions.eq("active", true)))
                    .uniqueResult() != null);
        } catch(HibernateException he) {
            return true;
        }                
    }
}
