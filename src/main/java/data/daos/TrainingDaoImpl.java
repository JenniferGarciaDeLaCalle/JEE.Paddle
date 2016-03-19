package data.daos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingDaoExtended {

	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private ReserveDao reserveDao;
	
	@Override
	public boolean createTraining(Calendar startDate, Calendar finishDate, Court court, User trainer) {
		if (trainingDao.findTrainingsByStartDate(startDate).size() == 0){
			trainingDao.save(new Training (startDate, finishDate, court, trainer));
			while (startDate.getTimeInMillis() < finishDate.getTimeInMillis()){
				Reserve reserve = reserveDao.findByCourtAndDate(court, startDate);
				//If reserve exist, delete current, and add new
				if (reserve != null){
					reserveDao.delete(reserve);
				}
				reserveDao.save(new Reserve(court, trainer, startDate));
				startDate.add(Calendar.DAY_OF_MONTH, 7);			
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addUserInTraining(User user, Training training) {
		if (training.getPlayers().size() < 4 && !training.getPlayers().contains(user)){
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
		Calendar startDate = training.getStartDate();
		Calendar finishDate = training.getFinishDate();
		Court court = training.getCourt();
		while (startDate.getTimeInMillis() < finishDate.getTimeInMillis()){
			Reserve reserve = reserveDao.findByCourtAndDate(court, startDate);
			if (reserve != null){
				reserveDao.delete(reserve);
			}
			startDate.add(Calendar.DAY_OF_MONTH, 7);			
		}
		trainingDao.delete(training);
		return true;
	}

}

