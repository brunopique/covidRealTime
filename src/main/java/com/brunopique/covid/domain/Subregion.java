package com.brunopique.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 A {@code Subregion} object represents a single region for each of the
 {@link com.brunopique.covid.domain.Region} objects and holds a list of
 daily {@link com.brunopique.covid.domain.CovidData} .
 */
@Entity
public class Subregion {

    private Long id;
    private String name;
    private Region region;
    private Set<CovidData> dailyCovidData;

    public Subregion() {}

    public Subregion(String name) {
        this.name = name;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @OneToMany(mappedBy = "subregion", fetch = FetchType.LAZY)
    public Set<CovidData> getDailyCovidData() {
        return dailyCovidData;
    }

    public void setDailyCovidData(Set<CovidData> dailyCovidData) {
        this.dailyCovidData = dailyCovidData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subregion)) return false;
        Subregion subregion = (Subregion) o;
        return id.equals(subregion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Subregion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
