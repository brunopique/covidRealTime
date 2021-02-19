package com.brunopique.covid.repository;

import com.brunopique.covid.domain.CovidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CovidDataRepository extends JpaRepository<CovidData, String> {

    CovidData findFirstByDate(LocalDate date);

}
