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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.CourtController;
import business.wrapper.CourtState;
import data.entities.Court;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class CourtPresenter {

    @Autowired
    private CourtController courtController;
    
    public CourtPresenter() {
    }
    
    @RequestMapping("/courtList")
    public ModelAndView listCourts() {
        ModelAndView modelAndView = new ModelAndView("/courtList");
        modelAndView.addObject("courtList", courtController.showCourts());
        return modelAndView;
    }
    
    @RequestMapping(value = "/createCourt", method = RequestMethod.GET)
    public String createCourt(Model model) {
    	int id = courtController.showCourts().size() + 1;
    	model.addAttribute("court", new CourtState(new Court(id)));
        return "createCourt";
    }

    @RequestMapping(value = "/createCourt", method = RequestMethod.POST)
    public String createCourtSubmit(@Valid CourtState court, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (courtController.createCourt(court.getCourtId())) {
            	if (!court.isActive()){
            		courtController.changeCourtActivation(court.getCourtId(), court.isActive());
            	}
                model.addAttribute("element", court);
                return "registrationSuccess";
            } else {
                bindingResult.rejectValue("courtId", "error.courtId", "Court now exist");
            }
        }
        return "/createCourt";
    }
    
    @RequestMapping(value = {"/courtList/{id}/{active}"})
    public String changeActiveCourt(@PathVariable int id, @PathVariable boolean active, Model model) {
    	courtController.changeCourtActivation(id, !active);
    	model.addAttribute("courtList", courtController.showCourts());
        return "/courtList";
    }
    
    @ModelAttribute("now")
    public String now() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }
    
}
