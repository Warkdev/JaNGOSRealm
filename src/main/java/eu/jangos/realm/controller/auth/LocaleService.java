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
import eu.jangos.realm.model.auth.Locale;
import eu.jangos.realm.utils.AuthParameterConstants;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LocaleService is an abstract implementation for interaction with Locale entity.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class LocaleService {     
    private static final Logger logger = LoggerFactory.getLogger(LocaleService.class);        
    
    private AuthParameterService ps = new AuthParameterService();
    
    /**
     * Returns the corresponding Locale entity for a given String (e.g. "enGB" will return the locale "English").
     * If the locale doesn't exist in database, the default locale will be returned.
     * @param locale The short name of the locale to retrieve from the database (e.g. "enGB" or "frFR").
     * @return The locale corresponding to the locale parameter or the default locale if not found.
     */
    public Locale getLocaleForString(String locale){
        if(locale == null || locale.isEmpty()){
            logger.debug("Locale string is empty.");
            return getDefaultLocale();
        }        
        
        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            Locale l = (Locale) session.createCriteria(Locale.class)
                    .add(Restrictions.like("localeString", locale))
                    .uniqueResult();                    
            logger.debug("Locale found, returning "+l.getLocale());
            return l;
        } catch (NoResultException nre) {
            logger.debug("Locale not found.");
            return getDefaultLocale();
        }
    }
    
    /**
     * Provides the default locale according to the parameter defaultLocale in database.
     * @return the default locale.
     */
    private Locale getDefaultLocale() {
        logger.debug("Locale not supported, providing default.");
        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            Locale l = (Locale) session.createCriteria(Locale.class)
                    .add(Restrictions.like("localeString", this.ps.getParameter(AuthParameterConstants.KEY_DEFAULT_LOCALE)))
                    .uniqueResult();                    
            logger.debug("Locale found, returning "+l.getLocale());
            return l;
        } catch (NoResultException nre) {
            logger.debug("Locale not found.");
        }    
        
        return null;
    }
}
