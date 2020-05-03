package com.mao.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("数据源为: " + DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }

}
