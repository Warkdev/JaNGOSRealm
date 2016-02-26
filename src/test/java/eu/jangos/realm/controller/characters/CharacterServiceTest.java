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
import eu.jangos.realm.controller.world.WorldParameterService;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.network.opcode.result.CharCreateEnum;
import eu.jangos.realm.network.opcode.result.CharDeleteEnum;
import eu.jangos.realm.utils.WorldParameterConstants;
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

    static AccountService as;
    static CharacterService cs;
    static WorldParameterService wps;
    static Account account;
    static byte skin;
    static byte face;
    static byte hairStyle;
    static byte hairColor;
    static byte facialHair;

    public CharacterServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        as = new AccountService();
        cs = new CharacterService();
        wps = new WorldParameterService();
        account = as.getAccount("CHARACTER");
        skin = 0;
        face = 0;
        hairStyle = 0;
        hairColor = 0;
        facialHair = 0;
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        for (Characters c : cs.getCharactersForAccount(account)) {
            cs.deleteChar(c.getGuid(), account, true);
        }
    }

    @Test
    public void testCreatingUnexistingParameters() {
        System.out.println("testCreatingUnexistingParameters");

        String name = "Human";
        byte raceID = -1;
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
        
        raceID = RaceEnum.HUMAN.getValue();
        classID = -1;
        genderID = GenderEnum.FEMALE.getValue();

        expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
        
        raceID = RaceEnum.HUMAN.getValue();
        classID = ProfessionsEnum.WARLOCK.getValue();
        genderID = -1;

        expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreatingCharactersOfEnemyFactionsPvP() {
        System.out.println("testCreatingCharactersOfEnemyFactionsPvP");
        
        String name = "Human";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
        
        name = "Orc";
        raceID = RaceEnum.ORC.getValue();
        classID = ProfessionsEnum.WARLOCK.getValue();
        genderID = GenderEnum.FEMALE.getValue();

        expResult = CharCreateEnum.CHAR_CREATE_PVP_TEAMS_VIOLATION;
        result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetNumberCharacters() {
        System.out.println("testGetNumberCharacters");

        testCreateHumanFemaleMage();
        testCreateHumanFemalePaladin();
        testCreateHumanFemalePriest();

        long count = cs.getNumberOfCharacters(account);

        assertEquals(3, count);
    }

    @Test
    public void testAccountOverflow() {
        System.out.println("testAccountOverflow");

        // First we create the max amount of accounts.
        for (int i = 0; i < Integer.parseInt(wps.getParameter(WorldParameterConstants.KEY_WORLD_MAX_CHARACTERS)); i++) {
            String name = "Human" + "abcdefghijklmnopqrstuvwxyz".toCharArray()[i];
            byte raceID = RaceEnum.HUMAN.getValue();
            byte classID = ProfessionsEnum.WARLOCK.getValue();
            byte genderID = GenderEnum.FEMALE.getValue();

            CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
            CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
            assertEquals(expResult, result);
        }

        // Then, we add an extra more, BOUM !!!
        String name = "Overflow";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_ACCOUNT_LIMIT;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteChar method, of class CharacterService.
     */
    @Test
    public void testDeleteCharPermanently() {
        System.out.println("deleteChar");

        CharDeleteEnum expResult = CharDeleteEnum.CHAR_DELETE_SUCCESS;

        for (Characters character : cs.getCharactersForAccount(account)) {
            CharDeleteEnum result = cs.deleteChar(character.getGuid(), account, true);
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of loginChar method, of class CharacterService.
     */
    @Test
    public void testLoginChar() {
        /**
         * System.out.println("loginChar"); long id = 0L; Account account =
         * null; CharacterService instance = new CharacterService(); boolean
         * expResult = false; boolean result = instance.loginChar(id, account);
         * assertEquals(expResult, result); // TODO review the generated test
         * code and remove the default call to fail. fail("The test case is a
         * prototype.");
         */
    }

    /**
     * Test of getCharactersForAccount method, of class CharacterService.
     */
    @Test
    public void testGetCharactersForAccount() {
        /**
         * System.out.println("getCharactersForAccount"); Account account =
         * null; CharacterService instance = new CharacterService();
         * List<Characters> expResult = null; List<Characters> result =
         * instance.getCharactersForAccount(account); assertEquals(expResult,
         * result); // TODO review the generated test code and remove the
         * default call to fail. fail("The test case is a prototype.");
         */
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleWarrior() {
        System.out.println("testCreateHumanMaleWarrior");
        String name = "HumanMWar";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemaleWarrior() {
        System.out.println("testCreateHumanFemaleWarrior");
        String name = "HumanFWar";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMalePaladin() {
        System.out.println("testCreateHumanMalePaladin");
        String name = "HumanMPal";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemalePaladin() {
        System.out.println("testCreateHumanFemalePaladin");
        String name = "HumanFPal";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleRogue() {
        System.out.println("testCreateHumanMaleRogue");
        String name = "HumanMRog";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemaleRogue() {
        System.out.println("testCreateHumanFemaleRogue");
        String name = "HumanFRog";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMalePriest() {
        System.out.println("testCreateHumanMalePriest");
        String name = "HumanMPri";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemalePriest() {
        System.out.println("testCreateHumanFemalePriest");
        String name = "HumanFPri";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleMage() {
        System.out.println("testCreateHumanMaleMage");
        String name = "HumanMMag";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemaleMage() {
        System.out.println("testCreateHumanFemaleMage");
        String name = "HumanFMag";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleWarlock() {
        System.out.println("testCreateHumanMaleWarlock");
        String name = "HumanMWarl";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemaleWarlock() {
        System.out.println("testCreateHumanFemaleWarlock");
        String name = "HumanFWarl";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleDruid() {
        System.out.println("testCreateHumanMaleDruid");
        String name = "HumanMDru";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleHunter() {
        System.out.println("testCreateHumanMaleHunter");
        String name = "HumanMHunt";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanMaleShaman() {
        System.out.println("testCreateHumanMaleShaman");
        String name = "HumanMSha";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFeMaleDruid() {
        System.out.println("testCreateHumanFeMaleDruid");
        String name = "HumanFDru";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemaleHunter() {
        System.out.println("testCreateHumanFemaleHunter");
        String name = "HumanFHunt";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateHumanFemaleShaman() {
        System.out.println("testCreateHumanFemaleShaman");
        String name = "HumanFSha";
        byte raceID = RaceEnum.HUMAN.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleWarrior() {
        System.out.println("testCreateDwarfMaleWarrior");
        String name = "DwarfMWar";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemaleWarrior() {
        System.out.println("testCreateDwarfFemaleWarrior");
        String name = "DwarfFWar";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMalePaladin() {
        System.out.println("testCreateDwarfMalePaladin");
        String name = "DwarfMPal";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemalePaladin() {
        System.out.println("testCreateDwarfFemalePaladin");
        String name = "DwarfFPal";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleRogue() {
        System.out.println("testCreateDwarfMaleRogue");
        String name = "DwarfMRog";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemaleRogue() {
        System.out.println("testCreateDwarfFemaleRogue");
        String name = "DwarfFRog";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMalePriest() {
        System.out.println("testCreateDwarfMalePriest");
        String name = "DwarfMPri";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemalePriest() {
        System.out.println("testCreateDwarfFemalePriest");
        String name = "DwarfFPri";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleMage() {
        System.out.println("testCreateDwarfMaleMage");
        String name = "DwarfMMag";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemaleMage() {
        System.out.println("testCreateDwarfFemaleMage");
        String name = "DwarfFMag";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleWarlock() {
        System.out.println("testCreateDwarfMaleWarlock");
        String name = "DwarfMWarl";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemaleWarlock() {
        System.out.println("testCreateDwarfFemaleWarlock");
        String name = "DwarfFWarl";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleDruid() {
        System.out.println("testCreateDwarfMaleDruid");
        String name = "DwarfMDru";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleHunter() {
        System.out.println("testCreateDwarfMaleHunter");
        String name = "DwarfMHunt";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfMaleShaman() {
        System.out.println("testCreateDwarfMaleShaman");
        String name = "DwarfMSha";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFeMaleDruid() {
        System.out.println("testCreateDwarfFeMaleDruid");
        String name = "DwarfFDru";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemaleHunter() {
        System.out.println("testCreateDwarfFemaleHunter");
        String name = "DwarfFHunt";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateDwarfFemaleShaman() {
        System.out.println("testCreateDwarfFemaleShaman");
        String name = "DwarfFSha";
        byte raceID = RaceEnum.DWARF.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleWarrior() {
        System.out.println("testCreateElfMaleWarrior");
        String name = "ElfMWar";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemaleWarrior() {
        System.out.println("testCreateElfFemaleWarrior");
        String name = "ElfFWar";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMalePaladin() {
        System.out.println("testCreateElfMalePaladin");
        String name = "ElfMPal";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemalePaladin() {
        System.out.println("testCreateElfFemalePaladin");
        String name = "ElfFPal";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleRogue() {
        System.out.println("testCreateElfMaleRogue");
        String name = "ElfMRog";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemaleRogue() {
        System.out.println("testCreateElfFemaleRogue");
        String name = "ElfFRog";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMalePriest() {
        System.out.println("testCreateElfMalePriest");
        String name = "ElfMPri";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemalePriest() {
        System.out.println("testCreateElfFemalePriest");
        String name = "ElfFPri";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleMage() {
        System.out.println("testCreateElfMaleMage");
        String name = "ElfMMag";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemaleMage() {
        System.out.println("testCreateElfFemaleMage");
        String name = "ElfFMag";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleWarlock() {
        System.out.println("testCreateElfMaleWarlock");
        String name = "ElfMWarl";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemaleWarlock() {
        System.out.println("testCreateElfFemaleWarlock");
        String name = "ElfFWarl";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleDruid() {
        System.out.println("testCreateElfMaleDruid");
        String name = "ElfMDru";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleHunter() {
        System.out.println("testCreateElfMaleHunter");
        String name = "ElfMHunt";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfMaleShaman() {
        System.out.println("testCreateElfMaleShaman");
        String name = "ElfMSha";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFeMaleDruid() {
        System.out.println("testCreateElfFeMaleDruid");
        String name = "ElfFDru";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemaleHunter() {
        System.out.println("testCreateElfFemaleHunter");
        String name = "ElfFHunt";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateElfFemaleShaman() {
        System.out.println("testCreateElfFemaleShaman");
        String name = "ElfFSha";
        byte raceID = RaceEnum.NIGHTELF.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleWarrior() {
        System.out.println("testCreateGnomeMaleWarrior");
        String name = "GnomeMWar";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemaleWarrior() {
        System.out.println("testCreateGnomeFemaleWarrior");
        String name = "GnomeFWar";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMalePaladin() {
        System.out.println("testCreateGnomeMalePaladin");
        String name = "GnomeMPal";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemalePaladin() {
        System.out.println("testCreateGnomeFemalePaladin");
        String name = "GnomeFPal";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleRogue() {
        System.out.println("testCreateGnomeMaleRogue");
        String name = "GnomeMRog";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemaleRogue() {
        System.out.println("testCreateGnomeFemaleRogue");
        String name = "GnomeFRog";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMalePriest() {
        System.out.println("testCreateGnomeMalePriest");
        String name = "GnomeMPri";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemalePriest() {
        System.out.println("testCreateGnomeFemalePriest");
        String name = "GnomeFPri";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleMage() {
        System.out.println("testCreateGnomeMaleMage");
        String name = "GnomeMMag";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemaleMage() {
        System.out.println("testCreateGnomeFemaleMage");
        String name = "GnomeFMag";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleWarlock() {
        System.out.println("testCreateGnomeMaleWarlock");
        String name = "GnomeMWarl";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemaleWarlock() {
        System.out.println("testCreateGnomeFemaleWarlock");
        String name = "GnomeFWarl";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleDruid() {
        System.out.println("testCreateGnomeMaleDruid");
        String name = "GnomeMDru";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleHunter() {
        System.out.println("testCreateGnomeMaleHunter");
        String name = "GnomeMHunt";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeMaleShaman() {
        System.out.println("testCreateGnomeMaleShaman");
        String name = "GnomeMSha";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFeMaleDruid() {
        System.out.println("testCreateGnomeFeMaleDruid");
        String name = "GnomeFDru";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemaleHunter() {
        System.out.println("testCreateGnomeFemaleHunter");
        String name = "GnomeFHunt";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateGnomeFemaleShaman() {
        System.out.println("testCreateGnomeFemaleShaman");
        String name = "GnomeFSha";
        byte raceID = RaceEnum.GNOME.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleWarrior() {
        System.out.println("testCreateOrcMaleWarrior");
        String name = "OrcMWar";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemaleWarrior() {
        System.out.println("testCreateOrcFemaleWarrior");
        String name = "OrcFWar";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMalePaladin() {
        System.out.println("testCreateOrcMalePaladin");
        String name = "OrcMPal";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemalePaladin() {
        System.out.println("testCreateOrcFemalePaladin");
        String name = "OrcFPal";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleRogue() {
        System.out.println("testCreateOrcMaleRogue");
        String name = "OrcMRog";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemaleRogue() {
        System.out.println("testCreateOrcFemaleRogue");
        String name = "OrcFRog";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMalePriest() {
        System.out.println("testCreateOrcMalePriest");
        String name = "OrcMPri";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemalePriest() {
        System.out.println("testCreateOrcFemalePriest");
        String name = "OrcFPri";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleMage() {
        System.out.println("testCreateOrcMaleMage");
        String name = "OrcMMag";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemaleMage() {
        System.out.println("testCreateOrcFemaleMage");
        String name = "OrcFMag";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleWarlock() {
        System.out.println("testCreateOrcMaleWarlock");
        String name = "OrcMWarl";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemaleWarlock() {
        System.out.println("testCreateOrcFemaleWarlock");
        String name = "OrcFWarl";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleDruid() {
        System.out.println("testCreateOrcMaleDruid");
        String name = "OrcMDru";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleHunter() {
        System.out.println("testCreateOrcMaleHunter");
        String name = "OrcMHunt";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcMaleShaman() {
        System.out.println("testCreateOrcMaleShaman");
        String name = "OrcMSha";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFeMaleDruid() {
        System.out.println("testCreateOrcFeMaleDruid");
        String name = "OrcFDru";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemaleHunter() {
        System.out.println("testCreateOrcFemaleHunter");
        String name = "OrcFHunt";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateOrcFemaleShaman() {
        System.out.println("testCreateOrcFemaleShaman");
        String name = "OrcFSha";
        byte raceID = RaceEnum.ORC.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleWarrior() {
        System.out.println("testCreateUndeadMaleWarrior");
        String name = "UndeadMWar";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemaleWarrior() {
        System.out.println("testCreateUndeadFemaleWarrior");
        String name = "UndeadFWar";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMalePaladin() {
        System.out.println("testCreateUndeadMalePaladin");
        String name = "UndeadMPal";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemalePaladin() {
        System.out.println("testCreateUndeadFemalePaladin");
        String name = "UndeadFPal";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleRogue() {
        System.out.println("testCreateUndeadMaleRogue");
        String name = "UndeadMRog";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemaleRogue() {
        System.out.println("testCreateUndeadFemaleRogue");
        String name = "UndeadFRog";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMalePriest() {
        System.out.println("testCreateUndeadMalePriest");
        String name = "UndeadMPri";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemalePriest() {
        System.out.println("testCreateUndeadFemalePriest");
        String name = "UndeadFPri";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleMage() {
        System.out.println("testCreateUndeadMaleMage");
        String name = "UndeadMMag";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemaleMage() {
        System.out.println("testCreateUndeadFemaleMage");
        String name = "UndeadFMag";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleWarlock() {
        System.out.println("testCreateUndeadMaleWarlock");
        String name = "UndeadMWarl";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemaleWarlock() {
        System.out.println("testCreateUndeadFemaleWarlock");
        String name = "UndeadFWarl";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleDruid() {
        System.out.println("testCreateUndeadMaleDruid");
        String name = "UndeadMDru";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleHunter() {
        System.out.println("testCreateUndeadMaleHunter");
        String name = "UndeadMHunt";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadMaleShaman() {
        System.out.println("testCreateUndeadMaleShaman");
        String name = "UndeadMSha";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFeMaleDruid() {
        System.out.println("testCreateUndeadFeMaleDruid");
        String name = "UndeadFDru";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemaleHunter() {
        System.out.println("testCreateUndeadFemaleHunter");
        String name = "UndeadFHunt";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateUndeadFemaleShaman() {
        System.out.println("testCreateUndeadFemaleShaman");
        String name = "UndeadFSha";
        byte raceID = RaceEnum.UNDEAD.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleWarrior() {
        System.out.println("testCreateTaurenMaleWarrior");
        String name = "TaurenMWar";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemaleWarrior() {
        System.out.println("testCreateTaurenFemaleWarrior");
        String name = "TaurenFWar";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMalePaladin() {
        System.out.println("testCreateTaurenMalePaladin");
        String name = "TaurenMPal";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemalePaladin() {
        System.out.println("testCreateTaurenFemalePaladin");
        String name = "TaurenFPal";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleRogue() {
        System.out.println("testCreateTaurenMaleRogue");
        String name = "TaurenMRog";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemaleRogue() {
        System.out.println("testCreateTaurenFemaleRogue");
        String name = "TaurenFRog";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMalePriest() {
        System.out.println("testCreateTaurenMalePriest");
        String name = "TaurenMPri";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemalePriest() {
        System.out.println("testCreateTaurenFemalePriest");
        String name = "TaurenFPri";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleMage() {
        System.out.println("testCreateTaurenMaleMage");
        String name = "TaurenMMag";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemaleMage() {
        System.out.println("testCreateTaurenFemaleMage");
        String name = "TaurenFMag";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleWarlock() {
        System.out.println("testCreateTaurenMaleWarlock");
        String name = "TaurenMWarl";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemaleWarlock() {
        System.out.println("testCreateTaurenFemaleWarlock");
        String name = "TaurenFWarl";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleDruid() {
        System.out.println("testCreateTaurenMaleDruid");
        String name = "TaurenMDru";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleHunter() {
        System.out.println("testCreateTaurenMaleHunter");
        String name = "TaurenMHunt";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenMaleShaman() {
        System.out.println("testCreateTaurenMaleShaman");
        String name = "TaurenMSha";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFeMaleDruid() {
        System.out.println("testCreateTaurenFeMaleDruid");
        String name = "TaurenFDru";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemaleHunter() {
        System.out.println("testCreateTaurenFemaleHunter");
        String name = "TaurenFHunt";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTaurenFemaleShaman() {
        System.out.println("testCreateTaurenFemaleShaman");
        String name = "TaurenFSha";
        byte raceID = RaceEnum.TAUREN.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleWarrior() {
        System.out.println("testCreateTrollMaleWarrior");
        String name = "TrollMWar";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemaleWarrior() {
        System.out.println("testCreateTrollFemaleWarrior");
        String name = "TrollFWar";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.WARRIOR.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMalePaladin() {
        System.out.println("testCreateTrollMalePaladin");
        String name = "TrollMPal";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemalePaladin() {
        System.out.println("testCreateTrollFemalePaladin");
        String name = "TrollFPal";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.PALADIN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleRogue() {
        System.out.println("testCreateTrollMaleRogue");
        String name = "TrollMRog";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemaleRogue() {
        System.out.println("testCreateTrollFemaleRogue");
        String name = "TrollFRog";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.ROGUE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMalePriest() {
        System.out.println("testCreateTrollMalePriest");
        String name = "TrollMPri";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemalePriest() {
        System.out.println("testCreateTrollFemalePriest");
        String name = "TrollFPri";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.PRIEST.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleMage() {
        System.out.println("testCreateTrollMaleMage");
        String name = "TrollMMag";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemaleMage() {
        System.out.println("testCreateTrollFemaleMage");
        String name = "TrollFMag";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.MAGE.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleWarlock() {
        System.out.println("testCreateTrollMaleWarlock");
        String name = "TrollMWarl";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemaleWarlock() {
        System.out.println("testCreateTrollFemaleWarlock");
        String name = "TrollFWarl";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.WARLOCK.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleDruid() {
        System.out.println("testCreateTrollMaleDruid");
        String name = "TrollMDru";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleHunter() {
        System.out.println("testCreateTrollMaleHunter");
        String name = "TrollMHunt";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollMaleShaman() {
        System.out.println("testCreateTrollMaleShaman");
        String name = "TrollMSha";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.MALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFeMaleDruid() {
        System.out.println("testCreateTrollFeMaleDruid");
        String name = "TrollFDru";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.DRUID.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_FAILED;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemaleHunter() {
        System.out.println("testCreateTrollFemaleHunter");
        String name = "TrollFHunt";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.HUNTER.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createChar method, of class CharacterService.
     */
    @Test
    public void testCreateTrollFemaleShaman() {
        System.out.println("testCreateTrollFemaleShaman");
        String name = "TrollFSha";
        byte raceID = RaceEnum.TROLL.getValue();
        byte classID = ProfessionsEnum.SHAMAN.getValue();
        byte genderID = GenderEnum.FEMALE.getValue();

        CharCreateEnum expResult = CharCreateEnum.CHAR_CREATE_SUCCESS;
        CharCreateEnum result = cs.createChar(name, raceID, classID, genderID, skin, face, hairStyle, hairColor, facialHair, account);
        assertEquals(expResult, result);
    }
}
