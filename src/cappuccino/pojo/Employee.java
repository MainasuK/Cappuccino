package cappuccino.pojo;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by MainasuK on 2016-12-16.
 */

public class Employee {

    @NotNull
    @Size(min = 1, max = 16)
    private String fullName;

    @NotNull
    @Size(min = 5, max = 25)
    private String username;

    @NotNull
    @Size(min = 5, max = 25)
    private String password;

    private String role;
    private boolean enabled;

    public Employee() { }

    public Employee(String fullName, String username, String password, String role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public Employee(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getString("full_name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role"),
                resultSet.getBoolean("enabled")
        );
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRolePlainString() {
        if ("ROLE_ADMIN".equals(role)) {
            return "管理员";
        } else if ("ROLE_STOCK_KEEPER".equals(role)) {
            return "理货员";
        } else if ("ROLE_TELLER".equals(role)) {
            return "收银员";
        } else {
            return "";
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getValid() {
        return enabled ? "有效" : "无效";
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
