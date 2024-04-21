package org.launchcode.growsphere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Plant {

    @Id
    @GeneratedValue
    private int id;

    private String commonName;

    private String scientificName;

    private String plantType;

    private String waterRequirements;

    private String sow;

    private String harvest;

    private String exposure;

    private String phLevel;

    private String fertilizer;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "plants")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Plant() {
    }

    public Plant(int id,
                 String commonName,
                 String scientificName,
                 String plantType,
                 String waterRequirements,
                 String sow,
                 String harvest,
                 String exposure,
                 String phLevel,
                 String fertilizer) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.plantType = plantType;
        this.waterRequirements = waterRequirements;
        this.sow = sow;
        this.harvest = harvest;
        this.exposure = exposure;
        this.phLevel = phLevel;
        this.fertilizer = fertilizer;
    }

    public int getId() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getWaterRequirements() {
        return waterRequirements;
    }

    public void setWaterRequirements(String waterRequirements) {
        this.waterRequirements = waterRequirements;
    }

    public String getSow() {
        return sow;
    }

    public void setSow(String sow) {
        this.sow = sow;
    }

    public String getHarvest() {
        return harvest;
    }

    public void setHarvest(String harvest) {
        this.harvest = harvest;
    }

    public String getExposure() {
        return exposure;
    }

    public void setExposure(String exposure) {
        this.exposure = exposure;
    }

    public String getPhLevel() {
        return phLevel;
    }

    public void setPhLevel(String phLevel) {
        this.phLevel = phLevel;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return commonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id == plant.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
