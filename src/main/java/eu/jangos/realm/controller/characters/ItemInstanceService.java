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
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.model.characters.ItemInstance;
import eu.jangos.realm.model.characters.ItemStorageType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ItemStorageService is the business layer for ItemStorage class.
 *
 * @author Warkdev
 */
public class ItemInstanceService {

    private static final Logger logger = LoggerFactory.getLogger(ItemInstanceService.class);

    /**
     * Return the equipment of the character in parameter.
     *
     * @param character
     * @return The corresponding entity or null if not found.
     */
    public List<ItemInstance> getEquipment(Characters character) {
        logger.debug("getEquipment " + character.getName());

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            return (List<ItemInstance>) session.createCriteria(ItemInstance.class)
                    .add(Restrictions.and(
                                    Restrictions.eq("characters.guid", character.getGuid()),
                                    Restrictions.eq("itemStorageType.id", ItemStorageEnum.EQUIPPED.getValue())
                            ))
                    .addOrder(Order.asc("slot"))
                    .list();
        } catch (HibernateException he) {
            logger.debug("There was an error querying the database.");
            return null;
        }
    }

    /**
     * Return only the necessary fields of the equipment of the character in parameter for the CharEnum packet.
     *
     * @param character
     * @return A List of arrays of Object with the following structure: [slot,entry] where slot is the slot 
     * into which this item is located and entry is the ID of the item_template.
     */
    public List getEquipmentCharEnum(Characters character) {
        logger.debug("getEquipment " + character.getName());

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            ProjectionList proList = Projections.projectionList();
            proList.add(Projections.property("slot"), "slot");
            proList.add(Projections.property("fkObjectEntry"), "entry");

            return session.createCriteria(ItemInstance.class)
                    .setProjection(proList)
                    .add(Restrictions.and(
                                    Restrictions.eq("characters.guid", character.getGuid()),
                                    Restrictions.eq("itemStorageType.id", ItemStorageEnum.EQUIPPED.getValue())
                            ))
                    .addOrder(Order.asc("slot"))
                    .list();
        } catch (HibernateException he) {
            logger.debug("There was an error querying the database.");
            return null;
        }
    }
}
