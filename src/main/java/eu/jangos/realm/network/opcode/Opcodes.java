package eu.jangos.realm.network.opcode;

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
/**
 * Opcode is an enumeration of all possible opcode that the server may send back
 * to the client.
 *
 * @author Warkdev
 * @version v0.1 BETA.
 */
public enum Opcodes {

    /**
     * Document me.
     */
    MSG_NULL_ACTION((short) 0x000),
    /**
     * Document me.
     */
    CMSG_BOOTME((short) 0x001),
    /**
     * Document me.
     */
    CMSG_DBLOOKUP((short) 0x002),
    /**
     * Document me.
     */
    SMSG_DBLOOKUP((short) 0x003),
    /**
     * Document me.
     */
    CMSG_QUERY_OBJECT_POSITION((short) 0x004),
    /**
     * Document me.
     */
    SMSG_QUERY_OBJECT_POSITION((short) 0x005),
    /**
     * Document me.
     */
    CMSG_QUERY_OBJECT_ROTATION((short) 0x006),
    /**
     * Document me.
     */
    SMSG_QUERY_OBJECT_ROTATION((short) 0x007),
    /**
     * Document me.
     */
    CMSG_WORLD_TELEPORT((short) 0x008),
    /**
     * Document me.
     */
    CMSG_TELEPORT_TO_UNIT((short) 0x009),
    /**
     * Document me.
     */
    CMSG_ZONE_MAP((short) 0x00A),
    /**
     * Document me.
     */
    SMSG_ZONE_MAP((short) 0x00B),
    /**
     * Document me.
     */
    CMSG_DEBUG_CHANGECELLZONE((short) 0x00C),
    /**
     * Document me.
     */
    CMSG_EMBLAZON_TABARD_OBSOLETE((short) 0x00D),
    /**
     * Document me.
     */
    CMSG_UNEMBLAZON_TABARD_OBSOLETE((short) 0x00E),
    /**
     * Document me.
     */
    CMSG_RECHARGE((short) 0x00F),
    /**
     * Document me.
     */
    CMSG_LEARN_SPELL((short) 0x010),
    /**
     * Document me.
     */
    CMSG_CREATEMONSTER((short) 0x011),
    /**
     * Document me.
     */
    CMSG_DESTROYMONSTER((short) 0x012),
    /**
     * Document me.
     */
    CMSG_CREATEITEM((short) 0x013),
    /**
     * Document me.
     */
    CMSG_CREATEGAMEOBJECT((short) 0x014),
    /**
     * Document me.
     */
    SMSG_CHECK_FOR_BOTS((short) 0x015),
    /**
     * Document me.
     */
    CMSG_MAKEMONSTERATTACKGUID((short) 0x016),
    /**
     * Document me.
     */
    CMSG_BOT_DETECTED2((short) 0x017),
    /**
     * Document me.
     */
    CMSG_FORCEACTION((short) 0x018),
    /**
     * Document me.
     */
    CMSG_FORCEACTIONONOTHER((short) 0x019),
    /**
     * Document me.
     */
    CMSG_FORCEACTIONSHOW((short) 0x01A),
    /**
     * Document me.
     */
    SMSG_FORCEACTIONSHOW((short) 0x01B),
    /**
     * Document me.
     */
    CMSG_PETGODMODE((short) 0x01C),
    /**
     * Document me.
     */
    SMSG_PETGODMODE((short) 0x01D),
    /**
     * Document me.
     */
    SMSG_DEBUGINFOSPELLMISS_OBSOLETE((short) 0x01E),
    /**
     * Document me.
     */
    CMSG_WEATHER_SPEED_CHEAT((short) 0x01F),
    /**
     * Document me.
     */
    CMSG_UNDRESSPLAYER((short) 0x020),
    /**
     * Document me.
     */
    CMSG_BEASTMASTER((short) 0x021),
    /**
     * Document me.
     */
    CMSG_GODMODE((short) 0x022),
    /**
     * Document me.
     */
    SMSG_GODMODE((short) 0x023),
    /**
     * Document me.
     */
    CMSG_CHEAT_SETMONEY((short) 0x024),
    /**
     * Document me.
     */
    CMSG_LEVEL_CHEAT((short) 0x025),
    /**
     * Document me.
     */
    CMSG_PET_LEVEL_CHEAT((short) 0x026),
    /**
     * Document me.
     */
    CMSG_SET_WORLDSTATE((short) 0x027),
    /**
     * Document me.
     */
    CMSG_COOLDOWN_CHEAT((short) 0x028),
    /**
     * Document me.
     */
    CMSG_USE_SKILL_CHEAT((short) 0x029),
    /**
     * Document me.
     */
    CMSG_FLAG_QUEST((short) 0x02A),
    /**
     * Document me.
     */
    CMSG_FLAG_QUEST_FINISH((short) 0x02B),
    /**
     * Document me.
     */
    CMSG_CLEAR_QUEST((short) 0x02C),
    /**
     * Document me.
     */
    CMSG_SEND_EVENT((short) 0x02D),
    /**
     * Document me.
     */
    CMSG_DEBUG_AISTATE((short) 0x02E),
    /**
     * Document me.
     */
    SMSG_DEBUG_AISTATE((short) 0x02F),
    /**
     * Document me.
     */
    CMSG_DISABLE_PVP_CHEAT((short) 0x030),
    /**
     * Document me.
     */
    CMSG_ADVANCE_SPAWN_TIME((short) 0x031),
    /**
     * Document me.
     */
    CMSG_PVP_PORT_OBSOLETE((short) 0x032),
    /**
     * Document me.
     */
    CMSG_AUTH_SRP6_BEGIN((short) 0x033),
    /**
     * Document me.
     */
    CMSG_AUTH_SRP6_PROOF((short) 0x034),
    /**
     * Document me.
     */
    CMSG_AUTH_SRP6_RECODE((short) 0x035),
    /**
     * This message is sent by the client whenever it request the creation of a
     * character.
     */
    CMSG_CHAR_CREATE((short) 0x036),
    /**
     * This message is sent by the client whenever it request character list.
     */
    CMSG_CHAR_ENUM((short) 0x037),
    /**
     * This message is sent by the client whenever it request the deletion of a
     * character.
     */
    CMSG_CHAR_DELETE((short) 0x038),
    /**
     * Document me.
     */
    SMSG_AUTH_SRP6_RESPONSE((short) 0x039),
    /**
     * This message is sent by the server whenever it confirms or invalidate the
     * character creation.
     */
    SMSG_CHAR_CREATE((short) 0x03A),
    /**
     * This message is sent by the server whenever the client is requesting the
     * char enum.
     */
    SMSG_CHAR_ENUM((short) 0x03B),
    /**
     * This message is sent by the server whenever it confirms or invalidate the
     * deletion of the character.
     */
    SMSG_CHAR_DELETE((short) 0x03C),
    /**
     * This message is sent by the client whenever it logs in a character.
     */
    CMSG_PLAYER_LOGIN((short) 0x03D),
    /**
     * Document me.
     */
    SMSG_NEW_WORLD((short) 0x03E),
    /**
     * Document me.
     */
    SMSG_TRANSFER_PENDING((short) 0x03F),
    /**
     * Document me.
     */
    SMSG_TRANSFER_ABORTED((short) 0x040),
    /**
     * Document me.
     */
    SMSG_CHARACTER_LOGIN_FAILED((short) 0x041),
    /**
     * Document me.
     */
    SMSG_LOGIN_SETTIMESPEED((short) 0x042),
    /**
     * Document me.
     */
    SMSG_GAMETIME_UPDATE((short) 0x043),
    /**
     * Document me.
     */
    CMSG_GAMETIME_SET((short) 0x044),
    /**
     * Document me.
     */
    SMSG_GAMETIME_SET((short) 0x045),
    /**
     * Document me.
     */
    CMSG_GAMESPEED_SET((short) 0x046),
    /**
     * Document me.
     */
    SMSG_GAMESPEED_SET((short) 0x047),
    /**
     * Document me.
     */
    CMSG_SERVERTIME((short) 0x048),
    /**
     * Document me.
     */
    SMSG_SERVERTIME((short) 0x049),
    /**
     * Document me.
     */
    CMSG_PLAYER_LOGOUT((short) 0x04A),
    /**
     * Document me.
     */
    CMSG_LOGOUT_REQUEST((short) 0x04B),
    /**
     * Document me.
     */
    SMSG_LOGOUT_RESPONSE((short) 0x04C),
    /**
     * Document me.
     */
    SMSG_LOGOUT_COMPLETE((short) 0x04D),
    /**
     * Document me.
     */
    CMSG_LOGOUT_CANCEL((short) 0x04E),
    /**
     * Document me.
     */
    SMSG_LOGOUT_CANCEL_ACK((short) 0x04F),
    /**
     * Document me.
     */
    CMSG_NAME_QUERY((short) 0x050),
    /**
     * Document me.
     */
    SMSG_NAME_QUERY_RESPONSE((short) 0x051),
    /**
     * Document me.
     */
    CMSG_PET_NAME_QUERY((short) 0x052),
    /**
     * Document me.
     */
    SMSG_PET_NAME_QUERY_RESPONSE((short) 0x053),
    /**
     * Document me.
     */
    CMSG_GUILD_QUERY((short) 0x054),
    /**
     * Document me.
     */
    SMSG_GUILD_QUERY_RESPONSE((short) 0x055),
    /**
     * Document me.
     */
    CMSG_ITEM_QUERY_SINGLE((short) 0x056),
    /**
     * Document me.
     */
    CMSG_ITEM_QUERY_MULTIPLE((short) 0x057),
    /**
     * Document me.
     */
    SMSG_ITEM_QUERY_SINGLE_RESPONSE((short) 0x058),
    /**
     * Document me.
     */
    SMSG_ITEM_QUERY_MULTIPLE_RESPONSE((short) 0x059),
    /**
     * Document me.
     */
    CMSG_PAGE_TEXT_QUERY((short) 0x05A),
    /**
     * Document me.
     */
    SMSG_PAGE_TEXT_QUERY_RESPONSE((short) 0x05B),
    /**
     * Document me.
     */
    CMSG_QUEST_QUERY((short) 0x05C),
    /**
     * Document me.
     */
    SMSG_QUEST_QUERY_RESPONSE((short) 0x05D),
    /**
     * Document me.
     */
    CMSG_GAMEOBJECT_QUERY((short) 0x05E),
    /**
     * Document me.
     */
    SMSG_GAMEOBJECT_QUERY_RESPONSE((short) 0x05F),
    /**
     * Document me.
     */
    CMSG_CREATURE_QUERY((short) 0x060),
    /**
     * Document me.
     */
    SMSG_CREATURE_QUERY_RESPONSE((short) 0x061),
    /**
     * Document me.
     */
    CMSG_WHO((short) 0x062),
    /**
     * Document me.
     */
    SMSG_WHO((short) 0x063),
    /**
     * Document me.
     */
    CMSG_WHOIS((short) 0x064),
    /**
     * Document me.
     */
    SMSG_WHOIS((short) 0x065),
    /**
     * Document me.
     */
    CMSG_FRIEND_LIST((short) 0x066),
    /**
     * Document me.
     */
    SMSG_FRIEND_LIST((short) 0x067),
    /**
     * Document me.
     */
    SMSG_FRIEND_STATUS((short) 0x068),
    /**
     * Document me.
     */
    CMSG_ADD_FRIEND((short) 0x069),
    /**
     * Document me.
     */
    CMSG_DEL_FRIEND((short) 0x06A),
    /**
     * Document me.
     */
    SMSG_IGNORE_LIST((short) 0x06B),
    /**
     * Document me.
     */
    CMSG_ADD_IGNORE((short) 0x06C),
    /**
     * Document me.
     */
    CMSG_DEL_IGNORE((short) 0x06D),
    /**
     * Document me.
     */
    CMSG_GROUP_INVITE((short) 0x06E),
    /**
     * Document me.
     */
    SMSG_GROUP_INVITE((short) 0x06F),
    /**
     * Document me.
     */
    CMSG_GROUP_CANCEL((short) 0x070),
    /**
     * Document me.
     */
    SMSG_GROUP_CANCEL((short) 0x071),
    /**
     * Document me.
     */
    CMSG_GROUP_ACCEPT((short) 0x072),
    /**
     * Document me.
     */
    CMSG_GROUP_DECLINE((short) 0x073),
    /**
     * Document me.
     */
    SMSG_GROUP_DECLINE((short) 0x074),
    /**
     * Document me.
     */
    CMSG_GROUP_UNINVITE((short) 0x075),
    /**
     * Document me.
     */
    CMSG_GROUP_UNINVITE_GUID((short) 0x076),
    /**
     * Document me.
     */
    SMSG_GROUP_UNINVITE((short) 0x077),
    /**
     * Document me.
     */
    CMSG_GROUP_SET_LEADER((short) 0x078),
    /**
     * Document me.
     */
    SMSG_GROUP_SET_LEADER((short) 0x079),
    /**
     * Document me.
     */
    CMSG_LOOT_METHOD((short) 0x07A),
    /**
     * Document me.
     */
    CMSG_GROUP_DISBAND((short) 0x07B),
    /**
     * Document me.
     */
    SMSG_GROUP_DESTROYED((short) 0x07C),
    /**
     * Document me.
     */
    SMSG_GROUP_LIST((short) 0x07D),
    /**
     * Document me.
     */
    SMSG_PARTY_MEMBER_STATS((short) 0x07E),
    /**
     * Document me.
     */
    SMSG_PARTY_COMMAND_RESULT((short) 0x07F),
    /**
     * Document me.
     */
    UMSG_UPDATE_GROUP_MEMBERS((short) 0x080),
    /**
     * Document me.
     */
    CMSG_GUILD_CREATE((short) 0x081),
    /**
     * Document me.
     */
    CMSG_GUILD_INVITE((short) 0x082),
    /**
     * Document me.
     */
    SMSG_GUILD_INVITE((short) 0x083),
    /**
     * Document me.
     */
    CMSG_GUILD_ACCEPT((short) 0x084),
    /**
     * Document me.
     */
    CMSG_GUILD_DECLINE((short) 0x085),
    /**
     * Document me.
     */
    SMSG_GUILD_DECLINE((short) 0x086),
    /**
     * Document me.
     */
    CMSG_GUILD_INFO((short) 0x087),
    /**
     * Document me.
     */
    SMSG_GUILD_INFO((short) 0x088),
    /**
     * Document me.
     */
    CMSG_GUILD_ROSTER((short) 0x089),
    /**
     * Document me.
     */
    SMSG_GUILD_ROSTER((short) 0x08A),
    /**
     * Document me.
     */
    CMSG_GUILD_PROMOTE((short) 0x08B),
    /**
     * Document me.
     */
    CMSG_GUILD_DEMOTE((short) 0x08C),
    /**
     * Document me.
     */
    CMSG_GUILD_LEAVE((short) 0x08D),
    /**
     * Document me.
     */
    CMSG_GUILD_REMOVE((short) 0x08E),
    /**
     * Document me.
     */
    CMSG_GUILD_DISBAND((short) 0x08F),
    /**
     * Document me.
     */
    CMSG_GUILD_LEADER((short) 0x090),
    /**
     * Document me.
     */
    CMSG_GUILD_MOTD((short) 0x091),
    /**
     * Document me.
     */
    SMSG_GUILD_EVENT((short) 0x092),
    /**
     * Document me.
     */
    SMSG_GUILD_COMMAND_RESULT((short) 0x093),
    /**
     * Document me.
     */
    UMSG_UPDATE_GUILD((short) 0x094),
    /**
     * Document me.
     */
    CMSG_MESSAGECHAT((short) 0x095),
    /**
     * Document me.
     */
    SMSG_MESSAGECHAT((short) 0x096),
    /**
     * Document me.
     */
    CMSG_JOIN_CHANNEL((short) 0x097),
    /**
     * Document me.
     */
    CMSG_LEAVE_CHANNEL((short) 0x098),
    /**
     * Document me.
     */
    SMSG_CHANNEL_NOTIFY((short) 0x099),
    /**
     * Document me.
     */
    CMSG_CHANNEL_LIST((short) 0x09A),
    /**
     * Document me.
     */
    SMSG_CHANNEL_LIST((short) 0x09B),
    /**
     * Document me.
     */
    CMSG_CHANNEL_PASSWORD((short) 0x09C),
    /**
     * Document me.
     */
    CMSG_CHANNEL_SET_OWNER((short) 0x09D),
    /**
     * Document me.
     */
    CMSG_CHANNEL_OWNER((short) 0x09E),
    /**
     * Document me.
     */
    CMSG_CHANNEL_MODERATOR((short) 0x09F),
    /**
     * Document me.
     */
    CMSG_CHANNEL_UNMODERATOR((short) 0x0A0),
    /**
     * Document me.
     */
    CMSG_CHANNEL_MUTE((short) 0x0A1),
    /**
     * Document me.
     */
    CMSG_CHANNEL_UNMUTE((short) 0x0A2),
    /**
     * Document me.
     */
    CMSG_CHANNEL_INVITE((short) 0x0A3),
    /**
     * Document me.
     */
    CMSG_CHANNEL_KICK((short) 0x0A4),
    /**
     * Document me.
     */
    CMSG_CHANNEL_BAN((short) 0x0A5),
    /**
     * Document me.
     */
    CMSG_CHANNEL_UNBAN((short) 0x0A6),
    /**
     * Document me.
     */
    CMSG_CHANNEL_ANNOUNCEMENTS((short) 0x0A7),
    /**
     * Document me.
     */
    CMSG_CHANNEL_MODERATE((short) 0x0A8),
    /**
     * Document me.
     */
    SMSG_UPDATE_OBJECT((short) 0x0A9),
    /**
     * Document me.
     */
    SMSG_DESTROY_OBJECT((short) 0x0AA),
    /**
     * Document me.
     */
    CMSG_USE_ITEM((short) 0x0AB),
    /**
     * Document me.
     */
    CMSG_OPEN_ITEM((short) 0x0AC),
    /**
     * Document me.
     */
    CMSG_READ_ITEM((short) 0x0AD),
    /**
     * Document me.
     */
    SMSG_READ_ITEM_OK((short) 0x0AE),
    /**
     * Document me.
     */
    SMSG_READ_ITEM_FAILED((short) 0x0AF),
    /**
     * Document me.
     */
    SMSG_ITEM_COOLDOWN((short) 0x0B0),
    /**
     * Document me.
     */
    CMSG_GAMEOBJ_USE((short) 0x0B1),
    /**
     * Document me.
     */
    CMSG_GAMEOBJ_CHAIR_USE_OBSOLETE((short) 0x0B2),
    /**
     * Document me.
     */
    SMSG_GAMEOBJECT_CUSTOM_ANIM((short) 0x0B3),
    /**
     * Document me.
     */
    CMSG_AREATRIGGER((short) 0x0B4),
    /**
     * Document me.
     */
    MSG_MOVE_START_FORWARD((short) 0x0B5),
    /**
     * Document me.
     */
    MSG_MOVE_START_BACKWARD((short) 0x0B6),
    /**
     * Document me.
     */
    MSG_MOVE_STOP((short) 0x0B7),
    /**
     * Document me.
     */
    MSG_MOVE_START_STRAFE_LEFT((short) 0x0B8),
    /**
     * Document me.
     */
    MSG_MOVE_START_STRAFE_RIGHT((short) 0x0B9),
    /**
     * Document me.
     */
    MSG_MOVE_STOP_STRAFE((short) 0x0BA),
    /**
     * Document me.
     */
    MSG_MOVE_JUMP((short) 0x0BB),
    /**
     * Document me.
     */
    MSG_MOVE_START_TURN_LEFT((short) 0x0BC),
    /**
     * Document me.
     */
    MSG_MOVE_START_TURN_RIGHT((short) 0x0BD),
    /**
     * Document me.
     */
    MSG_MOVE_STOP_TURN((short) 0x0BE),
    /**
     * Document me.
     */
    MSG_MOVE_START_PITCH_UP((short) 0x0BF),
    /**
     * Document me.
     */
    MSG_MOVE_START_PITCH_DOWN((short) 0x0C0),
    /**
     * Document me.
     */
    MSG_MOVE_STOP_PITCH((short) 0x0C1),
    /**
     * Document me.
     */
    MSG_MOVE_SET_RUN_MODE((short) 0x0C2),
    /**
     * Document me.
     */
    MSG_MOVE_SET_WALK_MODE((short) 0x0C3),
    /**
     * Document me.
     */
    MSG_MOVE_TOGGLE_LOGGING((short) 0x0C4),
    /**
     * Document me.
     */
    MSG_MOVE_TELEPORT((short) 0x0C5),
    /**
     * Document me.
     */
    MSG_MOVE_TELEPORT_CHEAT((short) 0x0C6),
    /**
     * Document me.
     */
    MSG_MOVE_TELEPORT_ACK((short) 0x0C7),
    /**
     * Document me.
     */
    MSG_MOVE_TOGGLE_FALL_LOGGING((short) 0x0C8),
    /**
     * Document me.
     */
    MSG_MOVE_FALL_LAND((short) 0x0C9),
    /**
     * Document me.
     */
    MSG_MOVE_START_SWIM((short) 0x0CA),
    /**
     * Document me.
     */
    MSG_MOVE_STOP_SWIM((short) 0x0CB),
    /**
     * Document me.
     */
    MSG_MOVE_SET_RUN_SPEED_CHEAT((short) 0x0CC),
    /**
     * Document me.
     */
    MSG_MOVE_SET_RUN_SPEED((short) 0x0CD),
    /**
     * Document me.
     */
    MSG_MOVE_SET_RUN_BACK_SPEED_CHEAT((short) 0x0CE),
    /**
     * Document me.
     */
    MSG_MOVE_SET_RUN_BACK_SPEED((short) 0x0CF),
    /**
     * Document me.
     */
    MSG_MOVE_SET_WALK_SPEED_CHEAT((short) 0x0D0),
    /**
     * Document me.
     */
    MSG_MOVE_SET_WALK_SPEED((short) 0x0D1),
    /**
     * Document me.
     */
    MSG_MOVE_SET_SWIM_SPEED_CHEAT((short) 0x0D2),
    /**
     * Document me.
     */
    MSG_MOVE_SET_SWIM_SPEED((short) 0x0D3),
    /**
     * Document me.
     */
    MSG_MOVE_SET_SWIM_BACK_SPEED_CHEAT((short) 0x0D4),
    /**
     * Document me.
     */
    MSG_MOVE_SET_SWIM_BACK_SPEED((short) 0x0D5),
    /**
     * Document me.
     */
    MSG_MOVE_SET_ALL_SPEED_CHEAT((short) 0x0D6),
    /**
     * Document me.
     */
    MSG_MOVE_SET_TURN_RATE_CHEAT((short) 0x0D7),
    /**
     * Document me.
     */
    MSG_MOVE_SET_TURN_RATE((short) 0x0D8),
    /**
     * Document me.
     */
    MSG_MOVE_TOGGLE_COLLISION_CHEAT((short) 0x0D9),
    /**
     * Document me.
     */
    MSG_MOVE_SET_FACING((short) 0x0DA),
    /**
     * Document me.
     */
    MSG_MOVE_SET_PITCH((short) 0x0DB),
    /**
     * Document me.
     */
    MSG_MOVE_WORLDPORT_ACK((short) 0x0DC),
    /**
     * Document me.
     */
    SMSG_MONSTER_MOVE((short) 0x0DD),
    /**
     * Document me.
     */
    SMSG_MOVE_WATER_WALK((short) 0x0DE),
    /**
     * Document me.
     */
    SMSG_MOVE_LAND_WALK((short) 0x0DF),
    /**
     * Document me.
     */
    MSG_MOVE_SET_RAW_POSITION_ACK((short) 0x0E0),
    /**
     * Document me.
     */
    CMSG_MOVE_SET_RAW_POSITION((short) 0x0E1),
    /**
     * Document me.
     */
    SMSG_FORCE_RUN_SPEED_CHANGE((short) 0x0E2),
    /**
     * Document me.
     */
    CMSG_FORCE_RUN_SPEED_CHANGE_ACK((short) 0x0E3),
    /**
     * Document me.
     */
    SMSG_FORCE_RUN_BACK_SPEED_CHANGE((short) 0x0E4),
    /**
     * Document me.
     */
    CMSG_FORCE_RUN_BACK_SPEED_CHANGE_ACK((short) 0x0E5),
    /**
     * Document me.
     */
    SMSG_FORCE_SWIM_SPEED_CHANGE((short) 0x0E6),
    /**
     * Document me.
     */
    CMSG_FORCE_SWIM_SPEED_CHANGE_ACK((short) 0x0E7),
    /**
     * Document me.
     */
    SMSG_FORCE_MOVE_ROOT((short) 0x0E8),
    /**
     * Document me.
     */
    CMSG_FORCE_MOVE_ROOT_ACK((short) 0x0E9),
    /**
     * Document me.
     */
    SMSG_FORCE_MOVE_UNROOT((short) 0x0EA),
    /**
     * Document me.
     */
    CMSG_FORCE_MOVE_UNROOT_ACK((short) 0x0EB),
    /**
     * Document me.
     */
    MSG_MOVE_ROOT((short) 0x0EC),
    /**
     * Document me.
     */
    MSG_MOVE_UNROOT((short) 0x0ED),
    /**
     * Document me.
     */
    MSG_MOVE_HEARTBEAT((short) 0x0EE),
    /**
     * Document me.
     */
    SMSG_MOVE_KNOCK_BACK((short) 0x0EF),
    /**
     * Document me.
     */
    CMSG_MOVE_KNOCK_BACK_ACK((short) 0x0F0),
    /**
     * Document me.
     */
    MSG_MOVE_KNOCK_BACK((short) 0x0F1),
    /**
     * Document me.
     */
    SMSG_MOVE_FEATHER_FALL((short) 0x0F2),
    /**
     * Document me.
     */
    SMSG_MOVE_NORMAL_FALL((short) 0x0F3),
    /**
     * Document me.
     */
    SMSG_MOVE_SET_HOVER((short) 0x0F4),
    /**
     * Document me.
     */
    SMSG_MOVE_UNSET_HOVER((short) 0x0F5),
    /**
     * Document me.
     */
    CMSG_MOVE_HOVER_ACK((short) 0x0F6),
    /**
     * Document me.
     */
    MSG_MOVE_HOVER((short) 0x0F7),
    /**
     * Document me.
     */
    CMSG_TRIGGER_CINEMATIC_CHEAT((short) 0x0F8),
    /**
     * Document me.
     */
    CMSG_OPENING_CINEMATIC((short) 0x0F9),
    /**
     * Document me.
     */
    SMSG_TRIGGER_CINEMATIC((short) 0x0FA),
    /**
     * Document me.
     */
    CMSG_NEXT_CINEMATIC_CAMERA((short) 0x0FB),
    /**
     * Document me.
     */
    CMSG_COMPLETE_CINEMATIC((short) 0x0FC),
    /**
     * Document me.
     */
    SMSG_TUTORIAL_FLAGS((short) 0x0FD),
    /**
     * Document me.
     */
    CMSG_TUTORIAL_FLAG((short) 0x0FE),
    /**
     * Document me.
     */
    CMSG_TUTORIAL_CLEAR((short) 0x0FF),
    /**
     * Document me.
     */
    CMSG_TUTORIAL_RESET((short) 0x100),
    /**
     * Document me.
     */
    CMSG_STANDSTATECHANGE((short) 0x101),
    /**
     * Document me.
     */
    CMSG_EMOTE((short) 0x102),
    /**
     * Document me.
     */
    SMSG_EMOTE((short) 0x103),
    /**
     * Document me.
     */
    CMSG_TEXT_EMOTE((short) 0x104),
    /**
     * Document me.
     */
    SMSG_TEXT_EMOTE((short) 0x105),
    /**
     * Document me.
     */
    CMSG_AUTOEQUIP_GROUND_ITEM((short) 0x106),
    /**
     * Document me.
     */
    CMSG_AUTOSTORE_GROUND_ITEM((short) 0x107),
    /**
     * Document me.
     */
    CMSG_AUTOSTORE_LOOT_ITEM((short) 0x108),
    /**
     * Document me.
     */
    CMSG_STORE_LOOT_IN_SLOT((short) 0x109),
    /**
     * Document me.
     */
    CMSG_AUTOEQUIP_ITEM((short) 0x10A),
    /**
     * Document me.
     */
    CMSG_AUTOSTORE_BAG_ITEM((short) 0x10B),
    /**
     * Document me.
     */
    CMSG_SWAP_ITEM((short) 0x10C),
    /**
     * Document me.
     */
    CMSG_SWAP_INV_ITEM((short) 0x10D),
    /**
     * Document me.
     */
    CMSG_SPLIT_ITEM((short) 0x10E),
    /**
     * Document me.
     */
    CMSG_AUTOEQUIP_ITEM_SLOT((short) 0x10F),
    /**
     * Document me.
     */
    OBSOLETE_DROP_ITEM((short) 0x110),
    /**
     * Document me.
     */
    CMSG_DESTROYITEM((short) 0x111),
    /**
     * Document me.
     */
    SMSG_INVENTORY_CHANGE_FAILURE((short) 0x112),
    /**
     * Document me.
     */
    SMSG_OPEN_CONTAINER((short) 0x113),
    /**
     * Document me.
     */
    CMSG_INSPECT((short) 0x114),
    /**
     * Document me.
     */
    SMSG_INSPECT((short) 0x115),
    /**
     * Document me.
     */
    CMSG_INITIATE_TRADE((short) 0x116),
    /**
     * Document me.
     */
    CMSG_BEGIN_TRADE((short) 0x117),
    /**
     * Document me.
     */
    CMSG_BUSY_TRADE((short) 0x118),
    /**
     * Document me.
     */
    CMSG_IGNORE_TRADE((short) 0x119),
    /**
     * Document me.
     */
    CMSG_ACCEPT_TRADE((short) 0x11A),
    /**
     * Document me.
     */
    CMSG_UNACCEPT_TRADE((short) 0x11B),
    /**
     * Document me.
     */
    CMSG_CANCEL_TRADE((short) 0x11C),
    /**
     * Document me.
     */
    CMSG_SET_TRADE_ITEM((short) 0x11D),
    /**
     * Document me.
     */
    CMSG_CLEAR_TRADE_ITEM((short) 0x11E),
    /**
     * Document me.
     */
    CMSG_SET_TRADE_GOLD((short) 0x11F),
    /**
     * Document me.
     */
    SMSG_TRADE_STATUS((short) 0x120),
    /**
     * Document me.
     */
    SMSG_TRADE_STATUS_EXTENDED((short) 0x121),
    /**
     * Document me.
     */
    SMSG_INITIALIZE_FACTIONS((short) 0x122),
    /**
     * Document me.
     */
    SMSG_SET_FACTION_VISIBLE((short) 0x123),
    /**
     * Document me.
     */
    SMSG_SET_FACTION_STANDING((short) 0x124),
    /**
     * Document me.
     */
    CMSG_SET_FACTION_ATWAR((short) 0x125),
    /**
     * Document me.
     */
    CMSG_SET_FACTION_CHEAT((short) 0x126),
    /**
     * Document me.
     */
    SMSG_SET_PROFICIENCY((short) 0x127),
    /**
     * Document me.
     */
    CMSG_SET_ACTION_BUTTON((short) 0x128),
    /**
     * Document me.
     */
    SMSG_ACTION_BUTTONS((short) 0x129),
    /**
     * Document me.
     */
    SMSG_INITIAL_SPELLS((short) 0x12A),
    /**
     * Document me.
     */
    SMSG_LEARNED_SPELL((short) 0x12B),
    /**
     * Document me.
     */
    SMSG_SUPERCEDED_SPELL((short) 0x12C),
    /**
     * Document me.
     */
    CMSG_NEW_SPELL_SLOT((short) 0x12D),
    /**
     * Document me.
     */
    CMSG_CAST_SPELL((short) 0x12E),
    /**
     * Document me.
     */
    CMSG_CANCEL_CAST((short) 0x12F),
    /**
     * Document me.
     */
    SMSG_CAST_FAILED((short) 0x130),
    /**
     * Document me.
     */
    SMSG_SPELL_START((short) 0x131),
    /**
     * Document me.
     */
    SMSG_SPELL_GO((short) 0x132),
    /**
     * Document me.
     */
    SMSG_SPELL_FAILURE((short) 0x133),
    /**
     * Document me.
     */
    SMSG_SPELL_COOLDOWN((short) 0x134),
    /**
     * Document me.
     */
    SMSG_COOLDOWN_EVENT((short) 0x135),
    /**
     * Document me.
     */
    CMSG_CANCEL_AURA((short) 0x136),
    /**
     * Document me.
     */
    SMSG_UPDATE_AURA_DURATION((short) 0x137),
    /**
     * Document me.
     */
    SMSG_PET_CAST_FAILED((short) 0x138),
    /**
     * Document me.
     */
    MSG_CHANNEL_START((short) 0x139),
    /**
     * Document me.
     */
    MSG_CHANNEL_UPDATE((short) 0x13A),
    /**
     * Document me.
     */
    CMSG_CANCEL_CHANNELLING((short) 0x13B),
    /**
     * Document me.
     */
    SMSG_AI_REACTION((short) 0x13C),
    /**
     * Document me.
     */
    CMSG_SET_SELECTION((short) 0x13D),
    /**
     * Document me.
     */
    CMSG_SET_TARGET_OBSOLETE((short) 0x13E),
    /**
     * Document me.
     */
    CMSG_UNUSED((short) 0x13F),
    /**
     * Document me.
     */
    CMSG_UNUSED2((short) 0x140),
    /**
     * Document me.
     */
    CMSG_ATTACKSWING((short) 0x141),
    /**
     * Document me.
     */
    CMSG_ATTACKSTOP((short) 0x142),
    /**
     * Document me.
     */
    SMSG_ATTACKSTART((short) 0x143),
    /**
     * Document me.
     */
    SMSG_ATTACKSTOP((short) 0x144),
    /**
     * Document me.
     */
    SMSG_ATTACKSWING_NOTINRANGE((short) 0x145),
    /**
     * Document me.
     */
    SMSG_ATTACKSWING_BADFACING((short) 0x146),
    /**
     * Document me.
     */
    SMSG_ATTACKSWING_NOTSTANDING((short) 0x147),
    /**
     * Document me.
     */
    SMSG_ATTACKSWING_DEADTARGET((short) 0x148),
    /**
     * Document me.
     */
    SMSG_ATTACKSWING_CANT_ATTACK((short) 0x149),
    /**
     * Document me.
     */
    SMSG_ATTACKERSTATEUPDATE((short) 0x14A),
    /**
     * Document me.
     */
    SMSG_VICTIMSTATEUPDATE_OBSOLETE((short) 0x14B),
    /**
     * Document me.
     */
    SMSG_DAMAGE_DONE_OBSOLETE((short) 0x14C),
    /**
     * Document me.
     */
    SMSG_DAMAGE_TAKEN_OBSOLETE((short) 0x14D),
    /**
     * Document me.
     */
    SMSG_CANCEL_COMBAT((short) 0x14E),
    /**
     * Document me.
     */
    SMSG_PLAYER_COMBAT_XP_GAIN_OBSOLETE((short) 0x14F),
    /**
     * Document me.
     */
    SMSG_SPELLHEALLOG((short) 0x150),
    /**
     * Document me.
     */
    SMSG_SPELLENERGIZELOG((short) 0x151),
    /**
     * Document me.
     */
    CMSG_SHEATHE_OBSOLETE((short) 0x152),
    /**
     * Document me.
     */
    CMSG_SAVE_PLAYER((short) 0x153),
    /**
     * Document me.
     */
    CMSG_SETDEATHBINDPOINT((short) 0x154),
    /**
     * Document me.
     */
    SMSG_BINDPOINTUPDATE((short) 0x155),
    /**
     * Document me.
     */
    CMSG_GETDEATHBINDZONE((short) 0x156),
    /**
     * Document me.
     */
    SMSG_BINDZONEREPLY((short) 0x157),
    /**
     * Document me.
     */
    SMSG_PLAYERBOUND((short) 0x158),
    /**
     * Document me.
     */
    SMSG_CLIENT_CONTROL_UPDATE((short) 0x159),
    /**
     * Document me.
     */
    CMSG_REPOP_REQUEST((short) 0x15A),
    /**
     * Document me.
     */
    SMSG_RESURRECT_REQUEST((short) 0x15B),
    /**
     * Document me.
     */
    CMSG_RESURRECT_RESPONSE((short) 0x15C),
    /**
     * Document me.
     */
    CMSG_LOOT((short) 0x15D),
    /**
     * Document me.
     */
    CMSG_LOOT_MONEY((short) 0x15E),
    /**
     * Document me.
     */
    CMSG_LOOT_RELEASE((short) 0x15F),
    /**
     * Document me.
     */
    SMSG_LOOT_RESPONSE((short) 0x160),
    /**
     * Document me.
     */
    SMSG_LOOT_RELEASE_RESPONSE((short) 0x161),
    /**
     * Document me.
     */
    SMSG_LOOT_REMOVED((short) 0x162),
    /**
     * Document me.
     */
    SMSG_LOOT_MONEY_NOTIFY((short) 0x163),
    /**
     * Document me.
     */
    SMSG_LOOT_ITEM_NOTIFY((short) 0x164),
    /**
     * Document me.
     */
    SMSG_LOOT_CLEAR_MONEY((short) 0x165),
    /**
     * Document me.
     */
    SMSG_ITEM_PUSH_RESULT((short) 0x166),
    /**
     * Document me.
     */
    SMSG_DUEL_REQUESTED((short) 0x167),
    /**
     * Document me.
     */
    SMSG_DUEL_OUTOFBOUNDS((short) 0x168),
    /**
     * Document me.
     */
    SMSG_DUEL_INBOUNDS((short) 0x169),
    /**
     * Document me.
     */
    SMSG_DUEL_COMPLETE((short) 0x16A),
    /**
     * Document me.
     */
    SMSG_DUEL_WINNER((short) 0x16B),
    /**
     * Document me.
     */
    CMSG_DUEL_ACCEPTED((short) 0x16C),
    /**
     * Document me.
     */
    CMSG_DUEL_CANCELLED((short) 0x16D),
    /**
     * Document me.
     */
    SMSG_MOUNTRESULT((short) 0x16E),
    /**
     * Document me.
     */
    SMSG_DISMOUNTRESULT((short) 0x16F),
    /**
     * Document me.
     */
    SMSG_PUREMOUNT_CANCELLED_OBSOLETE((short) 0x170),
    /**
     * Document me.
     */
    CMSG_MOUNTSPECIAL_ANIM((short) 0x171),
    /**
     * Document me.
     */
    SMSG_MOUNTSPECIAL_ANIM((short) 0x172),
    /**
     * Document me.
     */
    SMSG_PET_TAME_FAILURE((short) 0x173),
    /**
     * Document me.
     */
    CMSG_PET_SET_ACTION((short) 0x174),
    /**
     * Document me.
     */
    CMSG_PET_ACTION((short) 0x175),
    /**
     * Document me.
     */
    CMSG_PET_ABANDON((short) 0x176),
    /**
     * Document me.
     */
    CMSG_PET_RENAME((short) 0x177),
    /**
     * Document me.
     */
    SMSG_PET_NAME_INVALID((short) 0x178),
    /**
     * Document me.
     */
    SMSG_PET_SPELLS((short) 0x179),
    /**
     * Document me.
     */
    SMSG_PET_MODE((short) 0x17A),
    /**
     * Document me.
     */
    CMSG_GOSSIP_HELLO((short) 0x17B),
    /**
     * Document me.
     */
    CMSG_GOSSIP_SELECT_OPTION((short) 0x17C),
    /**
     * Document me.
     */
    SMSG_GOSSIP_MESSAGE((short) 0x17D),
    /**
     * Document me.
     */
    SMSG_GOSSIP_COMPLETE((short) 0x17E),
    /**
     * Document me.
     */
    CMSG_NPC_TEXT_QUERY((short) 0x17F),
    /**
     * Document me.
     */
    SMSG_NPC_TEXT_UPDATE((short) 0x180),
    /**
     * Document me.
     */
    SMSG_NPC_WONT_TALK((short) 0x181),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_STATUS_QUERY((short) 0x182),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_STATUS((short) 0x183),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_HELLO((short) 0x184),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_QUEST_LIST((short) 0x185),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_QUERY_QUEST((short) 0x186),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_QUEST_AUTOLAUNCH((short) 0x187),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_QUEST_DETAILS((short) 0x188),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_ACCEPT_QUEST((short) 0x189),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_COMPLETE_QUEST((short) 0x18A),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_REQUEST_ITEMS((short) 0x18B),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_REQUEST_REWARD((short) 0x18C),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_OFFER_REWARD((short) 0x18D),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_CHOOSE_REWARD((short) 0x18E),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_QUEST_INVALID((short) 0x18F),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_CANCEL((short) 0x190),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_QUEST_COMPLETE((short) 0x191),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_QUEST_FAILED((short) 0x192),
    /**
     * Document me.
     */
    CMSG_QUESTLOG_SWAP_QUEST((short) 0x193),
    /**
     * Document me.
     */
    CMSG_QUESTLOG_REMOVE_QUEST((short) 0x194),
    /**
     * Document me.
     */
    SMSG_QUESTLOG_FULL((short) 0x195),
    /**
     * Document me.
     */
    SMSG_QUESTUPDATE_FAILED((short) 0x196),
    /**
     * Document me.
     */
    SMSG_QUESTUPDATE_FAILEDTIMER((short) 0x197),
    /**
     * Document me.
     */
    SMSG_QUESTUPDATE_COMPLETE((short) 0x198),
    /**
     * Document me.
     */
    SMSG_QUESTUPDATE_ADD_KILL((short) 0x199),
    /**
     * Document me.
     */
    SMSG_QUESTUPDATE_ADD_ITEM((short) 0x19A),
    /**
     * Document me.
     */
    CMSG_QUEST_CONFIRM_ACCEPT((short) 0x19B),
    /**
     * Document me.
     */
    SMSG_QUEST_CONFIRM_ACCEPT((short) 0x19C),
    /**
     * Document me.
     */
    CMSG_PUSHQUESTTOPARTY((short) 0x19D),
    /**
     * Document me.
     */
    CMSG_LIST_INVENTORY((short) 0x19E),
    /**
     * Document me.
     */
    SMSG_LIST_INVENTORY((short) 0x19F),
    /**
     * Document me.
     */
    CMSG_SELL_ITEM((short) 0x1A0),
    /**
     * Document me.
     */
    SMSG_SELL_ITEM((short) 0x1A1),
    /**
     * Document me.
     */
    CMSG_BUY_ITEM((short) 0x1A2),
    /**
     * Document me.
     */
    CMSG_BUY_ITEM_IN_SLOT((short) 0x1A3),
    /**
     * Document me.
     */
    SMSG_BUY_ITEM((short) 0x1A4),
    /**
     * Document me.
     */
    SMSG_BUY_FAILED((short) 0x1A5),
    /**
     * Document me.
     */
    CMSG_TAXICLEARALLNODES((short) 0x1A6),
    /**
     * Document me.
     */
    CMSG_TAXIENABLEALLNODES((short) 0x1A7),
    /**
     * Document me.
     */
    CMSG_TAXISHOWNODES((short) 0x1A8),
    /**
     * Document me.
     */
    SMSG_SHOWTAXINODES((short) 0x1A9),
    /**
     * Document me.
     */
    CMSG_TAXINODE_STATUS_QUERY((short) 0x1AA),
    /**
     * Document me.
     */
    SMSG_TAXINODE_STATUS((short) 0x1AB),
    /**
     * Document me.
     */
    CMSG_TAXIQUERYAVAILABLENODES((short) 0x1AC),
    /**
     * Document me.
     */
    CMSG_ACTIVATETAXI((short) 0x1AD),
    /**
     * Document me.
     */
    SMSG_ACTIVATETAXIREPLY((short) 0x1AE),
    /**
     * Document me.
     */
    SMSG_NEW_TAXI_PATH((short) 0x1AF),
    /**
     * Document me.
     */
    CMSG_TRAINER_LIST((short) 0x1B0),
    /**
     * Document me.
     */
    SMSG_TRAINER_LIST((short) 0x1B1),
    /**
     * Document me.
     */
    CMSG_TRAINER_BUY_SPELL((short) 0x1B2),
    /**
     * Document me.
     */
    SMSG_TRAINER_BUY_SUCCEEDED((short) 0x1B3),
    /**
     * Document me.
     */
    SMSG_TRAINER_BUY_FAILED((short) 0x1B4),// uint64), uint32), uint32 (0...2)
    /**
     * Document me.
     */
    CMSG_BINDER_ACTIVATE((short) 0x1B5),
    /**
     * Document me.
     */
    SMSG_PLAYERBINDERROR((short) 0x1B6),
    /**
     * Document me.
     */
    CMSG_BANKER_ACTIVATE((short) 0x1B7),
    /**
     * Document me.
     */
    SMSG_SHOW_BANK((short) 0x1B8),
    /**
     * Document me.
     */
    CMSG_BUY_BANK_SLOT((short) 0x1B9),
    /**
     * Document me.
     */
    SMSG_BUY_BANK_SLOT_RESULT((short) 0x1BA),
    /**
     * Document me.
     */
    CMSG_PETITION_SHOWLIST((short) 0x1BB),
    /**
     * Document me.
     */
    SMSG_PETITION_SHOWLIST((short) 0x1BC),
    /**
     * Document me.
     */
    CMSG_PETITION_BUY((short) 0x1BD),
    /**
     * Document me.
     */
    CMSG_PETITION_SHOW_SIGNATURES((short) 0x1BE),
    /**
     * Document me.
     */
    SMSG_PETITION_SHOW_SIGNATURES((short) 0x1BF),
    /**
     * Document me.
     */
    CMSG_PETITION_SIGN((short) 0x1C0),
    /**
     * Document me.
     */
    SMSG_PETITION_SIGN_RESULTS((short) 0x1C1),
    /**
     * Document me.
     */
    MSG_PETITION_DECLINE((short) 0x1C2),
    /**
     * Document me.
     */
    CMSG_OFFER_PETITION((short) 0x1C3),
    /**
     * Document me.
     */
    CMSG_TURN_IN_PETITION((short) 0x1C4),
    /**
     * Document me.
     */
    SMSG_TURN_IN_PETITION_RESULTS((short) 0x1C5),
    /**
     * Document me.
     */
    CMSG_PETITION_QUERY((short) 0x1C6),
    /**
     * Document me.
     */
    SMSG_PETITION_QUERY_RESPONSE((short) 0x1C7),
    /**
     * Document me.
     */
    SMSG_FISH_NOT_HOOKED((short) 0x1C8),
    /**
     * Document me.
     */
    SMSG_FISH_ESCAPED((short) 0x1C9),
    /**
     * Document me.
     */
    CMSG_BUG((short) 0x1CA),
    /**
     * Document me.
     */
    SMSG_NOTIFICATION((short) 0x1CB),
    /**
     * Document me.
     */
    CMSG_PLAYED_TIME((short) 0x1CC),
    /**
     * Document me.
     */
    SMSG_PLAYED_TIME((short) 0x1CD),
    /**
     * Document me.
     */
    CMSG_QUERY_TIME((short) 0x1CE),
    /**
     * Document me.
     */
    SMSG_QUERY_TIME_RESPONSE((short) 0x1CF),
    /**
     * Document me.
     */
    SMSG_LOG_XPGAIN((short) 0x1D0),
    /**
     * Document me.
     */
    SMSG_AURACASTLOG((short) 0x1D1),
    /**
     * Document me.
     */
    CMSG_RECLAIM_CORPSE((short) 0x1D2),
    /**
     * Document me.
     */
    CMSG_WRAP_ITEM((short) 0x1D3),
    /**
     * Document me.
     */
    SMSG_LEVELUP_INFO((short) 0x1D4),
    /**
     * Document me.
     */
    MSG_MINIMAP_PING((short) 0x1D5),
    /**
     * Document me.
     */
    SMSG_RESISTLOG((short) 0x1D6),
    /**
     * Document me.
     */
    SMSG_ENCHANTMENTLOG((short) 0x1D7),
    /**
     * Document me.
     */
    CMSG_SET_SKILL_CHEAT((short) 0x1D8),
    /**
     * Document me.
     */
    SMSG_START_MIRROR_TIMER((short) 0x1D9),
    /**
     * Document me.
     */
    SMSG_PAUSE_MIRROR_TIMER((short) 0x1DA),
    /**
     * Document me.
     */
    SMSG_STOP_MIRROR_TIMER((short) 0x1DB),
    /**
     * This message is sent by the client whenever it ping the server.
     */
    CMSG_PING((short) 0x1DC),
    /**
     * This message is sent by the server in reply to the client.
     */
    SMSG_PING((short) 0x1DD),
    /**
     * Document me.
     */
    SMSG_CLEAR_COOLDOWN((short) 0x1DE),
    /**
     * Document me.
     */
    SMSG_GAMEOBJECT_PAGETEXT((short) 0x1DF),
    /**
     * Document me.
     */
    CMSG_SETSHEATHED((short) 0x1E0),
    /**
     * Document me.
     */
    SMSG_COOLDOWN_CHEAT((short) 0x1E1),
    /**
     * Document me.
     */
    SMSG_SPELL_DELAYED((short) 0x1E2),
    /**
     * Document me.
     */
    CMSG_PLAYER_MACRO_OBSOLETE((short) 0x1E3),
    /**
     * Document me.
     */
    SMSG_PLAYER_MACRO_OBSOLETE((short) 0x1E4),
    /**
     * Document me.
     */
    CMSG_GHOST((short) 0x1E5),
    /**
     * Document me.
     */
    CMSG_GM_INVIS((short) 0x1E6),
    /**
     * Document me.
     */
    SMSG_INVALID_PROMOTION_CODE((short) 0x1E7),
    /**
     * Document me.
     */
    MSG_GM_BIND_OTHER((short) 0x1E8),
    /**
     * Document me.
     */
    MSG_GM_SUMMON((short) 0x1E9),
    /**
     * Document me.
     */
    SMSG_ITEM_TIME_UPDATE((short) 0x1EA),
    /**
     * Document me.
     */
    SMSG_ITEM_ENCHANT_TIME_UPDATE((short) 0x1EB),
    /**
     * This message is sent by the server whenever a client is connecting.
     */
    SMSG_AUTH_CHALLENGE((short) 0x1EC),
    /**
     * This is the message sent by the client after the server sent the
     * challenge.
     */
    CMSG_AUTH_SESSION((short) 0x1ED),
    /**
     * This message is sent by the server as an answer to the CMSG_AUTH_SESSION.
     */
    SMSG_AUTH_RESPONSE((short) 0x1EE),
    /**
     * Document me.
     */
    MSG_GM_SHOWLABEL((short) 0x1EF),
    /**
     * Document me.
     */
    CMSG_PET_CAST_SPELL((short) 0x1F0),
    /**
     * Document me.
     */
    MSG_SAVE_GUILD_EMBLEM((short) 0x1F1),
    /**
     * Document me.
     */
    MSG_TABARDVENDOR_ACTIVATE((short) 0x1F2),
    /**
     * Document me.
     */
    SMSG_PLAY_SPELL_VISUAL((short) 0x1F3),
    /**
     * Document me.
     */
    CMSG_ZONEUPDATE((short) 0x1F4),
    /**
     * Document me.
     */
    SMSG_PARTYKILLLOG((short) 0x1F5),
    /**
     * Document me.
     */
    SMSG_COMPRESSED_UPDATE_OBJECT((short) 0x1F6),
    /**
     * Document me.
     */
    SMSG_PLAY_SPELL_IMPACT((short) 0x1F7),
    /**
     * Document me.
     */
    SMSG_EXPLORATION_EXPERIENCE((short) 0x1F8),
    /**
     * Document me.
     */
    CMSG_GM_SET_SECURITY_GROUP((short) 0x1F9),
    /**
     * Document me.
     */
    CMSG_GM_NUKE((short) 0x1FA),
    /**
     * Document me.
     */
    MSG_RANDOM_ROLL((short) 0x1FB),
    /**
     * Document me.
     */
    SMSG_ENVIRONMENTALDAMAGELOG((short) 0x1FC),
    /**
     * Document me.
     */
    CMSG_RWHOIS_OBSOLETE((short) 0x1FD),
    /**
     * Document me.
     */
    SMSG_RWHOIS((short) 0x1FE),
    /**
     * Document me.
     */
    MSG_LOOKING_FOR_GROUP((short) 0x1FF),
    /**
     * Document me.
     */
    CMSG_SET_LOOKING_FOR_GROUP((short) 0x200),
    /**
     * Document me.
     */
    CMSG_UNLEARN_SPELL((short) 0x201),
    /**
     * Document me.
     */
    CMSG_UNLEARN_SKILL((short) 0x202),
    /**
     * Document me.
     */
    SMSG_REMOVED_SPELL((short) 0x203),
    /**
     * Document me.
     */
    CMSG_DECHARGE((short) 0x204),
    /**
     * Document me.
     */
    CMSG_GMTICKET_CREATE((short) 0x205),
    /**
     * Document me.
     */
    SMSG_GMTICKET_CREATE((short) 0x206),
    /**
     * Document me.
     */
    CMSG_GMTICKET_UPDATETEXT((short) 0x207),
    /**
     * Document me.
     */
    SMSG_GMTICKET_UPDATETEXT((short) 0x208),
    /**
     * Document me.
     */
    SMSG_ACCOUNT_DATA_TIMES((short) 0x209),
    /**
     * Document me.
     */
    CMSG_REQUEST_ACCOUNT_DATA((short) 0x20A),
    /**
     * Document me.
     */
    CMSG_UPDATE_ACCOUNT_DATA((short) 0x20B),
    /**
     * Document me.
     */
    SMSG_UPDATE_ACCOUNT_DATA((short) 0x20C),
    /**
     * Document me.
     */
    SMSG_CLEAR_FAR_SIGHT_IMMEDIATE((short) 0x20D),
    /**
     * Document me.
     */
    SMSG_POWERGAINLOG_OBSOLETE((short) 0x20E),
    /**
     * Document me.
     */
    CMSG_GM_TEACH((short) 0x20F),
    /**
     * Document me.
     */
    CMSG_GM_CREATE_ITEM_TARGET((short) 0x210),
    /**
     * Document me.
     */
    CMSG_GMTICKET_GETTICKET((short) 0x211),
    /**
     * Document me.
     */
    SMSG_GMTICKET_GETTICKET((short) 0x212),
    /**
     * Document me.
     */
    CMSG_UNLEARN_TALENTS((short) 0x213),
    /**
     * Document me.
     */
    SMSG_GAMEOBJECT_SPAWN_ANIM_OBSOLETE((short) 0x214),
    /**
     * Document me.
     */
    SMSG_GAMEOBJECT_DESPAWN_ANIM((short) 0x215),
    /**
     * Document me.
     */
    MSG_CORPSE_QUERY((short) 0x216),
    /**
     * Document me.
     */
    CMSG_GMTICKET_DELETETICKET((short) 0x217),
    /**
     * Document me.
     */
    SMSG_GMTICKET_DELETETICKET((short) 0x218),
    /**
     * Document me.
     */
    SMSG_CHAT_WRONG_FACTION((short) 0x219),
    /**
     * Document me.
     */
    CMSG_GMTICKET_SYSTEMSTATUS((short) 0x21A),
    /**
     * Document me.
     */
    SMSG_GMTICKET_SYSTEMSTATUS((short) 0x21B),
    /**
     * Document me.
     */
    CMSG_SPIRIT_HEALER_ACTIVATE((short) 0x21C),
    /**
     * Document me.
     */
    CMSG_SET_STAT_CHEAT((short) 0x21D),
    /**
     * Document me.
     */
    SMSG_SET_REST_START((short) 0x21E),
    /**
     * Document me.
     */
    CMSG_SKILL_BUY_STEP((short) 0x21F),
    /**
     * Document me.
     */
    CMSG_SKILL_BUY_RANK((short) 0x220),
    /**
     * Document me.
     */
    CMSG_XP_CHEAT((short) 0x221),
    /**
     * Document me.
     */
    SMSG_SPIRIT_HEALER_CONFIRM((short) 0x222),
    /**
     * Document me.
     */
    CMSG_CHARACTER_POINT_CHEAT((short) 0x223),
    /**
     * Document me.
     */
    SMSG_GOSSIP_POI((short) 0x224),
    /**
     * Document me.
     */
    CMSG_CHAT_IGNORED((short) 0x225),
    /**
     * Document me.
     */
    CMSG_GM_VISION((short) 0x226),
    /**
     * Document me.
     */
    CMSG_SERVER_COMMAND((short) 0x227),
    /**
     * Document me.
     */
    CMSG_GM_SILENCE((short) 0x228),
    /**
     * Document me.
     */
    CMSG_GM_REVEALTO((short) 0x229),
    /**
     * Document me.
     */
    CMSG_GM_RESURRECT((short) 0x22A),
    /**
     * Document me.
     */
    CMSG_GM_SUMMONMOB((short) 0x22B),
    /**
     * Document me.
     */
    CMSG_GM_MOVECORPSE((short) 0x22C),
    /**
     * Document me.
     */
    CMSG_GM_FREEZE((short) 0x22D),
    /**
     * Document me.
     */
    CMSG_GM_UBERINVIS((short) 0x22E),
    /**
     * Document me.
     */
    CMSG_GM_REQUEST_PLAYER_INFO((short) 0x22F),
    /**
     * Document me.
     */
    SMSG_GM_PLAYER_INFO((short) 0x230),
    /**
     * Document me.
     */
    CMSG_GUILD_RANK((short) 0x231),
    /**
     * Document me.
     */
    CMSG_GUILD_ADD_RANK((short) 0x232),
    /**
     * Document me.
     */
    CMSG_GUILD_DEL_RANK((short) 0x233),
    /**
     * Document me.
     */
    CMSG_GUILD_SET_PUBLIC_NOTE((short) 0x234),
    /**
     * Document me.
     */
    CMSG_GUILD_SET_OFFICER_NOTE((short) 0x235),
    /**
     * Document me.
     */
    SMSG_LOGIN_VERIFY_WORLD((short) 0x236),
    /**
     * Document me.
     */
    CMSG_CLEAR_EXPLORATION((short) 0x237),
    /**
     * Document me.
     */
    CMSG_SEND_MAIL((short) 0x238),
    /**
     * Document me.
     */
    SMSG_SEND_MAIL_RESULT((short) 0x239),
    /**
     * Document me.
     */
    CMSG_GET_MAIL_LIST((short) 0x23A),
    /**
     * Document me.
     */
    SMSG_MAIL_LIST_RESULT((short) 0x23B),
    /**
     * Document me.
     */
    CMSG_BATTLEFIELD_LIST((short) 0x23C),
    /**
     * Document me.
     */
    SMSG_BATTLEFIELD_LIST((short) 0x23D),
    /**
     * Document me.
     */
    CMSG_BATTLEFIELD_JOIN((short) 0x23E),
    /**
     * Document me.
     */
    SMSG_BATTLEFIELD_WIN_OBSOLETE((short) 0x23F),
    /**
     * Document me.
     */
    SMSG_BATTLEFIELD_LOSE_OBSOLETE((short) 0x240),
    /**
     * Document me.
     */
    CMSG_TAXICLEARNODE((short) 0x241),
    /**
     * Document me.
     */
    CMSG_TAXIENABLENODE((short) 0x242),
    /**
     * Document me.
     */
    CMSG_ITEM_TEXT_QUERY((short) 0x243),
    /**
     * Document me.
     */
    SMSG_ITEM_TEXT_QUERY_RESPONSE((short) 0x244),
    /**
     * Document me.
     */
    CMSG_MAIL_TAKE_MONEY((short) 0x245),
    /**
     * Document me.
     */
    CMSG_MAIL_TAKE_ITEM((short) 0x246),
    /**
     * Document me.
     */
    CMSG_MAIL_MARK_AS_READ((short) 0x247),
    /**
     * Document me.
     */
    CMSG_MAIL_RETURN_TO_SENDER((short) 0x248),
    /**
     * Document me.
     */
    CMSG_MAIL_DELETE((short) 0x249),
    /**
     * Document me.
     */
    CMSG_MAIL_CREATE_TEXT_ITEM((short) 0x24A),
    /**
     * Document me.
     */
    SMSG_SPELLLOGMISS((short) 0x24B),
    /**
     * Document me.
     */
    SMSG_SPELLLOGEXECUTE((short) 0x24C),
    /**
     * Document me.
     */
    SMSG_DEBUGAURAPROC((short) 0x24D),
    /**
     * Document me.
     */
    SMSG_PERIODICAURALOG((short) 0x24E),
    /**
     * Document me.
     */
    SMSG_SPELLDAMAGESHIELD((short) 0x24F),
    /**
     * Document me.
     */
    SMSG_SPELLNONMELEEDAMAGELOG((short) 0x250),
    /**
     * Document me.
     */
    CMSG_LEARN_TALENT((short) 0x251),
    /**
     * Document me.
     */
    SMSG_RESURRECT_FAILED((short) 0x252),
    /**
     * Document me.
     */
    CMSG_TOGGLE_PVP((short) 0x253),
    /**
     * Document me.
     */
    SMSG_ZONE_UNDER_ATTACK((short) 0x254),
    /**
     * Document me.
     */
    MSG_AUCTION_HELLO((short) 0x255),
    /**
     * Document me.
     */
    CMSG_AUCTION_SELL_ITEM((short) 0x256),
    /**
     * Document me.
     */
    CMSG_AUCTION_REMOVE_ITEM((short) 0x257),
    /**
     * Document me.
     */
    CMSG_AUCTION_LIST_ITEMS((short) 0x258),
    /**
     * Document me.
     */
    CMSG_AUCTION_LIST_OWNER_ITEMS((short) 0x259),
    /**
     * Document me.
     */
    CMSG_AUCTION_PLACE_BID((short) 0x25A),
    /**
     * Document me.
     */
    SMSG_AUCTION_COMMAND_RESULT((short) 0x25B),
    /**
     * Document me.
     */
    SMSG_AUCTION_LIST_RESULT((short) 0x25C),
    /**
     * Document me.
     */
    SMSG_AUCTION_OWNER_LIST_RESULT((short) 0x25D),
    /**
     * Document me.
     */
    SMSG_AUCTION_BIDDER_NOTIFICATION((short) 0x25E),
    /**
     * Document me.
     */
    SMSG_AUCTION_OWNER_NOTIFICATION((short) 0x25F),
    /**
     * Document me.
     */
    SMSG_PROCRESIST((short) 0x260),
    /**
     * Document me.
     */
    SMSG_STANDSTATE_CHANGE_FAILURE_OBSOLETE((short) 0x261),
    /**
     * Document me.
     */
    SMSG_DISPEL_FAILED((short) 0x262),
    /**
     * Document me.
     */
    SMSG_SPELLORDAMAGE_IMMUNE((short) 0x263),
    /**
     * Document me.
     */
    CMSG_AUCTION_LIST_BIDDER_ITEMS((short) 0x264),
    /**
     * Document me.
     */
    SMSG_AUCTION_BIDDER_LIST_RESULT((short) 0x265),
    /**
     * Document me.
     */
    SMSG_SET_FLAT_SPELL_MODIFIER((short) 0x266),
    /**
     * Document me.
     */
    SMSG_SET_PCT_SPELL_MODIFIER((short) 0x267),
    /**
     * Document me.
     */
    CMSG_SET_AMMO((short) 0x268),
    /**
     * Document me.
     */
    SMSG_CORPSE_RECLAIM_DELAY((short) 0x269),
    /**
     * Document me.
     */
    CMSG_SET_ACTIVE_MOVER((short) 0x26A),
    /**
     * Document me.
     */
    CMSG_PET_CANCEL_AURA((short) 0x26B),
    /**
     * Document me.
     */
    CMSG_PLAYER_AI_CHEAT((short) 0x26C),
    /**
     * Document me.
     */
    CMSG_CANCEL_AUTO_REPEAT_SPELL((short) 0x26D),
    /**
     * Document me.
     */
    MSG_GM_ACCOUNT_ONLINE((short) 0x26E),
    /**
     * Document me.
     */
    MSG_LIST_STABLED_PETS((short) 0x26F),
    /**
     * Document me.
     */
    CMSG_STABLE_PET((short) 0x270),
    /**
     * Document me.
     */
    CMSG_UNSTABLE_PET((short) 0x271),
    /**
     * Document me.
     */
    CMSG_BUY_STABLE_SLOT((short) 0x272),
    /**
     * Document me.
     */
    SMSG_STABLE_RESULT((short) 0x273),
    /**
     * Document me.
     */
    CMSG_STABLE_REVIVE_PET((short) 0x274),
    /**
     * Document me.
     */
    CMSG_STABLE_SWAP_PET((short) 0x275),
    /**
     * Document me.
     */
    MSG_QUEST_PUSH_RESULT((short) 0x276),
    /**
     * Document me.
     */
    SMSG_PLAY_MUSIC((short) 0x277),
    /**
     * Document me.
     */
    SMSG_PLAY_OBJECT_SOUND((short) 0x278),
    /**
     * Document me.
     */
    CMSG_REQUEST_PET_INFO((short) 0x279),
    /**
     * Document me.
     */
    CMSG_FAR_SIGHT((short) 0x27A),
    /**
     * Document me.
     */
    SMSG_SPELLDISPELLOG((short) 0x27B),
    /**
     * Document me.
     */
    SMSG_DAMAGE_CALC_LOG((short) 0x27C),
    /**
     * Document me.
     */
    CMSG_ENABLE_DAMAGE_LOG((short) 0x27D),
    /**
     * Document me.
     */
    CMSG_GROUP_CHANGE_SUB_GROUP((short) 0x27E),
    /**
     * Document me.
     */
    CMSG_REQUEST_PARTY_MEMBER_STATS((short) 0x27F),
    /**
     * Document me.
     */
    CMSG_GROUP_SWAP_SUB_GROUP((short) 0x280),
    /**
     * Document me.
     */
    CMSG_RESET_FACTION_CHEAT((short) 0x281),
    /**
     * Document me.
     */
    CMSG_AUTOSTORE_BANK_ITEM((short) 0x282),
    /**
     * Document me.
     */
    CMSG_AUTOBANK_ITEM((short) 0x283),
    /**
     * Document me.
     */
    MSG_QUERY_NEXT_MAIL_TIME((short) 0x284),
    /**
     * Document me.
     */
    SMSG_RECEIVED_MAIL((short) 0x285),
    /**
     * Document me.
     */
    SMSG_RAID_GROUP_ONLY((short) 0x286),
    /**
     * Document me.
     */
    CMSG_SET_DURABILITY_CHEAT((short) 0x287),
    /**
     * Document me.
     */
    CMSG_SET_PVP_RANK_CHEAT((short) 0x288),
    /**
     * Document me.
     */
    CMSG_ADD_PVP_MEDAL_CHEAT((short) 0x289),
    /**
     * Document me.
     */
    CMSG_DEL_PVP_MEDAL_CHEAT((short) 0x28A),
    /**
     * Document me.
     */
    CMSG_SET_PVP_TITLE((short) 0x28B),
    /**
     * Document me.
     */
    SMSG_PVP_CREDIT((short) 0x28C),
    /**
     * Document me.
     */
    SMSG_AUCTION_REMOVED_NOTIFICATION((short) 0x28D),
    /**
     * Document me.
     */
    CMSG_GROUP_RAID_CONVERT((short) 0x28E),
    /**
     * Document me.
     */
    CMSG_GROUP_ASSISTANT_LEADER((short) 0x28F),
    /**
     * Document me.
     */
    CMSG_BUYBACK_ITEM((short) 0x290),
    /**
     * Document me.
     */
    SMSG_SERVER_MESSAGE((short) 0x291),
    /**
     * Document me.
     */
    CMSG_MEETINGSTONE_JOIN((short) 0x292),// lua: SetSavedInstanceExtend
    /**
     * Document me.
     */
    CMSG_MEETINGSTONE_LEAVE((short) 0x293),
    /**
     * Document me.
     */
    CMSG_MEETINGSTONE_CHEAT((short) 0x294),
    /**
     * Document me.
     */
    SMSG_MEETINGSTONE_SETQUEUE((short) 0x295),
    /**
     * Document me.
     */
    CMSG_MEETINGSTONE_INFO((short) 0x296),
    /**
     * Document me.
     */
    SMSG_MEETINGSTONE_COMPLETE((short) 0x297),
    /**
     * Document me.
     */
    SMSG_MEETINGSTONE_IN_PROGRESS((short) 0x298),
    /**
     * Document me.
     */
    SMSG_MEETINGSTONE_MEMBER_ADDED((short) 0x299),
    /**
     * Document me.
     */
    CMSG_GMTICKETSYSTEM_TOGGLE((short) 0x29A),
    /**
     * Document me.
     */
    CMSG_CANCEL_GROWTH_AURA((short) 0x29B),
    /**
     * Document me.
     */
    SMSG_CANCEL_AUTO_REPEAT((short) 0x29C),
    /**
     * Document me.
     */
    SMSG_STANDSTATE_UPDATE((short) 0x29D),
    /**
     * Document me.
     */
    SMSG_LOOT_ALL_PASSED((short) 0x29E),
    /**
     * Document me.
     */
    SMSG_LOOT_ROLL_WON((short) 0x29F),
    /**
     * Document me.
     */
    CMSG_LOOT_ROLL((short) 0x2A0),
    /**
     * Document me.
     */
    SMSG_LOOT_START_ROLL((short) 0x2A1),
    /**
     * Document me.
     */
    SMSG_LOOT_ROLL((short) 0x2A2),
    /**
     * Document me.
     */
    CMSG_LOOT_MASTER_GIVE((short) 0x2A3),
    /**
     * Document me.
     */
    SMSG_LOOT_MASTER_LIST((short) 0x2A4),
    /**
     * Document me.
     */
    SMSG_SET_FORCED_REACTIONS((short) 0x2A5),
    /**
     * Document me.
     */
    SMSG_SPELL_FAILED_OTHER((short) 0x2A6),
    /**
     * Document me.
     */
    SMSG_GAMEOBJECT_RESET_STATE((short) 0x2A7),
    /**
     * Document me.
     */
    CMSG_REPAIR_ITEM((short) 0x2A8),
    /**
     * Document me.
     */
    SMSG_CHAT_PLAYER_NOT_FOUND((short) 0x2A9),
    /**
     * Document me.
     */
    MSG_TALENT_WIPE_CONFIRM((short) 0x2AA),
    /**
     * Document me.
     */
    SMSG_SUMMON_REQUEST((short) 0x2AB),
    /**
     * Document me.
     */
    CMSG_SUMMON_RESPONSE((short) 0x2AC),
    /**
     * Document me.
     */
    MSG_MOVE_TOGGLE_GRAVITY_CHEAT((short) 0x2AD),
    /**
     * Document me.
     */
    SMSG_MONSTER_MOVE_TRANSPORT((short) 0x2AE),
    /**
     * Document me.
     */
    SMSG_PET_BROKEN((short) 0x2AF),
    /**
     * Document me.
     */
    MSG_MOVE_FEATHER_FALL((short) 0x2B0),
    /**
     * Document me.
     */
    MSG_MOVE_WATER_WALK((short) 0x2B1),
    /**
     * Document me.
     */
    CMSG_SERVER_BROADCAST((short) 0x2B2),
    /**
     * Document me.
     */
    CMSG_SELF_RES((short) 0x2B3),
    /**
     * Document me.
     */
    SMSG_FEIGN_DEATH_RESISTED((short) 0x2B4),
    /**
     * Document me.
     */
    CMSG_RUN_SCRIPT((short) 0x2B5),
    /**
     * Document me.
     */
    SMSG_SCRIPT_MESSAGE((short) 0x2B6),
    /**
     * Document me.
     */
    SMSG_DUEL_COUNTDOWN((short) 0x2B7),
    /**
     * Document me.
     */
    SMSG_AREA_TRIGGER_MESSAGE((short) 0x2B8),
    /**
     * Document me.
     */
    CMSG_TOGGLE_HELM((short) 0x2B9),
    /**
     * Document me.
     */
    CMSG_TOGGLE_CLOAK((short) 0x2BA),
    /**
     * Document me.
     */
    SMSG_MEETINGSTONE_JOINFAILED((short) 0x2BB),
    /**
     * Document me.
     */
    SMSG_PLAYER_SKINNED((short) 0x2BC),
    /**
     * Document me.
     */
    SMSG_DURABILITY_DAMAGE_DEATH((short) 0x2BD),
    /**
     * Document me.
     */
    CMSG_SET_EXPLORATION((short) 0x2BE),
    /**
     * Document me.
     */
    CMSG_SET_ACTIONBAR_TOGGLES((short) 0x2BF),
    /**
     * Document me.
     */
    UMSG_DELETE_GUILD_CHARTER((short) 0x2C0),
    /**
     * Document me.
     */
    MSG_PETITION_RENAME((short) 0x2C1),
    /**
     * Document me.
     */
    SMSG_INIT_WORLD_STATES((short) 0x2C2),
    /**
     * Document me.
     */
    SMSG_UPDATE_WORLD_STATE((short) 0x2C3),
    /**
     * Document me.
     */
    CMSG_ITEM_NAME_QUERY((short) 0x2C4),
    /**
     * Document me.
     */
    SMSG_ITEM_NAME_QUERY_RESPONSE((short) 0x2C5),
    /**
     * Document me.
     */
    SMSG_PET_ACTION_FEEDBACK((short) 0x2C6),
    /**
     * Document me.
     */
    CMSG_CHAR_RENAME((short) 0x2C7),
    /**
     * Document me.
     */
    SMSG_CHAR_RENAME((short) 0x2C8),
    /**
     * Document me.
     */
    CMSG_MOVE_SPLINE_DONE((short) 0x2C9),
    /**
     * Document me.
     */
    CMSG_MOVE_FALL_RESET((short) 0x2CA),
    /**
     * Document me.
     */
    SMSG_INSTANCE_SAVE_CREATED((short) 0x2CB),
    /**
     * Document me.
     */
    SMSG_RAID_INSTANCE_INFO((short) 0x2CC),
    /**
     * Document me.
     */
    CMSG_REQUEST_RAID_INFO((short) 0x2CD),
    /**
     * Document me.
     */
    CMSG_MOVE_TIME_SKIPPED((short) 0x2CE),
    /**
     * Document me.
     */
    CMSG_MOVE_FEATHER_FALL_ACK((short) 0x2CF),
    /**
     * Document me.
     */
    CMSG_MOVE_WATER_WALK_ACK((short) 0x2D0),
    /**
     * Document me.
     */
    CMSG_MOVE_NOT_ACTIVE_MOVER((short) 0x2D1),
    /**
     * Document me.
     */
    SMSG_PLAY_SOUND((short) 0x2D2),
    /**
     * Document me.
     */
    CMSG_BATTLEFIELD_STATUS((short) 0x2D3),
    /**
     * Document me.
     */
    SMSG_BATTLEFIELD_STATUS((short) 0x2D4),
    /**
     * Document me.
     */
    CMSG_BATTLEFIELD_PORT((short) 0x2D5),
    /**
     * Document me.
     */
    MSG_INSPECT_HONOR_STATS((short) 0x2D6),
    /**
     * Document me.
     */
    CMSG_BATTLEMASTER_HELLO((short) 0x2D7),
    /**
     * Document me.
     */
    CMSG_MOVE_START_SWIM_CHEAT((short) 0x2D8),
    /**
     * Document me.
     */
    CMSG_MOVE_STOP_SWIM_CHEAT((short) 0x2D9),
    /**
     * Document me.
     */
    SMSG_FORCE_WALK_SPEED_CHANGE((short) 0x2DA),
    /**
     * Document me.
     */
    CMSG_FORCE_WALK_SPEED_CHANGE_ACK((short) 0x2DB),
    /**
     * Document me.
     */
    SMSG_FORCE_SWIM_BACK_SPEED_CHANGE((short) 0x2DC),
    /**
     * Document me.
     */
    CMSG_FORCE_SWIM_BACK_SPEED_CHANGE_ACK((short) 0x2DD),
    /**
     * Document me.
     */
    SMSG_FORCE_TURN_RATE_CHANGE((short) 0x2DE),
    /**
     * Document me.
     */
    CMSG_FORCE_TURN_RATE_CHANGE_ACK((short) 0x2DF),
    /**
     * Document me.
     */
    MSG_PVP_LOG_DATA((short) 0x2E0),
    /**
     * Document me.
     */
    CMSG_LEAVE_BATTLEFIELD((short) 0x2E1),
    /**
     * Document me.
     */
    CMSG_AREA_SPIRIT_HEALER_QUERY((short) 0x2E2),
    /**
     * Document me.
     */
    CMSG_AREA_SPIRIT_HEALER_QUEUE((short) 0x2E3),
    /**
     * Document me.
     */
    SMSG_AREA_SPIRIT_HEALER_TIME((short) 0x2E4),
    /**
     * Document me.
     */
    CMSG_GM_UNTEACH((short) 0x2E5),
    /**
     * Document me.
     */
    SMSG_WARDEN_DATA((short) 0x2E6),
    /**
     * Document me.
     */
    CMSG_WARDEN_DATA((short) 0x2E7),
    /**
     * Document me.
     */
    SMSG_GROUP_JOINED_BATTLEGROUND((short) 0x2E8),
    /**
     * Document me.
     */
    MSG_BATTLEGROUND_PLAYER_POSITIONS((short) 0x2E9),
    /**
     * Document me.
     */
    CMSG_PET_STOP_ATTACK((short) 0x2EA),
    /**
     * Document me.
     */
    SMSG_BINDER_CONFIRM((short) 0x2EB),
    /**
     * Document me.
     */
    SMSG_BATTLEGROUND_PLAYER_JOINED((short) 0x2EC),
    /**
     * Document me.
     */
    SMSG_BATTLEGROUND_PLAYER_LEFT((short) 0x2ED),
    /**
     * Document me.
     */
    CMSG_BATTLEMASTER_JOIN((short) 0x2EE),
    /**
     * Document me.
     */
    SMSG_ADDON_INFO((short) 0x2EF),
    /**
     * Document me.
     */
    CMSG_PET_UNLEARN((short) 0x2F0),
    /**
     * Document me.
     */
    SMSG_PET_UNLEARN_CONFIRM((short) 0x2F1),
    /**
     * Document me.
     */
    SMSG_PARTY_MEMBER_STATS_FULL((short) 0x2F2),
    /**
     * Document me.
     */
    CMSG_PET_SPELL_AUTOCAST((short) 0x2F3),
    /**
     * Document me.
     */
    SMSG_WEATHER((short) 0x2F4),
    /**
     * Document me.
     */
    SMSG_PLAY_TIME_WARNING((short) 0x2F5),
    /**
     * Document me.
     */
    SMSG_MINIGAME_SETUP((short) 0x2F6),
    /**
     * Document me.
     */
    SMSG_MINIGAME_STATE((short) 0x2F7),
    /**
     * Document me.
     */
    CMSG_MINIGAME_MOVE((short) 0x2F8),
    /**
     * Document me.
     */
    SMSG_MINIGAME_MOVE_FAILED((short) 0x2F9),
    /**
     * Document me.
     */
    SMSG_RAID_INSTANCE_MESSAGE((short) 0x2FA),
    /**
     * Document me.
     */
    SMSG_COMPRESSED_MOVES((short) 0x2FB),
    /**
     * Document me.
     */
    CMSG_GUILD_INFO_TEXT((short) 0x2FC),
    /**
     * Document me.
     */
    SMSG_CHAT_RESTRICTED((short) 0x2FD),
    /**
     * Document me.
     */
    SMSG_SPLINE_SET_RUN_SPEED((short) 0x2FE),
    /**
     * Document me.
     */
    SMSG_SPLINE_SET_RUN_BACK_SPEED((short) 0x2FF),
    /**
     * Document me.
     */
    SMSG_SPLINE_SET_SWIM_SPEED((short) 0x300),
    /**
     * Document me.
     */
    SMSG_SPLINE_SET_WALK_SPEED((short) 0x301),
    /**
     * Document me.
     */
    SMSG_SPLINE_SET_SWIM_BACK_SPEED((short) 0x302),
    /**
     * Document me.
     */
    SMSG_SPLINE_SET_TURN_RATE((short) 0x303),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_UNROOT((short) 0x304),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_FEATHER_FALL((short) 0x305),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_NORMAL_FALL((short) 0x306),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_SET_HOVER((short) 0x307),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_UNSET_HOVER((short) 0x308),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_WATER_WALK((short) 0x309),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_LAND_WALK((short) 0x30A),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_START_SWIM((short) 0x30B),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_STOP_SWIM((short) 0x30C),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_SET_RUN_MODE((short) 0x30D),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_SET_WALK_MODE((short) 0x30E),
    /**
     * Document me.
     */
    CMSG_GM_NUKE_ACCOUNT((short) 0x30F),
    /**
     * Document me.
     */
    MSG_GM_DESTROY_CORPSE((short) 0x310),
    /**
     * Document me.
     */
    CMSG_GM_DESTROY_ONLINE_CORPSE((short) 0x311),
    /**
     * Document me.
     */
    CMSG_ACTIVATETAXIEXPRESS((short) 0x312),
    /**
     * Document me.
     */
    SMSG_SET_FACTION_ATWAR((short) 0x313),
    /**
     * Document me.
     */
    SMSG_GAMETIMEBIAS_SET((short) 0x314),
    /**
     * Document me.
     */
    CMSG_DEBUG_ACTIONS_START((short) 0x315),
    /**
     * Document me.
     */
    CMSG_DEBUG_ACTIONS_STOP((short) 0x316),
    /**
     * Document me.
     */
    CMSG_SET_FACTION_INACTIVE((short) 0x317),
    /**
     * Document me.
     */
    CMSG_SET_WATCHED_FACTION((short) 0x318),
    /**
     * Document me.
     */
    MSG_MOVE_TIME_SKIPPED((short) 0x319),
    /**
     * Document me.
     */
    SMSG_SPLINE_MOVE_ROOT((short) 0x31A),
    /**
     * Document me.
     */
    CMSG_SET_EXPLORATION_ALL((short) 0x31B),
    /**
     * Document me.
     */
    SMSG_INVALIDATE_PLAYER((short) 0x31C),
    /**
     * Document me.
     */
    CMSG_RESET_INSTANCES((short) 0x31D),
    /**
     * Document me.
     */
    SMSG_INSTANCE_RESET((short) 0x31E),
    /**
     * Document me.
     */
    SMSG_INSTANCE_RESET_FAILED((short) 0x31F),
    /**
     * Document me.
     */
    SMSG_UPDATE_LAST_INSTANCE((short) 0x320),
    /**
     * Document me.
     */
    MSG_RAID_TARGET_UPDATE((short) 0x321),
    /**
     * Document me.
     */
    MSG_RAID_READY_CHECK((short) 0x322),
    /**
     * Document me.
     */
    CMSG_LUA_USAGE((short) 0x323),
    /**
     * Document me.
     */
    SMSG_PET_ACTION_SOUND((short) 0x324),
    /**
     * Document me.
     */
    SMSG_PET_DISMISS_SOUND((short) 0x325),
    /**
     * Document me.
     */
    SMSG_GHOSTEE_GONE((short) 0x326),
    /**
     * Document me.
     */
    CMSG_GM_UPDATE_TICKET_STATUS((short) 0x327),
    /**
     * Document me.
     */
    SMSG_GM_TICKET_STATUS_UPDATE((short) 0x328),
    /**
     * Document me.
     */
    CMSG_GMSURVEY_SUBMIT((short) 0x32A),
    /**
     * Document me.
     */
    SMSG_UPDATE_INSTANCE_OWNERSHIP((short) 0x32B),
    /**
     * Document me.
     */
    CMSG_IGNORE_KNOCKBACK_CHEAT((short) 0x32C),
    /**
     * Document me.
     */
    SMSG_CHAT_PLAYER_AMBIGUOUS((short) 0x32D),
    /**
     * Document me.
     */
    MSG_DELAY_GHOST_TELEPORT((short) 0x32E),
    /**
     * Document me.
     */
    SMSG_SPELLINSTAKILLLOG((short) 0x32F),
    /**
     * Document me.
     */
    SMSG_SPELL_UPDATE_CHAIN_TARGETS((short) 0x330),
    /**
     * Document me.
     */
    CMSG_CHAT_FILTERED((short) 0x331),
    /**
     * Document me.
     */
    SMSG_EXPECTED_SPAM_RECORDS((short) 0x332),
    /**
     * Document me.
     */
    SMSG_SPELLSTEALLOG((short) 0x333),
    /**
     * Document me.
     */
    CMSG_LOTTERY_QUERY_OBSOLETE((short) 0x334),
    /**
     * Document me.
     */
    SMSG_LOTTERY_QUERY_RESULT_OBSOLETE((short) 0x335),
    /**
     * Document me.
     */
    CMSG_BUY_LOTTERY_TICKET_OBSOLETE((short) 0x336),
    /**
     * Document me.
     */
    SMSG_LOTTERY_RESULT_OBSOLETE((short) 0x337),
    /**
     * Document me.
     */
    SMSG_CHARACTER_PROFILE((short) 0x338),
    /**
     * Document me.
     */
    SMSG_CHARACTER_PROFILE_REALM_CONNECTED((short) 0x339),
    /**
     * Document me.
     */
    SMSG_DEFENSE_MESSAGE((short) 0x33A),
    /**
     * Document me.
     */
    MSG_GM_RESETINSTANCELIMIT((short) 0x33C),
    /**
     * Document me.
     */
    // SMSG_MOTD                                       ((short) 0x33D),
    /**
     * Document me.
     */
    SMSG_MOVE_SET_FLIGHT((short) 0x33E),
    /**
     * Document me.
     */
    SMSG_MOVE_UNSET_FLIGHT((short) 0x33F),
    /**
     * Document me.
     */
    CMSG_MOVE_FLIGHT_ACK((short) 0x340),
    /**
     * Document me.
     */
    MSG_MOVE_START_SWIM_CHEAT((short) 0x341),
    /**
     * Document me.
     */
    MSG_MOVE_STOP_SWIM_CHEAT((short) 0x342),
    /**
     * Document me.
     */
    // [-ZERO] Last existed in 1.12.1 opcode), maybe some renumbering from other side
    /**
     * Document me.
     */
    CMSG_CANCEL_MOUNT_AURA((short) 0x375),
    /**
     * Document me.
     */
    CMSG_CANCEL_TEMP_ENCHANTMENT((short) 0x379),
    /**
     * Document me.
     */
    CMSG_MAELSTROM_INVALIDATE_CACHE((short) 0x387),
    /**
     * Document me.
     */
    CMSG_SET_TAXI_BENCHMARK_MODE((short) 0x389),
    /**
     * Document me.
     */
    CMSG_MOVE_CHNG_TRANSPORT((short) 0x38D),
    /**
     * Document me.
     */
    MSG_PARTY_ASSIGNMENT((short) 0x38E),
    /**
     * Document me.
     */
    SMSG_OFFER_PETITION_ERROR((short) 0x38F),
    /**
     * Document me.
     */
    SMSG_RESET_FAILED_NOTIFY((short) 0x396),
    /**
     * Document me.
     */
    SMSG_REAL_GROUP_UPDATE((short) 0x397),
    /**
     * Document me.
     */
    SMSG_INIT_EXTRA_AURA_INFO((short) 0x3A3),
    /**
     * Document me.
     */
    SMSG_SET_EXTRA_AURA_INFO((short) 0x3A4),
    /**
     * Document me.
     */
    SMSG_SET_EXTRA_AURA_INFO_NEED_UPDATE((short) 0x3A5),
    /**
     * Document me.
     */
    SMSG_SPELL_CHANCE_PROC_LOG((short) 0x3AA),
    /**
     * Document me.
     */
    CMSG_MOVE_SET_RUN_SPEED((short) 0x3AB),
    /**
     * Document me.
     */
    SMSG_DISMOUNT((short) 0x3AC),
    /**
     * Document me.
     */
    MSG_RAID_READY_CHECK_CONFIRM((short) 0x3AE),
    /**
     * Document me.
     */
    SMSG_CLEAR_TARGET((short) 0x3BE),
    /**
     * Document me.
     */
    CMSG_BOT_DETECTED((short) 0x3BF),
    /**
     * Document me.
     */
    SMSG_KICK_REASON((short) 0x3C4),
    /**
     * Document me.
     */
    MSG_RAID_READY_CHECK_FINISHED((short) 0x3C5),
    /**
     * Document me.
     */
    CMSG_TARGET_CAST((short) 0x3CF),
    /**
     * Document me.
     */
    CMSG_TARGET_SCRIPT_CAST((short) 0x3D0),
    /**
     * Document me.
     */
    CMSG_CHANNEL_DISPLAY_LIST((short) 0x3D1),
    /**
     * Document me.
     */
    CMSG_GET_CHANNEL_MEMBER_COUNT((short) 0x3D3),
    /**
     * Document me.
     */
    SMSG_CHANNEL_MEMBER_COUNT((short) 0x3D4),
    /**
     * Document me.
     */
    CMSG_DEBUG_LIST_TARGETS((short) 0x3D7),
    /**
     * Document me.
     */
    SMSG_DEBUG_LIST_TARGETS((short) 0x3D8),
    /**
     * Document me.
     */
    CMSG_PARTY_SILENCE((short) 0x3DC),
    /**
     * Document me.
     */
    CMSG_PARTY_UNSILENCE((short) 0x3DD),
    /**
     * Document me.
     */
    MSG_NOTIFY_PARTY_SQUELCH((short) 0x3DE),
    /**
     * Document me.
     */
    SMSG_COMSAT_RECONNECT_TRY((short) 0x3DF),
    /**
     * Document me.
     */
    SMSG_COMSAT_DISCONNECT((short) 0x3E0),
    /**
     * Document me.
     */
    SMSG_COMSAT_CONNECT_FAIL((short) 0x3E1),
    /**
     * Document me.
     */
    CMSG_SET_CHANNEL_WATCH((short) 0x3EE),
    /**
     * Document me.
     */
    SMSG_USERLIST_ADD((short) 0x3EF),
    /**
     * Document me.
     */
    SMSG_USERLIST_REMOVE((short) 0x3F0),
    /**
     * Document me.
     */
    SMSG_USERLIST_UPDATE((short) 0x3F1),
    /**
     * Document me.
     */
    CMSG_CLEAR_CHANNEL_WATCH((short) 0x3F2),
    /**
     * Document me.
     */
    SMSG_GOGOGO_OBSOLETE((short) 0x3F4),
    /**
     * Document me.
     */
    SMSG_ECHO_PARTY_SQUELCH((short) 0x3F5),
    /**
     * Document me.
     */
    CMSG_SPELLCLICK((short) 0x3F7),
    /**
     * Document me.
     */
    SMSG_LOOT_LIST((short) 0x3F8),
    /**
     * Document me.
     */
    MSG_GUILD_PERMISSIONS((short) 0x3FC),
    /**
     * Document me.
     */
    MSG_GUILD_EVENT_LOG_QUERY((short) 0x3FE),
    /**
     * Document me.
     */
    CMSG_MAELSTROM_RENAME_GUILD((short) 0x3FF),
    /**
     * Document me.
     */
    CMSG_GET_MIRRORIMAGE_DATA((short) 0x400),
    /**
     * Document me.
     */
    SMSG_MIRRORIMAGE_DATA((short) 0x401),
    /**
     * Document me.
     */
    SMSG_FORCE_DISPLAY_UPDATE((short) 0x402),
    /**
     * Document me.
     */
    SMSG_SPELL_CHANCE_RESIST_PUSHBACK((short) 0x403),
    /**
     * Document me.
     */
    CMSG_IGNORE_DIMINISHING_RETURNS_CHEAT((short) 0x404),
    /**
     * Document me.
     */
    SMSG_IGNORE_DIMINISHING_RETURNS_CHEAT((short) 0x405),
    /**
     * Document me.
     */
    CMSG_KEEP_ALIVE((short) 0x406),
    /**
     * Document me.
     */
    SMSG_RAID_READY_CHECK_ERROR((short) 0x407),
    /**
     * Document me.
     */
    CMSG_OPT_OUT_OF_LOOT((short) 0x408),
    /**
     * Document me.
     */
    CMSG_SET_GRANTABLE_LEVELS((short) 0x40B),
    /**
     * Document me.
     */
    CMSG_GRANT_LEVEL((short) 0x40C),
    /**
     * Document me.
     */
    CMSG_DECLINE_CHANNEL_INVITE((short) 0x40F),
    /**
     * Document me.
     */
    CMSG_GROUPACTION_THROTTLED((short) 0x410),
    /**
     * Document me.
     */
    SMSG_OVERRIDE_LIGHT((short) 0x411),
    /**
     * Document me.
     */
    SMSG_TOTEM_CREATED((short) 0x412),
    /**
     * Document me.
     */
    CMSG_TOTEM_DESTROYED((short) 0x413),
    /**
     * Document me.
     */
    CMSG_EXPIRE_RAID_INSTANCE((short) 0x414),
    /**
     * Document me.
     */
    CMSG_NO_SPELL_VARIANCE((short) 0x415),
    /**
     * Document me.
     */
    CMSG_QUESTGIVER_STATUS_MULTIPLE_QUERY((short) 0x416),
    /**
     * Document me.
     */
    SMSG_QUESTGIVER_STATUS_MULTIPLE((short) 0x417),
    /**
     * Document me.
     */
    CMSG_QUERY_SERVER_BUCK_DATA((short) 0x41A),
    /**
     * Document me.
     */
    CMSG_CLEAR_SERVER_BUCK_DATA((short) 0x41B),
    /**
     * Document me.
     */
    SMSG_SERVER_BUCK_DATA((short) 0x41C),
    /**
     * Document me.
     */
    SMSG_SEND_UNLEARN_SPELLS((short) 0x41D),
    /**
     * Document me.
     */
    SMSG_PROPOSE_LEVEL_GRANT((short) 0x41E),
    /**
     * Document me.
     */
    CMSG_ACCEPT_LEVEL_GRANT((short) 0x41F),
    /**
     * Document me.
     */
    SMSG_REFER_A_FRIEND_FAILURE((short) 0x420),
    /**
     * Document me.
     */
    SMSG_SUMMON_CANCEL((short) 0x423);

    private final short result;

    private Opcodes(short result) {
        this.result = result;
    }

    public short getValue() {
        return this.result;
    }

    public static Opcodes convert(short value) {
        for (Opcodes cmd : Opcodes.values()) {
            if (cmd.getValue() == value) {
                return cmd;
            }
        }
        return null;
    }
}
