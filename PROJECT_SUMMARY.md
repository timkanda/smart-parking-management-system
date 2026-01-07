# Smart Parking Management System

## Project Summary

### Overview
A Java-based parking lot management application demonstrating advanced Object-Oriented Programming principles. Originally developed as a university project, now enhanced to industry-standard quality for portfolio showcase.

---

### Core Functionality
- Add/delete parking slots (Staff and Visitor types)
- Park and remove vehicles with validation
- Search cars by registration number or slot ID
- Calculate parking duration and fees
- Persist data to JSON files

---

### Technical Highlights

| Category | Implementation |
|----------|----------------|
| Interfaces | IParkable, ISearchable, IFeeCalculator |
| Inheritance | ParkingSlot → StaffSlot, VisitorSlot |
| Design Patterns | Singleton, Factory, Strategy |
| Custom Exceptions | 8 domain-specific exceptions with error codes |
| Data Structures | HashMap for O(1) lookups |
| Testing | JUnit 5 test suite (40+ test cases) |
| Persistence | JSON file storage using Gson |
| Build Tool | Maven |

---

### Project Structure

```
src/main/java/com/parkingsystem/
├── interfaces/      # Contract definitions
├── models/          # Car, ParkingSlot, StaffSlot, VisitorSlot
├── services/        # CarPark (Singleton), FeeCalculator
├── factory/         # ParkingSlotFactory
├── exceptions/      # Custom exception hierarchy
├── persistence/     # JSON data storage
└── utils/           # Validation utilities
```

---

### Key OOP Concepts Demonstrated

**1. Encapsulation**
- Private fields with controlled access via getters/setters
- Data integrity maintained through validation

**2. Abstraction**
- Abstract ParkingSlot base class
- Interfaces define contracts without implementation details

**3. Inheritance**
- StaffSlot and VisitorSlot extend ParkingSlot
- Custom exceptions extend ParkingException base class

**4. Polymorphism**
- Different slot types behave differently (fee rates, validation)
- Strategy pattern allows swappable fee calculation algorithms

---

### Design Patterns Used

**Singleton Pattern**
- CarPark class ensures single instance across application
- Thread-safe implementation

**Factory Pattern**
- ParkingSlotFactory creates appropriate slot types
- Centralizes object creation logic

**Strategy Pattern**
- IFeeCalculator interface with multiple implementations
- StandardFeeCalculator, WeekendFeeCalculator, HolidayFeeCalculator

---

### Technologies Used
- Java 11+
- Maven (build automation)
- JUnit 5 (testing framework)
- Gson (JSON serialization)

---

### Author
**Timothy Kanda**
- GitHub: https://github.com/timkanda
- LinkedIn: https://www.linkedin.com/in/timothy-kanda-aaa190b4/

---

### Repository
https://github.com/timkanda/smart-parking-management-system

---

### License
MIT License
