package cappuccino.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MainasuK on 2016-12-21.
 */
@Controller
@RequestMapping("/404")
public class NotFoundController {

    @RequestMapping(method = RequestMethod.POST)
    public String post() {
        return "redirect: /404";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "404";
    }

}
