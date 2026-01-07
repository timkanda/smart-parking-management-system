package com.parkingsystem.exceptions;

/**
 * Exception thrown when a car cannot be found in the system.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class CarNotFoundException extends ParkingException {
    
    private final String registrationNumber;
    
    /**
     * Creates a new CarNotFoundException
     * @param registrationNumber The registration of the car not found
     */
    public CarNotFoundException(String registrationNumber) {
        super("Car not found: " + registrationNumber, "CAR_NOT_FOUND");
        this.registrationNumber = registrationNumber;
    }
    
    /**
     * Gets the registration number that was not found
     * @return The registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
