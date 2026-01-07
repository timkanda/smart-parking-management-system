package com.parkingsystem.exceptions;

/**
 * Exception thrown when attempting to park a car that is already parked.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class DuplicateCarException extends ParkingException {
    
    private final String registrationNumber;
    
    /**
     * Creates a new DuplicateCarException
     * @param registrationNumber The duplicate car registration
     */
    public DuplicateCarException(String registrationNumber) {
        super("Car is already parked: " + registrationNumber, "DUPLICATE_CAR");
        this.registrationNumber = registrationNumber;
    }
    
    /**
     * Gets the duplicate registration number
     * @return The registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
