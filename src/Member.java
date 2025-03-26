package GYM_MANAGEMENT_SYSTEM.src;

public class Member {
    private String name;
    private int memberId;
    private String membershipType;
    private boolean isActive;

    public Member(String name, int memberId, String membershipType) {
        this.name = name;
        this.memberId = memberId;
        this.membershipType = membershipType;
        this.isActive = true;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getMemberId() { return memberId; }
    public String getMembershipType() { return membershipType; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
} 