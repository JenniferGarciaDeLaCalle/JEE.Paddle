package webs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

    public Presenter() {
    }
    
    @RequestMapping("/home")
    public String home(Model model) {
        return "/home";
    }
    
    @ModelAttribute("now")
    public String now() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }
    
}
