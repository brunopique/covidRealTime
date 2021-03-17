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

    Optional<CovidData> findFirstByOrderByDeathsDesc();

    Optional<CovidData> findFirstByOrderByConfirmedDesc();

    Optional<CovidData> findFirstByOrderByActiveDesc();

    Optional<CovidData> findFirstByOrderByIncidentRateDesc();

    Optional<CovidData> findFirstByOrderByCaseFatalityRatioDesc();

    Optional<CovidData> findFirstByOrderByRecoveredDesc();

    Optional<List<CovidData>> findAllByDeathsIsGreaterThanEqualAndDateGreaterThanEqual(Long deaths, LocalDate date);
}
