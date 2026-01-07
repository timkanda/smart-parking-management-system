package com.parkingsystem.interfaces;

import com.parkingsystem.models.Car;
import com.parkingsystem.models.ParkingSlot;
import java.util.List;

/**
 * Interface defining search operations for the parking system.
 * Enables flexible search implementations.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public interface ISearchable {
    
    /**
     * Finds a parking slot by its unique identifier
     * @param slotId The slot ID to search for
     * @return The found parking slot or null
     */
    ParkingSlot findSlotById(String slotId);
    
    /**
     * Finds a car by its registration number
     * @param registrationNumber The registration to search for
     * @return The parking slot containing the car, or null
     */
    ParkingSlot findCarByRegistration(String registrationNumber);
    
    /**
     * Gets all parking slots in the system
     * @return List of all parking slots
     */
    List<ParkingSlot> getAllSlots();
    
    /**
     * Gets all available (unoccupied) slots
     * @return List of available slots
     */
    List<ParkingSlot> getAvailableSlots();
    
    /**
     * Gets all occupied slots
     * @return List of occupied slots
     */
    List<ParkingSlot> getOccupiedSlots();
}
