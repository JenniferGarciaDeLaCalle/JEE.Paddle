package business.wrapper;

import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import data.entities.Training;
import data.entities.User;

public class TrainingWrapper {
	
	private int id;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Calendar startDate;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Calendar finishDate;
	
    private int courtId;
    
	private int trainerId;
	
	private List<User> players;

    public TrainingWrapper() {
    }
    
    public TrainingWrapper(int id, Calendar startDate, Calendar finishDate, int courtId, int trainerId, List<User> players) {
    	this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courtId = courtId;
        this.trainerId = trainerId;
        this.players = players;
    }

    public TrainingWrapper(Calendar startDate, Calendar finishDate, int courtId, int trainerId) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courtId = courtId;
        this.trainerId = trainerId;
    }
    
    public TrainingWrapper(Calendar startDate, Calendar finishDate, int courtId) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courtId = courtId;
    }
    
    public TrainingWrapper(Training training) {
        this(training.getId(), training.getStartDate(), training.getFinishDate(), training.getCourt().getId(), training.getTrainer().getId(), training.getPlayers());
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "TrainingWrapper [id=" + id + ", startDate=" + startDate + ", finishDate=" + finishDate + ", courtId="
				+ courtId + ", trainerId=" + trainerId + ", players=" + players + "]";
	}
	
}
