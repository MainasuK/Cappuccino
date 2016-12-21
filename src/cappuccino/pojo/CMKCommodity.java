package cappuccino.pojo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MainasuK on 2016-12-19.
 */
public class CMKCommodity extends Commodity {
    private Integer quantity = 0;
    private BigDecimal totalPrice = new BigDecimal("0.00");

    public CMKCommodity(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setQuantityPlus(Integer n) {
        this.quantity += n;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
