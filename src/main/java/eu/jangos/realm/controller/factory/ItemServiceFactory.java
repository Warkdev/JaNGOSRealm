package eu.jangos.realm.controller.factory;

import eu.jangos.realm.controller.world.ItemService;

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

/**
 * ItemService factory allows to have only one ItemService for the server.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class ItemServiceFactory {

    private static ItemService itemService;
    
    private ItemServiceFactory() {
        
    }

    public synchronized static ItemService getInstance(){
        if(itemService == null){
            itemService = new ItemService();
        }
        return itemService;
    }    
}
