package com.parkingsystem.models;

/**
 * Represents a parking slot reserved for staff members.
 * Extends ParkingSlot demonstrating inheritance.
 * Has lower hourly rate than visitor slots.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class StaffSlot extends ParkingSlot {
    
    private static final long serialVersionUID = 1L;
    private static final double HOURLY_RATE = 3.0; // $3/hour for staff
    
    /**
     * Constructor for StaffSlot
     * @param slotId The slot identifier
     */
    public StaffSlot(String slotId) {
        super(slotId);
    }
    
    @Override
    public boolean isStaffSlot() {
        return true;
    }
    
    @Override
    public String getSlotType() {
        return "Staff";
    }
    
    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
    
    @Override
    public String toString() {
        return "[STAFF] " + super.toString() + String.format(" (Rate: $%.2f/hr)", HOURLY_RATE);
    }
}
