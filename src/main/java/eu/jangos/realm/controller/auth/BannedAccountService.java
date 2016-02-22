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
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.auth.Bannedaccount;
import eu.jangos.realm.utils.Utils;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BannedAccountService is an abstraction layer to perform activities on a
 * BannedAccount entity.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public class BannedAccountService {

    private static final Logger logger = LoggerFactory.getLogger(BannedAccountService.class);
    
    /**
     * Ban the given account. If the account is already banned, it will increase its ban duration.
     *
     * @param account The account to ban..
     * @param banisher The account of the banisher.
     * @param reason The reason of the ban.
     * @param days The number of days for the ban. (0 means unlimited).
     */
    public void ban(Account account, Account banisher, String reason, int days) {
        if (account == null) {
            logger.error("A ban action was requested on an unexisting account.");
            throw new IllegalArgumentException("No account to ban.");
        }

        if (banisher == null) {
            logger.error("A ban action was requested by an unknown banisher.");
            throw new IllegalArgumentException("No banisher account.");
        }
        // If an account is already banned, the duration is renewed.

        if (reason == null || reason.isEmpty()) {
            logger.error("The ban reason is null or empty.");
            throw new IllegalArgumentException("You must provide a ban reason.");
        }

        if (days < 0) {
            logger.error("The ban duration is negative.");
            throw new IllegalArgumentException("The ban duration cannot be lower than 0.");
        }
        
        Bannedaccount ban = getActiveBan(account);
        
        // This account does not have any active ban.
        // Creating a new record.
        if(ban == null)
        {
            ban = new Bannedaccount();
            ban.setAccountByFkBannedaccount(account);            
            ban.setActive(true);
            ban.setBandate(new Date());            
        }
        
        // We set the default date to today.
        if(ban.getUnban() == null)
        {
            ban.setUnban(new Date());
        }
        
        if(days == 0)
        {
            ban.setUnban(null);
        } else {
            ban.setUnban(Utils.addDays(ban.getUnban(), days));
        }
        
        ban.setReason(reason);
        ban.setAccountByFkBannedby(account);
        
        update(ban);
    }

    /**
     * Unban the account given in parameter.
     * 
     * @param account The account to be unban.
     */
    public void unban(Account account) {
        if (account == null) {
            logger.error("A ban action was requested on an unexisting account.");
            throw new IllegalArgumentException("No account to ban.");
        }
        
        Bannedaccount ban = getActiveBan(account);
        
        ban.setActive(false);
        
        update(ban);
    }
    
    /**
     * This method checks whether an account is banned or not into the database.
     *
     * @param account The account to be checked.
     * @return true if the account is banned, false otherwise.
     */
    public boolean isAccountBanned(Account account) {
        if (account == null) {
            logger.debug("Account parameter is null. ban = true.");
            return true;
        }

        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            return (session.createCriteria(Bannedaccount.class)
                    .add(Restrictions.and(
                                    Restrictions.eq("accountByFkBannedaccount.id", account.getId()),
                                    Restrictions.eq("active", true)))
                    .uniqueResult() != null);
        } catch (HibernateException he) {
            logger.error("There was an error connecting to the database.");            
            return true;
        }
    }

    /**
     * This method will return the active ban record for the given account.
     * 
     * @param account The account for which the active ban must be returned.
     * @return 
     */
    public Bannedaccount getActiveBan(Account account) {
        if(account == null) {
            logger.debug("Account parameter is null. Returning null.");
            return null;
        }
        
        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            return (Bannedaccount) (session.createCriteria(Bannedaccount.class)
                    .add(Restrictions.and(
                                    Restrictions.eq("accountByFkBannedaccount.id", account.getId()),
                                    Restrictions.eq("active", true)))
                    .uniqueResult());
        } catch (HibernateException he) {
            logger.error("There was an error connecting to the database.");            
            return null;
        }
    }
    
    /**
     * This method updates the ban record information into the database.
     *
     * @param ban The ban record to update in the dabatase.
     */
    private void update(Bannedaccount ban) {
        if (ban == null) {
            logger.error("The ban record to update is null.");
            return;
        }        

        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            session.beginTransaction();
            session.merge(ban);
            session.flush();
            session.getTransaction().commit();
            logger.debug("The ban record for the account with id " + ban.getId() + " updated.");
        } catch (HibernateException he) {
            logger.error("There was an issue while updating the ban record for the account " + ban.getId());
        }
    }
}

