package org.launchcode.growsphere.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserRole extends AbstractEntity {

    private String role;

    @OneToMany(mappedBy = "userRole")
    private final List<User> users = new ArrayList<>();

    public UserRole(String role) {
        this.role = role;
    }

    public UserRole() {}

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
