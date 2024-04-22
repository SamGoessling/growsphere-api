package org.launchcode.growsphere.services;

import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;


    public List<Plant> getAllPlants() {
        return (List<Plant>) plantRepository.findAll();
    }


    public List<Plant> getPlantsByIds(Set<Integer> plantIds) {
        List<Integer> integerPlantIds = new ArrayList<>(plantIds);

        return (List<Plant>) plantRepository.findAllById(integerPlantIds);
    }
    public List<CalendarEvent> generateCalendar(Set<Integer> selectedPlantIds) {


        // Retrieve selected plants using findAllById with a Set of IDs
        List<Plant> selectedPlants = (List<Plant>) plantRepository.findAllById(selectedPlantIds);

        List<CalendarEvent> calendarEvents = new ArrayList<>();

        for (Plant plant : selectedPlants) {
            CalendarEvent event = new CalendarEvent();
            event.setPlant(plant);
            event.setInsideSowDate(plant.getInsideSowDate());
            event.setOutsideSowDate(plant.getOutsideSowDate());
            event.setHarvestDate(plant.getHarvestDate());
            event.setSowDescription(plant.getSowDescription());
            calendarEvents.add(event);
        }

        return calendarEvents;
    }
}
