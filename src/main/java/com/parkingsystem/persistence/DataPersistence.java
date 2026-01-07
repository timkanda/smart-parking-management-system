package com.parkingsystem.persistence;

import com.parkingsystem.services.CarPark;
import com.parkingsystem.models.ParkingSlot;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Handles data persistence for the parking system.
 * Supports JSON format for human-readable storage.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class DataPersistence {
    
    private static final String DEFAULT_FILE = "parking_data.json";
    private final Gson gson;
    
    public DataPersistence() {
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    }
    
    /**
     * Saves the car park data to JSON file
     * @param carPark The car park to save
     * @param filename The file to save to
     * @throws IOException if save fails
     */
    public void saveToFile(CarPark carPark, String filename) throws IOException {
        List<ParkingSlot> slots = carPark.getAllSlots();
        
        List<Map<String, Object>> slotData = new ArrayList<>();
        
        for (ParkingSlot slot : slots) {
            Map<String, Object> data = new HashMap<>();
            data.put("slotId", slot.getSlotId());
            data.put("slotType", slot.getSlotType());
            data.put("isOccupied", slot.isOccupied());
            
            if (slot.isOccupied()) {
                Map<String, Object> carData = new HashMap<>();
                carData.put("registrationNumber", slot.getParkedCar().getRegistrationNumber());
                carData.put("ownerName", slot.getParkedCar().getOwnerName());
                carData.put("isStaff", slot.getParkedCar().isStaff());
                carData.put("parkingTime", slot.getParkedCar().getFormattedParkingTime());
                data.put("car", carData);
            }
            
            slotData.add(data);
        }
        
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("version", "2.0");
        saveData.put("savedAt", LocalDateTime.now().toString());
        saveData.put("totalSlots", carPark.getTotalSlots());
        saveData.put("occupiedSlots", carPark.getOccupiedCount());
        saveData.put("slots", slotData);
        
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(saveData, writer);
        }
    }
    
    /**
     * Saves to default file
     * @param carPark The car park to save
     * @throws IOException if save fails
     */
    public void save(CarPark carPark) throws IOException {
        saveToFile(carPark, DEFAULT_FILE);
    }
    
    /**
     * Loads car park data from JSON file
     * @param filename The file to load from
     * @return Map containing loaded data
     * @throws IOException if load fails
     */
    public Map<String, Object> loadFromFile(String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            return gson.fromJson(reader, type);
        }
    }
    
    /**
     * Checks if save file exists
     * @param filename The file to check
     * @return true if file exists
     */
    public boolean saveFileExists(String filename) {
        return new File(filename).exists();
    }
    
    /**
     * Custom adapter for LocalDateTime serialization
     */
    private static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }
        
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }
}
