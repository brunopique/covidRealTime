package com.brunopique.covid.web.rest;


import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/covid/days", produces = "application/json")
public class CovidDataController {

    @Autowired
    private CovidDataService covidDataService;

    @EventListener(ApplicationReadyEvent.class)
    public void parseDailyCovidData() {
        if(covidDataService.parseData())
            System.out.println("Covid data parsed correctly!");
        else
            System.out.println("An error occurred parsing the data!");
    }

    @GetMapping("/{regionName}")
    @ResponseBody
    public ResponseEntity<CovidData> getRegion(@PathVariable String regionName) {
        CovidData day = covidDataService.getDayWithMostDeathsForSubregion(regionName);
        if (day == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(day);
    }


    // TODO: this endpoint should go in subregion controller
    @GetMapping("/deaths")
    @ResponseBody
    public ResponseEntity<CovidData> getSubRegion() {
        CovidData day = covidDataService.findAllByOrderByDeathsDesc();

        System.out.println("day.getSubregion() = " + day.getSubregion());

        if (day == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(day);
    }

    // TODO: this endpoint should go in region controller
    @GetMapping("/region/{name}")
    @ResponseBody
    public String getDataByRegion(@PathVariable String name) {
        List<CovidData> day = covidDataService.findAllBySubregion_Region_NameOrderByDeathsDesc(name);

        // TODO: this works but can sum be done with SQL?

        long deaths = day.stream()
                .mapToLong(CovidData::getDeaths)
                .sum();

    return "Number of deaths in " + name + ": " + deaths;
    }
}
