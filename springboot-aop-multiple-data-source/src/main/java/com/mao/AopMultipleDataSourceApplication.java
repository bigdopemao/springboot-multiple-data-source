package com.mao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的application.properties单数据源配置
 * @author bigdope
 * @create 2020-01-10
 **/
//@MapperScan("com.mao.mapper")
//@MapperScan(basePackages = "com.mao.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AopMultipleDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopMultipleDataSourceApplication.class, args);
        // 无需tomcat则用
//        new SpringApplicationBuilder(AopMultipleDataSourceApplication.class).web(false).run(args);
    }

}
