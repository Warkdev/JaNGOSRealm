package eu.jangos.realm.model.characters;
// Generated 06-mai-2016 23:00:34 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Whispers generated by hbm2java
 */
@Entity
@Table(name="whispers"
    ,catalog="jangoscharacters"
)
public class Whispers  implements java.io.Serializable {


     private Integer id;
     private Characters charactersByFkTo;
     private Characters charactersByFkFrom;
     private Ticket ticket;
     private String message;
     private Date sent;

    public Whispers() {
    }

	
    public Whispers(Characters charactersByFkTo, Characters charactersByFkFrom, String message, Date sent) {
        this.charactersByFkTo = charactersByFkTo;
        this.charactersByFkFrom = charactersByFkFrom;
        this.message = message;
        this.sent = sent;
    }
    public Whispers(Characters charactersByFkTo, Characters charactersByFkFrom, Ticket ticket, String message, Date sent) {
       this.charactersByFkTo = charactersByFkTo;
       this.charactersByFkFrom = charactersByFkFrom;
       this.ticket = ticket;
       this.message = message;
       this.sent = sent;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_to", nullable=false)
    public Characters getCharactersByFkTo() {
        return this.charactersByFkTo;
    }
    
    public void setCharactersByFkTo(Characters charactersByFkTo) {
        this.charactersByFkTo = charactersByFkTo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_from", nullable=false)
    public Characters getCharactersByFkFrom() {
        return this.charactersByFkFrom;
    }
    
    public void setCharactersByFkFrom(Characters charactersByFkFrom) {
        this.charactersByFkFrom = charactersByFkFrom;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_ticket")
    public Ticket getTicket() {
        return this.ticket;
    }
    
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    
    @Column(name="message", nullable=false, length=256)
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="sent", nullable=false, length=19)
    public Date getSent() {
        return this.sent;
    }
    
    public void setSent(Date sent) {
        this.sent = sent;
    }




}


