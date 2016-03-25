package business.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AlreadyExistTrainingFieldException;
import business.api.exceptions.ApiException;
import business.api.exceptions.InvalidDateException;
import business.api.exceptions.NotFoundCourtIdException;
import business.api.exceptions.NotFoundTrainingIdException;
import business.api.exceptions.NotFoundUserIdException;
import business.controllers.CourtController;
import business.controllers.TrainingController;
import business.controllers.UserController;
import business.wrapper.TrainingWrapper;
import data.entities.Training;
import data.entities.User;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {
	
	private TrainingController trainingController;
	
	private CourtController courtController;
	
	private UserController userController;
	
/*	@Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }
    
    @Autowired
    public void setCourtController(CourtController courtController) {
        this.courtController = courtController;
    }
	
	@Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
*/	
	@RequestMapping(value = "/findTrainings" + Uris.STARTDATE, method = RequestMethod.GET)
	public List<Training> findTrainingsByStartDate(@PathVariable Calendar startDate)
			throws InvalidDateException, NotFoundCourtIdException, AlreadyExistTrainingFieldException{
		return trainingController.findTrainingsByStartDate(startDate);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createTraining(@RequestBody TrainingWrapper trainingWrapper)
			throws InvalidDateException, AlreadyExistTrainingFieldException, NotFoundCourtIdException, ApiException{
		if(trainingWrapper.getStartDate().getTimeInMillis() < trainingWrapper.getFinishDate().getTimeInMillis()){
			throw new InvalidDateException();
		}
		if (this.trainingController.findTrainingsByStartDate(trainingWrapper.getStartDate()).size() > 0) {
			throw new AlreadyExistTrainingFieldException();
		}
		if(!courtController.exist(trainingWrapper.getCourt().getId())){
			throw new NotFoundCourtIdException();
		}
		if (!this.trainingController.createTraining(trainingWrapper)) {
			throw new ApiException("Error al crear el entrenamiento", 1);
	  	}
	}
	
	@RequestMapping(value = Uris.ID + "/user" + Uris.ID, method = RequestMethod.POST)
	public void addUserInTraining(@PathVariable int idTraining, @PathVariable int idUser) 
			throws NotFoundUserIdException, NotFoundTrainingIdException, ApiException{
		Training training = trainingController.findTrainingById(idTraining);
		if (training == null) {
			throw new NotFoundTrainingIdException();
	  	}
		User user = userController.findById(idUser);
		if (user == null){
			throw new NotFoundUserIdException();
		}
		if (!this.trainingController.addUserInTraining(training, user)) {
			throw new ApiException("Error al borrar un usuario del entrenamiento", 1);
	  	}
	}
	
	@RequestMapping(value = Uris.ID + "/user" + Uris.ID, method = RequestMethod.DELETE)
	public void deleteUserInTraining(@PathVariable int idTraining, @PathVariable int idUser) 
			throws NotFoundUserIdException, NotFoundTrainingIdException, ApiException{
		Training training = trainingController.findTrainingById(idTraining);
		if (training == null) {
			throw new NotFoundTrainingIdException();
	  	}
		User user = userController.findById(idUser);
		if (user == null){
			throw new NotFoundUserIdException();
		}
		if (!this.trainingController.deleteUserInTraining(training, user)) {
			throw new ApiException("Error al borrar un usuario del entrenamiento", 1);
	  	}
	}
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
	public void deleteTraining(@PathVariable int id) 
			throws NotFoundTrainingIdException, ApiException{
		Training training = trainingController.findTrainingById(id);
		if (training == null) {
			throw new NotFoundTrainingIdException();
	  	}
		if (!this.trainingController.deleteTraining(training)){
			throw new ApiException("Error al borrar el entrenamiento", 1);
		}
	}
}
