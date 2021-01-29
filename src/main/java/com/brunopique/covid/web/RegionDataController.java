package com.brunopique.covid.web;

import com.brunopique.covid.domain.RegionData;
import com.brunopique.covid.service.RegionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/covid", produces = "application/json")
public class RegionDataController {

    @Autowired
    RegionDataService regionDataService;

    @EventListener(ApplicationReadyEvent.class)
    public void parseDailyCovidData() {
        regionDataService.parseData();
    }

    @GetMapping("/allregions")
    @ResponseBody
    public ResponseEntity<List<RegionData>> getAllRegions() {
        List<RegionData> regionData = regionDataService.getRegionData();
        if (regionData == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(regionData);
    }

    @GetMapping("/{regionName}")
    @ResponseBody
    public ResponseEntity<List<RegionData>> getRegion(@PathVariable String regionName) {
        List<RegionData> regionData = regionDataService.getRegion(regionName);
        if (regionData == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(regionData);
    }

    @GetMapping("/totaldeaths")
    @ResponseBody
    public ResponseEntity<Long> getTotalDeaths() {
        Long totalDeaths = regionDataService.getTotalDeaths();
        if (totalDeaths == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(totalDeaths);
    }
}
