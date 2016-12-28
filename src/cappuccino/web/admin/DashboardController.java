package cappuccino.web.admin;

import cappuccino.pojo.Employee;
import cappuccino.protocol.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(method = GET)
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("employees", employeeRepository.employees());
        model.addAttribute("username", principal.getName());

        return "admin/dashboard";
    }

    @RequestMapping(value = "/add", method = POST)
    public String add(String username, String password, String fullName, String role) {
        employeeRepository.save(username, password, fullName, role);

        return "redirect: /dashboard";
    }

    @RequestMapping(value = "/modify", method = POST)
    public String modify(Employee employee) {
        employeeRepository.update(employee.getUsername(), employee);

        return "redirect: /dashboard";
    }

    @RequestMapping(value = "/delete", method = POST)
    public String setDisable(String username, boolean flag) {

        if (flag) {
            employeeRepository.disable(username);
        } else {
            employeeRepository.enable(username);
        }

        return "redirect: /dashboard";
    }

    @RequestMapping(value = "/addPhoto", method = GET)
    public String addPhoto() {
        return "admin/addPhoto";
    }

    @RequestMapping(value = "/addPhoto", method = POST)
    public String addPhotoForm(@RequestPart("photo") MultipartFile photo) {
        try {
            photo.transferTo(new File("/tmp/pic" + photo.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "admin/addPhoto";
    }

}
