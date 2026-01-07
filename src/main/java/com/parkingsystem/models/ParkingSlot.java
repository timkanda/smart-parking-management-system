package com.parkingsystem.models;

import com.parkingsystem.interfaces.IParkable;
import com.parkingsystem.exceptions.SlotOccupiedException;
import com.parkingsystem.exceptions.SlotTypeMismatchException;
import java.io.Serializable;

/**
 * Abstract base class for parking slots.
 * Demonstrates inheritance and abstract class usage.
 * Implements IParkable interface for polymorphism.
 * 
 * @author [Your Name]
 * @version 2.0
 */
public abstract class ParkingSlot implements IParkable, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected final String slotId;
    protected Car parkedCar;
    
    /**
     * Constructor for ParkingSlot
     * @param slotId The slot identifier (format: Letter + 2 digits)
     */
    protected ParkingSlot(String slotId) {
        this.slotId = slotId;
        this.parkedCar = null;
    }
    
    // Getters
    public String getSlotId() { return slotId; }
    
    @Override
    public Car getParkedCar() { return parkedCar; }
    
    @Override
    public boolean isOccupied() { return parkedCar != null; }
    
    /**
     * Abstract method to determine if this is a staff slot
     * @return true if staff slot
     */
    public abstract boolean isStaffSlot();
    
    /**
     * Abstract method to get the slot type as string
     * @return Slot type description
     */
    public abstract String getSlotType();
    
    /**
     * Abstract method to get the hourly parking rate
     * @return Hourly rate in dollars
     */
    public abstract double getHourlyRate();
    
    /**
     * Validates if a car can be parked in this slot
     * @param car The car to validate
     * @throws SlotOccupiedException if slot is occupied
     * @throws SlotTypeMismatchException if car type doesn't match slot type
     */
    protected void validateParking(Car car) throws SlotOccupiedException, SlotTypeMismatchException {
        if (isOccupied()) {
            throw new SlotOccupiedException(slotId);
        }
        
        if (isStaffSlot() != car.isStaff()) {
            throw new SlotTypeMismatchException(slotId, isStaffSlot(), car.isStaff());
        }
    }
    
    @Override
    public boolean parkCar(Car car) {
        try {
            validateParking(car);
            this.parkedCar = car;
            car.setParkingTime();
            return true;
        } catch (SlotOccupiedException | SlotTypeMismatchException e) {
            return false;
        }
    }
    
    /**
     * Parks a car with exception throwing for detailed error handling
     * @param car The car to park
     * @throws SlotOccupiedException if slot is occupied
     * @throws SlotTypeMismatchException if car type doesn't match
     */
    public void parkCarWithException(Car car) throws SlotOccupiedException, SlotTypeMismatchException {
        validateParking(car);
        this.parkedCar = car;
        car.setParkingTime();
    }
    
    @Override
    public Car removeCar() {
        Car removedCar = this.parkedCar;
        this.parkedCar = null;
        return removedCar;
    }
    
    /**
     * Calculates parking fee based on slot's hourly rate
     * @return Parking fee in dollars
     */
    public double calculateParkingFee() {
        if (parkedCar == null || parkedCar.getParkingTime() == null) {
            return 0.0;
        }
        
        long hours = parkedCar.getParkingDurationObject().toHours();
        if (hours == 0) hours = 1; // Minimum 1 hour charge
        
        return hours * getHourlyRate();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Slot %s [%s] - ", slotId, getSlotType()));
        
        if (isOccupied()) {
            sb.append(String.format("OCCUPIED by %s (Owner: %s)", 
                                  parkedCar.getRegistrationNumber(), 
                                  parkedCar.getOwnerName()));
        } else {
            sb.append("EMPTY");
        }
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ParkingSlot slot = (ParkingSlot) obj;
        return slotId.equals(slot.slotId);
    }
    
    @Override
    public int hashCode() {
        return slotId.hashCode();
    }
}
