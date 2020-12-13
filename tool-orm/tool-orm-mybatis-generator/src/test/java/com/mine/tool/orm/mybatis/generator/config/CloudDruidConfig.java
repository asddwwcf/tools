package com.mine.tool.orm.mybatis.generator.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.bruce.tool.logger.core.annotation.EnableSQLFormat;
import com.bruce.tool.logger.core.format.DefaultSlf4jLogFilter;
import com.bruce.tool.orm.mybatis.interceptor.SqlJointInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.util.Lists;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Prject: bqmart-elephant
 * @Package: cn.bqmart.config
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/12/5 20:00
 */
@Configuration
@EnableSQLFormat
@SuppressWarnings("all")
@EnableTransactionManagement
@MapperScan(basePackages = "com.bqmart.tool.mybatis.orm.mapper", sqlSessionFactoryRef = "cloudSqlSessionFactory")
public class CloudDruidConfig {

    @Autowired
    private SqlJointInterceptor sqlJointInterceptor;

    @Autowired
    private DefaultSlf4jLogFilter defaultSlf4jLogFilter;

    @ConfigurationProperties(prefix = "datasource.cloud")
    @Bean
    public Properties cloudDataSourceProperties() {
        return new Properties();
    }

    @Bean(name = "cloudDataSource")
    public DataSource cloudDataSource(@Autowired @Qualifier("cloudDataSourceProperties") Properties properties) {
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
        dataSource.setProxyFilters(Lists.newArrayList(defaultSlf4jLogFilter));
        return dataSource;
    }

    @Bean(name = "cloudSqlSessionFactory")
    public SqlSessionFactory cloudSqlSessionFactory(@Qualifier("cloudDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPlugins(new Interceptor[]{sqlJointInterceptor});
        // mybatis的配置  替代mybatis-config.xml文件
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate cloudSqlSessionTemplate(@Qualifier("cloudSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }
}
