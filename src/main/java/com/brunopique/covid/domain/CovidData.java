package com.brunopique.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CovidData {
    private Long id;
    private LocalDate date;
    private String regionAndCountry;
    private long confirmed;
    private long deaths;
    private double recovered;
    private long active;
    private double incidentRate;
    private double caseFatalityRatio;
    private Subregion subregion;

    public CovidData() {

    }

    public CovidData(LocalDate date, String regionAndCountry, long confirmed, long deaths, double recovered, long active, double incidentRate, double caseFatalityRatio) {
        this.date = date;
        this.regionAndCountry = regionAndCountry;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    public Subregion getSubregion() {
        return subregion;
    }

    public void setSubregion(Subregion region) {
        this.subregion = region;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRegionAndCountry() {
        return regionAndCountry;
    }

    public void setRegionAndCountry(String combinedNames) {
        this.regionAndCountry = combinedNames;
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

    public double getRecovered() {
        return recovered;
    }

    public void setRecovered(double recovered) {
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
                ", date=" + date +
                ", regionAndCountry='" + regionAndCountry + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", incidentRate=" + incidentRate +
                ", caseFatalityRatio=" + caseFatalityRatio +
                '}';
    }
}
