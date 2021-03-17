package com.brunopique.covid.repository;

import com.brunopique.covid.domain.Subregion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface SubregionRepository extends JpaRepository<Subregion, Long> {

    Optional<Subregion> findByName(String subregionName);

    Optional<Subregion> findByNameAndRegion_Name(String subregionName, String regionName);

    Optional<List<Subregion>> findByRegion_Name(String subregionName);

    @Query("select sr from Subregion sr " +
            "join fetch sr.dailyCovidData as cd " +
            "where cd.id = 1")
    Optional<Subregion> findByCovidDataId();

    @Query(value = "SELECT sr.name, SUM(cd.deaths) as deaths FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY sr.id " +
            "ORDER BY deaths DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithMostDeaths();

    @Query(value = "SELECT sr.name, SUM(cd.deaths) as deaths FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND deaths > 1 " +
            "GROUP BY sr.id " +
            "ORDER BY deaths LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLeastDeaths();

    @Query(value = "SELECT sr.name, SUM(cd.confirmed) as confirmed FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY sr.id " +
            "ORDER BY confirmed DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithMostConfirmedCases();

    @Query(value = "SELECT sr.name, SUM(cd.confirmed) as confirmed FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND confirmed > 1 " +
            "GROUP BY sr.id " +
            "ORDER BY confirmed LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLeastConfirmedCases();

    @Query(value = "SELECT sr.name, SUM(cd.recovered) as recovered FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY sr.id " +
            "ORDER BY recovered DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithMostRecovered();

    @Query(value = "SELECT sr.name, SUM(cd.recovered) as recovered FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND recovered > 1 " +
            "GROUP BY sr.id " +
            "ORDER BY recovered LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLeastRecovered();

    @Query(value = "SELECT sr.name, ROUND(SUM(cd.incident_rate) / COUNT(cd.id), 2) as incident_rate FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY " +
            "GROUP BY sr.id " +
            "ORDER BY incident_rate DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithHighestIncidentRate();

    @Query(value = "SELECT sr.name, ROUND(SUM(cd.incident_rate) / COUNT(cd.id), 2) as incident_rate FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND incident_rate > 1 " +
            "GROUP BY sr.id " +
            "ORDER BY incident_rate LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLowestIncidentRate();

    @Query(value = "SELECT sr.name, ROUND(SUM(cd.case_fatality_ratio) / COUNT(cd.id), 2) as case_fatality_ratio FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND case_fatality_ratio < 100  " +
            "GROUP BY sr.id " +
            "ORDER BY case_fatality_ratio DESC LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithHighestFatalityRate();

    @Query(value = "SELECT sr.name, ROUND(SUM(cd.case_fatality_ratio) / COUNT(cd.id), 2) as case_fatality_ratio FROM subregion sr " +
            "JOIN covid_data cd on sr.id = cd.subregion_id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND case_fatality_ratio > 1 " +
            "GROUP BY sr.id " +
            "ORDER BY case_fatality_ratio LIMIT 1",
            nativeQuery = true)
    Optional<Map<String, String>> findWithLowestFatalityRate();

    @Query(value = "SELECT SUM(cd.deaths) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND s.name = :subregionName",
            nativeQuery = true)
    Optional<Long> findTotalDeathsByName(String subregionName);

    @Query(value = "SELECT SUM(cd.confirmed) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND s.name = :subregionName",
            nativeQuery = true)
    Optional<Long> findTotalConfirmedByName(String subregionName);

    @Query(value = "SELECT SUM(cd.recovered) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND s.name = :subregionName",
            nativeQuery = true)
    Optional<Long> findTotalRecoveredByName(String subregionName);

    @Query(value = "SELECT ROUND(SUM(cd.incident_rate) / COUNT(cd.id), 2) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND s.name = :subregionName",
            nativeQuery = true)
    Optional<Double> findTotalIncidentRateByName(String subregionName);

    @Query(value = "SELECT ROUND(SUM(cd.case_fatality_ratio) / COUNT(cd.id), 2) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND s.name = :subregionName",
            nativeQuery = true)
    Optional<Double> findTotalFatalityRatioByName(String subregionName);

}
