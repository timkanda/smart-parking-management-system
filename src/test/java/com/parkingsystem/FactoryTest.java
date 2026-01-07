package com.parkingsystem;

import com.parkingsystem.factory.ParkingSlotFactory;
import com.parkingsystem.factory.ParkingSlotFactory.SlotType;
import com.parkingsystem.models.*;
import com.parkingsystem.exceptions.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ParkingSlotFactory.
 * Tests Factory Design Pattern implementation.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class FactoryTest {
    
    @Test
    @DisplayName("Factory should create StaffSlot for STAFF type")
    void testCreateStaffSlot() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createSlot("S01", SlotType.STAFF);
        
        assertInstanceOf(StaffSlot.class, slot);
        assertTrue(slot.isStaffSlot());
    }
    
    @Test
    @DisplayName("Factory should create VisitorSlot for VISITOR type")
    void testCreateVisitorSlot() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createSlot("V01", SlotType.VISITOR);
        
        assertInstanceOf(VisitorSlot.class, slot);
        assertFalse(slot.isStaffSlot());
    }
    
    @Test
    @DisplayName("Factory should infer type from S prefix")
    void testInferStaffFromPrefix() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createSlot("S01", false);
        
        assertInstanceOf(StaffSlot.class, slot);
    }
    
    @Test
    @DisplayName("Factory should infer type from V prefix")
    void testInferVisitorFromPrefix() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createSlot("V01", true);
        
        assertInstanceOf(VisitorSlot.class, slot);
    }
    
    @Test
    @DisplayName("Factory should use boolean for other prefixes")
    void testOtherPrefix() throws Exception {
        ParkingSlot staffSlot = ParkingSlotFactory.createSlot("D01", true);
        ParkingSlot visitorSlot = ParkingSlotFactory.createSlot("D02", false);
        
        assertInstanceOf(StaffSlot.class, staffSlot);
        assertInstanceOf(VisitorSlot.class, visitorSlot);
    }
    
    @Test
    @DisplayName("Factory should throw InvalidSlotIdException for invalid ID")
    void testInvalidSlotId() {
        assertThrows(InvalidSlotIdException.class, () -> {
            ParkingSlotFactory.createSlot("invalid", SlotType.STAFF);
        });
        
        assertThrows(InvalidSlotIdException.class, () -> {
            ParkingSlotFactory.createSlot("S1", SlotType.STAFF);
        });
        
        assertThrows(InvalidSlotIdException.class, () -> {
            ParkingSlotFactory.createSlot("123", SlotType.STAFF);
        });
    }
    
    @Test
    @DisplayName("createStaffSlot convenience method should work")
    void testCreateStaffSlotMethod() throws Exception {
        StaffSlot slot = ParkingSlotFactory.createStaffSlot("S01");
        
        assertNotNull(slot);
        assertTrue(slot.isStaffSlot());
    }
    
    @Test
    @DisplayName("createVisitorSlot convenience method should work")
    void testCreateVisitorSlotMethod() throws Exception {
        VisitorSlot slot = ParkingSlotFactory.createVisitorSlot("V01");
        
        assertNotNull(slot);
        assertFalse(slot.isStaffSlot());
    }
}
