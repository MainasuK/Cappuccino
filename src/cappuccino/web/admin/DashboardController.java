package cappuccino.web.admin;

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
@RequestMapping("/dashboard")
public class DashboardController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public DashboardController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("employees", employeeRepository.employees());
        model.addAttribute("username", principal.getName());

        return "admin/dashboard";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String username, String password, String fullName, String role) {
        employeeRepository.save(username, password, fullName, role);

        return "redirect: /dashboard";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Employee employee) {
        employeeRepository.update(employee.getUsername(), employee);

        return "redirect: /dashboard";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String setDisable(String username, boolean flag) {

        if (flag) {
            employeeRepository.disable(username);
        } else {
            employeeRepository.enable(username);
        }

        return "redirect: /dashboard";
    }

}
