package cappuccino.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by MainasuK on 2016-12-21.
 */
@Controller
@RequestMapping("/403")
public class AccessDeniedController {

    @RequestMapping(method = RequestMethod.POST)
    public String post() {
        return "redirect: /403";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());

        return "403";
    }
}
