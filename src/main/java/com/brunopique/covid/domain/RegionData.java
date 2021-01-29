package com.brunopique.covid.domain;

import java.util.Objects;

public class RegionData {

    private final String REGION_NAME;
    private final String PROVINCE_STATE;
    private final long CONFIRMED;
    private final long DEATHS;
    private final long RECOVERED;
    private final long ACTIVE;
    private final double INCIDENT_RATE;
    private final double CASE_FATALITY_RATIO;

    public RegionData(String regionName, String provinceState, long confirmed, long deaths, long recovered,long active, double incidentRate, double caseFatalityRatio) {
        REGION_NAME = regionName;
        PROVINCE_STATE = provinceState;
        CONFIRMED = confirmed;
        DEATHS = deaths;
        RECOVERED = recovered;
        ACTIVE = active;
        INCIDENT_RATE = incidentRate;
        CASE_FATALITY_RATIO = caseFatalityRatio;
    }

    public String getREGION_NAME() {
        return REGION_NAME;
    }

    public String getPROVINCE_STATE() {
        return PROVINCE_STATE;
    }

    public long getCONFIRMED() {
        return CONFIRMED;
    }

    public long getDEATHS() {
        return DEATHS;
    }

    public long getRECOVERED() {
        return RECOVERED;
    }

    public long getACTIVE() {
        return ACTIVE;
    }

    public double getINCIDENT_RATE() {
        return INCIDENT_RATE;
    }

    public double getCASE_FATALITY_RATIO() {
        return CASE_FATALITY_RATIO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionData)) return false;
        RegionData that = (RegionData) o;
        return CONFIRMED == that.CONFIRMED &&
                DEATHS == that.DEATHS &&
                RECOVERED == that.RECOVERED &&
                ACTIVE == that.ACTIVE &&
                Double.compare(that.INCIDENT_RATE, INCIDENT_RATE) == 0 &&
                Double.compare(that.CASE_FATALITY_RATIO, CASE_FATALITY_RATIO) == 0 &&
                Objects.equals(REGION_NAME, that.REGION_NAME) &&
                Objects.equals(PROVINCE_STATE, that.PROVINCE_STATE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(REGION_NAME, PROVINCE_STATE, CONFIRMED, DEATHS, RECOVERED, ACTIVE, INCIDENT_RATE, CASE_FATALITY_RATIO);
    }
}
