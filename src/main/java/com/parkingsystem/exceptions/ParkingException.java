package com.parkingsystem.exceptions;

/**
 * Base exception class for all parking system exceptions.
 * Provides a foundation for the exception hierarchy.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class ParkingException extends Exception {
    
    private final String errorCode;
    
    /**
     * Creates a new ParkingException
     * @param message The error message
     */
    public ParkingException(String message) {
        super(message);
        this.errorCode = "PARKING_ERROR";
    }
    
    /**
     * Creates a new ParkingException with error code
     * @param message The error message
     * @param errorCode The specific error code
     */
    public ParkingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * Creates a new ParkingException with cause
     * @param message The error message
     * @param cause The underlying cause
     */
    public ParkingException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "PARKING_ERROR";
    }
    
    /**
     * Gets the error code
     * @return The error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}
