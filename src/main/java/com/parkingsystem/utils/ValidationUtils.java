package com.parkingsystem.utils;

import com.parkingsystem.exceptions.InvalidSlotIdException;
import com.parkingsystem.exceptions.InvalidRegistrationException;

/**
 * Utility class for validation operations.
 * Contains static methods for validating input data.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public final class ValidationUtils {
    
    // Private constructor to prevent instantiation
    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Validates slot ID format (Letter + 2 digits)
     * @param slotId The slot ID to validate
     * @return true if valid
     * @throws InvalidSlotIdException if format is invalid
     */
    public static boolean validateSlotId(String slotId) throws InvalidSlotIdException {
        if (slotId == null || slotId.length() != 3) {
            throw new InvalidSlotIdException(slotId);
        }
        
        char firstChar = slotId.charAt(0);
        if (!Character.isUpperCase(firstChar) || !Character.isLetter(firstChar)) {
            throw new InvalidSlotIdException(slotId);
        }
        
        String digits = slotId.substring(1);
        try {
            Integer.parseInt(digits);
            return true;
        } catch (NumberFormatException e) {
            throw new InvalidSlotIdException(slotId);
        }
    }
    
    /**
     * Validates car registration format (Letter + 4 digits)
     * @param registration The registration to validate
     * @return true if valid
     * @throws InvalidRegistrationException if format is invalid
     */
    public static boolean validateRegistration(String registration) throws InvalidRegistrationException {
        if (registration == null || registration.length() != 5) {
            throw new InvalidRegistrationException(registration);
        }
        
        char firstChar = registration.charAt(0);
        if (!Character.isUpperCase(firstChar) || !Character.isLetter(firstChar)) {
            throw new InvalidRegistrationException(registration);
        }
        
        String digits = registration.substring(1);
        try {
            Integer.parseInt(digits);
            return true;
        } catch (NumberFormatException e) {
            throw new InvalidRegistrationException(registration);
        }
    }
    
    /**
     * Checks if slot ID format is valid without throwing exception
     * @param slotId The slot ID to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidSlotId(String slotId) {
        try {
            return validateSlotId(slotId);
        } catch (InvalidSlotIdException e) {
            return false;
        }
    }
    
    /**
     * Checks if registration format is valid without throwing exception
     * @param registration The registration to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidRegistration(String registration) {
        try {
            return validateRegistration(registration);
        } catch (InvalidRegistrationException e) {
            return false;
        }
    }
}
