package cappuccino.repository;

import cappuccino.pojo.Commodity;
import cappuccino.protocol.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MainasuK on 2016-12-19.
 */
@Repository
@EnableTransactionManagement
public class JdbcCommodityRepository implements CommodityRepository {

    JdbcOperations jdbcOperations;

    @Autowired
    public JdbcCommodityRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Commodity> commodities() {
        return jdbcOperations.query("SELECT co.* from commodities co ", (resultSet, i) -> new Commodity(resultSet));
    }

    @Override
    public List<Commodity> search(String keyword) {
        return commodities().parallelStream()
                .filter(c -> c.getPrice().toString().contains(keyword) || c.getName().contains(keyword) || c.getBarCode().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public void add(String barCode, String name, Double price) {
        jdbcOperations.update("INSERT INTO commodities (bar_code, `name`, price) VALUES (?, ?, ?)", barCode, name, price);
    }

    @Override
    public void modify(Integer commodifyID, String barCode, String name, Double price) {
        jdbcOperations.update("UPDATE commodities co SET bar_code = ?, `name` = ?, price = ? WHERE co.commodity_id = ?", barCode, name, price, commodifyID);
    }

    @Override
    public void disable(Integer commodityID) {
        jdbcOperations.update("UPDATE commodities co SET co.enabled = ? WHERE co.commodity_id = ?", false, commodityID);
    }

    @Override
    public void enable(Integer commodityID) {
        jdbcOperations.update("UPDATE commodities co SET co.enabled = ? WHERE co.commodity_id = ?", true, commodityID);
    }

    @Override
    public void addQuantity(Integer commodityID, Integer quantity) {
        jdbcOperations.update("UPDATE commodities co SET  co.storage = co.storage + ? WHERE co.commodity_id = ?", quantity, commodityID);
    }
}
