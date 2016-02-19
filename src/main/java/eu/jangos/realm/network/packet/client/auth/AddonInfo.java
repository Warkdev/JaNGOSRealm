package eu.jangos.realm.network.packet.client.auth;

/**
 * The Class AddonInfo. 
 * @author Warkdev
 * @version v0.1 BETA.
 */
public class AddonInfo { 
 
    /** The Name. */ 
    private String name; 
 
    /** The Enabled. */ 
    private byte enabled; 
 
    /** The CRC. */ 
    private int CRC; 
    
    /**
     * Unknown usage.
     */
    private int unk7;
    
    /**
     * Instantiates a new addon info. 
     *  
     * @param name 
     *        the name 
     * @param enabled 
     *        the enabled 
     * @param crc 
     *        the crc 
     * @param unk7
     *        Unknown parameter.
     */ 
    public AddonInfo(String name, byte enabled, int crc, int unk7) { 
        this.name = name; 
        this.enabled = enabled; 
        this.CRC = crc; 
        this.unk7 = unk7;
    } 
 
    /**
     * Gets the name. 
     *  
     * @return the name 
     */ 
    public String getName() { 
 
        return this.name; 
    } 
 
    /**
     * Sets the name. 
     *  
     * @param name 
     *        the name to set 
     */ 
    public void setName(String name) { 
 
        this.name = name; 
    } 
 
    /**
     * Gets the enabled. 
     *  
     * @return the enabled 
     */ 
    public byte getEnabled() { 
 
        return this.enabled; 
    } 
 
    /**
     * Sets the enabled. 
     *  
     * @param enabled 
     *        the enabled to set 
     */ 
    public void setEnabled(final byte enabled) { 
 
        this.enabled = enabled; 
    } 
 
    /**
     * Gets the cRC. 
     *  
     * @return the cRC 
     */ 
    public final int getCRC() { 
 
        return this.CRC; 
    } 
 
    /**
     * Sets the cRC. 
     *  
     * @param cRC 
     *        the cRC to set 
     */ 
    public final void setCRC(final int cRC) { 
 
        this.CRC = cRC; 
    } 

    public int getUnk7() {
        return unk7;
    }

    public void setUnk7(int unk7) {
        this.unk7 = unk7;
    }               
}
