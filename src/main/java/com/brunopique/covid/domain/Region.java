package com.brunopique.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Region {

    private Long id;
    private String name;
    private Set<CovidData> dailyCovidData = new HashSet<>();

    public Region() {}

    public Region(String name) {
        this.name = name;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    public Set<CovidData> getDailyCovidData() {
        return dailyCovidData;
    }

    public void setDailyCovidData(Set<CovidData> covidData) {
        this.dailyCovidData = covidData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region that = (Region) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dailyCovidData=" + dailyCovidData +
                '}';
    }
}
