import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

/**
 * Represents a car in the parking system.
 * Each car has a registration number, owner name, and staff status.
 * Advanced feature: Records parking time when parked.
 * 
 * @author Student
 * @version 2.0
 */
public class Car {
    private String registrationNumber;
    private String ownerName;
    private boolean isStaff;
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
    
    /**
     * Gets the car's registration number
     * @return The registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    /**
     * Gets the owner's name
     * @return The owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }
    
    /**
     * Checks if the owner is a staff member
     * @return true if staff member, false if visitor
     */
    public boolean isStaff() {
        return isStaff;
    }
    
    /**
     * Records the current time as parking time
     */
    public void setParkingTime() {
        this.parkingTime = LocalDateTime.now();
    }
    
    /**
     * Gets the parking time
     * @return The parking time as LocalDateTime
     */
    public LocalDateTime getParkingTime() {
        return parkingTime;
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
     * Calculates parking duration in a readable format
     * @return Parking duration string (e.g., "2 hours 30 minutes 15 seconds")
     */
    public String getParkingDuration() {
        if (parkingTime == null) {
            return "Not parked";
        }
        
        Duration duration = Duration.between(parkingTime, LocalDateTime.now());
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        return String.format("%d hours %d minutes %d seconds", hours, minutes, seconds);
    }
    
    /**
     * Calculates parking fee based on duration
     * Fee is $5/hour, minimum 1 hour charge
     * @return Parking fee in dollars
     */
    public double getParkingFee() {
        if (parkingTime == null) {
            return 0.0;
        }
        
        Duration duration = Duration.between(parkingTime, LocalDateTime.now());
        long hours = duration.toHours();
        
        // Minimum 1 hour charge
        if (hours == 0) {
            hours = 1;
        }
        
        return hours * 5.0;
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
}
