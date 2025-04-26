# Gym Management System - File Connections

## Overview
This document explains how all the files in the Gym Management System are connected to each other and how they work together.

## System Architecture

### 1. Main Connection Flow
```
GymManagementSystem.java (Main Class)
    ↓
    ├── MemberManager.java (Handles Members)
    │   └── Member.java (Member Data Structure)
    │
    └── WorkoutManager.java (Handles Workouts)
        └── Workout.java (Workout Data Structure)
```

### 2. Detailed File Connections

#### A. GymManagementSystem.java
- **Role**: Main controller of the application
- **Connections**:
  - Creates and manages `MemberManager` instance
  - Creates and manages `WorkoutManager` instance
  - Handles user interface and menu system
  - Routes user actions to appropriate managers

**Code Example**:
```java
public class GymManagementSystem {
    private static MemberManager memberManager;
    private static WorkoutManager workoutManager;

    public static void main(String[] args) {
        memberManager = new MemberManager();
        workoutManager = new WorkoutManager(memberManager);
        
        // Menu handling
        switch (choice) {
            case 1: memberManager.registerNewMember(); break;
            case 2: workoutManager.recordWorkout(); break;
            // ... other cases
        }
    }
}
```

#### B. MemberManager.java
- **Role**: Manages all member-related operations
- **Connections**:
  - Uses `Member.java` class to create member objects
  - Maintains ArrayList of Member objects
  - Provides member search functionality
  - Connected to `WorkoutManager` for member validation

**Code Example**:
```java
public class MemberManager {
    private ArrayList<Member> members;
    
    public void registerNewMember() {
        // Create new member
        Member newMember = new Member(name, nextMemberId++, membershipType);
        members.add(newMember);
    }
    
    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }
}
```

#### C. WorkoutManager.java
- **Role**: Manages all workout-related operations
- **Connections**:
  - Uses `Workout.java` class to create workout objects
  - Maintains ArrayList of Workout objects
  - Connected to `MemberManager` for member validation
  - Handles workout recording and display

**Code Example**:
```java
public class WorkoutManager {
    private ArrayList<Workout> workouts;
    private MemberManager memberManager;

    public void recordWorkout() {
        // Validate member exists
        if (memberManager.findMember(memberId) == null) {
            System.out.println("Member not found!");
            return;
        }
        
        // Create and store workout
        Workout workout = new Workout(memberId, date, exercise, duration);
        workouts.add(workout);
    }
}
```

#### D. Member.java
- **Role**: Data structure for member information
- **Connections**:
  - Used by `MemberManager` to create member objects
  - Contains member attributes and getters/setters
  - Referenced by `WorkoutManager` through member IDs

**Code Example**:
```java
public class Member {
    private String name;
    private int memberId;
    private String membershipType;
    private boolean isActive;

    // Constructor and getters/setters
    public Member(String name, int memberId, String membershipType) {
        this.name = name;
        this.memberId = memberId;
        this.membershipType = membershipType;
        this.isActive = true;
    }
}
```

#### E. Workout.java
- **Role**: Data structure for workout information
- **Connections**:
  - Used by `WorkoutManager` to create workout objects
  - Contains workout attributes and getters
  - Links to members through memberId

**Code Example**:
```java
public class Workout {
    private int memberId;
    private String date;
    private String exercise;
    private int duration;

    // Constructor and getters
    public Workout(int memberId, String date, String exercise, int duration) {
        this.memberId = memberId;
        this.date = date;
        this.exercise = exercise;
        this.duration = duration;
    }
}
```

## Data Flow Examples

### 1. Member Registration Process
```
User Input → GymManagementSystem
    ↓
MemberManager
    ↓
Creates new Member object
    ↓
Stores in ArrayList<Member>
```

**Example Flow**:
1. User selects "Register New Member"
2. GymManagementSystem calls `memberManager.registerNewMember()`
3. MemberManager collects member details
4. Creates new Member object
5. Adds to members ArrayList

### 2. Workout Recording Process
```
User Input → GymManagementSystem
    ↓
WorkoutManager
    ↓
Validates member with MemberManager
    ↓
Creates new Workout object
    ↓
Stores in ArrayList<Workout>
```

**Example Flow**:
1. User selects "Record Workout"
2. GymManagementSystem calls `workoutManager.recordWorkout()`
3. WorkoutManager validates member ID with MemberManager
4. Creates new Workout object
5. Adds to workouts ArrayList

### 3. Information Display Process
```
User Request → GymManagementSystem
    ↓
MemberManager/WorkoutManager
    ↓
Retrieves data from respective ArrayLists
    ↓
Displays formatted information
```

**Example Flow**:
1. User selects "Display Member Information"
2. GymManagementSystem calls `memberManager.displayMemberInfo()`
3. MemberManager finds member in ArrayList
4. Displays formatted member information

## Data Storage Structure

### MemberManager
```java
private ArrayList<Member> members;
```

### WorkoutManager
```java
private ArrayList<Workout> workouts;
```

## Key Dependencies

1. **Member.java**
   - Independent data structure
   - No dependencies on other classes

2. **Workout.java**
   - Independent data structure
   - No dependencies on other classes

3. **MemberManager.java**
   - Depends on Member.java
   - Used by WorkoutManager.java

4. **WorkoutManager.java**
   - Depends on Workout.java
   - Depends on MemberManager.java

5. **GymManagementSystem.java**
   - Depends on MemberManager.java
   - Depends on WorkoutManager.java

## Design Principles

1. **Separation of Concerns**
   - Each class has a specific responsibility
   - Data structures are separate from their managers
   - Main class coordinates the overall flow

2. **Encapsulation**
   - Private fields with public getters/setters
   - Data structures protected within their managers

3. **Dependency Management**
   - Clear hierarchy of dependencies
   - Managers handle their own data structures
   - Main class coordinates between managers

## Usage Flow

1. **Start Application**
   - GymManagementSystem creates managers
   - Displays main menu

2. **User Operations**
   - Select menu option
   - System routes to appropriate manager
   - Manager performs operation
   - Results displayed to user

3. **Data Management**
   - All data stored in memory
   - Managers maintain their own collections
   - Cross-validation between managers when needed

## Example Usage Scenarios

### 1. Registering a New Member
```java
// In GymManagementSystem
case 1: 
    memberManager.registerNewMember();
    // Prompts for name and membership type
    // Creates new Member object
    // Stores in members ArrayList
    break;
```

### 2. Recording a Workout
```java
// In GymManagementSystem
case 2:
    workoutManager.recordWorkout();
    // Prompts for member ID
    // Validates member exists
    // Records workout details
    // Stores in workouts ArrayList
    break;
```

### 3. Displaying Member Information
```java
// In GymManagementSystem
case 3:
    memberManager.displayMemberInfo();
    // Prompts for member ID
    // Retrieves member from ArrayList
    // Displays formatted information
    break;
``` 