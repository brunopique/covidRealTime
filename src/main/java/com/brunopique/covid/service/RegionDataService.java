package com.brunopique.covid.service;

import com.brunopique.covid.domain.RegionData;
import com.brunopique.covid.repository.RegionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionDataService {

    @Autowired
    private RegionDataRepository regionDataRepo;

    public boolean parseData() {
        return regionDataRepo.parseData();
    }

    public List<RegionData> getRegionData() {
        return regionDataRepo.getRegionData();
    }

    public List<RegionData> getRegion(String regionName) {
        return regionDataRepo.getRegion(regionName);
    }

    public Long getTotalDeaths() {
        return regionDataRepo.getTotalDeaths();
    }
}
