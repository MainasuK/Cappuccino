package cappuccino.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Configuration
@PropertySource("classpath:/cappuccino/db.properties")
public class DBConfig {

    @Bean
    public BasicDataSource dataSource(
            @Value("${db.url}") String url,
            @Value("${db.username}") String username,
            @Value("${db.password}") String password
    ) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(url);    // Set timezone to China Standard Time
        dataSource.setDefaultQueryTimeout(10);  // wait 10s
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
