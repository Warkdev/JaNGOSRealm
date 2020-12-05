package eu.jangos.realm.model.world;
// Generated 06-mai-2016 21:17:32 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Spellradius generated by hbm2java
 */
@Entity
@Table(name="spellradius"
    ,catalog="jangosworld"
)
public class Spellradius  implements java.io.Serializable {


     private byte id;
     private short radius;
     private Set<Spellspelleffects> spellspelleffectses = new HashSet<Spellspelleffects>(0);

    public Spellradius() {
    }

	
    public Spellradius(byte id, short radius) {
        this.id = id;
        this.radius = radius;
    }
    public Spellradius(byte id, short radius, Set<Spellspelleffects> spellspelleffectses) {
       this.id = id;
       this.radius = radius;
       this.spellspelleffectses = spellspelleffectses;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public byte getId() {
        return this.id;
    }
    
    public void setId(byte id) {
        this.id = id;
    }

    
    @Column(name="radius", nullable=false)
    public short getRadius() {
        return this.radius;
    }
    
    public void setRadius(short radius) {
        this.radius = radius;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="spellradius")
    public Set<Spellspelleffects> getSpellspelleffectses() {
        return this.spellspelleffectses;
    }
    
    public void setSpellspelleffectses(Set<Spellspelleffects> spellspelleffectses) {
        this.spellspelleffectses = spellspelleffectses;
    }




}

