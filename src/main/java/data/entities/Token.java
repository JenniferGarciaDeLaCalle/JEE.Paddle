package data.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar createTime;
    
    //1 hour
    private final int TIMEEXPIRE = 3600000;

	public Token() {
    }

    public Token(User user) {
        assert user != null;
        this.user = user;
        this.value = new Encrypt().encryptInBase64UrlSafe("" + user.getId() + user.getUsername() + Long.toString(new Date().getTime())
                + user.getPassword());
        this.createTime = Calendar.getInstance();
    }

	public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }
    
    public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	
	public boolean isValid() {
		if ((Calendar.getInstance().getTimeInMillis() - this.createTime.getTimeInMillis()) <= TIMEEXPIRE)
			return true;
		else
			return false;
	}
	
	public int getTIMEEXPIRE() {
		return TIMEEXPIRE;
	}

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Token) obj).id;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + ", value=" + value + ", userId=" + user.getId() + ", createTime=" + createTime + "]";
    }
}
