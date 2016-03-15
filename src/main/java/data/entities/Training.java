package data.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne
    @JoinColumn
    private List<User> players;
	
	//1 hour
    private final int TIMECLASS = 3600000;

	public Training( Calendar startDate, Court court, User trainer) {
		this.startDate = startDate;
		this.finishDate = startDate;
		finishDate.add(Calendar.MILLISECOND, TIMECLASS);
		this.court = court;	
		this.trainer = trainer;
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

	public void setPlayers(List<User> players) {
		this.players = players;
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
