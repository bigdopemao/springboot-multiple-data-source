package com.mao.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
@Aspect
@Order(1) // 设置AOP执行顺序(需要在事务之前，否则事务只发生在默认库中)
@Component
public class DynamicDataSourceAspect {

    // 切点为注释
    @Pointcut("@annotation(com.mao.config.TargetDataSource)")
    public void dataSourcePoint() {}

    // 切点为service包
    @Pointcut("execution(* com.mao.service.*.*(..))")
    public void dataSourcePointPackage() {}

//    @Before("dataSourcePoint()")
    @Before("dataSourcePointPackage()")
    public void before(JoinPoint joinPoint) {
        // 获得当前访问的class
        Class<?> className = joinPoint.getTarget().getClass();
        // 获得访问的方法名
        String methondName = joinPoint.getSignature().getName();
        // 得到方法的参数的类型
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DATA_SOURCE;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methondName, parameterTypes);
            // 判断是否存在@TargetDataSource注解
            if (null != method && method.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDataSource(dataSource);
    }

    @After("dataSourcePoint()")
    public void after(JoinPoint joinPoint) {
        DataSourceContextHolder.clearDataSource();
    }
}
