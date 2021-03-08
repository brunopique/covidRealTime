package com.brunopique.covid.repository;

import com.brunopique.covid.domain.CovidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CovidDataRepository extends JpaRepository<CovidData, String> {

    Optional<CovidData> findFirstByDate(LocalDate date);

    Optional<List<CovidData>> findAllByDate(LocalDate date);

    Optional<CovidData> findFirstBySubregion_NameOrderByDeathsDesc(String name);

    @Query(value = "select cd.id, cd.active, cd.case_fatality_ratio, cd.confirmed, cd.date, cd.deaths, cd.incident_rate, cd.recovered, cd.region_and_country, cd.recovered, cd.subregion_id from covid_data cd " +
            "left join subregion s on cd.subregion_id = s.id " +
            "order by cd.deaths desc limit 1", // TODO: should i filter by cd.date here?
            nativeQuery = true)
    Optional<CovidData> findFirstByOrderByDeathsDesc();

    Optional<CovidData> findFirstByOrderByDeathsDescSubregion_NameDesc();

    Optional<CovidData> findAllByOrderByDeathsDesc();

    @Query(value = "SELECT SUM(cd.deaths) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "LEFT JOIN region r ON s.region_id = r.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND r.name = :regionName",
            nativeQuery = true)
    Optional<Long> findTotalDeathsByRegion(String regionName);
}
