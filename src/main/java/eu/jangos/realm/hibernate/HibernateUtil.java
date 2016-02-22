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
package eu.jangos.realm.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Warkdev.
 * @version v1.0
 * @since 19-02-2016
 */
public class HibernateUtil {

    private static final SessionFactory authSessionFactory;
    private static final SessionFactory charSessionFactory;
    private static final SessionFactory worldSessionFactory;    

    private static final String AUTH_CFG = "/hibernate-auth.cfg.xml";
    private static final String WORLD_CFG = "/hibernate-world.cfg.xml";
    private static final String CHAR_CFG = "/hibernate-char.cfg.xml";
    
    static {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry authRegistry = new StandardServiceRegistryBuilder()
                .configure(AUTH_CFG) // configures settings from hibernate.cfg.xml
                .build();
        
        final StandardServiceRegistry worldRegistry = new StandardServiceRegistryBuilder()
                .configure(WORLD_CFG) // configures settings from hibernate.cfg.xml
                .build();
        
        final StandardServiceRegistry charRegistry = new StandardServiceRegistryBuilder()
                .configure(CHAR_CFG) // configures settings from hibernate.cfg.xml
                .build();
        
        try {
            authSessionFactory = new MetadataSources(authRegistry).buildMetadata().buildSessionFactory();
            charSessionFactory = new MetadataSources(charRegistry).buildMetadata().buildSessionFactory();            
            worldSessionFactory = new MetadataSources(worldRegistry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(authRegistry);
            StandardServiceRegistryBuilder.destroy(worldRegistry);
            StandardServiceRegistryBuilder.destroy(charRegistry);
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getAuthSession() {
        return authSessionFactory;
    }
    
    public static SessionFactory getCharSession() {
        return charSessionFactory;
    }
    
    public static SessionFactory getWorldSession() {
        return worldSessionFactory;
    }
}
