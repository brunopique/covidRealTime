package com.brunopique.covid.web;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.service.CovidDataService;
import com.brunopique.covid.service.RegionService;
import com.brunopique.covid.service.SubregionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        CovidData cd = covidDataService.findFirstByOrderByDeathsDesc();
        model.put("subregionName", cd.getSubregion().getName());
        model.put("subregionDeaths", cd.getDeaths());
        return "index";
    }

}
