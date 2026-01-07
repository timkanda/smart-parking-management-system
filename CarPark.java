import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manages the car park with a collection of parking slots.
 * Provides functionality to add, remove, and find parking slots.
 * 
 * @author Student
 * @version 2.0
 */
public class CarPark {
    private ArrayList<ParkingSlot> parkingSlots;
    
    /**
     * Constructor for CarPark
     */
    public CarPark() {
        this.parkingSlots = new ArrayList<>();
    }
    
    /**
     * Adds a parking slot to the car park
     * @param slot The parking slot to add
     * @return true if added successfully, false if slot ID already exists
     */
    public boolean addSlot(ParkingSlot slot) {
        // Check if slot ID already exists
        if (findSlotById(slot.getSlotId()) != null) {
            return false;
        }
        
        parkingSlots.add(slot);
        return true;
    }
    
    /**
     * Removes a parking slot by ID (only if not occupied)
     * @param slotId The ID of the slot to remove
     * @return true if removed successfully, false if not found or occupied
     */
    public boolean removeSlot(String slotId) {
        ParkingSlot slot = findSlotById(slotId);
        if (slot == null) {
            return false; // Slot not found
        }
        
        if (slot.isOccupied()) {
            return false; // Cannot remove occupied slot
        }
        
        parkingSlots.remove(slot);
        return true;
    }
    
    /**
     * Finds a parking slot by its ID
     * @param slotId The slot ID to search for
     * @return The parking slot, or null if not found
     */
    public ParkingSlot findSlotById(String slotId) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotId().equals(slotId)) {
                return slot;
            }
        }
        return null;
    }
    
    /**
     * Finds a car by its registration number
     * @param registrationNumber The car registration to search for
     * @return The parking slot containing the car, or null if not found
     */
    public ParkingSlot findCarByRegistration(String registrationNumber) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied() && 
                slot.getParkedCar().getRegistrationNumber().equals(registrationNumber)) {
                return slot;
            }
        }
        return null;
    }
    
    /**
     * Gets all parking slots
     * @return ArrayList of all parking slots
     */
    public ArrayList<ParkingSlot> getAllSlots() {
        return new ArrayList<>(parkingSlots);
    }
    
    /**
     * Removes all unoccupied parking slots
     * @return Number of slots removed
     */
    public int removeAllUnoccupiedSlots() {
        int removedCount = 0;
        Iterator<ParkingSlot> iterator = parkingSlots.iterator();
        
        while (iterator.hasNext()) {
            ParkingSlot slot = iterator.next();
            if (!slot.isOccupied()) {
                iterator.remove();
                removedCount++;
            }
        }
        
        return removedCount;
    }
    
    /**
     * Parks a car in the specified slot
     * @param slotId The slot ID where to park
     * @param car The car to park
     * @return true if parked successfully, false otherwise
     */
    public boolean parkCar(String slotId, Car car) {
        // Check if car is already parked somewhere
        if (findCarByRegistration(car.getRegistrationNumber()) != null) {
            return false; // Car already parked
        }
        
        ParkingSlot slot = findSlotById(slotId);
        if (slot == null) {
            return false; // Slot not found
        }
        
        return slot.parkCar(car);
    }
    
    /**
     * Removes a car by its registration number
     * @param registrationNumber The registration number of the car to remove
     * @return The removed car, or null if not found
     */
    public Car removeCarByRegistration(String registrationNumber) {
        ParkingSlot slot = findCarByRegistration(registrationNumber);
        if (slot == null) {
            return null; // Car not found
        }
        
        return slot.removeCar();
    }
    
    /**
     * Gets the total number of slots
     * @return Total number of parking slots
     */
    public int getTotalSlots() {
        return parkingSlots.size();
    }
    
    /**
     * Gets the number of occupied slots
     * @return Number of occupied slots
     */
    public int getOccupiedSlots() {
        int count = 0;
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied()) {
                count++;
            }
        }
        return count;
    }
}
