package cappuccino.protocol;

import cappuccino.pojo.Employee;

import java.util.List;

/**
 * Created by MainasuK on 2016-12-16.
 */
public interface EmployeeRepository {
    List<Employee> employees();
    void save(String username, String password, String fullName, String role);
    void update(String username, Employee employee);
    void disable(String username);
    void enable(String username);
}
