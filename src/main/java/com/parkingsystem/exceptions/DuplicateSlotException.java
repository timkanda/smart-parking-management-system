package com.parkingsystem.exceptions;

/**
 * Exception thrown when attempting to add a slot with a duplicate ID.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class DuplicateSlotException extends ParkingException {
    
    private final String slotId;
    
    /**
     * Creates a new DuplicateSlotException
     * @param slotId The duplicate slot ID
     */
    public DuplicateSlotException(String slotId) {
        super("Parking slot already exists: " + slotId, "DUPLICATE_SLOT");
        this.slotId = slotId;
    }
    
    /**
     * Gets the duplicate slot ID
     * @return The slot ID
     */
    public String getSlotId() {
        return slotId;
    }
}
