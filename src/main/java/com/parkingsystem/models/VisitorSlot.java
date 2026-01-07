package com.parkingsystem.models;

/**
 * Represents a parking slot for visitors.
 * Extends ParkingSlot demonstrating inheritance.
 * Has higher hourly rate than staff slots.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class VisitorSlot extends ParkingSlot {
    
    private static final long serialVersionUID = 1L;
    private static final double HOURLY_RATE = 5.0; // $5/hour for visitors
    
    /**
     * Constructor for VisitorSlot
     * @param slotId The slot identifier
     */
    public VisitorSlot(String slotId) {
        super(slotId);
    }
    
    @Override
    public boolean isStaffSlot() {
        return false;
    }
    
    @Override
    public String getSlotType() {
        return "Visitor";
    }
    
    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
    
    @Override
    public String toString() {
        return "[VISITOR] " + super.toString() + String.format(" (Rate: $%.2f/hr)", HOURLY_RATE);
    }
}
