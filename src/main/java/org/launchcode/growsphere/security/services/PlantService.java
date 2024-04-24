package org.launchcode.growsphere.security.services;

import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;


    public List<Plant> getAllPlants() {
        return (List<Plant>) plantRepository.findAll();
    }


    public List<Plant> getPlantsByIds(Set<Integer> plantIds) {
        List<Integer> integerPlantIds = new ArrayList<>(plantIds);

        return  plantRepository.findAllById(integerPlantIds);
    }

}
