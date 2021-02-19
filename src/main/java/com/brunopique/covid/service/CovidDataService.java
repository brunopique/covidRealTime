package com.brunopique.covid.service;

import com.brunopique.covid.domain.CovidData;
import com.brunopique.covid.domain.Region;
import com.brunopique.covid.repository.CovidDataRepository;
import com.brunopique.covid.repository.RegionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class CovidDataService {

    @Autowired
    private CovidDataRepository covidDataRepo;
    @Autowired
    private RegionRepository regionRepo;

    private CSVRecord csvRecord;
    private String todaysFileName;

    // If the server were constantly running, this method would not be called on
    // 'ApplicationReadyEvent' but on a scheduled time (using '@EnableScheduling')
    public boolean parseData() {

        // TODO: check whether to keep day minus 1
        // If date already exists in repo return false and don't parse
        if (covidDataRepo.findFirstByDate(LocalDate.now().minusDays(1)) != null) {
            return false;
        }

        Region region = new Region();

        updateTodaysFileName();

        CompletableFuture<Boolean> fileDownload = CompletableFuture.supplyAsync(() -> downloadTodaysFile(todaysFileName));
        while (!fileDownload.isDone()) ;
        //System.out.println("Downloading file...");

        Reader in;
        Iterable<CSVRecord> records = null;
        try {
            in = new FileReader(todaysFileName);
            records = CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {
                csvRecord = record;

                // TODO: space-complexity, check if I can avoid further checks by saving info here (eg. regions)

                // Tried using optionals but wouldn't work with empty fields
                String regionName = record.get("Country_Region");
                String combinedKey = record.get("Combined_Key");
                String regionProvinceState = record.get("Province_State");
                long confirmed = Long.parseLong(getRecordValueOrElseZero("Confirmed"));
                long deaths = Long.parseLong(getRecordValueOrElseZero("Deaths"));
                long recovered = Long.parseLong(getRecordValueOrElseZero("Recovered"));
                long active = Long.parseLong(getRecordValueOrElseZero("Active"));
                double incidentRate = Double.parseDouble(getRecordValueOrElseZero("Incident_Rate"));
                double caseFatalityRatio = Double.parseDouble(getRecordValueOrElseZero("Case_Fatality_Ratio"));

                CovidData currentRecord = new CovidData(LocalDate.now().minusDays(1), combinedKey, confirmed, deaths, recovered, active, incidentRate, caseFatalityRatio);

                // If region exists get it from repo, otherwise create it and save it
                region = regionRepo.findDistinctByName(regionName).orElseGet(() -> {
                    Region newRegion = new Region(regionName);
                    regionRepo.save(newRegion);
                    return newRegion;
                });

                // Here we save region to covid data
                currentRecord.setRegion(region);
                covidDataRepo.save(currentRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void updateTodaysFileName() {
        // TODO: set right amount of minusDays
        todaysFileName = DateTimeFormatter.ofPattern("MM-dd-yyyy").format(LocalDate.now().minusDays(1)) + ".csv";
    }

    private Boolean downloadTodaysFile(String fileName) {
        try {
            FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/" + fileName),
                    new File(fileName));
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Today's file hasn't been uploaded yet!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // TODO: IMPLEMENT LOGIC TO CHECK PARSING
//    private boolean fileParsedCorrectly() {
//        System.out.println("covidDataRepo.count() > 0" + covidDataRepo.count());
//        return covidDataRepo.count() > 0;
//    }

    private String getRecordValueOrElseZero(String header) {
        String record = csvRecord.get(header);
        return record.isEmpty() ? "0" : record;
    }
}