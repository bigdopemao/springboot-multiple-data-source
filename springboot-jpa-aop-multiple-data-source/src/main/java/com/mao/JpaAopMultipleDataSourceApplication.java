package com.mao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的application.properties单数据源配置
 * @author bigdope
 * @create 2020-01-10
 **/
//@MapperScan("com.mao.mapper")
//@MapperScan(basePackages = "com.mao.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JpaAopMultipleDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaAopMultipleDataSourceApplication.class, args);
    }

}
