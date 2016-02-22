package eu.jangos.realm.enums.characters;


/**
 * Classes represents the classes that the characters can have.
 *
 * @author Warkdev
 * @version v0.1 BETA
 */
public enum ProfessionsEnum {

    WARRIOR((byte) 1),
    PALADIN((byte) 2),
    HUNTER((byte) 3),
    ROGUE((byte) 4),
    PRIEST((byte) 5),
    SHAMAN((byte) 7),
    MAGE((byte) 8),
    WARLOCK((byte) 9),
    DRUID((byte) 11);

    private final byte value;

    /**
     * Default constructor.
     *
     * @param value
     */
    private ProfessionsEnum(byte value) {
        this.value = value;
    }

    /**
     * Return the int value of this Class.
     * @return the backing int value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * Convert an int value into a Class value.
     * @param value
     * @return The Class corresponding to that value, null if there is no match.
     */
    public static ProfessionsEnum convert(byte value) {
        for (ProfessionsEnum v : ProfessionsEnum.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * Method to check whether the given value exists within this enum.
     * @param value The value to be checked.
     * @return True if the value exists.
     */
    public static boolean exists(byte value){
        for(ProfessionsEnum g : ProfessionsEnum.values())
        {
            if(g.getValue() == value)
                return true;
        }
        return false;
    }
}
