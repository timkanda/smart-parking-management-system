package com.parkingsystem.services;

import com.parkingsystem.interfaces.IFeeCalculator;
import com.parkingsystem.models.Car;

/**
 * Service class providing different fee calculation strategies.
 * Demonstrates Strategy Design Pattern implementation.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class FeeCalculatorService {
    
    /**
     * Standard fee calculator - flat rate per hour
     */
    public static class StandardFeeCalculator implements IFeeCalculator {
        private final double hourlyRate;
        
        public StandardFeeCalculator(double hourlyRate) {
            this.hourlyRate = hourlyRate;
        }
        
        @Override
        public double calculateFee(Car car) {
            if (car.getParkingTime() == null) return 0.0;
            
            long hours = car.getParkingDurationObject().toHours();
            if (hours == 0) hours = 1; // Minimum 1 hour
            
            return hours * hourlyRate;
        }
        
        @Override
        public double getHourlyRate() { return hourlyRate; }
        
        @Override
        public String getDescription() {
            return String.format("Standard Rate: $%.2f/hour", hourlyRate);
        }
    }
    
    /**
     * Weekend fee calculator - discounted rate
     */
    public static class WeekendFeeCalculator implements IFeeCalculator {
        private final double hourlyRate;
        private static final double WEEKEND_DISCOUNT = 0.8; // 20% discount
        
        public WeekendFeeCalculator(double hourlyRate) {
            this.hourlyRate = hourlyRate;
        }
        
        @Override
        public double calculateFee(Car car) {
            if (car.getParkingTime() == null) return 0.0;
            
            long hours = car.getParkingDurationObject().toHours();
            if (hours == 0) hours = 1;
            
            return hours * hourlyRate * WEEKEND_DISCOUNT;
        }
        
        @Override
        public double getHourlyRate() { return hourlyRate * WEEKEND_DISCOUNT; }
        
        @Override
        public String getDescription() {
            return String.format("Weekend Rate: $%.2f/hour (20%% off)", hourlyRate * WEEKEND_DISCOUNT);
        }
    }
    
    /**
     * Daily maximum fee calculator - caps at daily maximum
     */
    public static class DailyMaxFeeCalculator implements IFeeCalculator {
        private final double hourlyRate;
        private final double dailyMax;
        
        public DailyMaxFeeCalculator(double hourlyRate, double dailyMax) {
            this.hourlyRate = hourlyRate;
            this.dailyMax = dailyMax;
        }
        
        @Override
        public double calculateFee(Car car) {
            if (car.getParkingTime() == null) return 0.0;
            
            long hours = car.getParkingDurationObject().toHours();
            if (hours == 0) hours = 1;
            
            double fee = hours * hourlyRate;
            return Math.min(fee, dailyMax);
        }
        
        @Override
        public double getHourlyRate() { return hourlyRate; }
        
        @Override
        public String getDescription() {
            return String.format("Daily Max: $%.2f/hour (max $%.2f/day)", hourlyRate, dailyMax);
        }
    }
}
