package cappuccino.repository;

import cappuccino.pojo.CMKCommodity;
import cappuccino.pojo.Commodity;
import cappuccino.protocol.CMKCommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by MainasuK on 2016-12-19.
 */
@Repository
@EnableTransactionManagement
public class JdbcCMKCommodityRepository implements CMKCommodityRepository {

    JdbcOperations jdbcOperations;

    List<CMKCommodity> commodityList = new ArrayList<>();
    Commodity currentCommodity = null;

    @Autowired
    public JdbcCMKCommodityRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<CMKCommodity> commodities() {
        return commodityList;
    }

    @Override
    public Commodity currentCommodity() {
        return currentCommodity;
    }

    @Override
    public void save(String barCode) {
        try {
            CMKCommodity commodity = jdbcOperations.queryForObject(
                    "SELECT * FROM commodities c WHERE c.bar_code = ? AND c.enabled = ?",
                    (resultSet, i) -> new CMKCommodity(resultSet),
                    barCode, true
            );

            currentCommodity = commodity;

            Optional<CMKCommodity> optional = commodityList.parallelStream()
                    .filter(c -> c.getCommodityID() == commodity.getCommodityID())
                    .findFirst();

            if (optional.isPresent()) {
                optional.ifPresent(c -> c.setQuantityPlus(1));
            } else {
                commodity.setQuantityPlus(1);
                commodityList.add(commodity);
            }
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void clear() {
        currentCommodity = null;
        commodityList.clear();
    }

    @Override
    public Integer totalQuantity() {
        return commodityList.parallelStream()
                .mapToInt(c -> c.getQuantity())
                .reduce(0, (left, right) -> left + right);
    }

    @Override
    public BigDecimal totalPrice() {
        return commodityList.parallelStream()
                .map(c -> c.getPrice().multiply(new BigDecimal(c.getQuantity())))
                .reduce(new BigDecimal("0.00"), (left, right) -> left.add(right));
    }

    @Override
    @Transactional
    public void pay() {
        BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                CMKCommodity commodity = commodityList.get(i);
                preparedStatement.setInt(1, commodity.getQuantity());
                preparedStatement.setInt(2, commodity.getCommodityID());
            }

            @Override
            public int getBatchSize() {
                return commodityList.size();
            }
        };
        jdbcOperations.batchUpdate("UPDATE commodities co SET co.storage = co.storage - ? WHERE co.commodity_id = ?", setter);

        // Insert sales record
        KeyHolder holder = new GeneratedKeyHolder();

        // Create preparedStatementCreator via lambda
        PreparedStatementCreator insertSalesRecordStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sales_record (sr_price) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setBigDecimal(1, totalPrice());

            return preparedStatement;
        };

        jdbcOperations.update(insertSalesRecordStatementCreator, holder);

        int salesRecoredID = holder.getKey().intValue();

        BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                CMKCommodity commodity = commodityList.get(i);
                preparedStatement.setInt(1, salesRecoredID);
                preparedStatement.setInt(2, commodity.getCommodityID());
                preparedStatement.setInt(3, commodity.getQuantity());
                preparedStatement.setBigDecimal(4, commodity.getPrice());
            }

            @Override
            public int getBatchSize() {
                return commodityList.size();
            }
        };

        jdbcOperations.batchUpdate("INSERT INTO sales_record_detail (sr_id, commodity_id, quantity, price) VALUES (?, ?, ?, ?)", batchPreparedStatementSetter);

        // Finish
        clear();
    }


}
