package com.brunopique.covid.web.rest;

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
    private SubregionService subregionService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Subregion>> getAllSubregions() {
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
        return ResponseEntity.ok(subregion);
    }

}
