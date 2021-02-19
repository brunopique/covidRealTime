package com.brunopique.covid.repository;

import com.brunopique.covid.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    // List<Region> findAllByCountry_Name(String regionName);

    Optional<Region> findDistinctByName(String regionName);

}
