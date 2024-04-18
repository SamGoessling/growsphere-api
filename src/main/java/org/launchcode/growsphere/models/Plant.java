package org.launchcode.growsphere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Plant extends AbstractEntity {

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "type")
    private String plantType;

    @Column(name = "water_frequency")
    private String waterFrequency;

    @Column(name = "water_requirements")
    private String waterRequirements;

    @Column(name = "inside_sow_date")
    private LocalDate insideSowDate;

    @Column(name = "sow_description")
    private String sowDescription;

    @Column(name = "outside_sow_planting_date")
    private LocalDate outsideSowDate;

    @Column(name = "harvest_date")
    private LocalDate harvestDate;

    @Column(name = "exposure")
    private String exposure;

    @Column(name = "ph_level")
    private String phLevel;

    @Column(name = "fertilizer_ratio")
    private String fertilizerRatio;

    @Column(name = "fertilizer_frequency")
    private String fertilizerFrequency;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "plants")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Plant() {
    }

    public Plant(
            String commonName,
            String scientificName,
            String plantType,
            String waterFrequency,
            String waterRequirements,
            LocalDate insideSowDate,
            String sowDescription,
            LocalDate outsideSowDate,
            LocalDate harvestDate,
            String exposure,
            String phLevel,
            String fertilizerRatio,
            String fertilizerFrequency) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.plantType = plantType;
        this.waterFrequency = waterFrequency;
        this.waterRequirements = waterRequirements;
        this.insideSowDate = insideSowDate;
        this.sowDescription = sowDescription;
        this.outsideSowDate = outsideSowDate;
        this.harvestDate = harvestDate;
        this.exposure = exposure;
        this.phLevel = phLevel;
        this.fertilizerRatio = fertilizerRatio;
        this.fertilizerFrequency = fertilizerFrequency;
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

    public String getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(String waterFrequency) {
        this.waterFrequency = waterFrequency;
    }

    public LocalDate getInsideSowDate() {
        return insideSowDate;
    }

    public void setInsideSowDate(LocalDate insideSowDate) {
        this.insideSowDate = insideSowDate;
    }

    public String getSowDescription() {
        return sowDescription;
    }

    public void setSowDescription(String sowDescription) {
        this.sowDescription = sowDescription;
    }

    public LocalDate getOutsideSowDate() {
        return outsideSowDate;
    }

    public void setOutsideSowDate(LocalDate outsideSowDate) {
        this.outsideSowDate = outsideSowDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getFertilizerRatio() {
        return fertilizerRatio;
    }

    public void setFertilizerRatio(String fertilizerRatio) {
        this.fertilizerRatio = fertilizerRatio;
    }

    public String getFertilizerFrequency() {
        return fertilizerFrequency;
    }

    public void setFertilizerFrequency(String fertilizerFrequency) {
        this.fertilizerFrequency = fertilizerFrequency;
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
}
