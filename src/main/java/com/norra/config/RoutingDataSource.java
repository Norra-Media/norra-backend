package com.norra.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> routeContext = new ThreadLocal<>();


    public static void clearDataSourceRoute() {
        routeContext.remove();
    }

    public static void setDataSourceRoute(String datasource) {
        routeContext.set(datasource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return routeContext.get();
    }
}