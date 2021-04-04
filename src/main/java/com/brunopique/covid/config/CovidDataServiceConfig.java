package com.brunopique.covid.config;

import com.brunopique.covid.service.CovidDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 A {@code CovidDataServiceConfig} object implements Scheduling
 to automatically download, parse and upload data to database
 ever day at 03:00.
 */
@Configuration
@EnableScheduling
public class CovidDataServiceConfig {

    @Autowired
    private CovidDataService covidDataService;
    private static final Logger logger = LoggerFactory.getLogger(CovidDataServiceConfig.class);

    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Madrid")
    public void parseData() {
        if(covidDataService.parseData())
            logger.info("Covid data parsed correctly!");
        else
            logger.warn("Data was not parsed! (already in database?)");
    }
}
