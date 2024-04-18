package org.launchcode.growsphere.services;


import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.CalendarEventData;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CalendarService {

    @Autowired
    private PlantService plantService;
    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        return (List<Plant>) plantRepository.findAll();
    }

    public List<CalendarEvent> generateCalendar(Set<Long> selectedPlantIds) {
        List<Plant> selectedPlants = (List<Plant>) plantRepository.findAllById(selectedPlantIds);

        List<CalendarEvent> calendarEvents = new ArrayList<>();

        for (Plant plant : selectedPlants) {
            CalendarEvent event = new CalendarEvent();
            event.setPlant(plant);
            event.setInsideSowDate(plant.getInsideSowDate());
            event.setHarvestDate(plant.getHarvestDate());
            event.setOutsideSowDate(plant.getOutsideSowDate());
            // Add more properties as needed
            calendarEvents.add(event);
        }

        return calendarEvents;
    }

}
