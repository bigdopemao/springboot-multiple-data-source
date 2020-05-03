package com.mao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
public class DataSourceContextHolder {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private final static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public final static String DEFAULT_DATA_SOURCE = "dataSource1";

    /**
     * 置线程持有的DataSource, 底层以map形式呈现, key为当前线程
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        logger.info("切换到{}数据源", dataSource);
        contextHolder.set(dataSource);
    }

    /**
     * 获取线程持有的当前数据源
     * @return
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * 清除线程持有的当前数据源
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

}
