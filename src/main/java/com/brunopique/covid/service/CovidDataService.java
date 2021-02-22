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
    private String todaysFileName;

    // If the server were constantly running, this method would not be called on
    // 'ApplicationReadyEvent' but on a scheduled time (using '@EnableScheduling')
    public boolean parseData() {

        // TODO: check whether to keep day minus 1
        // If date already exists in repo return false and don't parse
        if (covidDataRepo.findFirstByDate(LocalDate.now().minusDays(1)) != null) {
            return false;
        }

        Region region;
        Subregion subregion;

        updateTodaysFileName();

        CompletableFuture<Boolean> fileDownload = CompletableFuture.supplyAsync(() -> downloadTodaysFile(todaysFileName));
        while (!fileDownload.isDone()) ;
        //System.out.println("Downloading file...");

        Reader in;
        Iterable<CSVRecord> records = null;
        try {
            in = new FileReader("./covid_19_daily_reports/" + todaysFileName);
            records = CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {
                csvRecord = record;

                // TODO: space-complexity, check if I can avoid further checks by saving info here (eg. regions)

                // Tried using optionals but wouldn't work with empty fields
                String regionName = record.get("Country_Region");
                String subregionName = record.get("Province_State").isEmpty() ? regionName : record.get("Province_State");
                long confirmed = Long.parseLong(getRecordValueOrElseZero("Confirmed"));
                long deaths = Long.parseLong(getRecordValueOrElseZero("Deaths"));
                long recovered = Long.parseLong(getRecordValueOrElseZero("Recovered"));
                long active = Long.parseLong(getRecordValueOrElseZero("Active"));
                double incidentRate = Double.parseDouble(getRecordValueOrElseZero("Incident_Rate"));
                double caseFatalityRatio = Double.parseDouble(getRecordValueOrElseZero("Case_Fatality_Ratio"));

                CovidData currentRecord = new CovidData(LocalDate.now().minusDays(1), confirmed, deaths, recovered, active, incidentRate, caseFatalityRatio);

                // If region exists get it from repo, otherwise create it and save it
                region = regionService.findByName(regionName).orElseGet(() -> {
                    Region newRegion = new Region(regionName);
                    regionService.save(newRegion);
                    return newRegion;
                });

                // If subregion exists get it from repo, otherwise create it and save it
                subregion = subregionService.findByName(subregionName).orElseGet(() -> {
                    Subregion newSubregion = new Subregion(subregionName);
                    subregionService.save(newSubregion);
                    return newSubregion;
                });

                subregion.setRegion(region);

                subregionService.save(subregion);


                currentRecord.setSubregion(subregion);

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
                    new File("./covid_19_daily_reports/" + fileName));
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