package eu.jangos.realm.model.characters;
// Generated 19-f�vr.-2016 21:17:55 by Hibernate Tools 4.3.1



/**
 * Equipment generated by hbm2java
 */
public class Equipment  implements java.io.Serializable {


     private int id;
     private Characters characters;
     private ItemInstance itemInstance;
     private byte fkSlot;

    public Equipment() {
    }

    public Equipment(int id, Characters characters, ItemInstance itemInstance, byte fkSlot) {
       this.id = id;
       this.characters = characters;
       this.itemInstance = itemInstance;
       this.fkSlot = fkSlot;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Characters getCharacters() {
        return this.characters;
    }
    
    public void setCharacters(Characters characters) {
        this.characters = characters;
    }
    public ItemInstance getItemInstance() {
        return this.itemInstance;
    }
    
    public void setItemInstance(ItemInstance itemInstance) {
        this.itemInstance = itemInstance;
    }
    public byte getFkSlot() {
        return this.fkSlot;
    }
    
    public void setFkSlot(byte fkSlot) {
        this.fkSlot = fkSlot;
    }




}


