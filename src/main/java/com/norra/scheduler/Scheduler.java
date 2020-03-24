package com.norra.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.norra.service.UserSummaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Scheduler {

    @Autowired
    UserSummaryService userSummaryService;


    @Scheduled(cron = "${cron-expression}")
    public void cronJobSch() {
    }

    @Scheduled(cron = "${cron-expression-per-minute}")
    public void pushNotificationSch() {
    }


    

}
