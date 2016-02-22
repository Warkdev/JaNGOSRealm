package eu.jangos.realm.controller.characters;

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
import eu.jangos.realm.controller.world.ProfessionService;
import eu.jangos.realm.controller.world.GenderService;
import eu.jangos.realm.controller.world.RaceService;
import eu.jangos.realm.controller.world.StartingEquipmentService;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.enums.items.InventoryTypeEnum;
import eu.jangos.realm.enums.items.ItemClassEnum;
import eu.jangos.realm.enums.items.ItemStorageEnum;
import eu.jangos.realm.enums.items.SlotEnum;
import eu.jangos.realm.enums.object.ObjectTypeEnum;
import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.world.Professions;
import eu.jangos.realm.network.opcode.result.CharCreateEnum;
import eu.jangos.realm.utils.StringUtils;
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.model.characters.ItemInstance;
import eu.jangos.realm.model.world.Gender;
import eu.jangos.realm.model.world.Item;
import eu.jangos.realm.model.world.Race;
import eu.jangos.realm.model.world.Startingequipment;
import eu.jangos.realm.network.opcode.result.CharDeleteEnum;
import eu.jangos.realm.utils.ItemUtils;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
    private static final byte START_LEVEL = 1;

    private static final RaceService raceService = new RaceService();
    private static final ProfessionService professionService = new ProfessionService();
    private static final GenderService genderService = new GenderService();
    private static final StartingEquipmentService startingEquipmentService = new StartingEquipmentService();
    private static final ItemStorageService iss = new ItemStorageService();

    private Characters loggedCharacter;

    /**
     * Create a character according to the parameters given.
     *
     * @param name The name of the character.
     * @param raceID The race of the character.
     * @param professionID The profession of the character.
     * @param genderID The gender of the character.
     * @param skin The skin of the character.
     * @param face The face of the character.
     * @param hairStyle The hair style of the character.
     * @param hairColor The hair color of the character.
     * @param facialHair The facial hair of the character.
     * @param account The account to which this character belongs.
     * @return CharCreateEnum corresponding to the result to be sent to the
     * client.
     */
    public synchronized CharCreateEnum createChar(String name, byte raceID, byte professionID, byte genderID, byte skin, byte face, byte hairStyle, byte hairColor, byte facialHair, Account account) {
        // TODO:        
        // Check whether the name is a reserved name.
        // Check whether the realm limit is reached.
        // Check whether the account limit is reached.
        // Check whether this realm type allows cross-faction characters.
        // Check whether account exists.
        // Check name length (max 12 characters).
        // Update character counter in DB.

        // Checking that the race and the class exists in our enum.
        if (!ProfessionsEnum.exists(professionID) || !RaceEnum.exists(raceID) || !GenderEnum.exists(genderID)) {
            logger.debug("Class, Race or Gender doesn't exist, aborting character creation.");
            return CharCreateEnum.CHAR_CREATE_FAILED;
        }

        // Checking name.
        CharCreateEnum checkName = checkName(name);
        if (checkName != CharCreateEnum.CHAR_NAME_SUCCESS) {
            logger.debug("Name of the character is invalid, aborting character creation.");
            return checkName;
        }

        Race race = raceService.getRaceById(raceID);
        Professions profession = professionService.getClassById(professionID);
        Gender gender = genderService.getGenderById(genderID);

        Characters character = new Characters();

        character.setName(Character.toUpperCase(name.charAt(0)) + name.substring(1));
        character.setRace(raceID);
        character.setFkDbcClass(professionID);
        character.setGender(genderID);
        character.setFkAccount(account.getId());
        character.setSkin(skin);
        character.setFace(face);
        character.setHairstyle(hairStyle);
        character.setHaircolor(hairColor);
        character.setFacialhair(facialHair);
        character.setLevel(START_LEVEL);
        // Setting default position.
        character.setPositionX(race.getPositionX());
        character.setPositionY(race.getPositionY());
        character.setPositionZ(race.getPositionZ());
        character.setOrientation(race.getOrientation());
        character.setFkDbcMap(race.getFkDbcMap());
        character.setFkDbcZone(race.getFkDbcZone());
        // Setting default homebind.
        character.setHomeX(race.getPositionX());
        character.setHomeY(race.getPositionY());
        character.setHomeZ(race.getPositionZ());
        character.setHomeOrientation(race.getOrientation());
        character.setHomeFkDbcMap(race.getFkDbcMap());
        character.setHomeFkDbcZone(race.getFkDbcZone());

        // Adding default equipment.
        Set<ItemInstance> listEquipment = new HashSet<>();

        byte bagslot = 0;
        
        for (Startingequipment i : startingEquipmentService.getStartingEquipment(race, profession, gender)) {
            Item item = i.getItem();
            ItemInstance ii = new ItemInstance();

            ii.setObjectType((byte) ObjectTypeEnum.ITEM.getValue());
            ii.setFkObjectEntry(item.getEntry());
            ii.setObjectScaleX(1.0f);
            ii.setObjectPadding(1);
            ii.setDurability((int) item.getMaxdurability());
            switch (ItemClassEnum.convert(item.getItemsubclass().getItemclass().getId())) {
                case ARMOR:
                case WEAPON:
                    ii.setItemStorageType(iss.getItemStorageByID(ItemStorageEnum.EQUIPPED.getValue()));
                    SlotEnum slot = ItemUtils.getSlotForInventoryType(InventoryTypeEnum.convert(item.getInventorytype().getId()));
                    if (slot != SlotEnum.NONE) {
                        ii.setSlot((byte) slot.getValue());
                    }
                    break;
                default:
                    ii.setItemStorageType(iss.getItemStorageByID(ItemStorageEnum.STORED.getValue()));
                    ii.setSlot(bagslot);                    
                    bagslot++;
                    break;
            }

            ii.setCharacters(character);

            listEquipment.add(ii);
        }

        character.setItemInstances(listEquipment);

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            session.beginTransaction();
            session.save(character);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            logger.debug("Error while connecting to the database.");
            return CharCreateEnum.CHAR_CREATE_FAILED;
        }

        return CharCreateEnum.CHAR_CREATE_SUCCESS;
    }

    /**
     * Delete the character corresponding to the ID given in parameter.
     *
     * @param id The id of the character to be deleted.
     * @param account the account to which this character must belong.
     * @return The result of the deletion.
     */
    public synchronized CharDeleteEnum deleteChar(long id, Account account) {
        try (Session session = HibernateUtil.getCharSession().openSession()) {
            Characters character = (Characters) session.createCriteria(Characters.class)
                    .add(Restrictions.and(
                                    Restrictions.eq("guid", id),
                                    Restrictions.eq("fkAccount", account.getId())))
                    .uniqueResult();
            character.setDeleted(true);
            character.setDeleteDate(new Date());
            session.beginTransaction();
            session.save(character);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            logger.error("Character with ID " + id + " does not exist for the account " + account.getName() + ", can't delete it.");
            return CharDeleteEnum.CHAR_DELETE_FAILED;
        }

        return CharDeleteEnum.CHAR_DELETE_SUCCESS;
    }

    /**
     * Login the player into the world.
     *
     * @param id The ID of the character.
     * @param account The account to which this character must belong.
     * @return The result of the login action, true if the player has been
     * logged in, false otherwise.
     */
    public boolean loginChar(long id, Account account) {
        // So far, we just check that this character exists.
        /**
         * try { this.loggedCharacter = (Pchars)
         * this.em.createNamedQuery("Pchars.findById").setParameter("id",
         * id).setParameter("fkAccount", account.getId()).getSingleResult();
         * logger.info("Character [ID: "+this.loggedCharacter.getId()+"]
         * "+this.loggedCharacter.getName()+" is logged in."); } catch
         * (Exception e) { if(this.em.getTransaction().isActive())
         * this.em.getTransaction().rollback(); logger.error("Character with ID
         * "+id+" does not exist for the account "+account.getName()+", can't
         * login."); return false; }
         *
         */
        return true;
    }

    /**
     * Returns the list of characters for a given account.
     *
     * @param account The account for which the list of characters must be
     * retrieved.
     * @return The list of characters for this account or an empty list.
     */
    public List<Characters> getCharactersForAccount(Account account) {
        if (account == null) {
            logger.debug("Account parameter is empty, exiting.");
            return null;
        }

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            return session.createCriteria(Characters.class)
                    .add(Restrictions.eq("fkAccount", account.getId())).list();
        } catch (HibernateException he) {
            logger.error("No result associated to the account: " + account.getId());
            return null;
        }
    }

    /**
     * Return whether the name is already in use in database.
     *
     * @param name The name to be checked.
     * @return True if the name is already in use.
     */
    private boolean nameInUse(String name) {
        if (name == null || name.isEmpty()) {
            logger.debug("Name parameter is empty, exiting. exist = false.");
            return false;
        }

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            Characters character = (Characters) session.createCriteria(Characters.class).add(Restrictions.like("name", name)).uniqueResult();
            logger.debug("The character " + name + " is found.");
            return (character != null);
        } catch (HibernateException he) {
            logger.error("The character " + name + " doesn't exist, exiting.");
            return true;
        }
    }

    /**
     * Performs the various checks on the name given.
     *
     * @param name The name to be checked.
     * @return CharCreateEnum corresponding to the result of the check.
     */
    private CharCreateEnum checkName(String name) {
        // TODO:        
        // CHAR_NAME_TOO_SHORT((short) 0x44),
        // CHAR_NAME_TOO_LONG((short) 0x45),           
        // CHAR_NAME_MIXED_LANGUAGES((short) 0x47),
        // CHAR_NAME_PROFANE((short) 0x48),
        // CHAR_NAME_RESERVED((short) 0x49),                

        if (name == null || name.isEmpty()) {
            logger.debug("Name is empty, aborting character creation.");
            return CharCreateEnum.CHAR_NAME_NO_NAME;
        }

        // Name must only be composed of letters.
        if (!StringUtils.isAlpha(name)) {
            return CharCreateEnum.CHAR_NAME_INVALID_CHARACTERS;
        }

        // Check if name is in use.
        if (nameInUse(name)) {
            return CharCreateEnum.CHAR_CREATE_NAME_IN_USE;
        }

        if (name.contains("'")) {
            return CharCreateEnum.CHAR_NAME_INVALID_APOSTROPHE;
        }

        // Handles single and multiple spaces.
        if (name.contains(" ")) {
            return CharCreateEnum.CHAR_NAME_INVALID_SPACE;
        }

        return CharCreateEnum.CHAR_NAME_SUCCESS;
    }
}
