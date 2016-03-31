package webs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.CourtController;
import business.controllers.TrainingController;
import business.controllers.UserController;
import business.wrapper.CourtState;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import data.entities.Training;
import data.entities.User;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class TrainingPresenter {

    @Autowired
    private TrainingController trainingController;
    
    @Autowired
    private CourtController courtController;
    
    @Autowired
    private UserController userController;
    
    public TrainingPresenter() {
    }
    
    @RequestMapping("/trainingList")
    public ModelAndView listTrainings() {
        ModelAndView modelAndView = new ModelAndView("/trainingList");
        modelAndView.addObject("trainingList", trainingController.showTrainings());
        return modelAndView;
    }
    
    @RequestMapping(value = "/createTraining", method = RequestMethod.GET)
    public String createTraining(Model model) {
    	ArrayList<Integer> courtIdList = new ArrayList<Integer>();;
    	List<CourtState> listCourts = courtController.showCourts();
    	for (CourtState court : listCourts){
    		if (court.isActive()){
    			courtIdList.add(court.getCourtId());
    		}
    	}
    	model.addAttribute("courtIdList",courtIdList);
    	ArrayList<Integer> trainerIdList = new ArrayList<Integer>();
    	List<UserWrapper> listTrainers = userController.showUsers();
    	for (UserWrapper user : listTrainers){
    		trainerIdList.add(user.getId());
    	}
    	model.addAttribute("trainerIdList", trainerIdList);
    	model.addAttribute("training", new TrainingWrapper());
        return "createTraining";
    }

    @RequestMapping(value = "/createTraining", method = RequestMethod.POST)
    public String createTrainingSubmit(@Valid TrainingWrapper training, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
        	if(trainingController.createTraining(training)){
                model.addAttribute("element", training);
                return "registrationSuccess";
            } else {
                bindingResult.rejectValue("trainingId", "error.trainingId", "Training now exist");
            }
        }
        return "/createTraining";
    }
    
    @RequestMapping(value = {"/deleteTraining/{id}"})
    public String deleteTraining(@PathVariable int id, Model model) {
    	trainingController.deleteTraining(trainingController.findTrainingById(id));
        model.addAttribute("trainingList", trainingController.showTrainings());
        return "/trainingList";
    }
    
    @RequestMapping(value = "/addUserTraining/{id}", method = RequestMethod.GET)
    public String addUserTraining(@PathVariable int id, Model model) {
    	model.addAttribute("trainingId", id);
    	Training training = trainingController.findTrainingById(id);
    	List<User> trainingUsersList = training.getPlayers();
    	ArrayList<Integer> playerIdList = new ArrayList<Integer>();;
    	List<UserWrapper> listPlayers = userController.showUsers();
    	for (UserWrapper userWrapper : listPlayers){
    		User user = userController.findById(userWrapper.getId());
    		if (!trainingUsersList.contains(user) && training.getTrainer().getId() != userWrapper.getId()){
    			playerIdList.add(userWrapper.getId());
    		}
    	}
    	model.addAttribute("userIdList", playerIdList);
    	model.addAttribute("user", new UserWrapper());
        return "addUserTraining";
    }
    
    @RequestMapping(value = "/addUserTraining/addUserTraining/{id}", method = RequestMethod.POST)
    public String addUserTrainingSubmit(@PathVariable int id, @Valid UserWrapper user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
        	Training training = trainingController.findTrainingById(id);
        	User userNew = userController.findById(user.getId());
			if(trainingController.addUserInTraining(training, userNew)){
		     	model.addAttribute("element", "User " + user.getId() + " add to training " + training.getId());
		     	return "registrationSuccess";
			} else {
				bindingResult.rejectValue("Training-User", "error.Training-User", "Training-User now exist");
			}
        }
        return "addUserTraining";
    }
    
    @RequestMapping(value = "/deleteUserTraining/{id}", method = RequestMethod.GET)
    public String deleteUserTraining(@PathVariable int id, Model model) {
    	model.addAttribute("trainingId", id);
    	Training training = trainingController.findTrainingById(id);
    	List<User> trainingUsersList = training.getPlayers();
    	ArrayList<Integer> playerIdList = new ArrayList<Integer>();
    	for (User user : trainingUsersList){
    		playerIdList.add(user.getId());
    	}
    	model.addAttribute("userIdList", playerIdList);
    	model.addAttribute("user", new UserWrapper());
        return "deleteUserTraining";
    }
    
    @RequestMapping(value = "/deleteUserTraining/deleteUserTraining/{id}", method = RequestMethod.POST)
    public String deleteUserTrainingSubmit(@PathVariable int id, @Valid UserWrapper user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
        	Training training = trainingController.findTrainingById(id);
        	User userNew = userController.findById(user.getId());
			if(trainingController.deleteUserInTraining(training, userNew)){
		     	model.addAttribute("element", "User " + user.getId() + " delete to training " + training.getId());
		     	return "registrationSuccess";
			} else {
				bindingResult.rejectValue("Training-User", "error.Training-User", "Training-User now exist");
			}
        }
        return "deleteUserTraining";
    }
 
    @ModelAttribute("now")
    public String now() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }
    
}
