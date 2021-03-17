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

    @GetMapping("/allregions")
    @ResponseBody
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regionData = regionService.getAllRegions();
        if (regionData == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(regionData);
    }

    @GetMapping("/{regionName}")
    @ResponseBody
    public ResponseEntity<Region> getRegion(@PathVariable String regionName) {
        Region regionData = regionService.findByName(regionName).orElse(new Region());
        return ResponseEntity.ok(regionData);
    }

    @GetMapping("/mostdeaths")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getDeathliestRegion() {
        Map<String, String> regionDeathliest = regionService.findWithMostDeaths();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastdeaths")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getLessDeathliestRegion() {
        Map<String, String> regionDeathliest = regionService.findWithLeastDeaths();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/mostconfirmed")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithMostConfirmed() {
        Map<String, String> regionDeathliest = regionService.findWithHighestConfirmedCases();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastconfirmed")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLeastConfirmed() {
        Map<String, String> regionDeathliest = regionService.findWithLowestConfirmedCases();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/mostrecovered")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithMostRecovered() {
        Map<String, String> regionDeathliest = regionService.findWithMostRecovered();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/leastrecovered")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLeastRecovered() {
        Map<String, String> regionDeathliest = regionService.findWithLeastRecovered();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/highestincident")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithHighestIncidentRate() {
        Map<String, String> regionDeathliest = regionService.findWithHighestIncidentRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/lowestincident")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLowestIncidentRate() {
        Map<String, String> regionDeathliest = regionService.findWithLowestIncidentRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/highestfatality")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithHighestFatalityRate() {
        Map<String, String> regionDeathliest = regionService.findWithHighestFatalityRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/lowestfatality")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getRegionWithLowestFatalityRate() {
        Map<String, String> regionDeathliest = regionService.findWithLowestFatalityRate();
        return ResponseEntity.ok(regionDeathliest);
    }

    @GetMapping("/deaths/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalDeathsByName(@PathVariable String regionName) {
        Long totalDeaths = regionService.findTotalDeathsByRegion(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"deaths\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/confirmed/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalConfirmed(@PathVariable String regionName) {
        Long totalDeaths = regionService.findTotalConfirmedByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"confirmed\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/recovered/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalRecovered(@PathVariable String regionName) {
        Long totalDeaths = regionService.findTotalRecoveredByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"recovered\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/incidentrate/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalIncidentRateByName(@PathVariable String regionName) {
        Double totalDeaths = regionService.findTotalIncidentRateByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"incident_rate\" : \"" + totalDeaths + "\" }");
    }

    @GetMapping("/fatalityratio/{regionName}")
    @ResponseBody
    public ResponseEntity<String> getTotalFatalityRatioByName(@PathVariable String regionName) {
        Double totalDeaths = regionService.findTotalFatalityRatioByName(regionName);
        return ResponseEntity.ok("{ \"country\" : \"" + regionName + "\", \"fatality_ratio\" : \"" + totalDeaths + "\" }");
    }
}
