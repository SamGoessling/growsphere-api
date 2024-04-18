package org.launchcode.growsphere.data;

import org.launchcode.growsphere.models.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PlantRepository extends CrudRepository<Plant, Integer> {

    List<Plant> findPlantsByUsersId(int userId);

    List<Plant> findAllById(Set<Long> selectedPlantIds);
}
