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

import eu.jangos.realm.controller.auth.AccountService;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.network.opcode.result.CharCreateEnum;
import eu.jangos.realm.network.opcode.result.CharDeleteEnum;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Warkdev
 */
public class CharacterServiceTest {
    
    public CharacterServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleWarrior() {
        System.out.println("createChar");
        String name = "HumanMWar";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();
        byte skin = 0;
        byte face = 0;
        byte hairStyle = 0;
        byte hairColor = 0;
        byte facialHair = 0;
        Account account = new AccountService().getAccount("TEST");
        CharacterService instance = new CharacterService();
        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = instance.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);        
    }

    /**
     * Test of deleteChar method, of class CharacterService.
     */
    @Test
    public void testDeleteChar() {
        System.out.println("deleteChar");
        long id = 0L;
        Account account = null;
        CharacterService instance = new CharacterService();
        CharDeleteEnum expResult = null;
        CharDeleteEnum result = instance.deleteChar(id, account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginChar method, of class CharacterService.
     */
    @Test
    public void testLoginChar() {
        System.out.println("loginChar");
        long id = 0L;
        Account account = null;
        CharacterService instance = new CharacterService();
        boolean expResult = false;
        boolean result = instance.loginChar(id, account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCharactersForAccount method, of class CharacterService.
     */
    @Test
    public void testGetCharactersForAccount() {
        System.out.println("getCharactersForAccount");
        Account account = null;
        CharacterService instance = new CharacterService();
        List<Characters> expResult = null;
        List<Characters> result = instance.getCharactersForAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
