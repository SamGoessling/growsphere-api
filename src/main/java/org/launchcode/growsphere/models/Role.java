package org.launchcode.growsphere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
          mappedBy = "roles")
  @JsonIgnore
  private Set<User> users = new HashSet<>();

  public Role() {
  }

  public Role(ERole name) {
    this.name = name;
  }

  public int getId() {
    return this.getId();
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }

    public Set<User> getUsers() {
        return users;
    }

}