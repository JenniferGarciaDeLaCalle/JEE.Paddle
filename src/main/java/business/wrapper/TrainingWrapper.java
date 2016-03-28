package business.wrapper;

import java.util.Calendar;

import data.entities.Training;

public class TrainingWrapper {

	private Calendar startDate;
	
	private Calendar finishDate;
	
    private int courtId;
    
	private int trainerId;

    public TrainingWrapper() {
    }

    public TrainingWrapper(Calendar startDate, Calendar finishDate, int courtId, int trainerId) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courtId = courtId;
        this.trainerId = trainerId;
    }
    
    public TrainingWrapper(Training training) {
        this(training.getStartDate(), training.getFinishDate(), training.getCourt().getId(), training.getTrainer().getId());
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

	@Override
	public String toString() {
		return "TrainingWrapper [startDate=" + startDate + ", finishDate=" + finishDate + ", courtId=" + courtId
				+ ", trainerId=" + trainerId + "]";
	}
	
}
