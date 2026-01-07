package com.parkingsystem.interfaces;

import com.parkingsystem.models.Car;

/**
 * Strategy interface for fee calculation.
 * Allows different fee calculation strategies to be implemented.
 * Demonstrates the Strategy Design Pattern.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public interface IFeeCalculator {
    
    /**
     * Calculates the parking fee for a car
     * @param car The car to calculate fee for
     * @return The calculated fee amount
     */
    double calculateFee(Car car);
    
    /**
     * Gets the hourly rate for this calculator
     * @return Hourly rate in dollars
     */
    double getHourlyRate();
    
    /**
     * Gets a description of this fee calculation strategy
     * @return Description string
     */
    String getDescription();
}
