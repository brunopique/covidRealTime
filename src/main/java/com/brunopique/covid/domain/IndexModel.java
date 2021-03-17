package com.brunopique.covid.domain;

import java.util.Map;

/**
 An {@code IndexModel} is a POJO that holds all the data retrieved
 from {@link com.brunopique.covid.repository.CovidDataRepository}, {@link com.brunopique.covid.repository.SubregionRepository}
 and {@link com.brunopique.covid.repository.RegionRepository},
 which is then displayed in the index.
 */
public class IndexModel {

    private Long worldwideDeaths;
    private Long worldwideConfirmed;
    private Long worldwideRecovered;
    private Double worldwideIncidentRate;
    private Double worldwideFatalityRatio;

    private Map<String, String> regionWithMostDeaths;
    private Map<String, String> regionWithLeastDeaths;
    private Map<String, String> regionWithHighestConfirmedCases;
    private Map<String, String> regionWithLowestConfirmedCases;
    private Map<String, String> regionWithMostRecovered;
    private Map<String, String> regionWithLeastRecovered;
    private Map<String, String> regionWithHighestIncidentRate;
    private Map<String, String> regionWithLowestIncidentRate;
    private Map<String, String> regionWithHighestFatalityRate;
    private Map<String, String> regionWithLowestFatalityRate;

    private Map<String, String> subregionWithMostDeaths;
    private Map<String, String> subregionWithLeastDeaths;
    private Map<String, String> subregionWithHighestConfirmedCases;
    private Map<String, String> subregionWithLowestConfirmedCases;
    private Map<String, String> subregionWithMostRecovered;
    private Map<String, String> subregionWithLeastRecovered;
    private Map<String, String> subregionWithHighestIncidentRate;
    private Map<String, String> subregionWithLowestIncidentRate;
    private Map<String, String> subregionWithHighestFatalityRate;
    private Map<String, String> subregionWithLowestFatalityRate;

    public Long getWorldwideDeaths() {
        return worldwideDeaths;
    }

    public void setWorldwideDeaths(Long worldwideDeaths) {
        this.worldwideDeaths = worldwideDeaths;
    }

    public Long getWorldwideConfirmed() {
        return worldwideConfirmed;
    }

    public void setWorldwideConfirmed(Long worldwideConfirmed) {
        this.worldwideConfirmed = worldwideConfirmed;
    }

    public Long getWorldwideRecovered() {
        return worldwideRecovered;
    }

    public void setWorldwideRecovered(Long worldwideRecovered) {
        this.worldwideRecovered = worldwideRecovered;
    }

    public Double getWorldwideIncidentRate() {
        return worldwideIncidentRate;
    }

    public void setWorldwideIncidentRate(Double worldwideIncidentRate) {
        this.worldwideIncidentRate = worldwideIncidentRate;
    }

    public Double getWorldwideFatalityRatio() {
        return worldwideFatalityRatio;
    }

    public void setWorldwideFatalityRatio(Double worldwideFatalityRatio) {
        this.worldwideFatalityRatio = worldwideFatalityRatio;
    }

    public Map<String, String> getRegionWithMostDeaths() {
        return regionWithMostDeaths;
    }

    public void setRegionWithMostDeaths(Map<String, String> regionWithMostDeaths) {
        this.regionWithMostDeaths = regionWithMostDeaths;
    }

    public Map<String, String> getRegionWithLeastDeaths() {
        return regionWithLeastDeaths;
    }

    public void setRegionWithLeastDeaths(Map<String, String> regionWithLeastDeaths) {
        this.regionWithLeastDeaths = regionWithLeastDeaths;
    }

    public Map<String, String> getRegionWithHighestConfirmedCases() {
        return regionWithHighestConfirmedCases;
    }

    public void setRegionWithHighestConfirmedCases(Map<String, String> regionWithHighestConfirmedCases) {
        this.regionWithHighestConfirmedCases = regionWithHighestConfirmedCases;
    }

    public Map<String, String> getRegionWithLowestConfirmedCases() {
        return regionWithLowestConfirmedCases;
    }

    public void setRegionWithLowestConfirmedCases(Map<String, String> regionWithLowestConfirmedCases) {
        this.regionWithLowestConfirmedCases = regionWithLowestConfirmedCases;
    }

    public Map<String, String> getRegionWithMostRecovered() {
        return regionWithMostRecovered;
    }

    public void setRegionWithMostRecovered(Map<String, String> regionWithMostRecovered) {
        this.regionWithMostRecovered = regionWithMostRecovered;
    }

    public Map<String, String> getRegionWithLeastRecovered() {
        return regionWithLeastRecovered;
    }

    public void setRegionWithLeastRecovered(Map<String, String> regionWithLeastRecovered) {
        this.regionWithLeastRecovered = regionWithLeastRecovered;
    }

    public Map<String, String> getRegionWithHighestIncidentRate() {
        return regionWithHighestIncidentRate;
    }

    public void setRegionWithHighestIncidentRate(Map<String, String> regionWithHighestIncidentRate) {
        this.regionWithHighestIncidentRate = regionWithHighestIncidentRate;
    }

    public Map<String, String> getRegionWithLowestIncidentRate() {
        return regionWithLowestIncidentRate;
    }

    public void setRegionWithLowestIncidentRate(Map<String, String> regionWithLowestIncidentRate) {
        this.regionWithLowestIncidentRate = regionWithLowestIncidentRate;
    }

    public Map<String, String> getRegionWithHighestFatalityRate() {
        return regionWithHighestFatalityRate;
    }

    public void setRegionWithHighestFatalityRate(Map<String, String> regionWithHighestFatalityRate) {
        this.regionWithHighestFatalityRate = regionWithHighestFatalityRate;
    }

    public Map<String, String> getRegionWithLowestFatalityRate() {
        return regionWithLowestFatalityRate;
    }

    public void setRegionWithLowestFatalityRate(Map<String, String> regionWithLowestFatalityRate) {
        this.regionWithLowestFatalityRate = regionWithLowestFatalityRate;
    }

    public Map<String, String> getSubregionWithMostDeaths() {
        return subregionWithMostDeaths;
    }

    public void setSubregionWithMostDeaths(Map<String, String> subregionWithMostDeaths) {
        this.subregionWithMostDeaths = subregionWithMostDeaths;
    }

    public Map<String, String> getSubregionWithLeastDeaths() {
        return subregionWithLeastDeaths;
    }

    public void setSubregionWithLeastDeaths(Map<String, String> subregionWithLeastDeaths) {
        this.subregionWithLeastDeaths = subregionWithLeastDeaths;
    }

    public Map<String, String> getSubregionWithHighestConfirmedCases() {
        return subregionWithHighestConfirmedCases;
    }

    public void setSubregionWithHighestConfirmedCases(Map<String, String> subregionWithHighestConfirmedCases) {
        this.subregionWithHighestConfirmedCases = subregionWithHighestConfirmedCases;
    }

    public Map<String, String> getSubregionWithLowestConfirmedCases() {
        return subregionWithLowestConfirmedCases;
    }

    public void setSubregionWithLowestConfirmedCases(Map<String, String> subregionWithLowestConfirmedCases) {
        this.subregionWithLowestConfirmedCases = subregionWithLowestConfirmedCases;
    }

    public Map<String, String> getSubregionWithMostRecovered() {
        return subregionWithMostRecovered;
    }

    public void setSubregionWithMostRecovered(Map<String, String> subregionWithMostRecovered) {
        this.subregionWithMostRecovered = subregionWithMostRecovered;
    }

    public Map<String, String> getSubregionWithLeastRecovered() {
        return subregionWithLeastRecovered;
    }

    public void setSubregionWithLeastRecovered(Map<String, String> subregionWithLeastRecovered) {
        this.subregionWithLeastRecovered = subregionWithLeastRecovered;
    }

    public Map<String, String> getSubregionWithHighestIncidentRate() {
        return subregionWithHighestIncidentRate;
    }

    public void setSubregionWithHighestIncidentRate(Map<String, String> subregionWithHighestIncidentRate) {
        this.subregionWithHighestIncidentRate = subregionWithHighestIncidentRate;
    }

    public Map<String, String> getSubregionWithLowestIncidentRate() {
        return subregionWithLowestIncidentRate;
    }

    public void setSubregionWithLowestIncidentRate(Map<String, String> subregionWithLowestIncidentRate) {
        this.subregionWithLowestIncidentRate = subregionWithLowestIncidentRate;
    }

    public Map<String, String> getSubregionWithHighestFatalityRate() {
        return subregionWithHighestFatalityRate;
    }

    public void setSubregionWithHighestFatalityRate(Map<String, String> subregionWithHighestFatalityRate) {
        this.subregionWithHighestFatalityRate = subregionWithHighestFatalityRate;
    }

    public Map<String, String> getSubregionWithLowestFatalityRate() {
        return subregionWithLowestFatalityRate;
    }

    public void setSubregionWithLowestFatalityRate(Map<String, String> subregionWithLowestFatalityRate) {
        this.subregionWithLowestFatalityRate = subregionWithLowestFatalityRate;
    }

    @Override
    public String toString() {
        return "IndexModel{" +
                "worldwideDeaths=" + worldwideDeaths +
                ", worldwideConfirmed=" + worldwideConfirmed +
                ", worldwideRecovered=" + worldwideRecovered +
                ", worldwideIncidentRate=" + worldwideIncidentRate +
                ", worldwideFatalityRatio=" + worldwideFatalityRatio +
                ", regionWithMostDeaths=" + regionWithMostDeaths +
                ", regionWithLeastDeaths=" + regionWithLeastDeaths +
                ", regionWithHighestConfirmedCases=" + regionWithHighestConfirmedCases +
                ", regionWithLowestConfirmedCases=" + regionWithLowestConfirmedCases +
                ", regionWithMostRecovered=" + regionWithMostRecovered +
                ", regionWithLeastRecovered=" + regionWithLeastRecovered +
                ", regionWithHighestIncidentRate=" + regionWithHighestIncidentRate +
                ", regionWithLowestIncidentRate=" + regionWithLowestIncidentRate +
                ", regionWithHighestFatalityRate=" + regionWithHighestFatalityRate +
                ", regionWithLowestFatalityRate=" + regionWithLowestFatalityRate +
                ", subregionWithMostDeaths=" + subregionWithMostDeaths +
                ", subregionWithLeastDeaths=" + subregionWithLeastDeaths +
                ", subregionWithHighestConfirmedCases=" + subregionWithHighestConfirmedCases +
                ", subregionWithLowestConfirmedCases=" + subregionWithLowestConfirmedCases +
                ", subregionWithMostRecovered=" + subregionWithMostRecovered +
                ", subregionWithLeastRecovered=" + subregionWithLeastRecovered +
                ", subregionWithHighestIncidentRate=" + subregionWithHighestIncidentRate +
                ", subregionWithLowestIncidentRate=" + subregionWithLowestIncidentRate +
                ", subregionWithHighestFatalityRate=" + subregionWithHighestFatalityRate +
                ", subregionWithLowestFatalityRate=" + subregionWithLowestFatalityRate +
                '}';
    }
}
