package com.parkingsystem.services;

import com.parkingsystem.interfaces.ISearchable;
import com.parkingsystem.models.*;
import com.parkingsystem.exceptions.*;
import com.parkingsystem.factory.ParkingSlotFactory;

import java.util.*;
import java.io.Serializable;

/**
 * Singleton service class managing the car park operations.
 * Demonstrates Singleton Design Pattern.
 * Uses HashMap for O(1) lookups.
 * 
 * @author [Your Name]
 * @version 2.0
 */
public class CarPark implements ISearchable, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Singleton instance
    private static CarPark instance;
    
    // Use HashMap for O(1) slot lookups by ID
    private final Map<String, ParkingSlot> slotMap;
    
    // Use HashMap for O(1) car lookups by registration
    private final Map<String, ParkingSlot> carToSlotMap;
    
    /**
     * Private constructor for Singleton pattern
     */
    private CarPark() {
        this.slotMap = new LinkedHashMap<>(); // Maintains insertion order
        this.carToSlotMap = new HashMap<>();
    }
    
    /**
     * Gets the singleton instance of CarPark
     * @return The CarPark instance
     */
    public static synchronized CarPark getInstance() {
        if (instance == null) {
            instance = new CarPark();
        }
        return instance;
    }
    
    /**
     * Resets the singleton instance (useful for testing)
     */
    public static synchronized void resetInstance() {
        instance = null;
    }
    
    /**
     * Adds a parking slot to the car park
     * @param slot The parking slot to add
     * @throws DuplicateSlotException if slot ID already exists
     */
    public void addSlot(ParkingSlot slot) throws DuplicateSlotException {
        if (slotMap.containsKey(slot.getSlotId())) {
            throw new DuplicateSlotException(slot.getSlotId());
        }
        slotMap.put(slot.getSlotId(), slot);
    }
    
    /**
     * Adds a slot (returns boolean for backward compatibility)
     * @param slot The parking slot to add
     * @return true if added successfully
     */
    public boolean addSlotSafe(ParkingSlot slot) {
        try {
            addSlot(slot);
            return true;
        } catch (DuplicateSlotException e) {
            return false;
        }
    }
    
    /**
     * Removes a parking slot by ID
     * @param slotId The ID of the slot to remove
     * @throws SlotNotFoundException if slot doesn't exist
     * @throws SlotOccupiedException if slot is occupied
     */
    public void removeSlot(String slotId) throws SlotNotFoundException, SlotOccupiedException {
        ParkingSlot slot = slotMap.get(slotId);
        
        if (slot == null) {
            throw new SlotNotFoundException(slotId);
        }
        
        if (slot.isOccupied()) {
            throw new SlotOccupiedException(slotId);
        }
        
        slotMap.remove(slotId);
    }
    
    /**
     * Removes a slot (returns boolean for backward compatibility)
     * @param slotId The slot ID to remove
     * @return true if removed successfully
     */
    public boolean removeSlotSafe(String slotId) {
        try {
            removeSlot(slotId);
            return true;
        } catch (ParkingException e) {
            return false;
        }
    }
    
    @Override
    public ParkingSlot findSlotById(String slotId) {
        return slotMap.get(slotId);
    }
    
    @Override
    public ParkingSlot findCarByRegistration(String registrationNumber) {
        return carToSlotMap.get(registrationNumber);
    }
    
    @Override
    public List<ParkingSlot> getAllSlots() {
        return new ArrayList<>(slotMap.values());
    }
    
    @Override
    public List<ParkingSlot> getAvailableSlots() {
        List<ParkingSlot> available = new ArrayList<>();
        for (ParkingSlot slot : slotMap.values()) {
            if (!slot.isOccupied()) {
                available.add(slot);
            }
        }
        return available;
    }
    
    @Override
    public List<ParkingSlot> getOccupiedSlots() {
        List<ParkingSlot> occupied = new ArrayList<>();
        for (ParkingSlot slot : slotMap.values()) {
            if (slot.isOccupied()) {
                occupied.add(slot);
            }
        }
        return occupied;
    }
    
    /**
     * Parks a car in the specified slot
     * @param slotId The slot ID
     * @param car The car to park
     * @throws SlotNotFoundException if slot doesn't exist
     * @throws SlotOccupiedException if slot is occupied
     * @throws SlotTypeMismatchException if car type doesn't match slot
     * @throws DuplicateCarException if car is already parked
     */
    public void parkCar(String slotId, Car car) throws SlotNotFoundException, 
            SlotOccupiedException, SlotTypeMismatchException, DuplicateCarException {
        
        // Check if car is already parked
        if (carToSlotMap.containsKey(car.getRegistrationNumber())) {
            throw new DuplicateCarException(car.getRegistrationNumber());
        }
        
        ParkingSlot slot = slotMap.get(slotId);
        if (slot == null) {
            throw new SlotNotFoundException(slotId);
        }
        
        slot.parkCarWithException(car);
        carToSlotMap.put(car.getRegistrationNumber(), slot);
    }
    
    /**
     * Parks a car (returns boolean for backward compatibility)
     * @param slotId The slot ID
     * @param car The car to park
     * @return true if parked successfully
     */
    public boolean parkCarSafe(String slotId, Car car) {
        try {
            parkCar(slotId, car);
            return true;
        } catch (ParkingException e) {
            return false;
        }
    }
    
    /**
     * Removes a car by registration number
     * @param registrationNumber The registration to remove
     * @return The removed car
     * @throws CarNotFoundException if car is not found
     */
    public Car removeCar(String registrationNumber) throws CarNotFoundException {
        ParkingSlot slot = carToSlotMap.get(registrationNumber);
        
        if (slot == null) {
            throw new CarNotFoundException(registrationNumber);
        }
        
        Car removedCar = slot.removeCar();
        carToSlotMap.remove(registrationNumber);
        return removedCar;
    }
    
    /**
     * Removes a car (returns Car or null for backward compatibility)
     * @param registrationNumber The registration to remove
     * @return The removed car or null
     */
    public Car removeCarSafe(String registrationNumber) {
        try {
            return removeCar(registrationNumber);
        } catch (CarNotFoundException e) {
            return null;
        }
    }
    
    /**
     * Removes all unoccupied slots
     * @return Number of slots removed
     */
    public int removeAllUnoccupiedSlots() {
        List<String> toRemove = new ArrayList<>();
        
        for (ParkingSlot slot : slotMap.values()) {
            if (!slot.isOccupied()) {
                toRemove.add(slot.getSlotId());
            }
        }
        
        for (String slotId : toRemove) {
            slotMap.remove(slotId);
        }
        
        return toRemove.size();
    }
    
    // Statistics methods
    public int getTotalSlots() { return slotMap.size(); }
    public int getOccupiedCount() { return carToSlotMap.size(); }
    public int getAvailableCount() { return slotMap.size() - carToSlotMap.size(); }
    
    /**
     * Clears all data (useful for testing)
     */
    public void clear() {
        slotMap.clear();
        carToSlotMap.clear();
    }
}
