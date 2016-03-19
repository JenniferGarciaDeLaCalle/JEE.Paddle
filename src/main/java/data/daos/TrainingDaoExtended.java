package data.daos;

import java.util.Calendar;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {
	
	public boolean createTraining(Calendar startDate, Calendar finishDate, Court court, User trainer);

	public boolean addUserInTraining(User user, Training training);
	
	public boolean deleteUserInTraining(User user, Training training);
	
	public boolean deleteTraining(Training training);
}
