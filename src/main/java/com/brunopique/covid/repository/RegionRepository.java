package com.brunopique.covid.repository;

import com.brunopique.covid.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByName(String regionName);

    @Query(value = "SELECT r FROM Region as r " +
            "JOIN FETCH r.subregions as sr " +
            "JOIN FETCH sr.dailyCovidData as cd " +
            "GROUP BY r.id " +
            "order by SUM(cd.deaths) desc")
    List<String> findByHighestMortalityTest();

    @Query(value = "SELECT r.name, SUM(cd.deaths) as deaths FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY r.id " +
            "ORDER BY deaths DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithMostDeaths();

    @Query(value = "SELECT r.name, SUM(cd.deaths) as deaths FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND deaths > 1 " +
            "GROUP BY r.id " +
            "ORDER BY deaths LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLeastDeaths();

    @Query(value = "SELECT r.name, SUM(cd.confirmed) as confirmed FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY r.id " +
            "ORDER BY confirmed DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithMostConfirmedCases();

    @Query(value = "SELECT r.name, SUM(cd.confirmed) as confirmed FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND confirmed > 1 " +
            "GROUP BY r.id " +
            "ORDER BY confirmed LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLeastConfirmedCases();

    @Query(value = "SELECT r.name, SUM(cd.recovered) as recovered FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY r.id " +
            "ORDER BY recovered DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithMostRecovered();

    @Query(value = "SELECT r.name, SUM(cd.recovered) as recovered FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND recovered > 1 " +
            "GROUP BY r.id " +
            "ORDER BY recovered LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLeastRecovered();

    @Query(value = "SELECT r.name, ROUND(SUM(cd.incident_rate) / COUNT(cd.id), 2) as incident_rate FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY r.id " +
            "ORDER BY incident_rate DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithHighestIncidentRate();

    @Query(value = "SELECT r.name, ROUND(SUM(cd.incident_rate) / COUNT(cd.id), 2) as incident_rate FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND incident_rate > 1 " +
            "GROUP BY r.id " +
            "ORDER BY incident_rate LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLowestIncidentRate();

    @Query(value = "SELECT r.name, ROUND(SUM(cd.case_fatality_ratio) / COUNT(cd.id), 2) as case_fatality_ratio FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY r.id " +
            "ORDER BY case_fatality_ratio DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithHighestFatalityRate();

    @Query(value = "SELECT r.name, ROUND(SUM(cd.case_fatality_ratio) / COUNT(cd.id), 2) as case_fatality_ratio FROM region r " +
            "JOIN subregion sr ON r.id = sr.region_id " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND case_fatality_ratio > 1 " +
            "GROUP BY r.id " +
            "ORDER BY case_fatality_ratio LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLowestFatalityRate();

}
