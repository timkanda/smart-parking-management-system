package com.parkingsystem;

import com.parkingsystem.services.CarPark;
import com.parkingsystem.models.*;
import com.parkingsystem.factory.ParkingSlotFactory;
import com.parkingsystem.exceptions.*;
import com.parkingsystem.utils.ValidationUtils;
import com.parkingsystem.persistence.DataPersistence;

import java.util.*;
import java.io.IOException;

/**
 * Main application class with console-based user interface.
 * Demonstrates the use of all OOP components.
 * 
 * @author [Your Name]
 * @version 2.0
 */
public class Application {
    
    private final CarPark carPark;
    private final Scanner scanner;
    private final DataPersistence persistence;
    
    public Application() {
        this.carPark = CarPark.getInstance();
        this.scanner = new Scanner(System.in);
        this.persistence = new DataPersistence();
    }
    
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
    
    public void run() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     PARKING SPOT MANAGEMENT SYSTEM v2.0              ║");
        System.out.println("║     Professional OOP Implementation                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        initializeCarPark();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            
            try {
                switch (choice) {
                    case 1: addParkingSlot(); break;
                    case 2: deleteParkingSlot(); break;
                    case 3: listAllSlots(); break;
                    case 4: deleteAllUnoccupiedSlots(); break;
                    case 5: parkCar(); break;
                    case 6: findCar(); break;
                    case 7: removeCar(); break;
                    case 8: saveData(); break;
                    case 9: 
                        System.out.println("\nThank you for using Parking Management System!");
                        running = false; 
                        break;
                    default: 
                        System.out.println("Invalid option.");
                }
            } catch (ParkingException e) {
                System.out.println("Error [" + e.getErrorCode() + "]: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private void initializeCarPark() {
        System.out.println("\n--- Car Park Initialization ---");
        
        int staffSlots = getPositiveInteger("Enter number of staff slots: ");
        int visitorSlots = getPositiveInteger("Enter number of visitor slots: ");
        
        try {
            for (int i = 1; i <= staffSlots; i++) {
                String slotId = String.format("S%02d", i);
                ParkingSlot slot = ParkingSlotFactory.createStaffSlot(slotId);
                carPark.addSlot(slot);
            }
            
            for (int i = 1; i <= visitorSlots; i++) {
                String slotId = String.format("V%02d", i);
                ParkingSlot slot = ParkingSlotFactory.createVisitorSlot(slotId);
                carPark.addSlot(slot);
            }
            
            System.out.printf("\n✓ Initialized: %d staff slots ($3/hr) + %d visitor slots ($5/hr)\n", 
                             staffSlots, visitorSlots);
        } catch (ParkingException e) {
            System.out.println("Initialization error: " + e.getMessage());
        }
    }
    
    private void displayMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         MAIN MENU                   │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Add a parking slot              │");
        System.out.println("│  2. Delete a parking slot           │");
        System.out.println("│  3. List all slots                  │");
        System.out.println("│  4. Delete all unoccupied slots     │");
        System.out.println("│  5. Park a car                      │");
        System.out.println("│  6. Find a car                      │");
        System.out.println("│  7. Remove a car                    │");
        System.out.println("│  8. Save data                       │");
        System.out.println("│  9. Exit                            │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Select option (1-9): ");
    }
    
    private int getMenuChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 9) return choice;
                System.out.print("Enter 1-9: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter 1-9: ");
            }
        }
    }
    
    private void addParkingSlot() throws ParkingException {
        System.out.println("\n--- Add Parking Slot ---");
        
        String slotId = getValidSlotId("Enter slot ID (e.g., D01): ");
        boolean isStaff = getYesNoInput("Is this a staff slot? (y/n): ");
        
        ParkingSlot slot = ParkingSlotFactory.createSlot(slotId, isStaff);
        carPark.addSlot(slot);
        
        System.out.printf("✓ Added %s slot: %s\n", slot.getSlotType(), slotId);
    }
    
    private void deleteParkingSlot() throws ParkingException {
        System.out.println("\n--- Delete Parking Slot ---");
        
        if (carPark.getTotalSlots() == 0) {
            System.out.println("No slots available.");
            return;
        }
        
        String slotId = getValidSlotId("Enter slot ID to delete: ");
        carPark.removeSlot(slotId);
        System.out.printf("✓ Deleted slot: %s\n", slotId);
    }
    
    private void listAllSlots() {
        System.out.println("\n--- All Parking Slots ---");
        
        List<ParkingSlot> slots = carPark.getAllSlots();
        
        if (slots.isEmpty()) {
            System.out.println("No slots available.");
            return;
        }
        
        System.out.printf("Total: %d | Occupied: %d | Available: %d\n\n",
                         carPark.getTotalSlots(),
                         carPark.getOccupiedCount(),
                         carPark.getAvailableCount());
        
        for (ParkingSlot slot : slots) {
            System.out.println("  " + slot.toString());
        }
    }
    
    private void deleteAllUnoccupiedSlots() {
        System.out.println("\n--- Delete All Unoccupied Slots ---");
        
        int removed = carPark.removeAllUnoccupiedSlots();
        System.out.printf("✓ Removed %d unoccupied slot(s)\n", removed);
    }
    
    private void parkCar() throws ParkingException {
        System.out.println("\n--- Park a Car ---");
        
        if (carPark.getTotalSlots() == 0) {
            System.out.println("No slots available.");
            return;
        }
        
        String slotId = getValidSlotId("Enter slot ID: ");
        String registration = getValidRegistration("Enter registration (e.g., T1234): ");
        
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine().trim();
        if (ownerName.isEmpty()) {
            System.out.println("Owner name cannot be empty.");
            return;
        }
        
        boolean isStaff = getYesNoInput("Is owner a staff member? (y/n): ");
        
        Car car = new Car(registration, ownerName, isStaff);
        carPark.parkCar(slotId, car);
        
        System.out.printf("✓ Parked %s in slot %s\n", registration, slotId);
        System.out.printf("  Parking time: %s\n", car.getFormattedParkingTime());
    }
    
    private void findCar() {
        System.out.println("\n--- Find a Car ---");
        
        String registration = getValidRegistration("Enter registration to find: ");
        ParkingSlot slot = carPark.findCarByRegistration(registration);
        
        if (slot == null) {
            System.out.printf("Car %s not found.\n", registration);
        } else {
            Car car = slot.getParkedCar();
            System.out.printf("✓ Found in slot %s\n", slot.getSlotId());
            System.out.printf("  Owner: %s (%s)\n", car.getOwnerName(), car.isStaff() ? "Staff" : "Visitor");
            System.out.printf("  Parked at: %s\n", car.getFormattedParkingTime());
            System.out.printf("  Duration: %s\n", car.getParkingDuration());
            System.out.printf("  Current fee: $%.2f\n", slot.calculateParkingFee());
        }
    }
    
    private void removeCar() throws ParkingException {
        System.out.println("\n--- Remove a Car ---");
        
        String registration = getValidRegistration("Enter registration to remove: ");
        
        ParkingSlot slot = carPark.findCarByRegistration(registration);
        double fee = slot != null ? slot.calculateParkingFee() : 0;
        
        Car removed = carPark.removeCar(registration);
        
        System.out.printf("✓ Removed %s (Owner: %s)\n", registration, removed.getOwnerName());
        System.out.printf("  Total fee charged: $%.2f\n", fee);
    }
    
    private void saveData() {
        System.out.println("\n--- Save Data ---");
        try {
            persistence.save(carPark);
            System.out.println("✓ Data saved to parking_data.json");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    private String getValidSlotId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (ValidationUtils.isValidSlotId(input)) return input;
            System.out.println("Invalid format. Use: Letter + 2 digits (e.g., D01)");
        }
    }
    
    private String getValidRegistration(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (ValidationUtils.isValidRegistration(input)) return input;
            System.out.println("Invalid format. Use: Letter + 4 digits (e.g., T1234)");
        }
    }
    
    private int getPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                if (num > 0) return num;
                System.out.println("Enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }
    
    private boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.print("Enter 'y' or 'n': ");
        }
    }
}
