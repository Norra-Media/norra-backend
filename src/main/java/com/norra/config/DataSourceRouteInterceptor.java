package com.norra.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
@Slf4j
public class DataSourceRouteInterceptor {

    @Around("@annotation(dataSource)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, DataSource dataSource) throws Throwable {
        try {
            RoutingDataSource.setDataSourceRoute(dataSource.value());
            log.info("Routing database call to {}", dataSource.value());
            return proceedingJoinPoint.proceed();
        } finally {
            RoutingDataSource.clearDataSourceRoute();
        }
    }
}