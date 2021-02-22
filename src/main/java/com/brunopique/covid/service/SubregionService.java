package com.brunopique.covid.service;

import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.repository.SubregionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubregionService {

    @Autowired
    SubregionRepository subregionRepo;


    public Optional<Subregion> findByName(String subregionName) {
        return subregionRepo.findByName(subregionName);
    }

    public Subregion save(Subregion subregion) {
        return subregionRepo.save(subregion);
    }
}
