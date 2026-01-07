import java.util.Scanner;
import java.util.ArrayList;

/**
 * Console-based user interface for the Parking Spot System.
 * Handles all user interactions and menu operations.
 * 
 * @author Student
 * @version 1.0
 */
public class Application {
    private CarPark carPark;
    private Scanner scanner;
    
    /**
     * Constructor for Application
     */
    public Application() {
        this.carPark = new CarPark();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method - entry point of the program
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
    
    /**
     * Main program loop
     */
    public void run() {
        System.out.println("=== Welcome to Parking Spot Management System ===");
        
        // Initialize car park
        initializeCarPark();
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addParkingSlot();
                    break;
                case 2:
                    deleteParkingSlot();
                    break;
                case 3:
                    listAllSlots();
                    break;
                case 4:
                    deleteAllUnoccupiedSlots();
                    break;
                case 5:
                    parkCar();
                    break;
                case 6:
                    findCar();
                    break;
                case 7:
                    removeCar();
                    break;
                case 8:
                    System.out.println("Program end!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Initializes the car park by asking for staff and visitor slot numbers
     */
    private void initializeCarPark() {
        System.out.println("\n--- Car Park Initialization ---");
        
        int staffSlots = getPositiveInteger("Enter number of staff slots: ");
        int visitorSlots = getPositiveInteger("Enter number of visitor slots: ");
        
        // Create staff slots (starting with 'S')
        for (int i = 1; i <= staffSlots; i++) {
            String slotId = String.format("S%02d", i);
            ParkingSlot slot = new ParkingSlot(slotId, true);
            carPark.addSlot(slot);
        }
        
        // Create visitor slots (starting with 'V')
        for (int i = 1; i <= visitorSlots; i++) {
            String slotId = String.format("V%02d", i);
            ParkingSlot slot = new ParkingSlot(slotId, false);
            carPark.addSlot(slot);
        }
        
        System.out.printf("Car park initialized with %d staff slots and %d visitor slots.\n", 
                         staffSlots, visitorSlots);
    }
    
    /**
     * Displays the main menu
     */
    private void displayMenu() {
        System.out.println("\n=== Parking Management System Menu ===");
        System.out.println("1. Add a parking slot");
        System.out.println("2. Delete a parking slot by slot ID");
        System.out.println("3. List all slots");
        System.out.println("4. Delete all unoccupied parking slots");
        System.out.println("5. Park a car into a slot");
        System.out.println("6. Find a car by registration number");
        System.out.println("7. Remove a car by registration number");
        System.out.println("8. Exit");
        System.out.print("Please select an option (1-8): ");
    }
    
    /**
     * Gets and validates menu choice
     * @return Valid menu choice (1-8)
     */
    private int getMenuChoice() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 8) {
                    return choice;
                }
                System.out.print("Please enter a number between 1 and 8: ");
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    /**
     * Adds a new parking slot
     */
    private void addParkingSlot() {
        System.out.println("\n--- Add Parking Slot ---");
        
        String slotId = getValidSlotId("Enter slot ID (format: Letter + 2 digits, e.g., D01): ");
        
        System.out.print("Is this a staff slot? (y/n): ");
        boolean isStaffSlot = getYesNoInput();
        
        ParkingSlot newSlot = new ParkingSlot(slotId, isStaffSlot);
        
        if (carPark.addSlot(newSlot)) {
            System.out.printf("Successfully added %s slot %s.\n", 
                            isStaffSlot ? "staff" : "visitor", slotId);
        } else {
            System.out.printf("Error: Slot %s already exists.\n", slotId);
        }
    }
    
    /**
     * Deletes a parking slot by ID
     */
    private void deleteParkingSlot() {
        System.out.println("\n--- Delete Parking Slot ---");
        
        if (carPark.getTotalSlots() == 0) {
            System.out.println("No parking slots available to delete.");
            return;
        }
        
        String slotId = getValidSlotId("Enter slot ID to delete: ");
        
        ParkingSlot slot = carPark.findSlotById(slotId);
        if (slot == null) {
            System.out.printf("Error: Slot %s not found.\n", slotId);
            return;
        }
        
        if (slot.isOccupied()) {
            System.out.printf("Error: Cannot delete slot %s because it is occupied.\n", slotId);
            return;
        }
        
        if (carPark.removeSlot(slotId)) {
            System.out.printf("Successfully deleted slot %s.\n", slotId);
        } else {
            System.out.printf("Error: Failed to delete slot %s.\n", slotId);
        }
    }
    
    /**
     * Lists all parking slots
     */
    private void listAllSlots() {
        System.out.println("\n--- All Parking Slots ---");
        
        ArrayList<ParkingSlot> slots = carPark.getAllSlots();
        
        if (slots.isEmpty()) {
            System.out.println("No parking slots available.");
            return;
        }
        
        System.out.printf("Total slots: %d | Occupied: %d | Available: %d\n\n", 
                         carPark.getTotalSlots(), 
                         carPark.getOccupiedSlots(),
                         carPark.getTotalSlots() - carPark.getOccupiedSlots());
        
        for (ParkingSlot slot : slots) {
            System.out.println(slot.toString());
        }
    }
    
    /**
     * Deletes all unoccupied parking slots
     */
    private void deleteAllUnoccupiedSlots() {
        System.out.println("\n--- Delete All Unoccupied Slots ---");
        
        int removedCount = carPark.removeAllUnoccupiedSlots();
        
        if (removedCount > 0) {
            System.out.printf("Successfully removed %d unoccupied slot(s).\n", removedCount);
        } else {
            System.out.println("No unoccupied slots to remove.");
        }
    }
    
    /**
     * Parks a car in a specified slot
     */
    private void parkCar() {
        System.out.println("\n--- Park a Car ---");
        
        if (carPark.getTotalSlots() == 0) {
            System.out.println("No parking slots available.");
            return;
        }
        
        String slotId = getValidSlotId("Enter slot ID: ");
        
        ParkingSlot slot = carPark.findSlotById(slotId);
        if (slot == null) {
            System.out.printf("Error: Slot %s not found.\n", slotId);
            return;
        }
        
        if (slot.isOccupied()) {
            System.out.printf("Error: Slot %s is already occupied.\n", slotId);
            return;
        }
        
        // Get car information
        String registration = getValidCarRegistration("Enter car registration (format: Letter + 4 digits, e.g., T2345): ");
        
        // Check if car is already parked
        if (carPark.findCarByRegistration(registration) != null) {
            System.out.printf("Error: Car %s is already parked in the system.\n", registration);
            return;
        }
        
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine().trim();
        
        if (ownerName.isEmpty()) {
            System.out.println("Error: Owner name cannot be empty.");
            return;
        }
        
        System.out.print("Is the owner a staff member? (y/n): ");
        boolean isStaff = getYesNoInput();
        
        // Check if car type matches slot type
        if (slot.isStaffSlot() && !isStaff) {
            System.out.println("Error: Visitor cars cannot be parked in staff slots.");
            return;
        }
        
        if (!slot.isStaffSlot() && isStaff) {
            System.out.println("Error: Staff cars cannot be parked in visitor slots.");
            return;
        }
        
        Car car = new Car(registration, ownerName, isStaff);
        
        if (carPark.parkCar(slotId, car)) {
            System.out.printf("Successfully parked car %s in slot %s.\n", registration, slotId);
            System.out.printf("Parking time: %s\n", car.getFormattedParkingTime());
        } else {
            System.out.println("Error: Failed to park the car.");
        }
    }
    
    /**
     * Finds a car by registration number
     */
    private void findCar() {
        System.out.println("\n--- Find a Car ---");
        
        String registration = getValidCarRegistration("Enter car registration to find: ");
        
        ParkingSlot slot = carPark.findCarByRegistration(registration);
        
        if (slot == null) {
            System.out.printf("Car %s is not found in the parking system.\n", registration);
        } else {
            Car car = slot.getParkedCar();
            System.out.printf("Car %s found in slot %s\n", registration, slot.getSlotId());
            System.out.printf("Owner: %s (%s)\n", car.getOwnerName(), car.isStaff() ? "Staff" : "Visitor");
            System.out.printf("Parking time: %s\n", car.getFormattedParkingTime());
            System.out.printf("Duration: %s\n", car.getParkingDuration());
            System.out.printf("Current fee: $%.2f\n", car.getParkingFee());
        }
    }
    
    /**
     * Removes a car by registration number
     */
    private void removeCar() {
        System.out.println("\n--- Remove a Car ---");
        
        String registration = getValidCarRegistration("Enter car registration to remove: ");
        
        Car removedCar = carPark.removeCarByRegistration(registration);
        
        if (removedCar == null) {
            System.out.printf("Car %s is not found in the parking system.\n", registration);
        } else {
            System.out.printf("Successfully removed car %s (Owner: %s).\n", 
                            registration, removedCar.getOwnerName());
            
            // Show final parking details
            if (removedCar.getParkingTime() != null) {
                System.out.printf("Final parking duration: %s\n", removedCar.getParkingDuration());
                System.out.printf("Total parking fee: $%.2f\n", removedCar.getParkingFee());
            }
        }
    }
    
    /**
     * Gets a valid slot ID from user input
     * @param prompt The prompt message
     * @return Valid slot ID
     */
    private String getValidSlotId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (isValidSlotId(input)) {
                return input;
            }
            
            System.out.println("Invalid format. Slot ID must be an uppercase letter followed by 2 digits (e.g., D01).");
        }
    }
    
    /**
     * Gets a valid car registration from user input
     * @param prompt The prompt message
     * @return Valid car registration
     */
    private String getValidCarRegistration(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (isValidCarRegistration(input)) {
                return input;
            }
            
            System.out.println("Invalid format. Registration must be an uppercase letter followed by 4 digits (e.g., T2345).");
        }
    }
    
    /**
     * Gets a positive integer from user input
     * @param prompt The prompt message
     * @return Positive integer
     */
    private int getPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Gets yes/no input from user
     * @return true for yes, false for no
     */
    private boolean getYesNoInput() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.print("Please enter 'y' for yes or 'n' for no: ");
        }
    }
    
    /**
     * Validates slot ID format
     * @param slotId The slot ID to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidSlotId(String slotId) {
        if (slotId == null || slotId.length() != 3) {
            return false;
        }
        
        char firstChar = slotId.charAt(0);
        if (!Character.isUpperCase(firstChar) || !Character.isLetter(firstChar)) {
            return false;
        }
        
        String digits = slotId.substring(1);
        try {
            Integer.parseInt(digits);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validates car registration format
     * @param registration The registration to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidCarRegistration(String registration) {
        if (registration == null || registration.length() != 5) {
            return false;
        }
        
        char firstChar = registration.charAt(0);
        if (!Character.isUpperCase(firstChar) || !Character.isLetter(firstChar)) {
            return false;
        }
        
        String digits = registration.substring(1);
        try {
            Integer.parseInt(digits);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
