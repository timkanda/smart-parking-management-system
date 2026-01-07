package com.parkingsystem;

import com.parkingsystem.models.*;
import com.parkingsystem.exceptions.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ParkingSlot hierarchy.
 * Tests inheritance and polymorphism behavior.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class ParkingSlotTest {
    
    private StaffSlot staffSlot;
    private VisitorSlot visitorSlot;
    private Car staffCar;
    private Car visitorCar;
    
    @BeforeEach
    void setUp() {
        staffSlot = new StaffSlot("S01");
        visitorSlot = new VisitorSlot("V01");
        staffCar = new Car("A1234", "John", true);
        visitorCar = new Car("B5678", "Jane", false);
    }
    
    @Test
    @DisplayName("Staff slot should have correct properties")
    void testStaffSlotProperties() {
        assertEquals("S01", staffSlot.getSlotId());
        assertTrue(staffSlot.isStaffSlot());
        assertEquals("Staff", staffSlot.getSlotType());
        assertEquals(3.0, staffSlot.getHourlyRate());
    }
    
    @Test
    @DisplayName("Visitor slot should have correct properties")
    void testVisitorSlotProperties() {
        assertEquals("V01", visitorSlot.getSlotId());
        assertFalse(visitorSlot.isStaffSlot());
        assertEquals("Visitor", visitorSlot.getSlotType());
        assertEquals(5.0, visitorSlot.getHourlyRate());
    }
    
    @Test
    @DisplayName("Slot should be empty initially")
    void testInitialState() {
        assertFalse(staffSlot.isOccupied());
        assertNull(staffSlot.getParkedCar());
    }
    
    @Test
    @DisplayName("Staff car should park in staff slot")
    void testParkStaffCarInStaffSlot() {
        assertTrue(staffSlot.parkCar(staffCar));
        assertTrue(staffSlot.isOccupied());
        assertEquals(staffCar, staffSlot.getParkedCar());
    }
    
    @Test
    @DisplayName("Visitor car should park in visitor slot")
    void testParkVisitorCarInVisitorSlot() {
        assertTrue(visitorSlot.parkCar(visitorCar));
        assertTrue(visitorSlot.isOccupied());
        assertEquals(visitorCar, visitorSlot.getParkedCar());
    }
    
    @Test
    @DisplayName("Visitor car should not park in staff slot")
    void testParkVisitorCarInStaffSlot() {
        assertFalse(staffSlot.parkCar(visitorCar));
        assertFalse(staffSlot.isOccupied());
    }
    
    @Test
    @DisplayName("Staff car should not park in visitor slot")
    void testParkStaffCarInVisitorSlot() {
        assertFalse(visitorSlot.parkCar(staffCar));
        assertFalse(visitorSlot.isOccupied());
    }
    
    @Test
    @DisplayName("Cannot park in occupied slot")
    void testParkInOccupiedSlot() {
        staffSlot.parkCar(staffCar);
        
        Car anotherStaffCar = new Car("C9999", "Bob", true);
        assertFalse(staffSlot.parkCar(anotherStaffCar));
    }
    
    @Test
    @DisplayName("parkCarWithException should throw SlotOccupiedException")
    void testParkCarWithExceptionOccupied() {
        staffSlot.parkCar(staffCar);
        
        Car anotherCar = new Car("C9999", "Bob", true);
        assertThrows(SlotOccupiedException.class, () -> {
            staffSlot.parkCarWithException(anotherCar);
        });
    }
    
    @Test
    @DisplayName("parkCarWithException should throw SlotTypeMismatchException")
    void testParkCarWithExceptionTypeMismatch() {
        assertThrows(SlotTypeMismatchException.class, () -> {
            staffSlot.parkCarWithException(visitorCar);
        });
    }
    
    @Test
    @DisplayName("Remove car should return the parked car")
    void testRemoveCar() {
        staffSlot.parkCar(staffCar);
        
        Car removed = staffSlot.removeCar();
        assertEquals(staffCar, removed);
        assertFalse(staffSlot.isOccupied());
        assertNull(staffSlot.getParkedCar());
    }
    
    @Test
    @DisplayName("Remove from empty slot should return null")
    void testRemoveFromEmptySlot() {
        assertNull(staffSlot.removeCar());
    }
    
    @Test
    @DisplayName("Slots with same ID should be equal")
    void testSlotEquality() {
        StaffSlot sameSlot = new StaffSlot("S01");
        assertEquals(staffSlot, sameSlot);
    }
}
