package eu.jangos.realm.utils;

import eu.jangos.realm.network.opcode.Opcodes;
import java.util.Date;

/**
 *
 * @author Warkdev
 * @version
 */
public class StringUtils {

    /**
     * Checks whether a string name is only composed of letters.
     * @param name The name to be checked.
     * @return True if there are only letters in the string.
     */
    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a packet string in a readable format.
     * @param message The message to be transformed.
     * @param size The unencrypted size of the packet.
     * @param code The unencrypted code of the packet.
     * @return The transformed string.
     */
    public static String toPacketString(String message, int size, Opcodes code)
    {
        StringBuilder toString = new StringBuilder();
        toString.append(new Date().toString())
                .append("\n")
                .append("LENGTH: ").append(size)
                .append("\n")
                .append("CODE: ").append(code.toString())
                .append("\n")
                .append("DATA: ")
                .append("\n");        
        
        
        StringBuilder sb = new StringBuilder(message);
        int idx = 3;
        while (idx < sb.length())
        {                        
            if(idx % 48 == 0){
                // Insert a CR every 16 bytes (2 bytes + 1 space).
                sb.insert(idx - 1,"\n");                
            } else {
                // Insert a space every 2 bytes.
                sb.insert(idx - 1, " ");
            }
            idx += 3;              
        }                
        
        return toString.append(sb.toString()).toString();
    }
    
}
