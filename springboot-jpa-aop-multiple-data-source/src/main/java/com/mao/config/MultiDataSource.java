package com.mao.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 多数据源配置
 * 多个数据源
 * @author bigdope
 * @create 2020-01-13
 **/
@Component
//@Configuration
@ConfigurationProperties(prefix = "com.mao.boot")
@Data
@Slf4j
public class MultiDataSource {

    public MultiDataSource() {
        log.info("加载多数据源配置信息  -->  {}", "com.mao.boot.datasource");
    }
    /**
     * 多个数据源
     */
    private List<DruidProperties> datasource;

}
