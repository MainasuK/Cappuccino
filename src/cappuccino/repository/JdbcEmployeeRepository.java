package cappuccino.repository;

import cappuccino.pojo.Employee;
import cappuccino.protocol.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Repository
@EnableTransactionManagement
public class JdbcEmployeeRepository implements EmployeeRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcEmployeeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public List<Employee> employees() {
        return jdbcOperations.query("SELECT * FROM employee", (resultSet, i) -> new Employee(resultSet));
    }

    @Override
    @Transactional
    public void save(String username, String password, String fullName, String role) {
        jdbcOperations.update("INSERT INTO users (username, password, full_name) VALUES (?, ?, ?);", username, password, fullName);
        jdbcOperations.update("INSERT INTO user_roles (username, role) VALUES (?, ?);", username, role);
    }

    @Override
    @Transactional
    public void update(String username, Employee employee) {
        jdbcOperations.update("UPDATE users u SET u.password = ?, u.full_name = ? where u.username = ?",
                employee.getPassword(), employee.getFullName(), username);
        jdbcOperations.update("UPDATE user_roles ur SET ur.role = ? where ur.username = ?", employee.getRole(), username);
    }

    @Override
    public void disable(String username) {
        jdbcOperations.update("UPDATE users u SET u.enabled = ? WHERE u.username = ?", false, username);
    }

    @Override
    public void enable(String username) {
        jdbcOperations.update("UPDATE users u SET u.enabled = ? WHERE u.username = ?", true, username);
    }


}
