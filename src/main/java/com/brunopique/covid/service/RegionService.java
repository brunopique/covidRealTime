package com.brunopique.covid.service;

import com.brunopique.covid.domain.Region;
import com.brunopique.covid.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> findByName(String regionName) {
        return regionRepository.findByName(regionName);
    }

    public Long findTotalDeathsByName(String regionName) {
        return regionRepository.findTotalDeathsByName(regionName).orElse(0L);
    }

    public Long findTotalConfirmedByName(String regionName) {
        return regionRepository.findTotalConfirmedByName(regionName).orElse(0L);
    }

    public Long findTotalRecoveredByName(String regionName) {
        return regionRepository.findTotalRecoveredByName(regionName).orElse(0L);
    }

    public Double findTotalIncidentRateByName(String regionName) {
        return regionRepository.findTotalIncidentRateByName(regionName).orElse(0.0D);
    }

    public Double findTotalFatalityRatioByName(String regionName) {
        return regionRepository.findTotalFatalityRatioByName(regionName).orElse(0.0D);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public Long findTotalDeathsByRegion(String regionName) {
        return regionRepository.findTotalDeathsByName(regionName).orElse(0L);
    }

    public Map<String, String> findWithMostDeaths() {
        return regionRepository.findWithMostDeaths().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLeastDeaths() {
        return regionRepository.findWithLeastDeaths().orElse(new HashMap<>());
    }

    public Map<String, String> findWithHighestConfirmedCases() {
        return regionRepository.findWithMostConfirmedCases().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLowestConfirmedCases() {
        return regionRepository.findWithLeastConfirmedCases().orElse(new HashMap<>());
    }

    public Map<String, String> findWithMostRecovered() {
        return regionRepository.findWithMostRecovered().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLeastRecovered() {
        return regionRepository.findWithLeastRecovered().orElse(new HashMap<>());
    }

    public Map<String, String> findWithHighestIncidentRate() {
        return regionRepository.findWithHighestIncidentRate().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLowestIncidentRate() {
        return regionRepository.findWithLowestIncidentRate().orElse(new HashMap<>());
    }

    public Map<String, String> findWithHighestFatalityRate() {
        return regionRepository.findWithHighestFatalityRate().orElse(new HashMap<>());
    }

    public Map<String, String> findWithLowestFatalityRate() {
        return regionRepository.findWithLowestFatalityRate().orElse(new HashMap<>());
    }
}
