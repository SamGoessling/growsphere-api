package org.launchcode.growsphere.controllers;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.launchcode.growsphere.models.CalendarEvent;
import org.launchcode.growsphere.models.Plant;
import org.launchcode.growsphere.services.CalendarService;
import org.launchcode.growsphere.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/calendar")
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

//    @PostMapping("/generate")
//    public ResponseEntity<List<CalendarEvent>> generateCalendar(@RequestBody Set<Integer> selectedPlantIds, HttpServletRequest request, @RequestBody String requestBody) {
//
////         Log request body
//        System.out.println("Received request body:");
//        System.out.println(requestBody);
//
//        // Parse request body to extract selectedPlantIds
//        ObjectMapper objectMapper = new ObjectMapper();
//        Set<Integer> receivedPlantIds;
//        try {
//            receivedPlantIds = objectMapper.readValue(requestBody, new TypeReference<Set<Integer>>() {});
//            System.out.println("Parsed plant IDs:");
//            System.out.println(receivedPlantIds);
//        } catch (IOException e) {
//            // Handle deserialization error
//            System.err.println("Error deserializing request body: " + e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//
//        List<CalendarEvent> calendar = calendarService.generateCalendar(receivedPlantIds);
//        return ResponseEntity.ok(calendar);
//// Extract the request body
////        String requestBody = "";
////        try {
////            requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
////        } catch (IOException e) {
////            System.err.println("Error reading request body: " + e.getMessage());
////            return ResponseEntity.badRequest().build();
////        }
//
//// Log the raw request body
////        System.out.println("Received request body:");
////        System.out.println(requestBody);
////
////        ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper is used for JSON deserialization
////        Set<Integer> parsedSelectedPlantIds;
////        try {
////            // Deserialize the request body into Set<Integer>
////            parsedSelectedPlantIds = objectMapper.readValue(requestBody, new TypeReference<Set<Integer>>() {});
////        } catch (IOException e) {
////            // Handle deserialization error
////            System.err.println("Error deserializing request body: " + e.getMessage());
////            return ResponseEntity.badRequest().build();
////        }
////
////// Proceed with your existing logic
////        List<CalendarEvent> calendar = calendarService.generateCalendar(parsedSelectedPlantIds);
////        return ResponseEntity.ok(calendar);
//
////        // Log request body
////        System.out.println("Received request body:");
////        System.out.println(requestBody);
////
////        ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper is used for JSON deserialization
////        Set<Integer> parsedselectedPlantIds;
////        try {
////            // Deserialize the request body into Set<Integer>
////            parsedselectedPlantIds = objectMapper.readValue(requestBody, new TypeReference<Set<Integer>>() {});
////        } catch (IOException e) {
////            // Handle deserialization error
////            System.err.println("Error deserializing request body: " + e.getMessage());
////            return ResponseEntity.badRequest().build();
////        }
////        List<CalendarEvent> calendar = calendarService.generateCalendar(selectedPlantIds);
////        return ResponseEntity.ok(calendar);
//    }

    @GetMapping("/plants")
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }


}
