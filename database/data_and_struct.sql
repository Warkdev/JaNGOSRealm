-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: je4wcharacters
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actionbar`
--

DROP TABLE IF EXISTS `actionbar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actionbar` (
  `fk_guid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Global Unique Identifier, foreign key to characters.',
  `button` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The button to which the action applies, maximum value is 128.',
  `action` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The action on this button, depends on the type.',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'All the ActionType that exists for an action: SPELL, C, MACRO, CMACRO or ITEM.',
  PRIMARY KEY (`fk_guid`,`button`),
  CONSTRAINT `fk_character_action_characters` FOREIGN KEY (`fk_guid`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actionbar`
--

LOCK TABLES `actionbar` WRITE;
/*!40000 ALTER TABLE `actionbar` DISABLE KEYS */;
/*!40000 ALTER TABLE `actionbar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characters` (
  `guid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The character global unique identifier. This number must be unique and is the best way to identify separate characters.',
  `fk_account` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The account ID in which this character resides. Foreign key to account.',
  `name` varchar(12) NOT NULL DEFAULT '' COMMENT 'The name of the character.',
  `race` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The race of the character.\n1	1	Human\n2	2	Orc\n3	4	Dwarf\n4	8	Night Elf\n5	16	Undead\n6	32	Tauren\n7	64	Gnome\n8	128	Troll\n',
  `fk_dbc_class` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The Class Id of the character. Foreign key to ChrClasses.dbc',
  `gender` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The Sex/Gender of the character.\nValue	Gender\n0	Male\n1	Female\n',
  `level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The current level of the designated player.',
  `xp` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The total amount of xp that the signified player has.',
  `money` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'This is the amount of copper the character possesses.',
  `position_x` float NOT NULL DEFAULT '0' COMMENT 'The x position of the character’s location.',
  `position_y` float NOT NULL DEFAULT '0' COMMENT 'The y position of the character’s location.',
  `position_z` float NOT NULL DEFAULT '0' COMMENT 'The z position of the character’s location.',
  `fk_dbc_map` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Map Identifier, Foreign key to Map.dbc',
  `orientation` float NOT NULL DEFAULT '0' COMMENT 'The orientation the character is facing. (North = 0.0, South = 3.14159)',
  `online` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Records whether the character is online (1) or offline (0).',
  `cinematic` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Boolean 1 or 0 controlling whether the start cinematic has been shown or not.',
  `totaltime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The total time that the character has been active in the world, measured in seconds.',
  `leveltime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The total time the character has spent in the world at the current level, measured in seconds.',
  `logout_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'The time when the character last logged out, measured in Unix time.',
  `rest_bonus` float NOT NULL DEFAULT '0' COMMENT 'The remaining amount of XP bonus for rested characters.',
  `resettalents_cost` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The cost for the character to reset its talents, measured in copper.',
  `resettalents_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'The last time the character has performed a reset of his talents.',
  `trans_x` float NOT NULL DEFAULT '0' COMMENT 'The X position of the character if he''s on a transport.',
  `trans_y` float NOT NULL DEFAULT '0' COMMENT 'The Y position of the character if he''s on a transport.',
  `trans_z` float NOT NULL DEFAULT '0' COMMENT 'The Z position of the character if he''s on a transport.',
  `trans_o` float NOT NULL DEFAULT '0' COMMENT 'The O position of the character if he''s on a transport.',
  `fk_transguid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'The global unique ID of the transport if applicable. Foreign key to transport.',
  `stable_slots` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'The number of stable slots the player has available. Maximum is 2.',
  `fk_dbc_zone` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The zone ID the character is in. Foreign key to WorldMapArea.dbc',
  `death_expire_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'Time when a character can be resurrected in case of a server crash or client exit while in ghost form.',
  `fk_dbc_taxi_path` text COMMENT 'Stores the players current taxi path (TaxiPath.dbc) if logged off while on one.',
  `honor_highest_rank` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Internal honor rank for mangos (value 0 to 18).',
  `honor_standing` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Displayed rank in game (-4 to 14, Pariah to High Warlord)',
  `stored_honor_rating` float NOT NULL DEFAULT '0' COMMENT 'Amount of stored honor so far.',
  `stored_dishonorable_kills` int(11) NOT NULL DEFAULT '0' COMMENT 'Amount of stored dishonorable kills so far.',
  `stored_honorable_kills` int(11) NOT NULL DEFAULT '0' COMMENT 'Amount of stored honorable kills so far.',
  `fk_dbc_watchedFaction` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ID of the watched faction by this character. Foreign key to Faction.dbc.',
  `drunk` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT 'Boolean flag indicating whether the character is drunk or not.',
  `health` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Current health of the character.',
  `power1` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The amount of mana for this character.',
  `power2` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The amount of rage for this character.',
  `power3` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The amount of focus for this character.',
  `power4` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The amount of energy for this character.',
  `power5` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The amount of happiness for this character.',
  `fk_ammoId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ID of the ammo item. Foreign key to item_instance.',
  `deleted` tinyint(3) unsigned DEFAULT NULL COMMENT 'Boolean value indicatiing whether this character is deleted or not.',
  `deleteDate` bigint(20) unsigned DEFAULT NULL COMMENT 'The date at which the account was deleted.',
  `skin` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s skin. This value is part of the playerBytes information.',
  `face` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s face. This value is part of the playerBytes information.',
  `hairstyle` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s hairstyle. This value is part of the playerBytes information.',
  `haircolor` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s haircolor. This value is part of the playerBytes information.',
  `facialhair` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s facial hair. This value is part of the playerBytes2 information.',
  `unknownByte` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value -- unknown usage. This value is part of the playerBytes2 information.',
  `bankbagslot` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s bank bag slot count. This value is part of the playerBytes2 information.',
  `resttype` tinyint(3) unsigned DEFAULT NULL COMMENT 'Byte value for this character''s rest type.  This value is part of the playerBytes2 information.\nPossible values are from 0 to 7 (values can be combined together):\n1 - RESTED\n2 - NORMAL\n4 - RAF-LINKED',
  `changename` tinyint(3) unsigned DEFAULT NULL COMMENT 'Force the character to change name at login. This value is part of the at_login_flag information.',
  `resetspell` tinyint(3) unsigned DEFAULT NULL COMMENT 'Boolean value indicating whether the character will have spell reset at login or not. This value is part of the at_login_flag information.',
  `resettalents` tinyint(3) unsigned DEFAULT NULL COMMENT 'Boolean value indicating whether the character will have talents reset at login or not. This value is part of the at_login_flag information.',
  `charcustom` tinyint(3) unsigned DEFAULT NULL COMMENT 'Boolean value at login, unknown effect. This value is part of the at_login_flag information.',
  `groupleader` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is group leader or not. This field is part of the playerFlags information.',
  `afk` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is afk or not. This field is part of the playerFlags information.',
  `dnd` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is dnd or not. This field is part of the playerFlags information.',
  `gm` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is gm or not. This field is part of the playerFlags information.',
  `ghost` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is ghost or not. This field is part of the playerFlags information.',
  `resting` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is resting or not. This field is part of the playerFlags information.',
  `unknown7` tinyint(3) unsigned DEFAULT NULL COMMENT 'Unknown flag usage. This field is part of the playerFlags information.',
  `ffapvp` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is in ffa pvp mode or not. This field is part of the playerFlags information.',
  `contestedpvp` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is in contested pvp mode or not. This field is part of the playerFlags information.',
  `inpvp` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is in pvp or not. This field is part of the playerFlags information.',
  `hidehelm` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is hidding helm or not. This field is part of the playerFlags information.',
  `hidecloak` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is hidding cloak or not. This field is part of the playerFlags information.',
  `partialplaytime` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character has played a long time or not. This field is part of the playerFlags information.',
  `noplaytime` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character has played too long or not. This field is part of the playerFlags information.',
  `unknown15` tinyint(3) unsigned DEFAULT NULL COMMENT 'Unknown usage. This field is part of the playerFlags information.',
  `unknown16` tinyint(3) unsigned DEFAULT NULL COMMENT 'Unknown usage. This field is part of the playerFlags information.',
  `sanctuary` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is in a sanctuary zone or not. This field is part of the playerFlags information.',
  `taxibenchmark` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is in taxi benchmarking mode or not. This field is part of the playerFlags information.',
  `pvptimer` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character has the pvp timer enabled or not. This field is part of the playerFlags information.',
  `gmon` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character has the gm mode enabled or not. This field is part of the ExtraFlags information.',
  `gmaccepttickets` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is accepting tickets or not. This field is part of the ExtraFlags information.',
  `gmacceptwhispers` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is accepting whispers or not. This field is part of the ExtraFlags information.',
  `gmtaxicheat` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is using taxi cheats or not. This field is part of the ExtraFlags information.',
  `gminvisible` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character is invisible or not. This field is part of the ExtraFlags information.',
  `gmchat` tinyint(3) unsigned DEFAULT NULL COMMENT 'Flag value indicating whether this character shows is GM badge in the chat or not. This field is part of the ExtraFlags information.',
  `gmauctionneutral` tinyint(3) unsigned DEFAULT NULL COMMENT 'Neutral auction flag. This field is part of the ExtraFlags information.',
  `gmauctionenemy` tinyint(3) unsigned DEFAULT NULL COMMENT 'Enemy auction flag. This field is part of the ExtraFlags information.',
  `pvpdeath` tinyint(3) unsigned DEFAULT NULL COMMENT 'Store the PvP status until the corpse is created. This field is part of the ExtraFlags information.',
  `fk_petition` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the petition item that this character has signed.',
  `fk_guild_rank_guild` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to guild_rank table.',
  `fk_guild_rank` tinyint(3) unsigned DEFAULT NULL COMMENT 'Foreign key to the rank.',
  `note` varchar(60) DEFAULT NULL COMMENT 'The guild note of this player, visible by everybody.',
  `officernote` varchar(60) DEFAULT NULL COMMENT 'The officer note for this character, visible only by allowed ranks.',
  PRIMARY KEY (`guid`),
  KEY `idx_account` (`fk_account`),
  KEY `idx_online` (`online`),
  KEY `idx_name` (`name`),
  KEY `fk_characters_item_storage_idx` (`guid`),
  KEY `fk_petition_signer_idx` (`fk_petition`),
  KEY `fk_characters_guild_rank_idx` (`fk_guild_rank_guild`,`fk_guild_rank`),
  KEY `fk_characters_profession_idx` (`fk_dbc_class`),
  KEY `fk_characters_race_idx` (`race`),
  KEY `fk_characters_gender_idx` (`gender`),
  CONSTRAINT `fk_characters_gender` FOREIGN KEY (`gender`) REFERENCES `je4wrealm`.`gender` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_characters_guild_rank` FOREIGN KEY (`fk_guild_rank_guild`, `fk_guild_rank`) REFERENCES `guild_rank` (`fk_guild_id`, `id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_characters_profession` FOREIGN KEY (`fk_dbc_class`) REFERENCES `je4wrealm`.`professions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_characters_race` FOREIGN KEY (`race`) REFERENCES `je4wrealm`.`race` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_petition_signer` FOREIGN KEY (`fk_petition`) REFERENCES `item_instance` (`object_guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guild`
--

DROP TABLE IF EXISTS `guild`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild` (
  `id` int(10) unsigned NOT NULL COMMENT 'ID of the guild.',
  `name` varchar(24) NOT NULL COMMENT 'Name of the guild.',
  `emblemstyle` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Style of the emblem.',
  `emblemcolor` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Color of the emblem.',
  `borderstyle` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Style of the border.',
  `bordercolor` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Color of the border.',
  `background` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Background color.',
  `info` text NOT NULL COMMENT 'The guild info text.',
  `motd` varchar(255) NOT NULL DEFAULT '' COMMENT 'The message of the day for this guild.',
  `creationdate` datetime NOT NULL COMMENT 'The creation date of this guild.',
  `deleted` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this guild has been disband or not.',
  `deletiondate` datetime DEFAULT NULL COMMENT 'Deletion date of this guild, if applicable.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='The guild table represents a guild with all its information.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guild`
--

LOCK TABLES `guild` WRITE;
/*!40000 ALTER TABLE `guild` DISABLE KEYS */;
/*!40000 ALTER TABLE `guild` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guild_event_type`
--

DROP TABLE IF EXISTS `guild_event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild_event_type` (
  `id` tinyint(3) unsigned NOT NULL COMMENT 'Guild Event Type ID.',
  `type` varchar(45) DEFAULT NULL COMMENT 'Guild Event Type definition.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Guild event type is representing the different event that happens during the life of a guild';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guild_event_type`
--

LOCK TABLES `guild_event_type` WRITE;
/*!40000 ALTER TABLE `guild_event_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `guild_event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guild_events`
--

DROP TABLE IF EXISTS `guild_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild_events` (
  `id` int(10) unsigned NOT NULL COMMENT 'ID of the record.',
  `fk_guild` int(10) unsigned NOT NULL COMMENT 'Foreign key to the guild to which this event belongs.',
  `fk_event_type` tinyint(3) unsigned DEFAULT NULL COMMENT 'Foreign key to the type of event.',
  `fk_event_sender` int(10) unsigned DEFAULT NULL COMMENT 'The initiator of the event.',
  `fk_event_receiver` int(10) unsigned DEFAULT NULL COMMENT 'The receiver of this event.',
  `rank` tinyint(3) unsigned DEFAULT NULL COMMENT 'The new rank in case of demote/promote actions.',
  `date` datetime DEFAULT NULL COMMENT 'The date at which this event occured.',
  PRIMARY KEY (`id`,`fk_guild`),
  KEY `fk_guild_events_guild_idx` (`fk_guild`),
  KEY `fk_guild_events_sender_idx` (`fk_event_sender`),
  KEY `fk_guild_events_receiver_idx` (`fk_event_receiver`),
  KEY `fk_guild_events_event_type_idx` (`fk_event_type`),
  CONSTRAINT `fk_guild_events_event_type` FOREIGN KEY (`fk_event_type`) REFERENCES `guild_event_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_guild_events_guild` FOREIGN KEY (`fk_guild`) REFERENCES `guild` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_guild_events_receiver` FOREIGN KEY (`fk_event_receiver`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_guild_events_sender` FOREIGN KEY (`fk_event_sender`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Guild events represents all the events that occured on a gui';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guild_events`
--

LOCK TABLES `guild_events` WRITE;
/*!40000 ALTER TABLE `guild_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `guild_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guild_rank`
--

DROP TABLE IF EXISTS `guild_rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild_rank` (
  `fk_guild_id` int(10) unsigned NOT NULL COMMENT 'Foreign key to the guild ID.',
  `id` tinyint(3) unsigned NOT NULL COMMENT 'Rank id within the guild.',
  `name` varchar(20) NOT NULL COMMENT 'Rank name within the guild.',
  `authz_read_channel` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can read the guild channel.',
  `authz_write_channel` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can write on the guild channel.',
  `authz_read_off_channel` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can read the officer channel.',
  `authz_write_off_channel` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can write on the officer channel.',
  `authz_promote` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can promote another character with a lower rank.',
  `authz_demote` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can demote another character with a lower rank.',
  `authz_invite` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rnak can invite players into the guild.',
  `authz_revoke` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rnak can revoke players from the guild.',
  `authz_motd` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can define the message of the day.',
  `authz_write_note` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can write the player note.',
  `authz_read_off_note` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can see the officer note.',
  `authz_write_off_note` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can write the officer note.',
  `authz_guildinfo` tinyint(3) unsigned DEFAULT NULL COMMENT 'Indicates whether this rank can edit the guild info.',
  PRIMARY KEY (`fk_guild_id`,`id`),
  CONSTRAINT `fk_rank_guild` FOREIGN KEY (`fk_guild_id`) REFERENCES `guild` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='The guild ranking system. This table defines the rank for ea';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guild_rank`
--

LOCK TABLES `guild_rank` WRITE;
/*!40000 ALTER TABLE `guild_rank` DISABLE KEYS */;
/*!40000 ALTER TABLE `guild_rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_instance`
--

DROP TABLE IF EXISTS `item_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_instance` (
  `object_guid` int(10) unsigned NOT NULL COMMENT 'Global unique identifier of the item. It is the low part of the guid.',
  `object_type` tinyint(3) unsigned DEFAULT NULL COMMENT 'The type of the object, flag based. The following masks are applied (and combined) on it to determine the exact object type:\n1 - Object\n2 - Item\n4 - Container\n8 - Unit\n16 - Player\n32 - Gameobject\n64 - Dynamicobject\n128 - Corpse',
  `fk_object_entry` mediumint(8) unsigned DEFAULT NULL COMMENT 'Foreign key to the id of the original item_template.',
  `object_scale_x` float unsigned DEFAULT NULL,
  `object_padding` int(10) unsigned DEFAULT NULL COMMENT 'The object padding. - Provide more info if known ! -',
  `fk_creator_guid` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the creator of this item. It is used in case of crafted item.',
  `fk_gift_guid` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the creator of this item. It is used in case of wrapped item.',
  `duration` int(10) unsigned DEFAULT NULL COMMENT 'The remaining time for this object. (in ms).',
  `spell_charges1` int(10) DEFAULT NULL COMMENT 'The number of remaining charges for the spell nr 1 of the item.',
  `spell_charges2` int(10) DEFAULT NULL COMMENT 'The number of remaining charges for the spell nr 2 of the item.',
  `spell_charges3` int(10) DEFAULT NULL COMMENT 'The number of remaining charges for the spell nr 3 of the item.',
  `spell_charges4` int(10) DEFAULT NULL COMMENT 'The number of remaining charges for the spell nr 4 of the item.',
  `spell_charges5` int(10) DEFAULT NULL COMMENT 'The number of remaining charges for the spell nr 5 of the item.',
  `binded` tinyint(4) DEFAULT NULL COMMENT 'Flag value indicating whether this item is bind or not.',
  `unknown1` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unlocked` tinyint(4) DEFAULT NULL COMMENT 'Flag value indicating whether this item is locked or not.',
  `wrapped` tinyint(4) DEFAULT NULL COMMENT 'Flag value indicating whether this item is wrapped or not.',
  `unknown4` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown5` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown6` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown7` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown8` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `readable` tinyint(4) DEFAULT NULL,
  `unknown10` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown11` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown12` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown13` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown14` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown15` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown16` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `unknown17` tinyint(4) DEFAULT NULL COMMENT 'This flag has an unknown meaning, please find me !',
  `fk_dbc_perm_enchant_id` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the enchantment id of SpellItemEnchantement.dbc.',
  `perm_enchant_duration` int(11) DEFAULT NULL COMMENT 'Remaining time of the permanent enchantment.',
  `perm_enchant_charges` tinyint(3) unsigned DEFAULT NULL COMMENT 'Remaining charges of the enchantement.',
  `fk_dbc_temp_enchant_id` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the enchantment id of SpellItemEnchantement.dbc.',
  `temp_enchant_duration` int(11) DEFAULT NULL COMMENT 'Remaining time of the temporary enchantment.',
  `temp_enchant_charges` tinyint(3) unsigned DEFAULT NULL COMMENT 'Remaining charges of the enchantement.',
  `fk_dbc_prop0_enchant_id` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the enchantment id of SpellItemEnchantement.dbc.',
  `prop0_enchant_duration` int(11) DEFAULT NULL,
  `prop0_enchant_charges` tinyint(3) unsigned DEFAULT NULL,
  `fk_dbc_prop1_enchant_id` int(10) unsigned DEFAULT NULL,
  `prop1_enchant_duration` int(11) DEFAULT NULL,
  `prop1_enchant_charges` tinyint(3) unsigned DEFAULT NULL,
  `fk_dbc_prop2_enchant_id` int(10) unsigned DEFAULT NULL,
  `prop2_enchant_duration` int(11) DEFAULT NULL,
  `prop2_enchant_charges` tinyint(3) unsigned DEFAULT NULL,
  `fk_dbc_prop3_enchant_id` int(10) unsigned DEFAULT NULL,
  `prop3_enchant_duration` int(11) DEFAULT NULL,
  `prop3_enchant_charges` tinyint(4) DEFAULT NULL,
  `fk_dbc_prop4_enchant_id` int(11) DEFAULT NULL,
  `prop4_enchant_duration` int(10) unsigned DEFAULT NULL,
  `prop4_enchant_charges` tinyint(3) unsigned DEFAULT NULL,
  `property_seed` int(10) unsigned DEFAULT NULL COMMENT 'Suffix factor.',
  `fk_dbc_random_property_id` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the random property ID.',
  `fk_item_text_id` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the item text.',
  `durability` int(10) unsigned DEFAULT NULL COMMENT 'The remaining durability for this item.',
  `fk_storage_type` tinyint(3) unsigned DEFAULT NULL COMMENT 'The type of the main storage. Foreign key to item_storage_type.',
  `fk_storage_bag` int(10) unsigned DEFAULT NULL COMMENT 'ID to the bag into which this item is. NULL if this item is not stored in a bag.',
  `slot` tinyint(3) unsigned DEFAULT NULL COMMENT 'The slot in which this item is stored relative ',
  `fk_owner` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the owner of this item.',
  `stack_count` tinyint(3) unsigned DEFAULT NULL COMMENT 'Amount of object for this stack.',
  `fk_wrapped_in` int(10) unsigned DEFAULT NULL COMMENT 'Foreign key to the item in which this item is wrapped into.',
  `guild_petition_name` varchar(24) DEFAULT NULL COMMENT 'The temporary guild name attached to the petition item.',
  PRIMARY KEY (`object_guid`),
  KEY `fk_item_storage_type_idx` (`fk_storage_type`),
  KEY `fk_item_bag_idx` (`fk_storage_bag`),
  KEY `fk_item_owner_idx` (`fk_owner`),
  KEY `fk_item_wrapped_in_idx` (`fk_wrapped_in`),
  KEY `fk_item_item_template_idx` (`fk_object_entry`),
  CONSTRAINT `fk_item_bag` FOREIGN KEY (`fk_storage_bag`) REFERENCES `item_instance` (`object_guid`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_item_item_template` FOREIGN KEY (`fk_object_entry`) REFERENCES `je4wrealm`.`item` (`entry`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_owner` FOREIGN KEY (`fk_owner`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_storage_type` FOREIGN KEY (`fk_storage_type`) REFERENCES `item_storage_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_wrapped_in` FOREIGN KEY (`fk_wrapped_in`) REFERENCES `item_instance` (`object_guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Item instance represents an item that has been generated into the world.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_instance`
--

LOCK TABLES `item_instance` WRITE;
/*!40000 ALTER TABLE `item_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_storage_type`
--

DROP TABLE IF EXISTS `item_storage_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_storage_type` (
  `id` tinyint(3) unsigned NOT NULL COMMENT 'The ID of the item storage.',
  `type` varchar(45) DEFAULT NULL COMMENT 'A short description of what is the meaning of the type.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='This is a collection of all possible values for an item stor';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_storage_type`
--

LOCK TABLES `item_storage_type` WRITE;
/*!40000 ALTER TABLE `item_storage_type` DISABLE KEYS */;
INSERT INTO `item_storage_type` VALUES (0,'EQUIPPED'),(19,'BAG 1'),(20,'BAG 2'),(21,'BAG 3'),(22,'BAG 4'),(63,'BANK BAG 1'),(64,'BANK BAG 2'),(65,'BANK BAG 3'),(66,'BANK BAG 4'),(67,'BANK BAG 5'),(68,'BANK BAG 6'),(255,'STORED (BANK/BACKPACK)');
/*!40000 ALTER TABLE `item_storage_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail`
--

DROP TABLE IF EXISTS `mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail` (
  `id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Unique message id. Every new message gets a new auto incremented id.',
  `fk_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'The type of the Message (0 = Normal, 1 = COD, 2 = Auction).',
  `fk_dbc_stationery` tinyint(3) NOT NULL DEFAULT '41' COMMENT 'The StationeryID. Foreign key to Stationery.dbc.',
  `fk_dbc_mailTemplateId` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT 'The Mail Template Id. Foreign key to MailTemplate.dbc.',
  `fk_from` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Character Global Unique Identifier of the sender. Foreign key to characters.',
  `fk_to` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Character Global Unique Identifier of the receiver. Foreign key to characters.',
  `subject` longtext COMMENT 'The Subject of the mail.',
  `body` longtext NOT NULL COMMENT 'The Item text ID. Foreign Key to Item Text.',
  `expire` datetime NOT NULL COMMENT 'Current Unix Time + Unix Time till expiry.',
  `deliver` datetime NOT NULL COMMENT 'Current Unix Time + Unix Time till delivery.',
  `money` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Amount of money sent in copper unit.',
  `cod` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Amount of money needed (COD).',
  `isRead` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Checked flag Mask. Combination of these flags adds meaning:\n0	MAIL_CHECK_MASK_NONE\n1	MAIL_CHECK_MASK_READ\n2	MAIL_CHECK_MASK_RETURNED\n4	MAIL_CHECK_MASK_COPIED\n8	MAIL_CHECK_MASK_COD_PAYMENT\n16	MAIL_CHECK_MASK_HAS_BODY',
  `isReturned` tinyint(3) unsigned NOT NULL,
  `isCopied` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_receiver` (`fk_to`),
  KEY `fk_mail_sender_idx` (`fk_from`),
  CONSTRAINT `fk_mail_receiver` FOREIGN KEY (`fk_to`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mail_sender` FOREIGN KEY (`fk_from`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Mail System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail`
--

LOCK TABLES `mail` WRITE;
/*!40000 ALTER TABLE `mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reputation`
--

DROP TABLE IF EXISTS `reputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reputation` (
  `fk_guid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Global Unique Identifier of the character. Foreign key to characters.',
  `fk_dbc_faction` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The faction ID that the character has the given reputation in. Foreign key to DBC Faction.',
  `standing` int(11) NOT NULL DEFAULT '0' COMMENT 'The current reputation value that the character has.',
  `visible` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'This field is a bitmask containing flags that apply to the faction.',
  `war` tinyint(3) unsigned NOT NULL,
  `hidden` tinyint(3) unsigned NOT NULL,
  `invisible` tinyint(3) unsigned NOT NULL,
  `peace` tinyint(3) unsigned NOT NULL,
  `inactive` tinyint(3) unsigned NOT NULL,
  `rival` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`fk_guid`,`fk_dbc_faction`),
  CONSTRAINT `fk_character_reputation_characters` FOREIGN KEY (`fk_guid`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reputation`
--

LOCK TABLES `reputation` WRITE;
/*!40000 ALTER TABLE `reputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reputation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `fk_guid` int(11) unsigned NOT NULL COMMENT 'Global Unique Identifier, foreign key to the characters table.',
  `fk_dbc_skill` mediumint(9) unsigned NOT NULL COMMENT 'A foreign key to the DBC file skillLines.',
  `value` mediumint(9) unsigned NOT NULL COMMENT 'The actual skill value.',
  `max` mediumint(9) unsigned NOT NULL COMMENT 'The maximum skill value for this skill.',
  PRIMARY KEY (`fk_guid`,`fk_dbc_skill`),
  CONSTRAINT `fk_character_skills_characters` FOREIGN KEY (`fk_guid`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social`
--

DROP TABLE IF EXISTS `social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social` (
  `fk_owner` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Character Global Unique Identifier. Foreign key to characters.',
  `fk_relation` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Friend Global Unique Identifier, foreign key to the characters table.',
  `ignored` tinyint(1) unsigned NOT NULL COMMENT 'Friend Flags (0 = Friend, 1 = Ignored).',
  PRIMARY KEY (`fk_owner`,`fk_relation`,`ignored`),
  KEY `guid` (`fk_owner`),
  KEY `friend` (`fk_relation`),
  KEY `guid_flags` (`fk_owner`,`ignored`),
  KEY `friend_flags` (`fk_relation`,`ignored`),
  CONSTRAINT `fk_character_social_characters` FOREIGN KEY (`fk_owner`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_character_social_relation` FOREIGN KEY (`fk_relation`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social`
--

LOCK TABLES `social` WRITE;
/*!40000 ALTER TABLE `social` DISABLE KEYS */;
/*!40000 ALTER TABLE `social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spell`
--

DROP TABLE IF EXISTS `spell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spell` (
  `fk_owner` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The GUID (Global Unique Identifier) of the character. Foreign key to characters.',
  `fk_dbc_spell` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The spell ID (Spell Identifier). Foreign key to the Spell.dbc.',
  `fk_item` int(11) unsigned NOT NULL COMMENT 'Foreign key to the item that holds this spell.',
  `active` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT 'Boolean 1 or 0 signifying whether the spell is active (appears in the spell book).',
  `cooldown` datetime NOT NULL,
  PRIMARY KEY (`fk_owner`,`fk_dbc_spell`),
  KEY `Idx_spell` (`fk_dbc_spell`),
  CONSTRAINT `fk_character_spell_characters` FOREIGN KEY (`fk_owner`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spell`
--

LOCK TABLES `spell` WRITE;
/*!40000 ALTER TABLE `spell` DISABLE KEYS */;
/*!40000 ALTER TABLE `spell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` int(11) unsigned NOT NULL COMMENT 'A unique ticket ID.',
  `fk_character` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'The GUID of the character sending the ticket. Foreign key to characters.',
  `body` text COMMENT 'The ticket description text; the text written by the player in describing the problem.',
  `response` text COMMENT 'The ticket description text; the text written by the GM in describing the solution.',
  `lastupdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Stores the time when this ticket was last changed.',
  `solved` tinyint(1) DEFAULT '0' COMMENT 'Boolean Flag = set to 1 if the GM has sucessfully resolved the ticket',
  PRIMARY KEY (`id`),
  KEY `fk_character_ticket_characters_idx` (`fk_character`),
  CONSTRAINT `fk_character_ticket_characters` FOREIGN KEY (`fk_character`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Player System';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whispers`
--

DROP TABLE IF EXISTS `whispers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whispers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'The unique id of this whisper message',
  `fk_to` int(11) unsigned NOT NULL COMMENT 'guid of receiver of the whisper',
  `fk_from` int(11) unsigned NOT NULL COMMENT 'guid of sender of the whisper',
  `message` varchar(256) NOT NULL COMMENT 'The body text of the whisper message',
  `fk_ticket` int(11) unsigned DEFAULT '0' COMMENT 'The id of the ticket this whisper relates to. Foreign key to character_ticket.',
  `sent` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Stores the time when this whisper was sent.',
  PRIMARY KEY (`id`),
  KEY `fk_character_whispers_character_ticket_id_idx` (`fk_ticket`),
  KEY `fk_character_whispers_receiver_idx` (`fk_from`),
  KEY `fk_character_whispers_sender` (`fk_to`),
  CONSTRAINT `fk_character_whispers_character_ticket_id` FOREIGN KEY (`fk_ticket`) REFERENCES `ticket` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_character_whispers_receiver` FOREIGN KEY (`fk_from`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_character_whispers_sender` FOREIGN KEY (`fk_to`) REFERENCES `characters` (`guid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whispers`
--

LOCK TABLES `whispers` WRITE;
/*!40000 ALTER TABLE `whispers` DISABLE KEYS */;
/*!40000 ALTER TABLE `whispers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-19 19:28:10
