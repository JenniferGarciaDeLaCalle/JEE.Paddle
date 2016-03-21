package business.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.ReserveDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

public class TrainingController {

	private TrainingDao trainingDao;
	
	private UserDao userDao;
	
	private CourtDao courtDao;
	
	private ReserveDao reserveDao;
	
	private ReserveController reserveController;
	
	@Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }
	
	@Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
	
	@Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }
	
	public boolean createTraining(TrainingWrapper trainingwrapper){
		Court court = courtDao.findOne(trainingwrapper.getCourt().getId());
		User trainer = userDao.findByUsernameOrEmail(trainingwrapper.getTrainer().getUsername());
		if (trainingDao.findTrainingsByStartDate(trainingwrapper.getStartDate()).size() == 0){
			trainingDao.createTraining(trainingwrapper.getStartDate(), trainingwrapper.getFinishDate(), court, trainer);
			while (trainingwrapper.getStartDate().getTimeInMillis() < trainingwrapper.getFinishDate().getTimeInMillis()){
				Reserve reserve = reserveDao.findByCourtAndDate(court, trainingwrapper.getStartDate());
				//If reserve exist, delete current, and add new
				if (reserve != null){
					reserveDao.delete(reserve);
				}
				reserveController.reserveCourt(court.getId(), trainingwrapper.getStartDate(), trainer.getUsername());
				trainingwrapper.getStartDate().add(Calendar.DAY_OF_MONTH, 7);			
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addUserInTraining(TrainingWrapper trainingwrapper, User user){
		Training training = trainingDao.findTrainingsByStartDate(trainingwrapper.getStartDate()).get(0);
		if (training != null && training.getPlayers().size() < 4 && !training.getPlayers().contains(user)){
			trainingDao.addUserInTraining(user, training);
			return true;
		}
		return false;
	}
	
	public boolean deleteUserInTraining(TrainingWrapper trainingwrapper, User user){
		Training training = trainingDao.findTrainingsByStartDate(trainingwrapper.getStartDate()).get(0);
		if (training != null && training.getPlayers().contains(user)){
			trainingDao.deleteUserInTraining(user, training);
			return true;
		}
		return false;
	}
	
	public boolean deleteTraining(TrainingWrapper trainingwrapper){
		Training training = trainingDao.findTrainingsByStartDate(trainingwrapper.getStartDate()).get(0);
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
		trainingDao.deleteTraining(training);
		return true;
	}
}
