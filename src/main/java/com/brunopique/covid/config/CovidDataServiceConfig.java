package com.brunopique.covid.config;

import com.brunopique.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 A {@code CovidDataServiceConfig} object implements Scheduling
 to automatically download, parse and upload data to database
 ever day at 00:00.
 */
@Configuration
@EnableScheduling
public class CovidDataServiceConfig {

    @Autowired
    private CovidDataService covidDataService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Madrid")
    void parseData() {
        if(covidDataService.parseData())
            System.out.println("Covid data parsed correctly!");
        else
            System.out.println("An error occurred parsing the data!");
    }
}
