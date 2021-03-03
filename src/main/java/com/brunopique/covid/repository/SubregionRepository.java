package com.brunopique.covid.repository;

import com.brunopique.covid.domain.Region;
import com.brunopique.covid.domain.Subregion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubregionRepository extends JpaRepository<Subregion, Long> {


    Optional<Subregion> findByName(String subregionName);

    //Optional<List<Subregion>> findByRegion_Name(String regionName);

}
