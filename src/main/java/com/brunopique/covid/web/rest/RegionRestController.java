package com.brunopique.covid.web.rest;

import com.brunopique.covid.domain.Region;
import com.brunopique.covid.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/covid/regions", produces = "application/json")
public class RegionRestController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Region>> getAllRegions() {
        final var regionData = regionService.getAllRegions();
        return ResponseEntity.ok(regionData);
    }

    @GetMapping("/{regionName}")
    @ResponseBody
    public ResponseEntity<Region> getRegion(@PathVariable String regionName) {
        final var regionData = regionService.findByName(regionName).orElse(new Region());
        return ResponseEntity.ok(regionData);
    }

    @GetMapping("/mostdeaths")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getDeathliestRegion() {
        final var regionDeathliest = regionService.findWithMostDeaths();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastdeaths")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getLessDeathliestRegion() {
        final var regionDeathliest = regionService.findWithLeastDeaths();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/mostconfirmed")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithMostConfirmed() {
        final var regionDeathliest = regionService.findWithHighestConfirmedCases();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastconfirmed")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLeastConfirmed() {
        final var regionDeathliest = regionService.findWithLowestConfirmedCases();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/mostrecovered")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithMostRecovered() {
        final var regionDeathliest = regionService.findWithMostRecovered();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastrecovered")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLeastRecovered() {
        final var regionDeathliest = regionService.findWithLeastRecovered();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/highestincident")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithHighestIncidentRate() {
        final var regionDeathliest = regionService.findWithHighestIncidentRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/lowestincident")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLowestIncidentRate() {
        final var regionDeathliest = regionService.findWithLowestIncidentRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/highestfatality")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithHighestFatalityRate() {
        final var regionDeathliest = regionService.findWithHighestFatalityRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/lowestfatality")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLowestFatalityRate() {
        final var regionDeathliest = regionService.findWithLowestFatalityRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/deaths/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalDeathsByName(@PathVariable String regionName) {
        final var totalDeaths = regionService.findTotalDeathsByRegion(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"deaths\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/confirmed/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalConfirmed(@PathVariable String regionName) {
        final var totalDeaths = regionService.findTotalConfirmedByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"confirmed\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/recovered/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalRecovered(@PathVariable String regionName) {
        final var totalDeaths = regionService.findTotalRecoveredByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"recovered\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/incidentrate/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalIncidentRateByName(@PathVariable String regionName) {
        final var totalDeaths = regionService.findTotalIncidentRateByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"incident_rate\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/fatalityratio/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalFatalityRatioByName(@PathVariable String regionName) {
        final var totalDeaths = regionService.findTotalFatalityRatioByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"fatality_ratio\" : \"" + totalDeaths + "\" }");
    }
}
