package eu.jangos.realm.utils;

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
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Utils provide a set of common functions used by this manager.
 *
 * @author Warkdev.
 * @version v0.1
 */
public class Utils {

    /**
     * Check whether the ip passed in parameter is a valid IPv4 address or not.
     * @param ipAddress The IP to be validated
     * @return a boolean value, true if the address is a valid IPv4, false otherwise.
     */
    public static boolean isValidIP4Address(String ipAddress) {
        if (ipAddress.matches("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$")) {
            String[] groups = ipAddress.split("\\.");

            for (int i = 0; i <= 3; i++) {
                String segment = groups[i];
                if (segment == null || segment.length() <= 0) {
                    return false;
                }

                int value = 0;
                try {
                    value = Integer.parseInt(segment);
                } catch (NumberFormatException e) {
                    return false;
                }
                
                if (value > 255) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Pattern of an email address.
     */
    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );

    /**
     * Check whether the argument in parameter is a valid email address.
     * @param email The string to be checked.
     * @return true if the address is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        return rfc2822.matcher(email).matches();          
    }
    
    /**
     * Create an Image icon from the provided path.
     *
     * @param path The path to the icon.
     * @return the Generated ImageIcon or null if the path is invalid.
     */
    public static ImageIcon createImageIcon(String path, Class<?> c) {
        URL imgURL = c.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }

    /**
     * Create the hashpass for the database as expected by the SRP6
     * implementation.
     *
     * @param name The name of the account.
     * @param password The password.
     * @return The hash pass calculation.
     */
    public static String createHashPass(String name, String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");

        String user = name.toUpperCase() + ":" + password.toUpperCase();

        md.update(user.getBytes());

        return new BigNumber(md.digest()).toHexString();
    }

    /**
     * Add a number of days to the date given in parameters.
     *
     * @param date The starting date.
     * @param days The number of days to be added.
     * @return The starting date + the number of days.
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     * Returns the HTML representation of the item given in parameter.
     *
     * @param item The item to translate into a tooltip.
     * @return The HTML representation of the item.
     */
    /**
     * public static String createToolTip(Items item) { StringBuilder text = new
     * StringBuilder("<html><body>");
     *
     * // Used to get the '.' as decimal separator. DecimalFormat format = new
     * DecimalFormat("####0.00",
     * DecimalFormatSymbols.getInstance(Locale.ENGLISH)); String color =
     * item.getFkQuality().getColor();
     *
     * text.append("<b><font color='").append(color).append("'>").append(item.getName()).append("</font></b><br/>");
     *
     * text.append("<font color='WHITE'>"); *
     *
     *
     * switch(item.getFkInventorytype().getValue()) { case MAIN_HAND: break;
     * case OFF_HAND: break; case TWO_HANDS: float minDmg = item.getDmgMin1();
     * float maxDmg = item.getDmgMax1(); float delay = (float) item.getDelay() /
     * 1000; float dmgPerS = Math.round((minDmg + maxDmg) / 2) / delay;
     *
     * text.append("Two-Hand <br/>");
     * text.append(format.format(minDmg)).append(" -
     * ").append(format.format(maxDmg)).append(" damages ").append(" Speed
     * ").append(format.format(delay)).append("<br/>");
     * text.append("(").append(format.format(dmgPerS)).append(" damage per
     * second) <br/>"); text.append("Durability
     * ").append(item.getMaxDurability()).append(" /
     * ").append(item.getMaxDurability()); break; } *
     * text.append("</font></body></html>");
     *
     * System.out.println(text.toString());
     *
     * return text.toString(); }
     */
}
