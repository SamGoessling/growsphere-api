package org.launchcode.growsphere.controllers;


import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.CalendarEventData;
import org.launchcode.growsphere.services.CalendarService;
import org.launchcode.growsphere.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/user/calendar")
public class CalendarController {

    @Autowired
    private PlantService plantService;

    @PostMapping("/generate")
    public ResponseEntity<List<CalendarEvent>> generateCalendar(@RequestBody Set<Long> selectedPlantIds) {
        List<CalendarEvent> calendar = plantService.generateCalendar(selectedPlantIds);
        return ResponseEntity.ok(calendar);
    }
}
