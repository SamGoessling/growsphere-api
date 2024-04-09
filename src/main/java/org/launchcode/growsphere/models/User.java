package org.launchcode.growsphere.models;

import jakarta.persistence.*;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

@Entity
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @ManyToOne
    private Role role;

    private int userGrowthPts = 0;

//    @ManyToMany
//    private final List<Plant> plants = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_plants",
            joinColumns = { @JoinColumn(name = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "plants_id") })
    private Set<Plant> plants = new HashSet<>();

    public User() {
    }

    public User(String username, String email, Role role, int userGrowthPts) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.userGrowthPts = userGrowthPts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return role;
    }

    public void setUserRole(Role role) {
        this.role = role;
    }

    public int getUserGrowthPts() {
        return userGrowthPts;
    }

    public void setUserGrowthPts(int userGrowthPts) {
        this.userGrowthPts = userGrowthPts;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }

//    'getter' and 'setter' for plants associated with users
    public void addPlant(Plant plant) {
        this.plants.add(plant);
        plant.getUsers().add(this);
    }

    public void removePlant(int plantId) {
        Plant plant = this.plants.stream().filter(t -> t.getId() == plantId).findFirst().orElse(null);
        if (plant != null) {
            this.plants.remove(plant);
            plant.getUsers().remove(this);
        }
    }

    @Override
    public String toString() {
        return username;
    }
}
