package business.wrapper;

import java.util.Calendar;

import data.entities.Court;
import data.entities.User;

public class TrainingWrapper {

	private Calendar startDate;
	
	private Calendar finishDate;
	
    private Court court;
    
	private User trainer;

    public TrainingWrapper() {
    }

    public TrainingWrapper(Calendar startDate, Calendar finishDate, Court court, User trainer) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.court = court;
        this.trainer = trainer;
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

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "TrainingWrapper [startDate=" + startDate + ", finishDate=" + finishDate + ", court=" + court
				+ ", trainer=" + trainer + "]";
	}
	
}
