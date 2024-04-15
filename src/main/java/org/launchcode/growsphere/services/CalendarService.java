package org.launchcode.growsphere.services;


import org.launchcode.growsphere.models.CalendarEventData;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private PlantService plantService;

    public void addCalendarEventsForUser(String userId, CalendarEventData calendarEventData) {
        // Retrieve selected plants by user
        List<Plant> selectedPlants = plantService.getSelectedPlantsByUserId(userId);

        // Generate calendar events for each selected plant
        for (Plant plant : selectedPlants) {
            // Generate calendar event for sow date
            CalendarEvent sowEvent = new CalendarEvent();
            sowEvent.setTitle("Sow " + plant.getName());
            sowEvent.setStart(plant.getSowDate());
            // Add sow event to user's calendar

            // Generate calendar event for harvest date
            CalendarEvent harvestEvent = new CalendarEvent();
            harvestEvent.setTitle("Harvest " + plant.getName());
            harvestEvent.setStart(plant.getHarvestDate());
            // Add harvest event to user's calendar

            // Generate calendar event for watering schedule
            CalendarEvent waterEvent = new CalendarEvent();
            waterEvent.setTitle("Water " + plant.getName());
            // Calculate watering schedule based on plant's water schedule
            // Add water event to user's calendar
        }

        // Save calendar events to user's calendar (e.g., database)
    }
}
