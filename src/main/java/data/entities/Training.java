package data.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Training {

	@Id
    @GeneratedValue
    private int id;
	
	private Calendar startDate;
	
	private Calendar finishDate;
	
	@ManyToOne
    @JoinColumn
    private Court court;
	
	@ManyToOne
	@JoinColumn
	private User trainer;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<User> players;

	public Training() {
	}
	
	public Training( Calendar startDate, Calendar finishDate, Court court, User trainer) {
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.court = court;	
		this.trainer = trainer;
		this.players = new ArrayList<User>(4);
	}

	public int getId() {
		return id;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getFinishDate() {
		return finishDate;
	}

	public Court getCourt() {
		return court;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public List<User> getPlayers() {
		return players;
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
        return id == ((Training) obj).id;
    }

	@Override
	public String toString() {
		return "Training [id=" + id + ", startDate=" + startDate + ", finishDate=" + finishDate + ", court=" + court
				+ ", trainer=" + trainer + ", players=" + players + "]";
	}

}
