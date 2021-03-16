package com.brunopique.covid.web;

import com.brunopique.covid.service.CovidDataService;
import com.brunopique.covid.service.RegionService;
import com.brunopique.covid.service.SubregionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

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

        // TODO: turn data below into pojo
        // Worldwide
        model.put("worldwideDeaths", covidDataService.getWorldwideDeaths());
        model.put("worldwideConfirmed", covidDataService.getWorldwideConfirmed());
        model.put("worldwideRecovered", covidDataService.getWorldwideRecovered());
        model.put("worldwideIncidentRate", covidDataService.getWorldwideIncidentRate());
        model.put("worldwideFatalityRatio", covidDataService.getWorldwideFatalityRatio());

        Map<String, String> map =  regionService.findWithMostDeaths();

        // Regions
        model.put("regionHighestMortalityName", map.get("name"));
        model.put("regionHighestMortalityNumber", map.get("deaths"));
        map = regionService.findWithLeastDeaths();
        model.put("regionLowestMortalityName", map.get("name"));
        model.put("regionLowestMortalityNumber", map.get("deaths"));
        map = regionService.findWithHighestConfirmedCases();
        model.put("regionHighestConfirmedName", map.get("name"));
        model.put("regionHighestConfirmedNumber", map.get("confirmed"));
        map = regionService.findWithLowestConfirmedCases();
        model.put("regionLowestConfirmedName", map.get("name"));
        model.put("regionLowestConfirmedNumber", map.get("confirmed"));
        map = regionService.findWithMostRecovered();
        model.put("regionHighestRecoveredName", map.get("name"));
        model.put("regionHighestRecoveredNumber", map.get("recovered"));
        map = regionService.findWithLeastRecovered();
        model.put("regionLowestConfirmedName", map.get("name"));
        model.put("regionLowestConfirmedNumber", map.get("recovered"));
        map = regionService.findWithHighestIncidentRate();
        model.put("regionHighestIncidentName", map.get("name"));
        model.put("regionHighestIncidentNumber", map.get("incident_rate"));
        map = regionService.findWithLowestIncidentRate();
        model.put("regionLowestIncidentName", map.get("name"));
        model.put("regionLowestIncidentNumber", map.get("incident_rate"));
        map = regionService.findWithHighestFatalityRate();
        model.put("regionHighestFatalityName", map.get("name"));
        model.put("regionHighestFatalityNumber", map.get("case_fatality_ratio"));
        System.out.println("map.get(\"case_fatality_ratio\") = " + map.get("case_fatality_ratio"));
        map = regionService.findWithLowestFatalityRate();
        model.put("regionLowestFatalityName", map.get("name"));
        model.put("regionLowestFatalityNumber", map.get("case_fatality_ratio"));

        // Subregions
        map = subregionService.findWithMostDeaths();
        model.put("subregionMostDeathsName", map.get("name"));
        model.put("subregionMostDeathsNumber", map.get("deaths"));
        map = subregionService.findWithLeastDeaths();
        model.put("subregionLeastDeathsName", map.get("name"));
        model.put("subregionLeastDeathsNumber", map.get("deaths"));
        map = subregionService.findWithHighestConfirmedCases();
        model.put("subregionHighestConfirmedName", map.get("name"));
        model.put("subregionHighestConfirmedNumber", map.get("confirmed"));
        map = subregionService.findWithLowestConfirmedCases();
        model.put("subregionLowestConfirmedName", map.get("name"));
        model.put("subregionLowestConfirmedNumber", map.get("confirmed"));
        map = subregionService.findWithMostRecovered();
        model.put("subregionHighestRecoveredName", map.get("name"));
        model.put("subregionHighestRecoveredNumber", map.get("recovered"));
        map = subregionService.findWithLeastRecovered();
        model.put("subregionLowestRecoveredName", map.get("name"));
        model.put("subregionLowestRecoveredNumber", map.get("recovered"));
        map = subregionService.findWithHighestIncidentRate();
        model.put("subregionHighestIncidentName", map.get("name"));
        model.put("subregionHighestIncidentNumber", map.get("incident_rate"));
        map = subregionService.findWithLowestIncidentRate();
        model.put("subregionLowestIncidentName", map.get("name"));
        model.put("subregionLowestIncidentNumber", map.get("incident_rate"));
        map = subregionService.findWithHighestFatalityRate();
        model.put("subregionHighestFatalityName", map.get("name"));
        model.put("subregionHighestFatalityNumber", map.get("case_fatality_ratio"));
        System.out.println("map.get(\"case_fatality_ratio\") = " + map.get("case_fatality_ratio"));
        map = subregionService.findWithLowestFatalityRate();
        model.put("subregionLowestFatalityName", map.get("name"));
        model.put("subregionLowestFatalityNumber", map.get("case_fatality_ratio"));

        return "index";
    }

}
