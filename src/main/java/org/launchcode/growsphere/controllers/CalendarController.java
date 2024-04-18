package org.launchcode.growsphere.controllers;


import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.Plant;
import org.launchcode.growsphere.services.CalendarService;
import org.launchcode.growsphere.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/user/calendar")
public class CalendarController {


    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PlantService plantService;

    @PostMapping("/calendar/generate")
    public ResponseEntity<List<CalendarEvent>> generateCalendar(@RequestBody Set<Long> selectedPlantIds) {
        List<CalendarEvent> calendar = calendarService.generateCalendar(selectedPlantIds);
        return ResponseEntity.ok(calendar);
    }

    @GetMapping("/plants")
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }
}
