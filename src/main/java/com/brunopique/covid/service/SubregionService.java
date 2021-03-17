package com.brunopique.covid.service;

import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.repository.SubregionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubregionService {

    @Autowired
    private SubregionRepository subregionRepo;

    public Optional<Subregion> findByName(String subregionName) {
        return subregionRepo.findByName(subregionName);
    }

    public Optional<Subregion> findByNameAndRegion_Name(String subregionName, String regionName) {
        return subregionRepo.findByNameAndRegion_Name(subregionName, regionName);
    }

    public List<Subregion> getAllSubregions() {
        return subregionRepo.findAll();
    }

    public Subregion save(Subregion subregion) {
        return subregionRepo.save(subregion);
    }

    public Map<String, String> findWithMostDeaths() {
        return subregionRepo.findWithMostDeaths().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLeastDeaths() {
        return subregionRepo.findWithLeastDeaths().orElse(new HashMap<>());
    }

    public Map<String, String> findWithHighestConfirmedCases() {
        return subregionRepo.findWithMostConfirmedCases().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLowestConfirmedCases() {
        return subregionRepo.findWithLeastConfirmedCases().orElse(new HashMap<>());
    }

    public Map<String, String> findWithMostRecovered() {
        return subregionRepo.findWithMostRecovered().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLeastRecovered() {
        return subregionRepo.findWithLeastRecovered().orElse(new HashMap<>());
    }

    public Map<String, String> findWithHighestIncidentRate() {
        return subregionRepo.findWithHighestIncidentRate().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLowestIncidentRate() {
        return subregionRepo.findWithLowestIncidentRate().orElse(new HashMap<>());
    }

    public Map<String, String> findWithHighestFatalityRate() {
        return subregionRepo.findWithHighestFatalityRate().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLowestFatalityRate() {
        return subregionRepo.findWithLowestFatalityRate().orElse(new HashMap<>());
    }

    public List<Subregion> getAllRegions() {
        return subregionRepo.findAll();
    }

    public Long findTotalDeathsByName(String subregionName) {
        return subregionRepo.findTotalDeathsByName(subregionName).orElse(0L);
    }

    public Long findTotalConfirmedByName(String subregionName) {
        return subregionRepo.findTotalConfirmedByName(subregionName).orElse(0L);
    }

    public Long findTotalRecoveredByName(String subregionName) {
        return subregionRepo.findTotalRecoveredByName(subregionName).orElse(0L);
    }

    public Double findTotalIncidentRateByName(String subregionName) {
        return subregionRepo.findTotalIncidentRateByName(subregionName).orElse(0.0D);
    }

    public Double findTotalFatalityRatioByName(String subregionName) {
        return subregionRepo.findTotalFatalityRatioByName(subregionName).orElse(0.0D);
    }
}
