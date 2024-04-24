package org.launchcode.growsphere.controllers;

import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.data.UserRepository;
import org.launchcode.growsphere.exceptions.PlantNotFoundException;
import org.launchcode.growsphere.exceptions.UserNotFoundException;
import org.launchcode.growsphere.models.Plant;
import org.launchcode.growsphere.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/plant")
    Plant newPlant(@RequestBody Plant newPlant) {
        return plantRepository.save(newPlant);
    }

    @GetMapping("/plants")
    List<Plant> getAllPlants() {
        return (List<Plant>) plantRepository.findAll();
    }

//    Lists all plants selected by user
    @GetMapping("/users/{userId}/plants")
    List<Plant> getAllPlantsByUserId(@PathVariable(value = "userId") int userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return (List<Plant>) plantRepository.findPlantsByUsersId(userId);
    }

//    Lists all users who selected a plant
    @GetMapping("/plants/{plantId}/users")
    List<User> getAllUsersByPlantId(@PathVariable(value = "plantId") int plantId) {
        if (!plantRepository.existsById(plantId)) {
            throw new PlantNotFoundException(plantId);
        }
        return (List<User>) userRepository.findUsersByPlantsId(plantId);
    }

//    Adds a selected plant to a user's list of plants
    @PostMapping("/user/{userId}/plant/{plantId}")
    String addPlantToUser(@PathVariable(value = "userId") int userId,
                          @PathVariable int plantId) {
        userRepository.findById(userId).map(user -> {
            Plant plant = plantRepository.findById(plantId)
                    .orElseThrow(() -> new PlantNotFoundException(plantId));
            user.addPlant(plant);
            userRepository.save(user);
            return "";
        }).orElseThrow(() -> new UserNotFoundException(userId));
        return "Plant with ID " + plantId + " added to your plants.";
    }

//    Deletes a selected plant from the user's list of plants
    @DeleteMapping("/user/{userId}/plant/{plantId}")
    String deletePlantFromUser(@PathVariable(value = "userId") int userId,
                               @PathVariable(value = "plantId") int plantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.removePlant(plantId);
        userRepository.save(user);
        return "Plant with ID " + plantId + " removed from your plants.";
    }

    @GetMapping("/plant/{id}")
    Plant getPlantById(@PathVariable int id) {
        return plantRepository.findById(id)
                .orElseThrow(() ->new PlantNotFoundException(id));
    }
    @PutMapping("/plant/{id}")
    Plant updatePlant(@RequestBody Plant newPlant, @PathVariable int id) {
        return plantRepository.findById(id)
                .map(plant -> {
                    plant.setCommonName(newPlant.getCommonName());
                    plant.setScientificName(newPlant.getScientificName());
                    plant.setPlantType(newPlant.getPlantType());
                    plant.setExposure(newPlant.getExposure());
                    plant.setFertilizerRatio(newPlant.getFertilizerRatio());
                    plant.setHarvestDate(newPlant.getHarvestDate());
                    plant.setPhLevel(newPlant.getPhLevel());
                    plant.setInsideSowDate(newPlant.getInsideSowDate());
                    plant.setWaterFrequency(newPlant.getWaterFrequency());
                    plant.setWaterRequirements(newPlant.getWaterRequirements());
                    plant.setInsideSowDate(newPlant.getInsideSowDate());
                    plant.setSowDescription(newPlant.getSowDescription());
                    plant.setOutsideSowDate(newPlant.getOutsideSowDate());
                    plant.setHarvestDate(newPlant.getHarvestDate());
                    plant.setExposure(newPlant.getExposure());
                    plant.setPhLevel(newPlant.getPhLevel());
                    plant.setFertilizerRatio(newPlant.getFertilizerRatio());
                    plant.setFertilizerFrequency(newPlant.getFertilizerFrequency());


                    return plantRepository.save(plant);
                }).orElseThrow(()->new PlantNotFoundException(id));

    }
    @DeleteMapping("/plant/{id}")
    String deletePlant(@PathVariable int id) {
        if(!plantRepository.existsById(id)) {
            throw new PlantNotFoundException(id);
        }
        plantRepository.deleteById(id);
        return "Plant with ID:" + id + " has been successfully deleted.";
    }
}