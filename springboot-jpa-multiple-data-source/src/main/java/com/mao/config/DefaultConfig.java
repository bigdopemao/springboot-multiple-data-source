//package com.mao.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * @author bigdope
// * @create 2017-12-21
// **/
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactoryDefault",
//        transactionManagerRef="transactionManagerDefault",
//        basePackages= { "com.massestech.statistics.dal.owner" }) //设置Repository所在位置
//public class DefaultConfig {
//
//    @Autowired
//    @Qualifier("defaultDataSource")
//    private DataSource defaultDataSource;
//
//    @Bean(name = "entityManagerDefault")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryDefault(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "entityManagerFactoryDefault")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDefault (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(defaultDataSource)
//                .properties(getVendorProperties(defaultDataSource))
//                .packages("com.massestech.statistics.dal.owner") //设置实体类所在位置
//                .persistenceUnit("defaultPersistenceUnit")
//                .build();
//    }
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    @Bean(name = "transactionManagerDefault")
//    PlatformTransactionManager transactionManagerDefault(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryDefault(builder).getObject());
//    }
//
//}
