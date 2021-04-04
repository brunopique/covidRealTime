package com.brunopique.covid.web;

import com.brunopique.covid.domain.IndexModel;
import com.brunopique.covid.service.CovidDataService;
import com.brunopique.covid.service.RegionService;
import com.brunopique.covid.service.SubregionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private CovidDataService covidDataService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SubregionService subregionService;

    @GetMapping("/")
    public String getCovidData(ModelMap model) {

        final var indexModel = new IndexModel();

        // Worldwide
        indexModel.setWorldwideDeaths(covidDataService.getWorldwideDeaths());
        indexModel.setWorldwideConfirmed(covidDataService.getWorldwideConfirmed());
        indexModel.setWorldwideRecovered(covidDataService.getWorldwideRecovered());
        indexModel.setWorldwideIncidentRate(covidDataService.getWorldwideIncidentRate());
        indexModel.setWorldwideFatalityRatio(covidDataService.getWorldwideFatalityRatio());

        // Regions
        indexModel.setRegionWithMostDeaths(regionService.findWithMostDeaths());
        indexModel.setSubregionWithLeastDeaths(regionService.findWithLeastDeaths());
        indexModel.setRegionWithHighestConfirmedCases(regionService.findWithHighestConfirmedCases());
        indexModel.setRegionWithLowestConfirmedCases(regionService.findWithLowestConfirmedCases());
        indexModel.setRegionWithMostRecovered(regionService.findWithMostRecovered());
        indexModel.setRegionWithLeastRecovered(regionService.findWithLeastRecovered());
        indexModel.setRegionWithHighestIncidentRate(regionService.findWithHighestIncidentRate());
        indexModel.setRegionWithLowestIncidentRate(regionService.findWithLowestIncidentRate());
        indexModel.setRegionWithHighestFatalityRate(regionService.findWithHighestFatalityRate());
        indexModel.setRegionWithLowestFatalityRate(regionService.findWithLowestFatalityRate());

        // Subregions
        indexModel.setSubregionWithMostDeaths(subregionService.findWithMostDeaths());
        indexModel.setRegionWithLeastDeaths(subregionService.findWithLeastDeaths());
        indexModel.setSubregionWithHighestConfirmedCases(subregionService.findWithHighestConfirmedCases());
        indexModel.setSubregionWithLowestConfirmedCases(subregionService.findWithLowestConfirmedCases());
        indexModel.setSubregionWithMostRecovered(subregionService.findWithMostRecovered());
        indexModel.setSubregionWithLeastRecovered(subregionService.findWithLeastRecovered());
        indexModel.setSubregionWithHighestIncidentRate(subregionService.findWithHighestIncidentRate());
        indexModel.setSubregionWithLowestIncidentRate(subregionService.findWithLowestIncidentRate());
        indexModel.setSubregionWithHighestFatalityRate(subregionService.findWithHighestFatalityRate());
        indexModel.setSubregionWithLowestFatalityRate(subregionService.findWithLowestFatalityRate());

        System.out.println("indexModel.getSubregionWithLowestFatalityRate() = " + indexModel.getSubregionWithLowestFatalityRate());
        System.out.println("indexModel.getSubregionWithLowestFatalityRate() = " + indexModel.getRegionWithLowestFatalityRate());

        model.put("indexModel", indexModel);

        return "index";
    }

}
