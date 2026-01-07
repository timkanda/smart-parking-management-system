package com.parkingsystem.exceptions;

/**
 * Exception thrown when attempting to park in an occupied slot.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class SlotOccupiedException extends ParkingException {
    
    private final String slotId;
    
    /**
     * Creates a new SlotOccupiedException
     * @param slotId The ID of the occupied slot
     */
    public SlotOccupiedException(String slotId) {
        super("Parking slot is already occupied: " + slotId, "SLOT_OCCUPIED");
        this.slotId = slotId;
    }
    
    /**
     * Gets the occupied slot ID
     * @return The slot ID
     */
    public String getSlotId() {
        return slotId;
    }
}
