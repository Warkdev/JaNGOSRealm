package eu.jangos.realm.model.world;
// Generated 19-f�vr.-2016 21:00:57 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Professions generated by hbm2java
 */
public class Professions  implements java.io.Serializable {


     private byte id;
     private String professions;
     private Set startingequipments = new HashSet(0);

    public Professions() {
    }

	
    public Professions(byte id, String professions) {
        this.id = id;
        this.professions = professions;
    }
    public Professions(byte id, String professions, Set startingequipments) {
       this.id = id;
       this.professions = professions;
       this.startingequipments = startingequipments;
    }
   
    public byte getId() {
        return this.id;
    }
    
    public void setId(byte id) {
        this.id = id;
    }
    public String getProfessions() {
        return this.professions;
    }
    
    public void setProfessions(String professions) {
        this.professions = professions;
    }
    public Set getStartingequipments() {
        return this.startingequipments;
    }
    
    public void setStartingequipments(Set startingequipments) {
        this.startingequipments = startingequipments;
    }




}


