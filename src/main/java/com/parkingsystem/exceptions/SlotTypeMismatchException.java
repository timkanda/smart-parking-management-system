package com.parkingsystem.exceptions;

/**
 * Exception thrown when car type doesn't match slot type.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class SlotTypeMismatchException extends ParkingException {
    
    private final String slotId;
    private final boolean isStaffSlot;
    private final boolean isStaffCar;
    
    /**
     * Creates a new SlotTypeMismatchException
     * @param slotId The slot ID
     * @param isStaffSlot Whether the slot is for staff
     * @param isStaffCar Whether the car belongs to staff
     */
    public SlotTypeMismatchException(String slotId, boolean isStaffSlot, boolean isStaffCar) {
        super(String.format("Type mismatch: %s slot cannot accept %s car", 
              isStaffSlot ? "Staff" : "Visitor", 
              isStaffCar ? "staff" : "visitor"), "TYPE_MISMATCH");
        this.slotId = slotId;
        this.isStaffSlot = isStaffSlot;
        this.isStaffCar = isStaffCar;
    }
    
    public String getSlotId() { return slotId; }
    public boolean isStaffSlot() { return isStaffSlot; }
    public boolean isStaffCar() { return isStaffCar; }
}
