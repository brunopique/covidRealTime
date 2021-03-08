package com.brunopique.covid;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.repository.CovidDataRepository;
import com.brunopique.covid.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
class CovidRealTimeApplicationTests {

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CovidDataRepository covidDataRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void should_return_all_regions() {
        // assertEquals()

    }

    @Test
    void should_print_todays_date_in_csv_file_format() {
        System.out.println(DateTimeFormatter.ofPattern("MM-dd-yyyy")
                                            .format(LocalDate.now()));
    }

    @Test
    void should_return_covid_daily() {
        System.out.println(" dailyCovidDataRepository.findByName(\"Albania\"): " +  regionRepository.findByName("Albania"));
    }

    @Test
    void should_lazily_fetch_subregion() {
        Subregion subregion = covidDataRepository.findFirstByOrderByDeathsDescSubregion_NameDesc().get().getSubregion();
        System.out.println("subregion = " + subregion);
    }

    @Test
    void should_lazily_fetch_subregion2() {
        CovidData cd = covidDataRepository.findFirstByOrderByDeathsDesc().get();
        System.out.println("cd = " + cd);
    }

    @Test
    void should_get_total_deaths_per_region() {
       Long deaths = covidDataRepository.findTotalDeathsByRegion("australia").orElse(0L);
        System.out.println("deaths = " + deaths);
    }

}
