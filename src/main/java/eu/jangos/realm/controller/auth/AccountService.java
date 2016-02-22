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
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.utils.AuthParameterConstants;
import eu.jangos.realm.utils.AuthUtils;
import eu.jangos.realm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * AccountService is the controller for account management. It allows to manager back-end database transparently as well as ensuring business rules
 * such as failed login attempt. It handles the application logic.
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final BannedAccountService bas = new BannedAccountService();
    private final BannedIPService bis = new BannedIPService();
    private final LocaleService ls = new LocaleService();
    private final AuthParameterService aps = new AuthParameterService();

    /**
     * Returns the account corresponding to the given name. The name must
     * contain only alphanumeric values.
     *
     * @param name The name of the account to be found.
     * @return The account corresponding to the given name. Null if the account
     * if not found.
     */
    public Account getAccount(String name) {
        if (name == null || name.isEmpty()) {
            logger.error("The account name is null or empty.");
            return null;
        }

        if (!name.matches("[a-zA-Z0-9]+")) {
            logger.error("The account name must contain only alphanumeric values.");
            return null;
        }       

        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            Account account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.like("name", name))
                    .setFetchMode("realmAccounts", FetchMode.JOIN)
                    .uniqueResult();            
            return account;
        } catch (HibernateException he) {            
            logger.error("There was an error connecting to the database.");
            return null;
        }       
    }

    /**
     * It will check that a single account with a given named exists in the
     * dabase.
     *
     * @param name The name of the account used to login.
     * @return true if an account with the given name exists in the database,
     * false otherwise.
     */
    public boolean checkExistence(String name) {
        // Empty names are not allowed.
        if (name == null || name.isEmpty()) {
            logger.error("The account name is null or empty.");
            return false;
        }

        if (!name.matches("[a-zA-Z0-9]+")) {
            logger.error("The account name must contain only alphanumeric values.");
            return false;
        }
        
        try (Session session = HibernateUtil.getAuthSession().openSession()) {               
            return (session.createCriteria(Account.class).add(Restrictions.like("name", name)).uniqueResult() != null);                                   
        } catch (HibernateException he) {           
            logger.error("There was an error connecting to the database.");                        
            return false;
        } 
    }

    /**
     * Performs login update with the client information.
     *
     * @param account Account to login.
     * @param ip IP of the client who just logged in.
     * @param locale Locale of the client who just logged in.
     * @param session Hashed session key of the client who just logged in.
     * @return A boolean value indicating whether the login action has been
     * performed sucessfully.
     */
    public boolean login(Account account, String ip, String locale, String session) {
        if (account == null) {
            logger.error("Account trying to login is null.");
            return false;
        }

        if (!checkExistence(account.getName())) {
            logger.error("Account trying to login does not exist: " +account.getName());
            return false;
        }

        if (account.isLocked()) {
            logger.error("A locked account is attempting to login: " +account.getName());
            return false;
        }
        
        if (account.isOnline()) {
            logger.error("An account already online is attempting to login: "+account.getName());
            return false;
        }
        
        if (isBanned(account, ip)) {
            logger.error("A banned account is attempting to login: "+account.getName());
            return false;
        }
        
        if (ip == null || !Utils.isValidIP4Address(ip)) {
            logger.error("A valid IPv4 address must be provided.");
            return false;
        }

        if (this.ls.getLocaleForString(locale) == null) {
            logger.error("There was an error retrieving the client locale, please verify that you parameter database contains the key defaultLocale.");
            return false;
        }

        if (session == null || session.isEmpty()) {
            logger.error("The session key is null or empty.");
            return false;
        }

        account.setFailedattempt(0);
        account.setLastIp(ip);
        account.setLastlogin(new Date());
        account.setLocale(this.ls.getLocaleForString(locale));
        account.setSessionkey(session);
        account.setOnline(true);

        update(account);

        logger.info("User " + account.getName() + " just logged in successfully from ip " + ip);
        return true;
    }

    /**
     * Check whether the account or the client ip is banned or not.
     *
     * @param account Account to be checked.
     * @param ip IP of the client who is trying to log in.
     * @return true if the account or the ip is banned, false otherwise. It
     * returns false if there's no account loaded.
     */
    public boolean isBanned(Account account, String ip) {
        if (account == null) {
            logger.error("Account trying to login is null.");
            return true;
        }

        if (!checkExistence(account.getName())) {
            logger.error("Account trying to login does not exist.");
            return true;
        }

        if (ip == null || !Utils.isValidIP4Address(ip)) {
            logger.error("A valid IPv4 address must be provided.");
            return true;
        }

        return (this.bas.isAccountBanned(account) || this.bis.isIPBanned(ip));
    }

    /**
     * This method updates the failed attempt counter of the account. If the
     * number of attempts is higher than the maxFailedAttempt parameter, it
     * locks the account
     *
     * @param account The account for which the failed attempt must be updated.
     */
    public void updateFailedAttempt(Account account) {
        if (account == null) {
            logger.error("Account trying to login is null.");
            return;
        }

        if (!checkExistence(account.getName())) {
            logger.error("Account trying to login does not exist.");
            return;
        }

        if (account.isLocked()) {
            logger.error("A locked account is trying to update its failing attempt.");
            return;
        }
        
        int failed = account.getFailedattempt() + 1;

        if (Integer.parseInt(this.aps.getParameter(AuthParameterConstants.KEY_MAX_FAILED_ATTEMPT)) <= failed) {
            logger.error("MaxFailedAttempt reached for account " + account.getName() + ". Account is now locked.");
            account.setLocked(true);
        }

        account.setFailedattempt(failed);

        this.update(account);
        logger.error("Account " + account.getName() + " attempt counter increased to " + failed);
    }

    /**
     * This method updates the account information into the database.
     *
     * @param account The account to update in the dabatase.
     */
    public void update(Account account) {
        if (account == null) {
            logger.error("Account trying to login is null.");
            return;
        }

        if (!checkExistence(account.getName())) {
            logger.error("Account trying to login does not exist.");
            return;
        }

        try (Session session = HibernateUtil.getAuthSession().openSession()) {
            session.beginTransaction();
            session.merge(account);
            session.flush();
            session.getTransaction().commit();            
            logger.info("Account " + account.getName() + " updated.");            
        } catch (HibernateException he) {
            logger.error("There was an issue while updating account " + account.getName());
        }

    }     
}
