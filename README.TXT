------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Parking Spot Management System - GUI Version
PURPOSE OF PROJECT: COS70006 Object Oriented Programming Project 2
VERSION or DATE: August 2025
HOW TO START THIS PROJECT: 
    1. Open the project in BlueJ
    2. Right-click on the ParkingGUI class
    3. Select "void main(String[] args)" to run the GUI application
    
AUTHORS: [Your Name]
USER INSTRUCTIONS:

This is a GUI-based parking management system that provides a graphical interface for all parking operations from Project 1.

MAIN FEATURES:
- Visual representation of parking slots with color coding
- Interactive slot management (click on slots for actions)
- All 8 operations from Project 1 implemented with GUI
- Real-time parking duration and fee calculation
- User-friendly dialogs for all inputs

GUI COMPONENTS USED:
- JFrame: Main application window
- JPanel: Container panels for layout organization
- JButton: Operation buttons and interactive parking slots
- JTextField: Text input fields for car registration, owner names, etc.
- JTextArea: Information display area
- JScrollPane: Scrollable information panel
- JLabel: Text labels and status display
- JRadioButton: Selection for staff/visitor types
- ButtonGroup: Grouping radio buttons
- JDialog: Modal dialogs for user inputs
- JOptionPane: Message dialogs and confirmations

COLOR CODING:
- Light Blue: Empty staff parking slots
- Light Green: Empty visitor parking slots  
- Light Pink: Occupied parking slots
- Red Border: Currently selected slot

LAYOUT MANAGERS:
- BorderLayout: Main window layout
- GridLayout: Parking slots grid display
- FlowLayout: Button panels and controls
- GridBagLayout: Form layouts in dialogs

EVENT HANDLING:
- ActionListener: Button clicks and menu operations
- Slot click handling for interactive parking management
- Dialog form submissions and validations

OPERATIONS AVAILABLE:
1. Initialize car park with staff and visitor slots
2. Add new parking slots
3. Delete parking slots (only if empty)
4. List all slots with detailed information
5. Delete all empty slots
6. Park cars in selected slots
7. Find cars by registration number
8. Remove cars from parking system

ADVANCED FEATURES:
- Current time recording when cars are parked
- Real-time parking duration calculation
- Parking fee calculation ($5/hour, minimum 1 hour)
- Interactive slot management (right-click context menus)
- Visual feedback and status updates

To test the system:
1. Run ParkingGUI.main()
2. Initialize the car park with desired slot numbers
3. Use buttons or click on slots to perform operations
4. View real-time information in the side panel
