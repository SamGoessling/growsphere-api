package org.launchcode.growsphere.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

    @OneToMany(mappedBy = "role")
    private final List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

}