package cappuccino.pojo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MainasuK on 2016-12-16.
 */
public class Commodity {
    private Integer commodityID;
    private String name;
    private BigDecimal price;
    private Integer storage;
    private String barCode;
    private Boolean enabled;


    public Commodity() { }

    public Commodity(Integer commodityID, String name, BigDecimal price, Integer storage, String barCode, Boolean enabled) {
        this.commodityID = commodityID;
        this.name = name;
        this.price = price;
        this.storage = storage;
        this.barCode = barCode;
        this.enabled = enabled;
    }

    public Commodity(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getInt("commodity_id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price"),
                resultSet.getInt("storage"),
                resultSet.getString("bar_code"),
                resultSet.getBoolean("enabled")
        );
    }

    public Integer getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(Integer commodityID) {
        this.commodityID = commodityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
