//-- RoleRepository.java

/*--Spring Data interface for accessing Role data in the database. It provides
methods to query Role entities without needing to write SQL queries. --*/

package org.launchcode.growsphere.data;

import org.launchcode.growsphere.models.ERole;
import org.launchcode.growsphere.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
