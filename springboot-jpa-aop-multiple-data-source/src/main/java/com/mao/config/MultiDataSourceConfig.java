package com.mao.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * https://www.jianshu.com/p/fdd9a7546aee -- SpringBoot+JPA多数据源（注解方式）
 * @author bigdope
 * @create 2020-01-13
 **/
@Slf4j
@Component
public class MultiDataSourceConfig {

    @Autowired
    private DruidProperties druidProperties;

    @Autowired
    private MultiDataSource multiDataSource;

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(name = "com.mao.boot.multiDatasourceOpen", havingValue = "false")
    public DruidDataSource singleDatasource() {
        log.error("singleDatasource");
        return druidProperties.config(new DruidDataSource());
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(name = "com.mao.boot.multiDatasourceOpen", havingValue = "true")
    public DynamicDataSource multiDataSources() {
        log.error("multiDataSources");

        //存储数据源别名与数据源的映射
        Map<Object, Object> dbNameMap = new HashMap<>();
        // 核心数据源
        DruidDataSource mainDataSource = druidProperties.config();
        // 这里添加 主数据库，其它数据库挂了，默认使用主数据库
        dbNameMap.put("main", mainDataSource);
        // 其它数据源
        // 当前多数据源是否存在
        if (multiDataSource.getDatasource() != null) {
            //过滤掉没有添加 dbName 的数据源，先加载全局配置，再次加载当前配置
            List<DruidDataSource> multiDataSourceList = multiDataSource.getDatasource().stream()
                    .filter(dp -> !"".equals(Optional.ofNullable(dp.getDbName()).orElse("")))
                    .map(dp -> {
                        DruidDataSource druidDataSource = dp.config(druidProperties.config());
                        dbNameMap.put(dp.getDbName(), druidDataSource);
                        return druidDataSource;
                    })
                    .collect(Collectors.toList());

            // 测试所有的数据源
            try {
                mainDataSource.init();
                for (DruidDataSource druidDataSource : multiDataSourceList) {
                    druidDataSource.init();
                }
            } catch (SQLException sql) {
                log.error("=======================    多数据源配置错误   ==========================");
                sql.printStackTrace();
            }
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dbNameMap);
        dynamicDataSource.setDefaultTargetDataSource(mainDataSource);
        return dynamicDataSource;
    }

}
