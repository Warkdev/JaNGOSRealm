package eu.jangos.realm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
 * AuthUtils provides helpers to manage Realm authentication.
 * @author Warkdev
 * @version v0.1 BETA
 */
public class AuthUtils {
    
    /**
     * Used to check whether a client is registered or not.
     * @param account Account name.
     * @param seed Seed used by the server.
     * @param clientSeed Seed used by the client.
     * @param K Hashed Session Key of the client.
     * @param digest Digest sent by the client.
     * @return True if the Client Digest is ok (must result in a correct authentication), false otherwise.
     */
    public static boolean checkClientDigest(String account, byte [] seed, byte [] clientSeed, BigNumber K, byte [] digest) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        
        byte [] t = { 0x00, 0x00, 0x00, 0x00 };        
        
        md.update(account.getBytes());
        md.update(t, 0, 4);
        md.update(clientSeed, 0, 4);
        md.update(seed, 0, 4);        
        md.update(K.asByteArray(20));                                           
        
        return Arrays.equals(md.digest(), digest);
    }
}
