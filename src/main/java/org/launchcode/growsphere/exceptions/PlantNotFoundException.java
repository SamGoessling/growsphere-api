package org.launchcode.growsphere.exceptions;

public class PlantNotFoundException extends RuntimeException{
    public PlantNotFoundException(int id) { super("Could not find the plant with ID " + id);}
}