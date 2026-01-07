package com.parkingsystem.exceptions;

/**
 * Exception thrown when a parking slot cannot be found.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class SlotNotFoundException extends ParkingException {
    
    private final String slotId;
    
    /**
     * Creates a new SlotNotFoundException
     * @param slotId The ID of the slot that was not found
     */
    public SlotNotFoundException(String slotId) {
        super("Parking slot not found: " + slotId, "SLOT_NOT_FOUND");
        this.slotId = slotId;
    }
    
    /**
     * Gets the slot ID that was not found
     * @return The slot ID
     */
    public String getSlotId() {
        return slotId;
    }
}
