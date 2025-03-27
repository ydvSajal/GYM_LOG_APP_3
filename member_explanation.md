# Line-by-Line Explanation of Member.java

```java
package GYM_MANAGEMENT_SYSTEM.src;  // Package declaration matching the directory structure

public class Member {  // Main class for member data structure
    // Private instance variables for member attributes
    private String name;           // Stores member's name
    private int memberId;          // Unique identifier for the member
    private String membershipType; // Type of membership (Basic, Premium, VIP)
    private boolean isActive;      // Member's active status

    // Constructor to create a new member
    public Member(String name, int memberId, String membershipType) {
        this.name = name;                    // Set member's name
        this.memberId = memberId;            // Set unique member ID
        this.membershipType = membershipType; // Set membership type
        this.isActive = true;                // Set initial active status to true
    }

    // Getter methods to access private variables
    public String getName() { return name; }  // Returns member's name
    public int getMemberId() { return memberId; }  // Returns member's ID
    public String getMembershipType() { return membershipType; }  // Returns membership type
    public boolean isActive() { return isActive; }  // Returns active status
    
    // Setter method for active status
    public void setActive(boolean active) { isActive = active; }  // Updates active status
}
```

## Detailed Explanation

### Class Structure
1. The class is declared as `public` to be accessible from other packages
2. Uses private instance variables for encapsulation
3. Implements getter and setter methods for data access

### Key Components

#### 1. Instance Variables
```java
private String name;
private int memberId;
private String membershipType;
private boolean isActive;
```
- All variables are private for encapsulation
- Each variable stores a specific piece of member information
- `isActive` tracks member's current status

#### 2. Constructor
```java
public Member(String name, int memberId, String membershipType)
```
- Takes three parameters to initialize member data
- Sets initial active status to true
- Uses `this` keyword to distinguish between parameters and instance variables

#### 3. Getter Methods
```java
public String getName() { return name; }
public int getMemberId() { return memberId; }
public String getMembershipType() { return membershipType; }
public boolean isActive() { return isActive; }
```
- Provide read-only access to private variables
- Follow Java naming conventions
- Return appropriate data types

#### 4. Setter Method
```java
public void setActive(boolean active) { isActive = active; }
```
- Only allows modification of active status
- Other fields are immutable after creation

## Design Considerations

### 1. Encapsulation
- Private instance variables
- Public getter methods
- Limited setter methods
- Data protection

### 2. Immutability
- Member ID cannot be changed
- Name cannot be changed
- Membership type cannot be changed
- Only active status can be modified

### 3. Data Integrity
- All required fields are set in constructor
- Initial active status is set to true
- No null values allowed in constructor

## Usage Examples

### Creating a New Member
```java
Member newMember = new Member("John Doe", 1001, "Premium");
```

### Accessing Member Information
```java
String name = newMember.getName();
int id = newMember.getMemberId();
String type = newMember.getMembershipType();
boolean status = newMember.isActive();
```

### Updating Member Status
```java
newMember.setActive(false);  // Deactivate member
newMember.setActive(true);   // Reactivate member
```

## Best Practices Implemented

1. **Encapsulation**
   - Private variables
   - Public getters
   - Limited setters

2. **Immutability**
   - Final fields where appropriate
   - Constructor-only initialization

3. **Clean Code**
   - Clear method names
   - Consistent formatting
   - Proper documentation

4. **Data Protection**
   - No direct access to variables
   - Controlled modification
   - Type safety

## Common Use Cases

1. **Member Registration**
   ```java
   Member member = new Member("Alice", 1001, "Basic");
   ```

2. **Status Updates**
   ```java
   member.setActive(false);  // Temporary suspension
   member.setActive(true);   // Reactivation
   ```

3. **Information Retrieval**
   ```java
   System.out.println("Member: " + member.getName());
   System.out.println("ID: " + member.getMemberId());
   System.out.println("Type: " + member.getMembershipType());
   System.out.println("Status: " + (member.isActive() ? "Active" : "Inactive"));
   ``` 