package com.parkingsystem.interfaces;

import com.parkingsystem.models.Car;

/**
 * Interface defining parking operations for parking entities.
 * Demonstrates interface-based programming for loose coupling.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public interface IParkable {
    
    /**
     * Parks a car in this parking entity
     * @param car The car to park
     * @return true if parking was successful
     */
    boolean parkCar(Car car);
    
    /**
     * Removes the parked car from this entity
     * @return The removed car, or null if empty
     */
    Car removeCar();
    
    /**
     * Checks if this entity is currently occupied
     * @return true if occupied, false otherwise
     */
    boolean isOccupied();
    
    /**
     * Gets the parked car if any
     * @return The parked car or null
     */
    Car getParkedCar();
}
