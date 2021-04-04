package com.brunopique.covid;

import com.brunopique.covid.config.CovidDataServiceConfig;
import com.brunopique.covid.service.CovidDataService;
import com.brunopique.covid.service.RegionService;
import com.brunopique.covid.service.SubregionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
class CovidRealTimeApplicationTests {

    @Autowired
    private RegionService regionService;
    @Autowired
    private CovidDataService covidDataService;
    @Autowired
    private SubregionService subregionService;
    @Autowired
    private CovidDataServiceConfig covidDataServiceConfig;

    private final static Logger logger = LoggerFactory.getLogger(CovidRealTimeApplicationTests.class);

    @Test
    void should_print_todays_date_in_csv_file_format() {
        System.out.println(DateTimeFormatter.ofPattern("MM-dd-yyyy")
                                            .format(LocalDate.now()));
        System.out.println("LocalDate.now() = " + LocalDate.now());
    }

    @Test
    void should_return_region_by_name() {
        System.out.println("regionService.findByName(\"Albania\"): " +  regionService.findByName("Albania"));
    }

    @Test
    void should_return_worldwide_number_of_deaths() {
        Long worldwideDeaths = covidDataService.getWorldwideDeaths();
        System.out.println("worldwideDeaths = " + worldwideDeaths);
    }

    @Test
    void should_return_worldwide_number_of_confirmed() {
        Long worldwideConfirmed = covidDataService.getWorldwideConfirmed();
        System.out.println("worldwideConfirmed = " + worldwideConfirmed);
    }

    @Test
    void should_return_worldwide_number_of_recovered() {
        Long worldwideRecovered = covidDataService.getWorldwideRecovered();
        System.out.println("worldwideRecovered = " + worldwideRecovered);
    }

    @Test
    void should_return_worldwide_incedent_rate() {
        Double worldwideIncidentRate = covidDataService.getWorldwideIncidentRate();
        System.out.println("worldwideIncidentRate = " + worldwideIncidentRate);
    }

    @Test
    void should_return_worldwide_fatality_ratio() {
        Double worldwideFatalityRatio = covidDataService.getWorldwideFatalityRatio();
        System.out.println("worldwideFatalityRatio = " + worldwideFatalityRatio);
    }

    @Test
    void should_return_subregion_by_name_and_by_region_name() {
        System.out.println("Region from subregion search 'California' and 'US': " + subregionService.findByNameAndRegion_Name("California", "Us").get());
    }

    @Test
    void should_return_covid_data_from_region_search() {
        Map<String, String> region = regionService.findWithMostDeaths();
        System.out.println("region: " + region);
    }
    
    @Test
    void should_return_region_with_highest_incident_rate() {
        System.out.println("regionRepository.findWithHighestIncidentRate() = " + regionService.findWithHighestIncidentRate());
    }

    @Test
    void should_return_region_with_lowest_incident_rate() {
        System.out.println("regionRepository.findWithLowestIncidentRate() = " + regionService.findWithLowestIncidentRate());
    }
    
    @Test
    void should_return_total_deaths_by_region_name() {
        System.out.println("regionRepository.findTotalDeathsByName(\"Spain\") = " + regionService.findTotalDeathsByName("Spain"));
    }
    
    @Test
    void should_return_covid_data_object_with_more_deaths() {
        System.out.println("covidDataService.findFirstByOrderByDeathsDesc() = " + covidDataService.findFirstByOrderByDeathsDesc());
    }

    @Test
    void should_return_covid_data_object_with_more_confirmed_cases() {
        System.out.println("covidDataService.findFirstByOrderByConfirmedDesc() = " + covidDataService.findFirstByOrderByConfirmedDesc());
    }

    @Test
    void should_return_covid_data_object_with_more_active_cases() {
        System.out.println("covidDataService.findFirstByOrderByActiveDesc() = " + covidDataService.findFirstByOrderByActiveDesc());
    }

    @Test
    void should_return_covid_data_object_with_highest_incident_rate() {
        System.out.println("covidDataService.findFirstByOrderByIncidentRateDesc() = " + covidDataService.findFirstByOrderByIncidentRateDesc());
    }

    @Test
    void should_return_covid_data_object_with_highest_fatality_rate() {
        System.out.println("covidDataService.findFirstByOrderByCaseFatalityRatioDesc() = " + covidDataService.findFirstByOrderByCaseFatalityRatioDesc());
    }

    @Test
    void should_return_covid_data_object_by_number_of_deaths() {
        System.out.println("covidDataService.covidDataService.findAllByDeathsIsGreaterThanEqual(100000L) = " + covidDataService.findAllByDeathsIsGreaterThanEqualAndDateGreaterThanEqual(50000L));
    }

    @Test
    void should_download_and_parse_todays_data() {
        Assert.assertTrue(covidDataService.parseData());
    }

    @Test
    void should_return_logger_output() {
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
    }

    @Test
    void should_log_parsing_data_result() {
        covidDataServiceConfig.parseData();
    }

}
