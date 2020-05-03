package com.mao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author bigdope
 * @create 2020-01-17
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryThirdly",
        transactionManagerRef="transactionManagerThirdly",
        basePackages= {"com.mao.mapper.db2"}) //设置Repository所在位置
public class ThirdlyConfig {

    @Autowired
    @Qualifier("thirdlyDataSource")
    private DataSource thirdlyDataSource;

    @Bean(name = "entityManagerThirdly")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryThirdly(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryThirdly")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryThirdly (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(thirdlyDataSource)
                .properties(getVendorProperties(thirdlyDataSource))
                .packages("com.mao.model") //设置实体类所在位置
                .persistenceUnit("thirdlyPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerThirdly")
    public PlatformTransactionManager transactionManagerThirdly(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryThirdly(builder).getObject());
    }

}
