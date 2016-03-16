package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingDaoExtended {

	@Autowired
	private TrainingDao trainingDao;
	
	@Override
	public boolean addUserInTraining(User user, Training training) {
		if (training.getPlayers().size() < 4){
			training.getPlayers().add(user);
			trainingDao.save(training);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUserInTraining(User user, Training training) {
		if (training.getPlayers().contains(user)){
			training.getPlayers().remove(user);
			trainingDao.save(training);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTraining(Training training) {
		trainingDao.delete(training);
		return false;
	}
}

