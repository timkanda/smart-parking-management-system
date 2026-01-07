package com.parkingsystem;

import com.parkingsystem.models.Car;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Unit tests for Car class.
 * Demonstrates test-driven development practices.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class CarTest {
    
    private Car staffCar;
    private Car visitorCar;
    
    @BeforeEach
    void setUp() {
        staffCar = new Car("A1234", "John Smith", true);
        visitorCar = new Car("B5678", "Jane Doe", false);
    }
    
    @Test
    @DisplayName("Car should be created with correct attributes")
    void testCarCreation() {
        assertEquals("A1234", staffCar.getRegistrationNumber());
        assertEquals("John Smith", staffCar.getOwnerName());
        assertTrue(staffCar.isStaff());
        
        assertEquals("B5678", visitorCar.getRegistrationNumber());
        assertEquals("Jane Doe", visitorCar.getOwnerName());
        assertFalse(visitorCar.isStaff());
    }
    
    @Test
    @DisplayName("Parking time should be null initially")
    void testInitialParkingTime() {
        assertNull(staffCar.getParkingTime());
        assertEquals("Not parked", staffCar.getFormattedParkingTime());
    }
    
    @Test
    @DisplayName("Setting parking time should record current time")
    void testSetParkingTime() {
        LocalDateTime before = LocalDateTime.now();
        staffCar.setParkingTime();
        LocalDateTime after = LocalDateTime.now();
        
        assertNotNull(staffCar.getParkingTime());
        assertTrue(staffCar.getParkingTime().isAfter(before.minusSeconds(1)));
        assertTrue(staffCar.getParkingTime().isBefore(after.plusSeconds(1)));
    }
    
    @Test
    @DisplayName("Clearing parking time should set it to null")
    void testClearParkingTime() {
        staffCar.setParkingTime();
        assertNotNull(staffCar.getParkingTime());
        
        staffCar.clearParkingTime();
        assertNull(staffCar.getParkingTime());
    }
    
    @Test
    @DisplayName("Cars with same registration should be equal")
    void testCarEquality() {
        Car sameCar = new Car("A1234", "Different Name", false);
        assertEquals(staffCar, sameCar);
        assertEquals(staffCar.hashCode(), sameCar.hashCode());
    }
    
    @Test
    @DisplayName("Cars with different registration should not be equal")
    void testCarInequality() {
        assertNotEquals(staffCar, visitorCar);
    }
    
    @Test
    @DisplayName("toString should return formatted string")
    void testToString() {
        String result = staffCar.toString();
        assertTrue(result.contains("A1234"));
        assertTrue(result.contains("John Smith"));
        assertTrue(result.contains("Staff"));
    }
}
