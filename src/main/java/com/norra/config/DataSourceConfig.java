package com.norra.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceConfig {

    private static final String PRIMARY_DATASOURCE_PREFIX = "spring.datasource.primary.";
    private static final String SECONDARY_DATASOURCE_PREFIX = "spring.datasource.secondary.";
    private static final String DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSource dataSource() {
        final RoutingDataSource routingDataSource = new RoutingDataSource();

        Properties hikariProperties = new Properties();
        List<String> secondaries = new ArrayList<>();

        for (Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (!(propertySource instanceof MapPropertySource)) continue;

            for (String property : ((MapPropertySource) propertySource).getPropertyNames()) {
                String propertyValue = propertySource.getProperty(property).toString();

                if (property.contains("dataSource.hikari")) {
                    hikariProperties.setProperty(property, propertyValue);
                }

                if (property.contains(SECONDARY_DATASOURCE_PREFIX)) {
                    String[] splits = property.split("\\.");

                    if (splits.length < 5 || secondaries.contains(splits[3])) continue;
                    secondaries.add(splits[3]);
                }
            }

        }

        final Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource primaryDataSource = buildDataSource("PrimaryHikariPool", PRIMARY_DATASOURCE_PREFIX, hikariProperties);

        targetDataSources.put("PRIMARY", primaryDataSource);

        secondaries.forEach(s ->
                targetDataSources.put(s,
                        buildDataSource(s + "HikariPool", SECONDARY_DATASOURCE_PREFIX + s, hikariProperties))
        );

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);

        return routingDataSource;
    }

    private DataSource buildDataSource(String poolName, String dataSourcePrefix, Properties properties) {
        final HikariConfig hikariConfig = new HikariConfig(properties);

        hikariConfig.setPoolName(poolName);
        hikariConfig.setJdbcUrl(environment.getProperty(String.format("%s.url", dataSourcePrefix)));
        hikariConfig.setUsername(environment.getProperty(String.format("%s.username", dataSourcePrefix)));
        hikariConfig.setPassword(environment.getProperty(String.format("%s.password", dataSourcePrefix)));
        hikariConfig.setDriverClassName(environment.getProperty(DRIVER_CLASS_NAME));
        String maxConnnectionsStr = environment.getProperty(String.format("%s.hikari.maximum-pool-size", dataSourcePrefix));
        if(maxConnnectionsStr!=null && !maxConnnectionsStr.isEmpty()) {
            int maxConnections = Integer.parseInt(environment.getProperty(String.format("%s.hikari.maximum-pool-size", dataSourcePrefix)));
            hikariConfig.setMaximumPoolSize(maxConnections);
        }
        return new HikariDataSource(hikariConfig);
    }
}