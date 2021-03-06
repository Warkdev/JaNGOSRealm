package eu.jangos.realm.model.characters;
// Generated 06-mai-2016 23:00:34 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mail generated by hbm2java
 */
@Entity
@Table(name="mail"
    ,catalog="jangoscharacters"
)
public class Mail  implements java.io.Serializable {


     private Integer id;
     private byte fkType;
     private byte fkDbcStationery;
     private int fkDbcMailTemplateId;
     private long fkFrom;
     private long fkTo;
     private String subject;
     private String body;
     private Date expire;
     private Date deliver;
     private int money;
     private int cod;
     private byte isRead;
     private byte isReturned;
     private byte isCopied;

    public Mail() {
    }

	
    public Mail(byte fkType, byte fkDbcStationery, int fkDbcMailTemplateId, long fkFrom, long fkTo, String body, Date expire, Date deliver, int money, int cod, byte isRead, byte isReturned, byte isCopied) {
        this.fkType = fkType;
        this.fkDbcStationery = fkDbcStationery;
        this.fkDbcMailTemplateId = fkDbcMailTemplateId;
        this.fkFrom = fkFrom;
        this.fkTo = fkTo;
        this.body = body;
        this.expire = expire;
        this.deliver = deliver;
        this.money = money;
        this.cod = cod;
        this.isRead = isRead;
        this.isReturned = isReturned;
        this.isCopied = isCopied;
    }
    public Mail(byte fkType, byte fkDbcStationery, int fkDbcMailTemplateId, long fkFrom, long fkTo, String subject, String body, Date expire, Date deliver, int money, int cod, byte isRead, byte isReturned, byte isCopied) {
       this.fkType = fkType;
       this.fkDbcStationery = fkDbcStationery;
       this.fkDbcMailTemplateId = fkDbcMailTemplateId;
       this.fkFrom = fkFrom;
       this.fkTo = fkTo;
       this.subject = subject;
       this.body = body;
       this.expire = expire;
       this.deliver = deliver;
       this.money = money;
       this.cod = cod;
       this.isRead = isRead;
       this.isReturned = isReturned;
       this.isCopied = isCopied;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="fk_type", nullable=false)
    public byte getFkType() {
        return this.fkType;
    }
    
    public void setFkType(byte fkType) {
        this.fkType = fkType;
    }

    
    @Column(name="fk_dbc_stationery", nullable=false)
    public byte getFkDbcStationery() {
        return this.fkDbcStationery;
    }
    
    public void setFkDbcStationery(byte fkDbcStationery) {
        this.fkDbcStationery = fkDbcStationery;
    }

    
    @Column(name="fk_dbc_mailTemplateId", nullable=false)
    public int getFkDbcMailTemplateId() {
        return this.fkDbcMailTemplateId;
    }
    
    public void setFkDbcMailTemplateId(int fkDbcMailTemplateId) {
        this.fkDbcMailTemplateId = fkDbcMailTemplateId;
    }

    
    @Column(name="fk_from", nullable=false)
    public long getFkFrom() {
        return this.fkFrom;
    }
    
    public void setFkFrom(long fkFrom) {
        this.fkFrom = fkFrom;
    }

    
    @Column(name="fk_to", nullable=false)
    public long getFkTo() {
        return this.fkTo;
    }
    
    public void setFkTo(long fkTo) {
        this.fkTo = fkTo;
    }

    
    @Column(name="subject")
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    @Column(name="body", nullable=false)
    public String getBody() {
        return this.body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="expire", nullable=false, length=19)
    public Date getExpire() {
        return this.expire;
    }
    
    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deliver", nullable=false, length=19)
    public Date getDeliver() {
        return this.deliver;
    }
    
    public void setDeliver(Date deliver) {
        this.deliver = deliver;
    }

    
    @Column(name="money", nullable=false)
    public int getMoney() {
        return this.money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }

    
    @Column(name="cod", nullable=false)
    public int getCod() {
        return this.cod;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }

    
    @Column(name="isRead", nullable=false)
    public byte getIsRead() {
        return this.isRead;
    }
    
    public void setIsRead(byte isRead) {
        this.isRead = isRead;
    }

    
    @Column(name="isReturned", nullable=false)
    public byte getIsReturned() {
        return this.isReturned;
    }
    
    public void setIsReturned(byte isReturned) {
        this.isReturned = isReturned;
    }

    
    @Column(name="isCopied", nullable=false)
    public byte getIsCopied() {
        return this.isCopied;
    }
    
    public void setIsCopied(byte isCopied) {
        this.isCopied = isCopied;
    }




}


