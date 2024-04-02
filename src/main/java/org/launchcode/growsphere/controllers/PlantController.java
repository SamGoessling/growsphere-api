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
//        Plant plant = (Plant) userRepository.findById(userId).map(user -> {
//            int plantId = plantRequest.getId();
//
//            if (plantId != 0) {
//                Plant _plant = plantRepository.findById(plantId)
//                        .orElseThrow(() -> new PlantNotFoundException(plantId));
//                user.addPlant(_plant);
//                userRepository.save(user);
//                return "Plant with ID " + plantRequest.getId() + " added to your plants.";
//            }
//
//            user.addPlant(plantRequest);
//            return plantRepository.save(plantRequest);
//        }).orElseThrow(() -> new UserNotFoundException(userId));
//        return "Plant with ID " + plantRequest.getId() + " added to your plants.";
    }
    /*
    public ResponseEntity<Tag> addTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tagRequest) {
    Tag tag = tutorialRepository.findById(tutorialId).map(tutorial -> {
      long tagId = tagRequest.getId();

      // tag is existed
      if (tagId != 0L) {
        Tag _tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
        tutorial.addTag(_tag);
        tutorialRepository.save(tutorial);
        return _tag;
      }

      // add and create new Tag
      tutorial.addTag(tagRequest);
      return tagRepository.save(tagRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

    return new ResponseEntity<>(tag, HttpStatus.CREATED);
  }
     */

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
                    plant.setFertilizer(newPlant.getFertilizer());
                    plant.setHarvest(newPlant.getHarvest());
                    plant.setPhLevel(newPlant.getPhLevel());
                    plant.setSow(newPlant.getSow());
                    plant.setWaterRequirements(newPlant.getWaterRequirements());

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