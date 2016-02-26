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
package eu.jangos.realm.controller.auth;

import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.auth.Realm;
import eu.jangos.realm.model.auth.RealmAccount;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RealmAccountService is the class that handles the account infos for the realm characters.
 * @author Warkdev
 * @version 1.0
 */
public class RealmAccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(RealmAccountService.class);
    
    public RealmAccount getAccountInfo(Account account, Realm realm)
    {
        if(account == null)
        {
            logger.debug("The account object is empty, returning null.");
            return null;
        }
        
        try(Session session = HibernateUtil.getAuthSession().openSession())
        {
            return (RealmAccount) session.createCriteria(RealmAccount.class)
                    .add(Restrictions.and(
                            Restrictions.eq("id.fkRealm", realm.getId()), 
                            Restrictions.eq("id.fkAccount", account.getId())))
                    .uniqueResult();
        } catch (HibernateException he) {
            logger.debug("An exception occured while querying the database.");
            return null;
        }
    }
    
}
