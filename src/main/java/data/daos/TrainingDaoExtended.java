package data.daos;

import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {

	public boolean addUserInTraining(User user, Training training);
	
	public boolean deleteUserInTraining(User user, Training training);
	
	public boolean deleteTraining(Training training);
}
