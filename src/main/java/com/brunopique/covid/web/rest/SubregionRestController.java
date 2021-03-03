package com.brunopique.covid.web.rest;

import com.brunopique.covid.domain.Region;
import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.service.SubregionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/covid/subregions", produces = "application/json")
public class SubregionRestController {

    @Autowired
    SubregionService subregionService;

    @GetMapping("/allregions")
    @ResponseBody
    public ResponseEntity<List<Subregion>> getAllRegions() {
        List<Subregion> subregions = subregionService.getAllSubregions();
        if (subregions == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(subregions);
    }

    @GetMapping("/{subregionName}")
    @ResponseBody
    public ResponseEntity<Subregion> getRegion(@PathVariable String subregionName) {
        Subregion subregion = subregionService.findByName(subregionName).orElse(new Subregion());
        if (subregion == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(subregion);
    }

    @GetMapping("/mostdeaths")
    public Subregion getSubegionWithMostDeaths() {
        return subregionService.getSubregionWithMostDeaths();
    }
}
