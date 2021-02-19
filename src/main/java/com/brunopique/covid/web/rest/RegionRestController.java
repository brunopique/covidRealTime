package com.brunopique.covid.web.rest;

import com.brunopique.covid.domain.Region;
import com.brunopique.covid.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/covid", produces = "application/json")
public class RegionRestController {

    @Autowired
    RegionService regionService;

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
        Region regionData = regionService.getRegion(regionName).orElse(new Region());
        if (regionData == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(regionData);
    }

    @GetMapping("/totaldeaths")
    @ResponseBody
    public ResponseEntity<Long> getTotalDeaths() {
        Long totalDeaths = regionService.getTotalDeaths();
        if (totalDeaths == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(totalDeaths);
    }
}
