package com.norra;

import java.time.ZonedDateTime;
import java.util.TimeZone;
import javax.annotation.PostConstruct;

import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.norra.constants.Constants;
import com.norra.scheduler.Scheduler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

	@Value("${pool.sentry.dsn}")
	private String dsn;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConditionalOnProperty(value = "jobs.enabled")
    public Scheduler scheduler() {
        return new Scheduler();
    }

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(Constants.TIMEZONE_UTC));
		log.info("Started application in UTC timezone :" + ZonedDateTime.now());
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Setting up sentry");
		Sentry.init(dsn);
	}
}
