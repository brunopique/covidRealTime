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

    CovidData findFirstByDate(LocalDate date);

//    @Query("SELECT MAX(cd.deaths) FROM CovidData cd")
//    Long getDayWithMostDeaths();

    @Query("SELECT cd.id, cd.active, cd.caseFatalityRatio, cd.confirmed, cd.date," +
            " MAX(cd.deaths) as deaths," +
            " cd.incidentRate, cd.recovered, cd.subregion.id " +
            "FROM CovidData cd")
    CovidData getSubregionWithMostDeaths();

    CovidData findByDeaths(long deaths);

    CovidData findFirstBySubregion_NameOrderByDeathsDesc(String name);

    CovidData findFirstByOrderByDeathsDesc();

    List<CovidData> findAllBySubregion_Region_NameOrderByDeathsDesc(String name);


    @Query(value = "SELECT SUM(cd.deaths) FROM covid_data cd " +
            "LEFT JOIN subregion s ON cd.subregion_id = s.id " +
            "LEFT JOIN region r ON s.region_id = r.id " +
            "WHERE cd.date = CURDATE() - INTERVAL 1 DAY AND r.name = :regionName",
    nativeQuery = true)
    Optional<Long> findTotalDeathsByRegion(String regionName);

    // TODO: QUERY find X (var) number of top by death etc.

}
