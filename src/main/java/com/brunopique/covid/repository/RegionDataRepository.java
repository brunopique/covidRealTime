package com.brunopique.covid.repository;

import com.brunopique.covid.domain.RegionData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RegionDataRepository {

    private CSVRecord csvRecord;
    private List<RegionData> regionData;

    public boolean parseData() {
        regionData = new ArrayList<>();

        Reader in;
        Iterable<CSVRecord> records = null;
        try {
            in = new FileReader("01-26-2021.csv");
            records = CSVFormat.DEFAULT.withHeader().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord record : records) {
            csvRecord = record;

            // Tried using optionals but wouldn't work with empty fields
            String regionName = record.get("Country_Region");
            String regionProvinceState = record.get("Province_State");
            long confirmed = Long.parseLong(getRecordValueOrElseZero("Confirmed"));
            long deaths = Long.parseLong(getRecordValueOrElseZero("Deaths"));
            long recovered = Long.parseLong(getRecordValueOrElseZero("Recovered"));
            long active = Long.parseLong(getRecordValueOrElseZero("Active"));
            double incidentRate = Double.parseDouble(getRecordValueOrElseZero("Incident_Rate"));
            double caseFatalityRatio = Double.parseDouble(getRecordValueOrElseZero("Case_Fatality_Ratio"));

            regionData.add(new RegionData(regionName, regionProvinceState, confirmed, deaths, recovered, active, incidentRate, caseFatalityRatio));
        }
        return regionData != null;
    }

    private String getRecordValueOrElseZero(String header) {
        String record = csvRecord.get(header);
        return record.isEmpty() ? "0" : record;
    }

    public List<RegionData> getRegionData() {
        return regionData;
    }

    public List<RegionData> getRegion(String regionName) {
        return regionData.stream()
                .filter(region -> region.getREGION_NAME().equalsIgnoreCase(regionName))
                .collect(Collectors.toList());

    }

    public Long getTotalDeaths() {
        return regionData.stream()
                .mapToLong(RegionData::getDEATHS)
                .sum();
    }
}
