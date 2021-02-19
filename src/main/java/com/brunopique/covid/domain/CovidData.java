package com.brunopique.covid.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CovidData {
    private Long id;
    private String combinedKey;
    private LocalDate date;
    private long confirmed;
    private long deaths;
    private long recovered;
    private long active;
    private double incidentRate;
    private double caseFatalityRatio;
    private Region region;

    public CovidData() {

    }

    public CovidData(LocalDate date, String combinedKey, long confirmed, long deaths, long recovered, long active, double incidentRate, double caseFatalityRatio) {
        this.combinedKey = combinedKey;
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.incidentRate = incidentRate;
        this.caseFatalityRatio = caseFatalityRatio;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public double getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(double incidentRate) {
        this.incidentRate = incidentRate;
    }

    public double getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(double caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                "id=" + id +
                ", combinedKey='" + combinedKey + '\'' +
                ", date=" + date +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", incidentRate=" + incidentRate +
                ", caseFatalityRatio=" + caseFatalityRatio +
                ", dailyCovidData=" + region +
                '}';
    }
}
