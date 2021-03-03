package com.brunopique.covid;

import com.brunopique.covid.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class CovidRealTimeApplicationTests {

    @Autowired
    private RegionRepository regionRepository;

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

}
