package eu.jangos.realm.model.world;
// Generated 19-f�vr.-2016 21:00:57 by Hibernate Tools 4.3.1



/**
 * Slot generated by hbm2java
 */
public class Slot  implements java.io.Serializable {


     private byte id;
     private String slot;

    public Slot() {
    }

	
    public Slot(byte id) {
        this.id = id;
    }
    public Slot(byte id, String slot) {
       this.id = id;
       this.slot = slot;
    }
   
    public byte getId() {
        return this.id;
    }
    
    public void setId(byte id) {
        this.id = id;
    }
    public String getSlot() {
        return this.slot;
    }
    
    public void setSlot(String slot) {
        this.slot = slot;
    }




}


