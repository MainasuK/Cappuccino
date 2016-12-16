package cappuccino.web.admin;

import cappuccino.pojo.Employee;
import cappuccino.protocol.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard() {
        return "admin/dashboard";
    }

}
