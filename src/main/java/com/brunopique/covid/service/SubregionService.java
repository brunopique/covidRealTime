package com.brunopique.covid.service;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.domain.Region;
import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.repository.SubregionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubregionService {

    @Autowired
    private SubregionRepository subregionRepo;
    @Autowired
    private CovidDataService covidDataService;


    public Optional<Subregion> findByName(String subregionName) {
        return subregionRepo.findByName(subregionName);
    }

    public List<Subregion> getAllSubregions() {
        return subregionRepo.findAll();
    }

    public Subregion save(Subregion subregion) {
        return subregionRepo.save(subregion);
    }

    public Subregion getSubregionWithMostDeaths() {
        CovidData deadlyDay =  covidDataService.getDayWithMostDeaths();
        // The line below forces me to implement Eager fetching on CoviData -> Subregion
        // And it also returns all of the Subregions' dailyCovidData: DOESN'T WORK
        return deadlyDay.getSubregion();
    }
}
