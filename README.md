# Gym Management System

A simple terminal-based Gym Management System developed in Java for managing gym members and their workouts.

## Features

- Member Registration and Management
- Workout Tracking
- Member Information Display
- Membership Status Tracking
- Multiple Membership Types (Basic, Premium, VIP)

## Project Structure

The project is divided into 5 main Java files:

1. `src/GymManagementSystem.java` - Main class that coordinates the system
2. `src/Member.java` - Member data structure
3. `src/Workout.java` - Workout data structure
4. `src/MemberManager.java` - Handles member-related operations
5. `src/WorkoutManager.java` - Handles workout-related operations

## File Structure

```
GYM_MANAGEMENT_SYSTEM/
├── src/                    # Source code directory
│   ├── GymManagementSystem.java    # Main application class
│   ├── Member.java                 # Member data model
│   ├── Workout.java                # Workout data model
│   ├── MemberManager.java          # Member management logic
│   └── WorkoutManager.java         # Workout management logic
├── README.md               # Project documentation
├── LICENSE                 # MIT License
└── .gitignore             # Git ignore rules
```

## Workflow Structure

### 1. Member Registration Flow
```
User Input → MemberManager → Member Class → Data Storage
```

### 2. Workout Recording Flow
```
User Input → WorkoutManager → MemberManager (validation) → Workout Class → Data Storage
```

### 3. Information Display Flow
```
User Request → MemberManager/WorkoutManager → Data Retrieval → Display Information
```

## How to Use

1. Compile all Java files:
```bash
javac src/*.java
```

2. Run the program:
```bash
java -cp src GYM_MANAGEMENT_SYSTEM.GymManagementSystem
```

3. Use the menu system by entering numbers 1-5:
   - 1: Register New Member
   - 2: Record Workout
   - 3: Display Member Information
   - 4: Display All Members
   - 5: Exit

## Team Members

1. Sajal - `Member.java`
   - Responsible for member data structure
   - Handles member attributes and getters/setters

2. Devansh - `Workout.java`
   - Responsible for workout data structure
   - Handles workout attributes and getters

3. Kunal - `MemberManager.java`
   - Handles member registration
   - Manages member information display
   - Member search functionality

4. Aksh - `WorkoutManager.java`
   - Handles workout recording
   - Manages workout history display
   - Workout tracking functionality

## Requirements

- Java Development Kit (JDK) 8 or higher
- Basic understanding of Java programming

## Note

This is a simple terminal-based application designed for educational purposes. All data is stored in memory and will be lost when the program is closed.

## Copyright

© 2024 Sajal Kumar. All rights reserved.

Contact: sajalkumar1765@gmail.com 