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
package eu.jangos.realm.controller.characters;

import eu.jangos.realm.enums.items.ItemStorageEnum;
import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.characters.ItemStorageType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ItemStorageService is the business layer for ItemStorage class.
 * @author Warkdev
 */
public class ItemStorageService {
    
    private static final Logger logger = LoggerFactory.getLogger(ItemStorageService.class);     
    
    /**
     * Return the persisted object corresponding to the given enum.
     * @param id The id corresponding to the entity to be found.
     * @return The corresponding entity or null if not found.
     */
    public ItemStorageType getItemStorageByID(byte id)
    {
        logger.debug("getItemStorageByID "+id);
        
        try(Session session = HibernateUtil.getCharSession().openSession())
        {
            return (ItemStorageType) session.createCriteria(ItemStorageType.class)
                    .add(Restrictions.eq("id",id)).uniqueResult();
        } catch (HibernateException he) {
            logger.debug("There was an error querying the database.");
            return null;
        }
    }
    
    /**
     * Return the persisted object corresponding to the given enum.
     * @param type The Enum object corresponding to the entity to be found.
     * @return The corresponding entity or null if not found.
     */
    public ItemStorageType getItemStorageByType(ItemStorageEnum type)
    {
        logger.debug("getItemStorageByType "+type);
        
        try(Session session = HibernateUtil.getCharSession().openSession())
        {
            return (ItemStorageType) session.createCriteria(ItemStorageType.class)
                    .add(Restrictions.eq("type",type.getValue())).uniqueResult();
        } catch (HibernateException he) {
            logger.debug("There was an error querying the database.");
            return null;
        }
    }
}
