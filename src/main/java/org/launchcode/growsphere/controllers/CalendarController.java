package org.launchcode.growsphere.controllers;


import org.launchcode.growsphere.models.CalendarEventData;
import org.launchcode.growsphere.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/events")
    public ResponseEntity<Void> addCalendarEventsForUser(@RequestParam String userId, @RequestBody CalendarEventData calendarEventData) {
        try {
            calendarService.addCalendarEventsForUser(userId, calendarEventData);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
