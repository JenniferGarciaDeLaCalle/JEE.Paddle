package webs;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.UserController;
import business.wrapper.UserWrapper;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class UserPresenter {

	 @Autowired
	 private UserController userController;
	 
	 public UserPresenter() {
	 }
	
	 @RequestMapping("/userList")
	 public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView("/userList");
        modelAndView.addObject("userList", userController.showUsers());
        return modelAndView;
	 }
	 
	 @RequestMapping(value = "/createUser", method = RequestMethod.GET)
	 public String createUser(Model model) {
	  	model.addAttribute("user", new UserWrapper());
	  	return "createUser";
	 }

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUserSubmit(@Valid UserWrapper user, BindingResult bindingResult, Model model) {
	  	if (!bindingResult.hasErrors()) {
	  		if (userController.registration(user)) {
	  			model.addAttribute("element", user);
	        	return "registrationSuccess";
	      	} else {
	        	bindingResult.rejectValue("user", "error.user", "User now exist");
	     	}
	  	}
	  	return "/createUser";
	}
	    
	@ModelAttribute("now")
	public String now() {
	  	return new SimpleDateFormat("yyyy").format(new Date());
	}
}
