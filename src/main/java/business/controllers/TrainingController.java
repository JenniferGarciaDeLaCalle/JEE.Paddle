package business.controllers;

import java.util.Calendar;
import java.util.List;

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
	
	public Training findTrainingById(int id){
		return trainingDao.findTrainingById(id);
	}
	
	public List<Training> findTrainingsByStartDate(Calendar startDate){
		return trainingDao.findTrainingsByStartDate(startDate);
	}
	
	public boolean createTraining(TrainingWrapper trainingWrapper){
		Court court = courtDao.findOne(trainingWrapper.getCourt().getId());
		User trainer = userDao.findByUsernameOrEmail(trainingWrapper.getTrainer().getUsername());
		if (trainingDao.findTrainingsByStartDate(trainingWrapper.getStartDate()).size() == 0){
			trainingDao.createTraining(trainingWrapper.getStartDate(), trainingWrapper.getFinishDate(), court, trainer);
			while (trainingWrapper.getStartDate().getTimeInMillis() < trainingWrapper.getFinishDate().getTimeInMillis()){
				Reserve reserve = reserveDao.findByCourtAndDate(court, trainingWrapper.getStartDate());
				//If reserve exist, delete current, and add new
				if (reserve != null){
					reserveDao.delete(reserve);
				}
				reserveController.reserveCourt(court.getId(), trainingWrapper.getStartDate(), trainer.getUsername());
				trainingWrapper.getStartDate().add(Calendar.DAY_OF_MONTH, 7);			
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addUserInTraining(Training training, User user){
		if (training != null && training.getPlayers().size() < 4 && !training.getPlayers().contains(user)){
			trainingDao.addUserInTraining(user, training);
			return true;
		}
		return false;
	}
	
	public boolean deleteUserInTraining(Training training, User user){
		if (training != null && training.getPlayers().contains(user)){
			trainingDao.deleteUserInTraining(user, training);
			return true;
		}
		return false;
	}
	
	public boolean deleteTraining(Training training){
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
