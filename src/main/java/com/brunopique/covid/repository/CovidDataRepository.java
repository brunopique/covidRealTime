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

    @Query(value = "SELECT SUM(cd.deaths) FROM covid_data cd " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY"
            , nativeQuery = true)
    Optional<Long> getWorldwideDeaths();

    @Query(value = "SELECT SUM(cd.confirmed) FROM covid_data cd " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY"
            , nativeQuery = true)
    Optional<Long> getWorldwideConfirmed();

    @Query(value = "SELECT SUM(cd.recovered) FROM covid_data cd " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY"
            , nativeQuery = true)
    Optional<Long> getWorldwideRecovered();

    @Query(value = "SELECT ROUND(SUM(cd.incident_rate) / 3979, 2) FROM covid_data cd " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY"
            , nativeQuery = true)
    Optional<Double> getWorldwideIncidentRate();

    @Query(value = "SELECT ROUND(SUM(cd.case_fatality_ratio) / 3979, 2) FROM covid_data cd " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY"
            , nativeQuery = true)
    Optional<Double> getWorldwideFatalityRatio();

    Optional<List<CovidData>> findAllByDate(LocalDate date);

    Optional<CovidData> findFirstBySubregion_NameOrderByDeathsDesc(String name);

    // TODO: can I extract this logic to Region and Subregion repo (to avoid eager fetching in covid data)

    @Query(value = "SELECT cd.id, cd.active, cd.case_fatality_ratio, cd.confirmed, cd.date, cd.deaths, cd.incident_rate, cd.recovered, cd.region_and_country, cd.recovered, cd.subregion_id from covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "ORDER BY cd.deaths DESC limit 1",
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
