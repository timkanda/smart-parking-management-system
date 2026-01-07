package com.parkingsystem.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.io.Serializable;

/**
 * Represents a car in the parking system.
 * Implements Serializable for data persistence support.
 * 
 * @author [Your Name]
 * @version 2.0
 */
public class Car implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final String registrationNumber;
    private final String ownerName;
    private final boolean isStaff;
    private LocalDateTime parkingTime;
    
    /**
     * Constructor for Car object
     * @param registrationNumber The car's registration number (format: Letter + 4 digits)
     * @param ownerName The name of the car owner
     * @param isStaff Whether the owner is a staff member
     */
    public Car(String registrationNumber, String ownerName, boolean isStaff) {
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.isStaff = isStaff;
        this.parkingTime = null;
    }
    
    // Getters
    public String getRegistrationNumber() { return registrationNumber; }
    public String getOwnerName() { return ownerName; }
    public boolean isStaff() { return isStaff; }
    public LocalDateTime getParkingTime() { return parkingTime; }
    
    /**
     * Records the current time as parking time
     */
    public void setParkingTime() {
        this.parkingTime = LocalDateTime.now();
    }
    
    /**
     * Sets parking time to a specific value (for testing/persistence)
     * @param time The parking time to set
     */
    public void setParkingTime(LocalDateTime time) {
        this.parkingTime = time;
    }
    
    /**
     * Gets formatted parking time string
     * @return Formatted parking time string
     */
    public String getFormattedParkingTime() {
        if (parkingTime == null) {
            return "Not parked";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return parkingTime.format(formatter);
    }
    
    /**
     * Calculates parking duration
     * @return Duration object representing time parked
     */
    public Duration getParkingDurationObject() {
        if (parkingTime == null) {
            return Duration.ZERO;
        }
        return Duration.between(parkingTime, LocalDateTime.now());
    }
    
    /**
     * Calculates parking duration in a readable format
     * @return Parking duration string
     */
    public String getParkingDuration() {
        if (parkingTime == null) {
            return "Not parked";
        }
        
        Duration duration = getParkingDurationObject();
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        return String.format("%d hours %d minutes %d seconds", hours, minutes, seconds);
    }
    
    /**
     * Clears the parking time (when car is removed)
     */
    public void clearParkingTime() {
        this.parkingTime = null;
    }
    
    @Override
    public String toString() {
        return String.format("Car[%s, Owner: %s, Type: %s]", 
                           registrationNumber, ownerName, isStaff ? "Staff" : "Visitor");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return registrationNumber.equals(car.registrationNumber);
    }
    
    @Override
    public int hashCode() {
        return registrationNumber.hashCode();
    }
}
