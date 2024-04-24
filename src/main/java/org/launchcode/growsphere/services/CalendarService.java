package org.launchcode.growsphere.services;

import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.Plant;
import org.launchcode.growsphere.security.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CalendarService {

    @Autowired
    private PlantService plantService;

    public List<CalendarEvent> generateCalendar(Set<Integer> selectedPlantIds) {


        // Retrieve selected plants using PlantService
        List<Plant> selectedPlants = plantService.getPlantsByIds(selectedPlantIds);

        List<CalendarEvent> calendarEvents = new ArrayList<>();

        for (Plant plant : selectedPlants) {
            CalendarEvent event = new CalendarEvent();
            event.setPlant(plant);
            event.setInsideSowDate(plant.getInsideSowDate());
            event.setOutsideSowDate(plant.getOutsideSowDate());
            event.setHarvestDate(plant.getHarvestDate());
            event.setSowDescription(plant.getSowDescription());
            event.setWaterFrequency(plant.getWaterFrequency());
            event.setWaterRequirements(plant.getWaterRequirements());

            calendarEvents.add(event);
        }

        return calendarEvents;
    }
}
