package com.parkingsystem.exceptions;

/**
 * Exception thrown when a slot ID format is invalid.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class InvalidSlotIdException extends ParkingException {
    
    private final String invalidId;
    
    /**
     * Creates a new InvalidSlotIdException
     * @param invalidId The invalid slot ID
     */
    public InvalidSlotIdException(String invalidId) {
        super("Invalid slot ID format: " + invalidId + 
              ". Expected: Uppercase letter followed by 2 digits (e.g., S01)", 
              "INVALID_SLOT_ID");
        this.invalidId = invalidId;
    }
    
    /**
     * Gets the invalid ID
     * @return The invalid slot ID
     */
    public String getInvalidId() {
        return invalidId;
    }
}
