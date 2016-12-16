package cappuccino.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.math.BigDecimal;

/**
 * Created by MainasuK on 2016-12-16.
 */
public class Commodity {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer storage;

    public Commodity(String name, BigDecimal price) {
        this(null, name, price, null);
    }

    public Commodity(Integer id, String name, BigDecimal price, Integer storage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.storage = storage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
