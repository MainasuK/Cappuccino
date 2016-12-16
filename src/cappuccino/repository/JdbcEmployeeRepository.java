package cappuccino.repository;

import cappuccino.pojo.Employee;
import cappuccino.protocol.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcEmployeeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public List<Employee> findEmployees() {
        return jdbcOperations.query("SELECT * FROM user_table", (resultSet, i) -> new Employee(resultSet));
    }

    @Override
    public Employee verify(String username, String password) {
        return jdbcOperations.queryForObject("SELECT nullif(ut.isValid, 0)FROM user_table ut WHERE ut.username = ? AND ut.password = ?", (resultSet, i) -> new Employee(resultSet), username, password);
    }
}
