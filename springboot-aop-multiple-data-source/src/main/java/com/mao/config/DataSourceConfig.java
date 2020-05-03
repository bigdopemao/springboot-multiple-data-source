package com.mao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * https://blog.csdn.net/xiaosheng_papa/article/details/80218006 -- springboot+mybatis多数据源配置，AOP注解动态切换数据源
 * https://blog.csdn.net/u011976388/article/details/85331523 -- SpringBoot：多数据源配置——注解+AOP
 * 多数据源配置类
 * @author bigdope
 * @create 2020-01-10
 **/
@Configuration
public class DataSourceConfig {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean("dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.db1") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
//        return DataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean("dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.db2") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Primary
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());
        // 配置多数据源
        // 添加数据源标识和DataSource引用到目标源映射
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("dataSource1", dataSource1());
        dataSourceMap.put("dataSource2", dataSource2());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
