package com.mao.config;

import com.mao.config.annotation.MultiDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author bigdope
 * @create 2020-01-13
 **/
@Component
@Aspect
@ConditionalOnProperty(prefix = "com.mao.boot", name = "multiDatasourceOpen", havingValue = "true")
public class MultiDataSourceAop implements Ordered {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public MultiDataSourceAop() {
        log.info("多数据源初始化 AOP ");
    }

    @Pointcut(value = "@annotation(com.mao.config.annotation.MultiDataSource)")
    private void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Signature signature = point.getSignature();
        MethodSignature methodSignature ;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;
        //获取当点方法的注解
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        MultiDataSource datasource = currentMethod.getAnnotation(MultiDataSource.class);
        if (datasource != null) {
            DynamicDataSource.setDataSourceDbName(datasource.name());
            log.debug("设置数据源为：" + datasource.name());
        } else {
            DynamicDataSource.setDataSourceDbName("main");
            log.debug("设置数据源为：默认  -->  main");
        }
        try {
            return point.proceed();
        } finally {
            log.debug("清空数据源信息！");
            DynamicDataSource.clearDataSourceDbName();
        }
    }

    /**
     * aop的顺序要早于spring的事务
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
