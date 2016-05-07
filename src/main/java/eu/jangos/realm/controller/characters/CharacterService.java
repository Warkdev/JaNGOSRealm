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
import eu.jangos.realm.controller.auth.RealmAccountService;
import eu.jangos.realm.controller.auth.RealmService;
import eu.jangos.realm.controller.world.ProfessionService;
import eu.jangos.realm.controller.world.GenderService;
import eu.jangos.realm.controller.world.RaceService;
import eu.jangos.realm.controller.world.StartingEquipmentService;
import eu.jangos.realm.controller.world.StartingSpellService;
import eu.jangos.realm.controller.world.WorldParameterService;
import eu.jangos.realm.enums.characters.FactionEnum;
import eu.jangos.realm.enums.characters.ProfessionsEnum;
import eu.jangos.realm.enums.characters.GenderEnum;
import eu.jangos.realm.enums.characters.RaceEnum;
import eu.jangos.realm.enums.items.InventoryTypeEnum;
import eu.jangos.realm.enums.items.ItemClassEnum;
import eu.jangos.realm.enums.items.ItemStorageEnum;
import eu.jangos.realm.enums.items.SlotEnum;
import eu.jangos.realm.enums.object.ObjectTypeEnum;
import eu.jangos.realm.enums.world.RealmTypeEnum;
import eu.jangos.realm.hibernate.HibernateUtil;
import eu.jangos.realm.model.auth.Account;
import eu.jangos.realm.model.auth.Realm;
import eu.jangos.realm.model.auth.RealmAccount;
import eu.jangos.realm.model.auth.RealmAccountId;
import eu.jangos.realm.model.world.Professions;
import eu.jangos.realm.network.opcode.result.CharCreateEnum;
import eu.jangos.realm.utils.StringUtils;
import eu.jangos.realm.model.characters.Characters;
import eu.jangos.realm.model.characters.ItemInstance;
import eu.jangos.realm.model.characters.Spell;
import eu.jangos.realm.model.characters.SpellId;
import eu.jangos.realm.model.world.Gender;
import eu.jangos.realm.model.world.Item;
import eu.jangos.realm.model.world.Race;
import eu.jangos.realm.model.world.Startingequipment;
import eu.jangos.realm.model.world.Startingspell;
import eu.jangos.realm.network.opcode.result.CharDeleteEnum;
import eu.jangos.realm.utils.ItemUtils;
import eu.jangos.realm.utils.WorldParameterConstants;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
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

    private static final RealmService realmService = new RealmService();
    private static final RaceService raceService = new RaceService();
    private static final ProfessionService professionService = new ProfessionService();
    private static final GenderService genderService = new GenderService();
    private static final StartingEquipmentService startingEquipmentService = new StartingEquipmentService();
    private static final StartingSpellService startingSpellService = new StartingSpellService();
    private static final ItemStorageService iss = new ItemStorageService();
    private static final WorldParameterService wps = new WorldParameterService();
    private static final RealmAccountService ras = new RealmAccountService();

    private static final byte START_LEVEL = Byte.parseByte(wps.getParameter(WorldParameterConstants.KEY_WORLD_START_LEVEL));
    private static final byte MIN_NAME_LENGTH = Byte.parseByte(wps.getParameter(WorldParameterConstants.KEY_WORLD_MIN_LENGTH_NAME));
    private static final byte MAX_NAME_LENGTH = Byte.parseByte(wps.getParameter(WorldParameterConstants.KEY_WORLD_MAX_LENGTH_NAME));

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
        Realm realm;
        long characterCount;
        RealmAccount accountInfo;

        // Checking that the race and the class exists in our enum.
        if (!ProfessionsEnum.exists(professionID) || !RaceEnum.exists(raceID) || !GenderEnum.exists(genderID)) {
            logger.debug("Class, Race or Gender doesn't exist, aborting character creation.");
            return CharCreateEnum.CHAR_CREATE_FAILED;
        }

        // Checking account.
        if (account == null) {
            logger.debug("The account is empty, can't create a character for this account");
            return CharCreateEnum.CHAR_CREATE_ERROR;
        }

        // We check that this combination is allowed.
        RaceEnum r = RaceEnum.convert(raceID);
        ProfessionsEnum p = ProfessionsEnum.convert(professionID);

        switch (r) {
            case HUMAN:
                switch (p) {
                    case DRUID:
                    case HUNTER:
                    case SHAMAN:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case DWARF:
                switch (p) {
                    case MAGE:
                    case SHAMAN:
                    case WARLOCK:
                    case DRUID:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case GNOME:
                switch (p) {
                    case DRUID:
                    case SHAMAN:
                    case HUNTER:
                    case PRIEST:
                    case PALADIN:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case NIGHTELF:
                switch (p) {
                    case PALADIN:
                    case MAGE:
                    case SHAMAN:
                    case WARLOCK:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case ORC:
                switch (p) {
                    case DRUID:
                    case MAGE:
                    case PALADIN:
                    case PRIEST:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case TAUREN:
                switch (p) {
                    case MAGE:
                    case PALADIN:
                    case PRIEST:
                    case ROGUE:
                    case WARLOCK:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case TROLL:
                switch (p) {
                    case WARLOCK:                    
                    case PALADIN:
                    case DRUID:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
            case UNDEAD:
                switch (p) {
                    case SHAMAN:
                    case PALADIN:
                    case HUNTER:
                    case DRUID:
                        return CharCreateEnum.CHAR_CREATE_FAILED;
                }
                break;
        }

        // Checking realm population.
        realm = realmService.getRealmByName(wps.getParameter(WorldParameterConstants.KEY_WORLD_NAME));
        if (realm.getMaxPlayers() <= realm.getCountPlayers()) {
            logger.debug("The realm is already full, can't create new characters");
            return CharCreateEnum.CHAR_CREATE_SERVER_LIMIT;
        }

        // Checking realm type and character creation.
        // PVP realms doesn't allow creation of characters for both factions.
        switch (RealmTypeEnum.convert(realm.getRealmtype().getId())) {
            case PVP:
            case RP_PVP:
                if (hasCharacterInEnemyFaction(account, FactionEnum.getEnemyFactionForRace(RaceEnum.convert(raceID)))) {
                    return CharCreateEnum.CHAR_CREATE_PVP_TEAMS_VIOLATION;
                }
        }

        // Checking the number of characters for this account.
        characterCount = getNumberOfCharacters(account);
        if (characterCount >= Long.parseLong(wps.getParameter(WorldParameterConstants.KEY_WORLD_MAX_CHARACTERS))) {
            logger.debug("The number of characters for this realm is already at the maximum authorized.");
            return CharCreateEnum.CHAR_CREATE_ACCOUNT_LIMIT;
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
        character.setStableSlots((byte) 0);
        character.setHidehelm(Boolean.FALSE);
        character.setHidecloak(Boolean.FALSE);
        character.setGhost(Boolean.FALSE);
        character.setChangename(Boolean.FALSE);
        character.setResetspell(Boolean.FALSE);
        character.setResettalents(Boolean.FALSE);
        character.setTotaltime(0);                
        
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

        // Adding default spells
        Set<Spell> listSpells = new HashSet<>();
        
        for(Startingspell s : startingSpellService.getStartingSpells(race, profession))
        {
            Spell spell = new Spell();           
            spell.setActive(true);
            spell.setCooldown(new Date());
            spell.setItemInstance(null);
            
            spell.setId(new SpellId(0, s.getId().getFkDbcSpell()));
            
            listSpells.add(spell);
        }                
        
        // We update the account info for this account.
        accountInfo = ras.getAccountInfo(account, realm);
        if (accountInfo == null) {
            accountInfo = new RealmAccount();
            accountInfo.setAccount(account);
            accountInfo.setRealm(realm);
            accountInfo.setNumChars((byte) 1);
            accountInfo.setId(new RealmAccountId(account.getId(), realm.getId()));
        } else {
            accountInfo.setNumChars((byte) (accountInfo.getNumChars() + 1));
        }

        if (accountInfo.getNumChars() == 1) {
            // We update the realm count and population.
            realm.setCountPlayers(realm.getCountPlayers() + 1);
            realm.setPopulation(realm.getCountPlayers() * 1.0f / realm.getMaxPlayers() * 1.0f);
        }

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            session.beginTransaction();
            character = (Characters) session.merge(character);            
            
            // We re-assign the right ID to the spell and add the spells to the character.
            for(Spell spell : listSpells)
            {
                spell.getId().setFkOwner(character.getGuid());
            }
            character.setSpells(listSpells);
            session.merge(character);
            
            try (Session authSession = HibernateUtil.getAuthSession().openSession()) {
                authSession.beginTransaction();
                authSession.merge(realm);
                authSession.merge(accountInfo);
                authSession.flush();
                authSession.getTransaction().commit();
            } catch (HibernateException he) {
                logger.debug("Error while connecting to the auth database.");
                session.getTransaction().rollback();
                return CharCreateEnum.CHAR_CREATE_FAILED;
            }

            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
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
     * @param permanent Indicates whether this account must be deleted
     * permanently or not.
     * @return The result of the deletion.
     */
    public synchronized CharDeleteEnum deleteChar(long id, Account account, boolean permanent) {
        Realm realm = realmService.getRealmByName(wps.getParameter(WorldParameterConstants.KEY_WORLD_NAME));
        RealmAccount accountInfo = ras.getAccountInfo(account, realm);

        accountInfo.setNumChars((byte) (accountInfo.getNumChars() - 1));

        if (accountInfo.getNumChars() == 0) {
            realm.setCountPlayers(realm.getCountPlayers() - 1);
            realm.setPopulation(realm.getCountPlayers() * 1.0f / realm.getMaxPlayers() * 1.0f);
        }

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            Characters character = (Characters) session.createCriteria(Characters.class)
                    .add(Restrictions.and(
                                    Restrictions.eq("guid", id),
                                    Restrictions.eq("fkAccount", account.getId())))
                    .uniqueResult();
            session.beginTransaction();
            if (!permanent) {
                character.setDeleted(true);
                character.setDeleteDate(new Date());
                session.merge(character);
            } else {
                session.delete(character);
            }

            // We update realm infos.
            try (Session authSession = HibernateUtil.getAuthSession().openSession()) {
                authSession.beginTransaction();
                authSession.merge(realm);
                authSession.merge(accountInfo);
                authSession.flush();
                authSession.getTransaction().commit();;
            } catch (HibernateException he) {
                session.getTransaction().rollback();
                return CharDeleteEnum.CHAR_DELETE_FAILED;
            }

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
                    .add(Restrictions.and(
                                    Restrictions.isNull("deleted"),
                                    Restrictions.eq("fkAccount", account.getId()
                                    ))).list();
        } catch (HibernateException he) {
            logger.error("No result associated to the account: " + account.getId());
            return null;
        }
    }

    /**
     * Return the number of characters for this account.
     *
     * @param account The account for which the number of characters needs to be
     * retrieved.
     * @return The number of characters for this account.
     */
    public long getNumberOfCharacters(Account account) {
        if (account == null) {
            logger.debug("Account parameter is empty, exiting.");
            return 0;
        }

        try (Session session = HibernateUtil.getCharSession().openSession()) {
            return (long) session.createCriteria(Characters.class)
                    .add(Restrictions.and(
                                    Restrictions.isNull("deleted"),
                                    Restrictions.eq("fkAccount", account.getId()
                                    ))).setProjection(Projections.rowCount()).uniqueResult();
        } catch (HibernateException he) {
            logger.error("No result associated to the account: " + account.getId());
            return 0;
        }
    }

    /**
     * Indicates whether the account given in parameter has characters already
     * created in the opposite faction.
     *
     * @param account The account for which it needs to be checked.
     * @param faction The faction for which this account is trying to create a
     * character.
     * @return True if the account has already created characters in the enemy
     * faction.
     */
    private boolean hasCharacterInEnemyFaction(Account account, FactionEnum faction) {
        try (Session session = HibernateUtil.getCharSession().openSession()) {
            Disjunction or = Restrictions.disjunction();
            for (RaceEnum race : FactionEnum.getRacesForFactions(faction)) {
                or.add(Restrictions.eq("race", race.getValue()));
            }            
            
            return (((long) session.createCriteria(Characters.class)
                    .add(Restrictions.and(
                                    Restrictions.isNull("deleted"),
                                    Restrictions.eq("fkAccount", account.getId()),
                                    or
                            )).setProjection(Projections.rowCount()).uniqueResult()) != 0);

        } catch (HibernateException he) {
            return false;
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
            Characters character = (Characters) session.createCriteria(Characters.class)
                    .add(Restrictions.and(
                                    Restrictions.isNull("deleted"),
                                    Restrictions.like("name", name)))
                    .uniqueResult();
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
        // CHAR_NAME_MIXED_LANGUAGES((short) 0x47),
        // CHAR_NAME_PROFANE((short) 0x48),
        // CHAR_NAME_RESERVED((short) 0x49),                

        if (name == null || name.isEmpty()) {
            logger.debug("Name is empty, aborting character creation.");
            return CharCreateEnum.CHAR_NAME_NO_NAME;
        }

        if (name.length() < MIN_NAME_LENGTH) {
            return CharCreateEnum.CHAR_NAME_TOO_SHORT;
        }

        if (name.length() > MAX_NAME_LENGTH) {
            return CharCreateEnum.CHAR_NAME_TOO_LONG;
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
