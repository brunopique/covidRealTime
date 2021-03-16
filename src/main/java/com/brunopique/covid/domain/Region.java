package com.brunopique.covid.domain;

import javax.persistence.*;
import java.util.*;

/**
 A {@code Region} object represents a single country and
 holds a list of {@link com.brunopique.covid.domain.Subregion} objects,
 with their corresponding daily {@link com.brunopique.covid.domain.CovidData}.
 */
@Entity
public class Region {

    private Long id;
    private String name;
    private Set<Subregion> subregions = new HashSet<>();

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    public Set<Subregion> getSubregions() {
        return subregions;
    }

    public void setSubregions(Set<Subregion> covidData) {
        this.subregions = covidData;
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
                '}';
    }
}
