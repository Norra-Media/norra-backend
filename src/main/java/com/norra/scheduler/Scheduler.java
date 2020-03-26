package com.norra.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Scheduler {

    @Scheduled(cron = "${cron-expression}")
    public void cronJobSch() {
    }

    @Scheduled(cron = "${cron-expression-per-minute}")
    public void pushNotificationSch() {
    }


    

}
