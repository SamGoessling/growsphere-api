package org.launchcode.growsphere.controllers;



import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.Plant;
import org.launchcode.growsphere.security.services.PlantService;
import org.launchcode.growsphere.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "http://localhost:8081") // Set allowed origins
public class CalendarController {


    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PlantService plantService;


    @PostMapping("/generate")
    public ResponseEntity<List<CalendarEvent>> generateCalendar(@RequestBody Set<Integer> selectedPlantIds) {
        List<CalendarEvent> calendar = calendarService.generateCalendar(selectedPlantIds);
        return ResponseEntity.ok(calendar);
    }


    @GetMapping("/plants")
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }


}
