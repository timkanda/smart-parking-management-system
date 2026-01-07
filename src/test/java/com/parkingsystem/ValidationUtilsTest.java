package com.parkingsystem;

import com.parkingsystem.utils.ValidationUtils;
import com.parkingsystem.exceptions.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ValidationUtils.
 * Tests input validation logic.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class ValidationUtilsTest {
    
    // Slot ID validation tests
    
    @Test
    @DisplayName("Valid slot IDs should pass validation")
    void testValidSlotIds() throws Exception {
        assertTrue(ValidationUtils.validateSlotId("S01"));
        assertTrue(ValidationUtils.validateSlotId("V99"));
        assertTrue(ValidationUtils.validateSlotId("A00"));
        assertTrue(ValidationUtils.validateSlotId("Z12"));
    }
    
    @Test
    @DisplayName("Invalid slot IDs should throw exception")
    void testInvalidSlotIds() {
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId(null));
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId(""));
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId("S1"));
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId("S001"));
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId("s01"));
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId("1S1"));
        assertThrows(InvalidSlotIdException.class, () -> ValidationUtils.validateSlotId("SAB"));
    }
    
    @Test
    @DisplayName("isValidSlotId should return boolean without exception")
    void testIsValidSlotId() {
        assertTrue(ValidationUtils.isValidSlotId("S01"));
        assertFalse(ValidationUtils.isValidSlotId("invalid"));
        assertFalse(ValidationUtils.isValidSlotId(null));
    }
    
    // Registration validation tests
    
    @Test
    @DisplayName("Valid registrations should pass validation")
    void testValidRegistrations() throws Exception {
        assertTrue(ValidationUtils.validateRegistration("A1234"));
        assertTrue(ValidationUtils.validateRegistration("Z9999"));
        assertTrue(ValidationUtils.validateRegistration("T0000"));
    }
    
    @Test
    @DisplayName("Invalid registrations should throw exception")
    void testInvalidRegistrations() {
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration(null));
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration(""));
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration("A123"));
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration("A12345"));
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration("a1234"));
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration("12345"));
        assertThrows(InvalidRegistrationException.class, () -> ValidationUtils.validateRegistration("ABCDE"));
    }
    
    @Test
    @DisplayName("isValidRegistration should return boolean without exception")
    void testIsValidRegistration() {
        assertTrue(ValidationUtils.isValidRegistration("A1234"));
        assertFalse(ValidationUtils.isValidRegistration("invalid"));
        assertFalse(ValidationUtils.isValidRegistration(null));
    }
}
