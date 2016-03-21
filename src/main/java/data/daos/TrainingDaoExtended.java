package data.daos;

import java.util.Calendar;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {
	
	public void createTraining(Calendar startDate, Calendar finishDate, Court court, User trainer);

	public void addUserInTraining(User user, Training training);
	
	public void deleteUserInTraining(User user, Training training);
	
	public void deleteTraining(Training training);
}
