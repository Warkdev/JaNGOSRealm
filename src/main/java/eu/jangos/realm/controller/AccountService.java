package eu.jangos.realm.controller;

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

import eu.jangos.realm.model.auth.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

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
    private final AuthParameterService ps = new AuthParameterService();
             
}
