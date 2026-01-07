# Smart Parking Management System

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![JUnit5](https://img.shields.io/badge/JUnit-5.10-green.svg)](https://junit.org/junit5/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A professional **Object-Oriented Programming** implementation of a smart parking management system, demonstrating advanced OOP concepts, design patterns, and software engineering best practices.

## Features

- **Complete Parking Management**: Add/remove slots, park/remove cars, search functionality
- **Type-Safe Slots**: Staff and Visitor slots with appropriate rate structures
- **Fee Calculation**: Automatic parking duration tracking and fee computation
- **Data Persistence**: JSON-based save/load functionality
- **Comprehensive Validation**: Input validation with custom exceptions

## OOP Concepts Demonstrated

### Interfaces
- `IParkable` - Defines parking operations
- `ISearchable` - Defines search capabilities
- `IFeeCalculator` - Strategy pattern for fee calculation

### Inheritance
- `ParkingSlot` (abstract) → `StaffSlot`, `VisitorSlot`
- `ParkingException` → Multiple specific exceptions

### Design Patterns
- **Singleton**: `CarPark.getInstance()`
- **Factory**: `ParkingSlotFactory.createSlot()`
- **Strategy**: Multiple `IFeeCalculator` implementations

### Custom Exceptions
| Exception | Description |
|-----------|-------------|
| `SlotNotFoundException` | Slot ID doesn't exist |
| `DuplicateSlotException` | Slot ID already exists |
| `DuplicateCarException` | Car already parked |
| `CarNotFoundException` | Car not found |
| `SlotOccupiedException` | Cannot modify occupied slot |
| `SlotTypeMismatchException` | Car type doesn't match slot |
| `InvalidSlotIdException` | Invalid slot ID format |
| `InvalidRegistrationException` | Invalid registration format |

## Project Structure

```
src/
├── main/java/com/parkingsystem/
│   ├── interfaces/          # Interface definitions
│   │   ├── IParkable.java
│   │   ├── ISearchable.java
│   │   └── IFeeCalculator.java
│   ├── models/              # Domain models
│   │   ├── Car.java
│   │   ├── ParkingSlot.java (abstract)
│   │   ├── StaffSlot.java
│   │   └── VisitorSlot.java
│   ├── services/            # Business logic
│   │   ├── CarPark.java (Singleton)
│   │   └── FeeCalculatorService.java
│   ├── factory/             # Factory classes
│   │   └── ParkingSlotFactory.java
│   ├── exceptions/          # Custom exceptions
│   │   └── [8 exception classes]
│   ├── utils/               # Utilities
│   │   └── ValidationUtils.java
│   ├── persistence/         # Data persistence
│   │   └── DataPersistence.java
│   └── Application.java     # Main entry point
└── test/java/com/parkingsystem/
    ├── CarTest.java
    ├── ParkingSlotTest.java
    ├── CarParkTest.java
    ├── FactoryTest.java
    └── ValidationUtilsTest.java
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher

### Build & Run

```bash
# Clone the repository
git clone https://github.com/timkanda/smart-parking-management-system.git
cd smart-parking-management-system

# Build the project
mvn clean compile

# Run tests
mvn test

# Run the application
mvn exec:java -Dexec.mainClass="com.parkingsystem.Application"

# Package as JAR
mvn package
java -jar target/smart-parking-management-system-2.0.0.jar
```

## Usage

```
╔══════════════════════════════════════════════════════╗
║     SMART PARKING MANAGEMENT SYSTEM v2.0             ║
╚══════════════════════════════════════════════════════╝

--- Car Park Initialization ---
Enter number of staff slots: 3
Enter number of visitor slots: 2

✓ Initialized: 3 staff slots ($3/hr) + 2 visitor slots ($5/hr)

┌─────────────────────────────────────┐
│         MAIN MENU                   │
├─────────────────────────────────────┤
│  1. Add a parking slot              │
│  2. Delete a parking slot           │
│  3. List all slots                  │
│  4. Delete all unoccupied slots     │
│  5. Park a car                      │
│  6. Find a car                      │
│  7. Remove a car                    │
│  8. Save data                       │
│  9. Exit                            │
└─────────────────────────────────────┘
```

## Testing

The project includes comprehensive JUnit 5 tests:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CarParkTest

# Generate test report
mvn surefire-report:report
```

### Test Coverage
- **CarTest**: Car model functionality
- **ParkingSlotTest**: Inheritance and polymorphism
- **CarParkTest**: Singleton and business logic
- **FactoryTest**: Factory pattern
- **ValidationUtilsTest**: Input validation

## UML Class Diagram

```
┌─────────────────┐     ┌─────────────────┐
│   <<interface>> │     │   <<interface>> │
│    IParkable    │     │   ISearchable   │
└────────┬────────┘     └────────┬────────┘
         │                       │
         ▼                       ▼
┌─────────────────┐     ┌─────────────────┐
│  ParkingSlot    │◄────│    CarPark      │
│   (abstract)    │     │   (Singleton)   │
└────────┬────────┘     └─────────────────┘
         │
    ┌────┴────┐
    ▼         ▼
┌────────┐ ┌────────┐
│StaffSlot│ │VisitorSlot│
└────────┘ └────────┘
```

## Author

**Timothy Kanda**
- GitHub: [@timkanda](https://github.com/timkanda)
- LinkedIn: [Timothy Kanda](https://www.linkedin.com/in/timothy-kanda-aaa190b4/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

*Built as a portfolio project demonstrating OOP proficiency in Java.*
