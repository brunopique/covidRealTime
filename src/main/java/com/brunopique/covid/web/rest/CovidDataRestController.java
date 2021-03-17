package com.brunopique.covid.web.rest;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/covid/data", produces = "application/json")
public class CovidDataRestController {

    @Autowired
    private CovidDataService covidDataService;

    @EventListener(ApplicationReadyEvent.class)
    public void parseDailyCovidData() {
        if(covidDataService.parseData())
            System.out.println("Covid data parsed correctly!");
        else
            System.out.println("An error occurred parsing the data!");
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<CovidData>> getAllByDate(@PathVariable String date) {
        List<CovidData> covidDataList = covidDataService.findAllByDate(LocalDate.parse(date));
        return ResponseEntity.ok(covidDataList);
    }

}
