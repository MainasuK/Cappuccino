package cappuccino.protocol;

import cappuccino.pojo.Commodity;

import java.util.List;

/**
 * Created by MainasuK on 2016-12-19.
 */
public interface CommodityRepository {
    List<Commodity> commodities();
    List<Commodity> search(String keyword);
    void add(String barCode, String name, Double price);
    void modify(Integer commodifyID, String barCode, String name, Double price);
    void disable(Integer commodityID);
    void enable(Integer commodityID);
    void addQuantity(Integer commodityID, Integer quantity);
}
