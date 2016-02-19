package eu.jangos.realm.controller;

/**
 * jE4W is a featured server emulator for World of Warcraft 1.12.x.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * World of Warcraft, and all World of Warcraft or Warcraft art, images, and
 * lore are copyrighted by Blizzard Entertainment, Inc.
 *
 * A lot of credits goes to MaNGOS project from which several ideas (but not
 * all) were included in this project.
 *
 * Copyright (C) 2015-2015 jE4W project Copyright (C) 2005-2014 MaNGOS project
 * <http://getmangos.eu>
 */
import eu.jangos.realm.enums.characters.ClassEnum;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.world.Professions;
import eu.jangos.realm.model.characters.Equipment;
import eu.jangos.realm.network.opcode.result.CharCreateEnum;
import eu.jangos.realm.utils.StringUtils;
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.model.world.Gender;
import eu.jangos.realm.model.world.Race;
import eu.jangos.realm.model.world.Startingequipment;
import eu.jangos.realm.network.opcode.result.CharDeleteEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Character service manages the characters in the database. It checks the
 * business rules.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class CharacterService {

    private static final Logger logger = LoggerFactory.getLogger(CharacterService.class);
    
    // TODO - Make this a realm parameter.
    private static final int START_LEVEL = 1;        
    
    private static final RaceService raceService = new RaceService();
    private static final ClassService classService = new ClassService();
    private static final GenderService genderService = new GenderService();
    private static final StartingItemService startingItemService = new StartingItemService();
    
    private Characters loggedCharacter;
    
    /**
     * Create a character according to the parameters given.
     * @param name The name of the character.
     * @param raceID The race of the character.
     * @param classID The class of the character.
     * @param genderID The gender of the character.
     * @param skin The skin of the character.
     * @param face The face of the character.
     * @param hairStyle The hair style of the character.
     * @param hairColor The hair color of the character.
     * @param facialHair The facial hair of the character.
     * @param account The account to which this character belongs.
     * @return CharCreateEnum corresponding to the result to be sent to the client.
     */
    public synchronized CharCreateEnum createChar(String name, int raceID, int classID, int genderID, byte skin, byte face, byte hairStyle, byte hairColor, byte facialHair, Account account)
    {        
        // TODO:
        // Check whether the character creation is disabled for the given faction via parameters.
        // Check whether the name is a reserved name.
        // Check whether the realm limit is reached.
        // Check whether the account limit is reached.
        // Check whether this realm type allows cross-faction characters.
        // Update character counter in DB.
        
        // Checking that the race and the class exists in our enum.
        if(!ClassEnum.exists(classID) || !RaceEnum.exists(raceID)){
            logger.debug("Class or Race doesn't exist, aborting character creation.");
            return CharCreateEnum.CHAR_CREATE_FAILED;
        }
        
        // Checking name.
        CharCreateEnum checkName = checkName(name);
        if(checkName != CharCreateEnum.CHAR_NAME_SUCCESS){
            logger.debug("Name of the character is invalid, aborting character creation.");
            return checkName;
        }                
        
        Characters character = new Characters();
        Race race = raceService.getRaceByValue(RaceEnum.convert(raceID));
        Professions charClass = classService.getClassByValue(ClassEnum.convert(classID));
        Gender gender = genderService.getGenderByValue(GenderEnum.convert(genderID));
        
        character.setName(Character.toUpperCase(name.charAt(0)) + name.substring(1));
        character.setFkRace(race);
        character.setFkClass(charClass);
        character.setFkGender(gender);
        character.setFkAccount(account.getId());
        character.setSkin(skin);
        character.setFace(face);
        character.setHairStyle(hairStyle);
        character.setHairColor(hairColor);
        character.setFacialHair(facialHair);
        character.setLevel(START_LEVEL);
        // Setting default position.
        character.setPosx(race.getPosx());
        character.setPosy(race.getPosy());
        character.setPosz(race.getPosz());
        character.setOrientation(race.getOrientation());
        character.setMap(race.getMap());
        character.setZone(race.getZone());         
        // Setting default homebind.
        character.setHomeX(race.getPosx());
        character.setHomeY(race.getPosy());
        character.setHomeZ(race.getPosz());
        character.setHomeOrientation(race.getOrientation());
        character.setHomeMap(race.getMap());
        character.setHomeZone(race.getZone());
        
        try{
            this.em.getTransaction().begin();
            this.em.persist(character);
            this.em.getTransaction().commit();
        } catch(Exception e) {
            logger.debug(e.getMessage());
            if(this.em.getTransaction().isActive())
                this.em.getTransaction().rollback();
            return CharCreateEnum.CHAR_CREATE_FAILED;
        }
        
        // Adding default equipment.
        List<Equipment> listEquipment = new ArrayList<>(); 
        
        for(Startingitem i : startingItemService.getStartingItems(race, charClass, gender))
        {
            Equipment equipment = new Equipment();
            equipment.setFkItem(i.getItems());
            equipment.setFkSlot(i.getItemslot());
            equipment.setFkChar(character);
            listEquipment.add(equipment);
        }
        
        character.setEquipmentCollection(listEquipment);
        
        try{
            this.em.getTransaction().begin();
            this.em.persist(character);
            this.em.getTransaction().commit();
        } catch(Exception e) {
            logger.debug(e.getMessage());
            this.em.getTransaction().rollback();
            return CharCreateEnum.CHAR_CREATE_FAILED;
        }
        
        return CharCreateEnum.CHAR_CREATE_SUCCESS;
    }    
    
    /**
     * Delete the character corresponding to the ID given in parameter.
     * @param id The id of the character to be deleted.
     * @param account the account to which this character must belong.
     * @return The result of the deletion.
     */
    public synchronized CharDeleteEnum deleteChar(long id, Account account)
    {
        try {
            Pchars character = (Pchars) this.em.createNamedQuery("Pchars.findById").setParameter("id", id).setParameter("fkAccount", account.getId()).getSingleResult();
            character.setDeleted(true);
            character.setDateDelete(new Date());
            this.em.getTransaction().begin();
            this.em.persist(character);
            this.em.getTransaction().commit();       
        } catch (Exception e) {
            if(this.em.getTransaction().isActive())
                this.em.getTransaction().rollback();
            logger.error("Character with ID "+id+" does not exist for the account "+account.getName()+", can't delete it.");
            return CharDeleteEnum.CHAR_DELETE_FAILED;
        }
        
        return CharDeleteEnum.CHAR_DELETE_SUCCESS;
    }
    
    /**
     * Login the player into the world.
     * @param id The ID of the character.
     * @param account The account to which this character must belong.
     * @return The result of the login action, true if the player has been logged in, false otherwise.
     */
    public boolean loginChar(long id, Account account)
    {
        // So far, we just check that this character exists.
        try {
            this.loggedCharacter = (Pchars) this.em.createNamedQuery("Pchars.findById").setParameter("id", id).setParameter("fkAccount", account.getId()).getSingleResult();                        
            logger.info("Character [ID: "+this.loggedCharacter.getId()+"] "+this.loggedCharacter.getName()+" is logged in.");
        } catch (Exception e) {
            if(this.em.getTransaction().isActive())
                this.em.getTransaction().rollback();
            logger.error("Character with ID "+id+" does not exist for the account "+account.getName()+", can't login.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Returns the list of characters for a given account.
     * @param account The account for which the list of characters must be retrieved.
     * @return The list of characters for this account or an empty list.
     */
    public List<Pchars> getCharactersForAccount(Account account)
    {
        List<Pchars> listChars = new ArrayList<>();
        
        if(account == null){
            logger.debug("Account parameter is empty, exiting.");
            return listChars;
        }
        
        try{
            this.em.getEntityManagerFactory().getCache().evict(Pchars.class);
            listChars = em.createNamedQuery("Pchars.findByFkAccount").setParameter("fkAccount", account.getId()).getResultList();
        } catch (NoResultException nre) {
            logger.error("No result associated to the account: "+account.getId());
            return listChars;
        }
        
        return listChars;
    }

    /**
     * Getter of the logged character.
     * @return The logged character.
     */
    public Pchars getLoggedCharacter() {
        return loggedCharacter;
    }

    /**
     * Setter of the logged character.
     * @param loggedCharacter The character to be logged.
     */
    public void setLoggedCharacter(Pchars loggedCharacter) {
        this.loggedCharacter = loggedCharacter;
    }    
    
    /**
     * Return whether the name is already in use in database.
     * @param name The name to be checked.
     * @return True if the name is already in use.
     */
    private boolean nameInUse(String name){
        if(name == null || name.isEmpty()){
            logger.debug("Name parameter is empty, exiting. exist = false.");
            return false;
        }
        
        try {            
            this.em.getEntityManagerFactory().getCache().evict(Pchars.class);
            em.createNamedQuery("Pchars.findByName").setParameter("name", name).getSingleResult();            
        } catch (NoResultException nre) {
            logger.error("The character " + name + " doesn't exist, exiting.");
            return false;
        }                
        
        logger.debug("The character " + name + " is found.");
        
        return true;
    }
    
    /**
     * Performs the various checks on the name given.
     * @param name The name to be checked.
     * @return CharCreateEnum corresponding to the result of the check.
     */
    private CharCreateEnum checkName(String name)
    {
        // TODO:        
        // CHAR_NAME_TOO_SHORT((short) 0x44),
        // CHAR_NAME_TOO_LONG((short) 0x45),           
        // CHAR_NAME_MIXED_LANGUAGES((short) 0x47),
        // CHAR_NAME_PROFANE((short) 0x48),
        // CHAR_NAME_RESERVED((short) 0x49),                
        
        if(name == null || name.isEmpty()){
            logger.debug("Name is empty, aborting character creation.");
            return CharCreateEnum.CHAR_NAME_NO_NAME;
        }        
        
        // Name must only be composed of letters.
        if(!StringUtils.isAlpha(name)){
            return CharCreateEnum.CHAR_NAME_INVALID_CHARACTERS;
        }
        
        // Check if name is in use.
        if(nameInUse(name)){
            return CharCreateEnum.CHAR_CREATE_NAME_IN_USE;
        }
        
        if(name.contains("'")){
            return CharCreateEnum.CHAR_NAME_INVALID_APOSTROPHE;
        }
        
        // Handles single and multiple spaces.
        if(name.contains(" ")){
            return CharCreateEnum.CHAR_NAME_INVALID_SPACE;
        }                
        
        return CharCreateEnum.CHAR_NAME_SUCCESS;
    }
}
