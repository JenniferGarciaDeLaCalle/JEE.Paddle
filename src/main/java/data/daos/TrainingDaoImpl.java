package data.daos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingDaoExtended {

	@Autowired
	private TrainingDao trainingDao;
	
	@Override
	public void createTraining(Calendar startDate, Calendar finishDate, Court court, User trainer) {
		trainingDao.save(new Training (startDate, finishDate, court, trainer));
	}
	
	@Override
	public void addUserInTraining(User user, Training training) {
		training.getPlayers().add(user);
		trainingDao.save(training);
	}

	@Override
	public void deleteUserInTraining(User user, Training training) {
		training.getPlayers().remove(user);
		trainingDao.save(training);
	}

	@Override
	public void deleteTraining(Training training) {
		trainingDao.delete(training);
	}

}

