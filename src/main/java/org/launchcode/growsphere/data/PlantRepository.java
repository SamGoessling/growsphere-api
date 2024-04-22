package org.launchcode.growsphere.data;

import org.launchcode.growsphere.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    List<Plant> findPlantsByUsersId(int userId);

}
