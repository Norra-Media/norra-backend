package com.norra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

@Configuration
public class SchedulingConfig implements SchedulingConfigurer {

    @Value("${bounce.pool.scheduler.springscheduler.threadpool.size}")
    private int scheduledThreadPoolSize;

    /**
     * Needed to ensure spring's scheduler uses the default scheduler and not the redisson's task scheduler instead
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(scheduledThreadPoolSize));
    }
}
