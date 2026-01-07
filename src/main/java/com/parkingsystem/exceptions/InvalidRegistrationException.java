package com.parkingsystem.exceptions;

/**
 * Exception thrown when a car registration format is invalid.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class InvalidRegistrationException extends ParkingException {
    
    private final String invalidRegistration;
    
    /**
     * Creates a new InvalidRegistrationException
     * @param invalidRegistration The invalid registration
     */
    public InvalidRegistrationException(String invalidRegistration) {
        super("Invalid registration format: " + invalidRegistration + 
              ". Expected: Uppercase letter followed by 4 digits (e.g., T1234)", 
              "INVALID_REGISTRATION");
        this.invalidRegistration = invalidRegistration;
    }
    
    /**
     * Gets the invalid registration
     * @return The invalid registration
     */
    public String getInvalidRegistration() {
        return invalidRegistration;
    }
}
