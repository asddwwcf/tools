package com.mine.tool.orm.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Prject: bqmart-elephant
 * @Package: cn.bqmart.config
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/12/5 20:00
 */
@Configuration
@SuppressWarnings("all")
@EnableTransactionManagement
public class CloudDruidConfig {

    @ConfigurationProperties(prefix = "datasource.cloud")
    @Bean
    public Properties cloudDataSourceProperties() {
        return new Properties();
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        Properties properties = cloudDataSourceProperties();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(properties.getProperty("dbType"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setDriverClassName(properties.getProperty("driver-class-name"));
        dataSource.setMaxActive(Integer.valueOf((String) properties.get("maxActive")));
        dataSource.setMinIdle(Integer.valueOf((String) properties.get("minIdle")));
        dataSource.setInitialSize(Integer.valueOf((String) properties.get("initialSize")));
        dataSource.setMaxWait(Integer.parseInt(properties.get("maxWait").toString()));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(getDataSource());
        return transactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }
}
