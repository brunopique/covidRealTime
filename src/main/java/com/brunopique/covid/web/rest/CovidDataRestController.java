package com.brunopique.covid.web.rest;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/covid/data", produces = "application/json")
public class CovidDataRestController {

    @Autowired
    private CovidDataService covidDataService;

    @GetMapping("/{date}")
    public ResponseEntity<List<CovidData>> getAllByDate(@PathVariable String date) {
        try {
            final var covidDataList = covidDataService.findAllByDate(LocalDate.parse(date));
            return ResponseEntity.ok(covidDataList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mostdeaths")
    public ResponseEntity<CovidData> getByMostDeaths() {
        final var covidData = covidDataService.findFirstByOrderByDeathsDesc();
        return ResponseEntity.ok(covidData);
    }

    @GetMapping("/mostconfirmed")
    public ResponseEntity<CovidData> getByMostConfirmed() {
        final var covidData = covidDataService.findFirstByOrderByConfirmedDesc();
        return ResponseEntity.ok(covidData);
    }

    @GetMapping("/mostrecovered")
    public ResponseEntity<CovidData> getByMostRecovered() {
        final var covidData = covidDataService.findFirstByOrderByRecoveredDesc();
        return ResponseEntity.ok(covidData);
    }

    @GetMapping("/mostactive")
    public ResponseEntity<CovidData> getByMostActive() {
        final var covidData = covidDataService.findFirstByOrderByActiveDesc();
        return ResponseEntity.ok(covidData);
    }

    @GetMapping("/highestincident")
    public ResponseEntity<CovidData> getByHighestIncidentRate() {
        final var covidData = covidDataService.findFirstByOrderByIncidentRateDesc();
        return ResponseEntity.ok(covidData);
    }
    @GetMapping("/highestfatality")
    public ResponseEntity<CovidData> getByHighestFatalityRatio() {
        final var covidData = covidDataService.findFirstByOrderByCaseFatalityRatioDesc();
        return ResponseEntity.ok(covidData);
    }

    @GetMapping("/deaths/{deaths}")
    public ResponseEntity<List<CovidData>> getByNumberOfDeaths(@PathVariable Long deaths) {
        final List<CovidData> covidDataList = covidDataService.findAllByDeathsIsGreaterThanEqualAndDateGreaterThanEqual(deaths);
        return ResponseEntity.ok(covidDataList);
    }
}
