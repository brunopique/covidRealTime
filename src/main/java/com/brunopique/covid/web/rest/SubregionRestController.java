package com.brunopique.covid.web.rest;

import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.service.SubregionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/covid/subregions", produces = "application/json")
public class SubregionRestController {

    @Autowired
    private SubregionService subregionService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Subregion>> getAllSubregions() {
        final var subregionList = subregionService.getAllSubregions();
        return ResponseEntity.ok(subregionList);
    }

    @GetMapping("/{subregionName}")
    @ResponseBody
    public ResponseEntity<Subregion> getSubregionByName(@PathVariable String subregionName) {
        final var subregion = subregionService.findByName(subregionName).orElse(new Subregion());
        return ResponseEntity.ok(subregion);
    }

    @GetMapping("/mostdeaths")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithMostDeaths() {
        final var regionDeathliest = subregionService.findWithMostDeaths();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastdeaths")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithLeastDeaths() {
        final var regionDeathliest = subregionService.findWithLeastDeaths();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/mostconfirmed")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithMostConfirmed() {
        final var regionDeathliest = subregionService.findWithHighestConfirmedCases();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastconfirmed")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithLeastConfirmed() {
        final var regionDeathliest = subregionService.findWithLowestConfirmedCases();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/mostrecovered")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithMostRecovered() {
        final var regionDeathliest = subregionService.findWithMostRecovered();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastrecovered")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithLeastRecovered() {
        final var regionDeathliest = subregionService.findWithLeastRecovered();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/highestincident")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithHighestIncidentRate() {
        final var regionDeathliest = subregionService.findWithHighestIncidentRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/lowestincident")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithLowestIncidentRate() {
        final var regionDeathliest = subregionService.findWithLowestIncidentRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/highestfatality")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithHighestFatalityRate() {
        final var regionDeathliest = subregionService.findWithHighestFatalityRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/lowestfatality")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSubregionWithLowestFatalityRate() {
        final var regionDeathliest = subregionService.findWithLowestFatalityRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/deaths/{subregionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalDeathsByName(@PathVariable String subregionName) {
        final var totalDeaths = subregionService.findTotalDeathsByName(subregionName);
        return ResponseEntity.ok("{ \"country\" : \"" + subregionName + "\", \"deaths\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/confirmed/{subregionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalConfirmed(@PathVariable String subregionName) {
        final var totalDeaths = subregionService.findTotalConfirmedByName(subregionName);
        return ResponseEntity.ok("{ \"country\" : \"" + subregionName + "\", \"confirmed\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/recovered/{subregionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalRecovered(@PathVariable String subregionName) {
        final var totalDeaths = subregionService.findTotalRecoveredByName(subregionName);
        return ResponseEntity.ok("{ \"country\" : \"" + subregionName + "\", \"recovered\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/incidentrate/{subregionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalIncidentRateByName(@PathVariable String subregionName) {
        final var totalDeaths = subregionService.findTotalIncidentRateByName(subregionName);
        return ResponseEntity.ok("{ \"country\" : \"" + subregionName + "\", \"incident_rate\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/fatalityratio/{subregionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalFatalityRatioByName(@PathVariable String subregionName) {
        final var totalDeaths = subregionService.findTotalFatalityRatioByName(subregionName);
        return ResponseEntity.ok("{ \"country\" : \"" + subregionName + "\", \"fatality_ratio\" : \"" + totalDeaths + "\" }");
    }

}
