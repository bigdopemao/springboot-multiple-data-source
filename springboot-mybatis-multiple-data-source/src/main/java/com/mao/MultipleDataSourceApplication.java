package com.mao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author bigdope
 * @create 2020-01-17
 **/
@SpringBootApplication
public class MultipleDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourceApplication.class, args);
        // 无需tomcat则用
//        new SpringApplicationBuilder(AopMultipleDataSourceApplication.class).web(false).run(args);
    }

}
