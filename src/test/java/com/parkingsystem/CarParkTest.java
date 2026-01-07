package com.parkingsystem;

import com.parkingsystem.services.CarPark;
import com.parkingsystem.models.*;
import com.parkingsystem.factory.ParkingSlotFactory;
import com.parkingsystem.exceptions.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for CarPark service.
 * Tests Singleton pattern and business logic.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class CarParkTest {
    
    private CarPark carPark;
    
    @BeforeEach
    void setUp() {
        CarPark.resetInstance();
        carPark = CarPark.getInstance();
    }
    
    @AfterEach
    void tearDown() {
        carPark.clear();
    }
    
    @Test
    @DisplayName("CarPark should be a singleton")
    void testSingleton() {
        CarPark another = CarPark.getInstance();
        assertSame(carPark, another);
    }
    
    @Test
    @DisplayName("Should add slots successfully")
    void testAddSlot() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createStaffSlot("S01");
        carPark.addSlot(slot);
        
        assertEquals(1, carPark.getTotalSlots());
        assertNotNull(carPark.findSlotById("S01"));
    }
    
    @Test
    @DisplayName("Should throw DuplicateSlotException for duplicate ID")
    void testAddDuplicateSlot() throws Exception {
        ParkingSlot slot1 = ParkingSlotFactory.createStaffSlot("S01");
        ParkingSlot slot2 = ParkingSlotFactory.createStaffSlot("S01");
        
        carPark.addSlot(slot1);
        
        assertThrows(DuplicateSlotException.class, () -> {
            carPark.addSlot(slot2);
        });
    }
    
    @Test
    @DisplayName("Should remove unoccupied slot")
    void testRemoveSlot() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createStaffSlot("S01");
        carPark.addSlot(slot);
        
        carPark.removeSlot("S01");
        assertEquals(0, carPark.getTotalSlots());
    }
    
    @Test
    @DisplayName("Should throw SlotNotFoundException for non-existent slot")
    void testRemoveNonExistentSlot() {
        assertThrows(SlotNotFoundException.class, () -> {
            carPark.removeSlot("X99");
        });
    }
    
    @Test
    @DisplayName("Should throw SlotOccupiedException when removing occupied slot")
    void testRemoveOccupiedSlot() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createStaffSlot("S01");
        carPark.addSlot(slot);
        
        Car car = new Car("A1234", "John", true);
        carPark.parkCar("S01", car);
        
        assertThrows(SlotOccupiedException.class, () -> {
            carPark.removeSlot("S01");
        });
    }
    
    @Test
    @DisplayName("Should park car successfully")
    void testParkCar() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createStaffSlot("S01");
        carPark.addSlot(slot);
        
        Car car = new Car("A1234", "John", true);
        carPark.parkCar("S01", car);
        
        assertEquals(1, carPark.getOccupiedCount());
        assertNotNull(carPark.findCarByRegistration("A1234"));
    }
    
    @Test
    @DisplayName("Should throw DuplicateCarException for already parked car")
    void testParkDuplicateCar() throws Exception {
        ParkingSlot slot1 = ParkingSlotFactory.createStaffSlot("S01");
        ParkingSlot slot2 = ParkingSlotFactory.createStaffSlot("S02");
        carPark.addSlot(slot1);
        carPark.addSlot(slot2);
        
        Car car = new Car("A1234", "John", true);
        carPark.parkCar("S01", car);
        
        Car sameCar = new Car("A1234", "John", true);
        assertThrows(DuplicateCarException.class, () -> {
            carPark.parkCar("S02", sameCar);
        });
    }
    
    @Test
    @DisplayName("Should remove car successfully")
    void testRemoveCar() throws Exception {
        ParkingSlot slot = ParkingSlotFactory.createStaffSlot("S01");
        carPark.addSlot(slot);
        
        Car car = new Car("A1234", "John", true);
        carPark.parkCar("S01", car);
        
        Car removed = carPark.removeCar("A1234");
        assertEquals(car, removed);
        assertEquals(0, carPark.getOccupiedCount());
    }
    
    @Test
    @DisplayName("Should throw CarNotFoundException for non-existent car")
    void testRemoveNonExistentCar() {
        assertThrows(CarNotFoundException.class, () -> {
            carPark.removeCar("X9999");
        });
    }
    
    @Test
    @DisplayName("Should find car by registration with O(1) lookup")
    void testFindCarByRegistration() throws Exception {
        // Add multiple slots and cars
        for (int i = 1; i <= 100; i++) {
            ParkingSlot slot = ParkingSlotFactory.createStaffSlot(String.format("S%02d", i));
            carPark.addSlot(slot);
            
            Car car = new Car(String.format("A%04d", i), "Owner" + i, true);
            carPark.parkCar(slot.getSlotId(), car);
        }
        
        // Should find quickly due to HashMap
        ParkingSlot found = carPark.findCarByRegistration("A0050");
        assertNotNull(found);
        assertEquals("A0050", found.getParkedCar().getRegistrationNumber());
    }
    
    @Test
    @DisplayName("Should remove all unoccupied slots")
    void testRemoveAllUnoccupiedSlots() throws Exception {
        for (int i = 1; i <= 5; i++) {
            ParkingSlot slot = ParkingSlotFactory.createStaffSlot(String.format("S%02d", i));
            carPark.addSlot(slot);
        }
        
        // Park in slots 1 and 3
        carPark.parkCar("S01", new Car("A0001", "John", true));
        carPark.parkCar("S03", new Car("A0003", "Jane", true));
        
        int removed = carPark.removeAllUnoccupiedSlots();
        
        assertEquals(3, removed);
        assertEquals(2, carPark.getTotalSlots());
    }
    
    @Test
    @DisplayName("Should return correct available and occupied lists")
    void testGetAvailableAndOccupiedSlots() throws Exception {
        for (int i = 1; i <= 4; i++) {
            ParkingSlot slot = ParkingSlotFactory.createStaffSlot(String.format("S%02d", i));
            carPark.addSlot(slot);
        }
        
        carPark.parkCar("S01", new Car("A0001", "John", true));
        carPark.parkCar("S03", new Car("A0003", "Jane", true));
        
        List<ParkingSlot> available = carPark.getAvailableSlots();
        List<ParkingSlot> occupied = carPark.getOccupiedSlots();
        
        assertEquals(2, available.size());
        assertEquals(2, occupied.size());
    }
}
