package com.brunopique.covid.web.rest;


import com.brunopique.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidDataController {

    @Autowired
    CovidDataService covidDataService;

    @EventListener(ApplicationReadyEvent.class)
    public void parseDailyCovidData() {
        //covidDataService.getCountriesAndRegionsOnFirstRun();
        if(covidDataService.parseData())
            System.out.println("Covid data parsed correctly!");
        else
            System.out.println("An error occurred parsing the data!");
    }
}
