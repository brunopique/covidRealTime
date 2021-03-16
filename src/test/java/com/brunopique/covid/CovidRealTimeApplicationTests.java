package com.brunopique.covid;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.domain.Region;
import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.repository.CovidDataRepository;
import com.brunopique.covid.repository.RegionRepository;
import com.brunopique.covid.repository.SubregionRepository;
import com.brunopique.covid.service.SubregionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
class CovidRealTimeApplicationTests {

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CovidDataRepository covidDataRepository;
    @Autowired
    private SubregionService subregionService;
    @Autowired
    private SubregionRepository subregionRepository;

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
    void should_lazily_fetch_subregion2() {
        CovidData cd = covidDataRepository.findFirstByOrderByDeathsDesc().get();
        System.out.println("cd = " + cd);
    }

    @Test
    void should_get_total_deaths_per_region() {
       Long regionDeaths = covidDataRepository.findTotalDeathsByRegion("australia").orElse(0L);
        System.out.println("deaths = " + regionDeaths);
    }

    @Test
    void should_return_worldwide_number_of_deaths() {
        Long worldwideDeaths = covidDataRepository.getWorldwideDeaths().orElse(0L);
        System.out.println("worldwideDeaths = " + worldwideDeaths);
    }

    @Test
    void should_return_worldwide_number_of_confirmed() {
        Long worldwideConfirmed = covidDataRepository.getWorldwideConfirmed().orElse(0L);
        System.out.println("worldwideConfirmed = " + worldwideConfirmed);
    }

    @Test
    void should_return_worldwide_number_of_recovered() {
        Long worldwideRecovered = covidDataRepository.getWorldwideRecovered().orElse(0L);
        System.out.println("worldwideRecovered = " + worldwideRecovered);
    }

    @Test
    void should_return_worldwide_incedent_rate() {
        Double worldwideIncidentRate = covidDataRepository.getWorldwideIncidentRate().orElse(0.0);
        System.out.println("worldwideIncidentRate = " + worldwideIncidentRate);
    }

    @Test
    void should_return_worldwide_fatality_ratio() {
        Double worldwideFatalityRatio = covidDataRepository.getWorldwideFatalityRatio().orElse(0.0);
        System.out.println("worldwideFatalityRatio = " + worldwideFatalityRatio);
    }

    @Test
    void should_return_subregion_by_name_and_by_region_name() {
        System.out.println("Region from subregion search 'California' and 'US': " + subregionService.findByNameAndRegion_Name("California", "Us").get());
    }

    @Test
    void should_return_subregion_with_covid_data() {
        Subregion subregion = subregionRepository.findByCovidDataId().orElse(new Subregion());
        System.out.println("subregion = " + subregion);
        System.out.println("subregion.getDailyCovidData() = " + subregion.getDailyCovidData());
    }

    @Test
    void should_return_covid_data_from_region_search() {
        Map<String, String> region = regionRepository.findWithMostDeaths().get();
        System.out.println("region: " + region);
//        System.out.println("subregions: " + region.getSubregions());
//        region.getSubregions().forEach(sr -> System.out.println("sr.getDailyCovidData() = " + sr.getDailyCovidData()));
    }
    
    @Test
    void should_return_region_with_highest_incident_rate() {
        System.out.println("regionRepository.findWithHighestIncidentRate() = " + regionRepository.findWithHighestIncidentRate());
    }

    @Test
    void should_return_region_with_lowest_incident_rate() {
        System.out.println("regionRepository.findWithLowestIncidentRate() = " + regionRepository.findWithLowestIncidentRate());
    }

}
