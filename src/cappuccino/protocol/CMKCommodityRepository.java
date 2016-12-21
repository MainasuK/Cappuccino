package cappuccino.protocol;

import cappuccino.pojo.CMKCommodity;
import cappuccino.pojo.Commodity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MainasuK on 2016-12-19.
 */
public interface CMKCommodityRepository {
    List<CMKCommodity> commodities();
    Commodity currentCommodity();
    void save(String barCode);
    void clear();
    Integer totalQuantity();
    BigDecimal totalPrice();
    void pay();
}
