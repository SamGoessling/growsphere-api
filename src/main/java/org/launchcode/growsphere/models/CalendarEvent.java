package org.launchcode.growsphere.models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
public class CalendarEvent {

    private long id; // Unique identifier for the event
    @Setter
    private Plant plant; // Reference to the plant associated with the event
    @Setter
    private LocalDate insideSowDate; // Date for inside sow
    @Setter
    private LocalDate outsideSowDate; // Date for outside sow
    @Setter
    private LocalDate harvestDate; // Date for harvest

    public CalendarEvent(long id, Plant plant, LocalDate insideSowDate, LocalDate outsideSowDate, LocalDate harvestDate) {
        this.id = id;
        this.plant = plant;
        this.insideSowDate = insideSowDate;
        this.outsideSowDate = outsideSowDate;
        this.harvestDate = harvestDate;
    }

    public CalendarEvent() {

    }

}
