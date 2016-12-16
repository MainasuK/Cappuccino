package cappuccino.protocol;

import cappuccino.misc.enums.PositionEnum;
import cappuccino.pojo.Employee;

import java.util.List;

/**
 * Created by MainasuK on 2016-12-16.
 */
public interface EmployeeRepository {
    List<Employee> findEmployees();
    Employee verify(String username, String password);
}
