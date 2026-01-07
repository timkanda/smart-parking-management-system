/**
 * Represents a parking slot in the car park.
 * Each slot has an ID, type (staff/visitor), and can contain a car.
 * 
 * @author Student
 * @version 2.0
 */
public class ParkingSlot {
    private String slotId;
    private boolean isStaffSlot;
    private Car parkedCar;
    
    /**
     * Constructor for ParkingSlot
     * @param slotId The slot identifier (format: Letter + 2 digits)
     * @param isStaffSlot true if staff slot, false if visitor slot
     */
    public ParkingSlot(String slotId, boolean isStaffSlot) {
        this.slotId = slotId;
        this.isStaffSlot = isStaffSlot;
        this.parkedCar = null;
    }
    
    /**
     * Gets the slot ID
     * @return The slot ID
     */
    public String getSlotId() {
        return slotId;
    }
    
    /**
     * Checks if this is a staff slot
     * @return true if staff slot, false if visitor slot
     */
    public boolean isStaffSlot() {
        return isStaffSlot;
    }
    
    /**
     * Checks if the slot is occupied
     * @return true if occupied, false if empty
     */
    public boolean isOccupied() {
        return parkedCar != null;
    }
    
    /**
     * Gets the parked car
     * @return The parked car, or null if empty
     */
    public Car getParkedCar() {
        return parkedCar;
    }
    
    /**
     * Parks a car in this slot
     * @param car The car to park
     * @return true if successful, false if slot is occupied or car type doesn't match
     */
    public boolean parkCar(Car car) {
        // Check if slot is already occupied
        if (isOccupied()) {
            return false;
        }
        
        // Check if car type matches slot type
        if (isStaffSlot && !car.isStaff()) {
            return false; // Staff slot, but visitor car
        }
        if (!isStaffSlot && car.isStaff()) {
            return false; // Visitor slot, but staff car
        }
        
        this.parkedCar = car;
        car.setParkingTime(); // Record parking time
        return true;
    }
    
    /**
     * Removes the car from this slot
     * @return The removed car, or null if slot was empty
     */
    public Car removeCar() {
        Car removedCar = this.parkedCar;
        if (removedCar != null) {
            removedCar.clearParkingTime();
        }
        this.parkedCar = null;
        return removedCar;
    }
    
    /**
     * Gets the slot type as a string
     * @return "Staff" or "Visitor"
     */
    public String getSlotType() {
        return isStaffSlot ? "Staff" : "Visitor";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Slot %s [%s] - ", slotId, getSlotType()));
        
        if (isOccupied()) {
            sb.append(String.format("OCCUPIED by %s (Owner: %s)", 
                                  parkedCar.getRegistrationNumber(), 
                                  parkedCar.getOwnerName()));
            
            // Add parking time and fee information for advanced features
            if (parkedCar.getParkingTime() != null) {
                sb.append(String.format(" | Parked: %s | Duration: %s | Fee: $%.2f", 
                                      parkedCar.getFormattedParkingTime(),
                                      parkedCar.getParkingDuration(),
                                      parkedCar.getParkingFee()));
            }
        } else {
            sb.append("EMPTY");
        }
        
        return sb.toString();
    }
}
