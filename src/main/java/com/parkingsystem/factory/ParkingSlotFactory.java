package com.parkingsystem.factory;

import com.parkingsystem.models.ParkingSlot;
import com.parkingsystem.models.StaffSlot;
import com.parkingsystem.models.VisitorSlot;
import com.parkingsystem.exceptions.InvalidSlotIdException;
import com.parkingsystem.utils.ValidationUtils;

/**
 * Factory class for creating ParkingSlot objects.
 * Demonstrates the Factory Design Pattern.
 * Encapsulates slot creation logic.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class ParkingSlotFactory {
    
    /**
     * Enum for slot types
     */
    public enum SlotType {
        STAFF, VISITOR
    }
    
    // Private constructor - static factory methods only
    private ParkingSlotFactory() {}
    
    /**
     * Creates a parking slot of the specified type
     * @param slotId The slot identifier
     * @param type The type of slot to create
     * @return The created ParkingSlot
     * @throws InvalidSlotIdException if slot ID format is invalid
     */
    public static ParkingSlot createSlot(String slotId, SlotType type) throws InvalidSlotIdException {
        ValidationUtils.validateSlotId(slotId);
        
        switch (type) {
            case STAFF:
                return new StaffSlot(slotId);
            case VISITOR:
                return new VisitorSlot(slotId);
            default:
                throw new IllegalArgumentException("Unknown slot type: " + type);
        }
    }
    
    /**
     * Creates a parking slot, inferring type from slot ID prefix
     * S = Staff, V = Visitor, others = based on isStaff parameter
     * @param slotId The slot identifier
     * @param isStaff Whether to create a staff slot (used if cannot infer from ID)
     * @return The created ParkingSlot
     * @throws InvalidSlotIdException if slot ID format is invalid
     */
    public static ParkingSlot createSlot(String slotId, boolean isStaff) throws InvalidSlotIdException {
        ValidationUtils.validateSlotId(slotId);
        
        char prefix = slotId.charAt(0);
        
        // Infer type from prefix if possible
        if (prefix == 'S') {
            return new StaffSlot(slotId);
        } else if (prefix == 'V') {
            return new VisitorSlot(slotId);
        }
        
        // Otherwise use the isStaff parameter
        return isStaff ? new StaffSlot(slotId) : new VisitorSlot(slotId);
    }
    
    /**
     * Creates a staff slot
     * @param slotId The slot identifier
     * @return The created StaffSlot
     * @throws InvalidSlotIdException if slot ID format is invalid
     */
    public static StaffSlot createStaffSlot(String slotId) throws InvalidSlotIdException {
        ValidationUtils.validateSlotId(slotId);
        return new StaffSlot(slotId);
    }
    
    /**
     * Creates a visitor slot
     * @param slotId The slot identifier
     * @return The created VisitorSlot
     * @throws InvalidSlotIdException if slot ID format is invalid
     */
    public static VisitorSlot createVisitorSlot(String slotId) throws InvalidSlotIdException {
        ValidationUtils.validateSlotId(slotId);
        return new VisitorSlot(slotId);
    }
}
