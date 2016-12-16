package cappuccino.pojo;

import cappuccino.misc.enums.GenderEnum;
import cappuccino.misc.enums.PositionEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MainasuK on 2016-12-16.
 */
public class Employee {
    private String fullname;
    private String username;
    private String password;
    private PositionEnum positionEnum;
    private GenderEnum genderEnum;
    private boolean isValid;

    public Employee(String fullname, String username, String password, PositionEnum positionEnum, GenderEnum genderEnum, boolean isValid) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.positionEnum = positionEnum;
        this.genderEnum = genderEnum;
        this.isValid = isValid;
    }

    public Employee(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getString("fullname"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                PositionEnum.values()[resultSet.getInt("position")],
                GenderEnum.values()[resultSet.getInt("gender")],
                resultSet.getBoolean("isValid")
        );
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public PositionEnum getPositionEnum() {
        return positionEnum;
    }

    public void setPositionEnum(PositionEnum positionEnum) {
        this.positionEnum = positionEnum;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getValid() {
        return isValid ? "有效" : "无效";
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
