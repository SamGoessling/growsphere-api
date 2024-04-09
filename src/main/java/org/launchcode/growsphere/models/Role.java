package org.launchcode.growsphere.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends AbstractEntity {

    private String role;

    @OneToMany(mappedBy = "role")
    private final List<User> users = new ArrayList<>();

    public Role(String role) {
        this.role = role;
    }

    public Role() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return role;
    }

}
