package com.brunopique.covid.service;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.domain.Region;
import com.brunopique.covid.domain.Subregion;
import com.brunopique.covid.repository.CovidDataRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
public class CovidDataService {

    @Autowired
    private CovidDataRepository covidDataRepo;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SubregionService subregionService;

    private CSVRecord csvRecord;
    private String yesterdaysFileName;

    // If the server were constantly running, this method would not be called on
    // 'ApplicationReadyEvent' but on a scheduled time (using '@EnableScheduling')
    public boolean parseData() {

        // If date already exists in repo return false and don't parse
        if (covidDataRepo.findFirstByDate(LocalDate.now().minusDays(1)).isPresent())
            return false;

        Region region;
        Subregion subregion;

        updateYesterdaysFileName();

        CompletableFuture<Boolean> fileDownload = CompletableFuture.supplyAsync(() -> downloadYesterdaysFile(yesterdaysFileName));
        while (!fileDownload.isDone())
            System.out.print("Downloading file... ");

        try (Reader in = new FileReader("./covid_19_daily_reports/" + yesterdaysFileName)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
            for (CSVRecord record : records) {
                csvRecord = record;
                String regionName = record.get("Country_Region");
                String subregionName = record.get("Province_State").isEmpty() ? regionName : record.get("Province_State");
                String combinedNames = record.get("Combined_Key").isEmpty() ? regionName : record.get("Combined_Key");
                long confirmed = Long.parseLong(getRecordValueOrElseZero("Confirmed"));
                long deaths = Long.parseLong(getRecordValueOrElseZero("Deaths"));
                double recovered = Double.parseDouble(getRecordValueOrElseZero("Recovered"));
                long active = Long.parseLong(getRecordValueOrElseZero("Active"));
                double incidentRate = Double.parseDouble(getRecordValueOrElseZero("Incident_Rate"));
                double caseFatalityRatio = Double.parseDouble(getRecordValueOrElseZero("Case_Fatality_Ratio"));
                // Instantiate current record
                CovidData currentRecord = new CovidData(LocalDate.now().minusDays(1), combinedNames, confirmed, deaths, recovered, active, incidentRate, caseFatalityRatio);
                // If region exists get it from repo, otherwise create it and save it
                region = regionService.findByName(regionName).orElseGet(() -> {
                    Region newRegion = new Region(regionName);
                    regionService.save(newRegion);
                    return newRegion;
                });
                // If subregion exists get it from repo, otherwise create it and save it
                subregion = subregionService.findByName(subregionName).orElseGet(() -> new Subregion(subregionName));
                // Add Region to Subregion
                subregion.setRegion(region);
                // Save subregion to repo
                subregionService.save(subregion);
                // Add subregion to current covid data record
                currentRecord.setSubregion(subregion);
                // Save current record to covid data repo
                covidDataRepo.save(currentRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void updateYesterdaysFileName() {
        yesterdaysFileName = DateTimeFormatter.ofPattern("MM-dd-yyyy").format(LocalDate.now().minusDays(1)) + ".csv";
    }

    private Boolean downloadYesterdaysFile(String fileName) {
        try {
            FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/" + fileName),
                    new File("./covid_19_daily_reports/" + fileName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getRecordValueOrElseZero(String header) {
        String record = csvRecord.get(header);
        return record.isEmpty() ? "0" : record;
    }

    public CovidData getDayWithMostDeathsForSubregion(String subregion) {
        return covidDataRepo.findFirstBySubregion_NameOrderByDeathsDesc(subregion).orElse(new CovidData());
    }

    public CovidData findFirstByOrderByDeathsDesc() {
        return covidDataRepo.findFirstByOrderByDeathsDesc().orElse(new CovidData());
    }

    public Long findTotalDeathsByRegion(String regionName) {
        return covidDataRepo.findTotalDeathsByRegion(regionName).orElse(0L);
    }
}