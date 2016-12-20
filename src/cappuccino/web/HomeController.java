package cappuccino.web;

import cappuccino.pojo.Employee;
import cappuccino.protocol.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Controller
@RequestMapping({"/"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, Principal principal) {

        if (null == principal || null == principal.getName()) {
            model.addAttribute("username", "");
        } else {
            model.addAttribute("username", principal.getName());
        }

        return "home";
    }

}
