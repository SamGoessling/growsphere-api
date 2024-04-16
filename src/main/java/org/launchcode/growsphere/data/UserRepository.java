package org.launchcode.growsphere.data;

import org.launchcode.growsphere.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findUsersByPlantsId(int plantId);

}
