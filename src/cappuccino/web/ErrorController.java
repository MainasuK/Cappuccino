package cappuccino.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MainasuK on 2016-12-21.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(method = RequestMethod.POST)
    public String post() {
        return "redirect: /error";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "error";
    }

}
