package com.brunopique.covid.service;

import com.brunopique.covid.domain.Region;
import com.brunopique.covid.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegion(String regionName) {
        return regionRepository.findDistinctByName(regionName);
    }

    public Long getTotalDeaths() {
        return 1L;
    }
}
