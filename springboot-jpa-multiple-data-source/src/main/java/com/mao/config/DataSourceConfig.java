package com.mao.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author bigdope
 * @create 2017-12-21
 **/
@Configuration
public class DataSourceConfig {

//    @Bean(name = "defaultDataSource")
//    @Qualifier("defaultDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.default")
//    public DataSource defaultDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "thirdlyDataSource")
    @Qualifier("thirdlyDataSource")
    @ConfigurationProperties(prefix="spring.datasource.thirdly")
    public DataSource thirdlyDataSource() {
        return DataSourceBuilder.create().build();
    }

}
