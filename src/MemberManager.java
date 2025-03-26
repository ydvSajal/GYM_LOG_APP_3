package GYM_MANAGEMENT_SYSTEM.src;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberManager {
    private ArrayList<Member> members;
    private int nextMemberId;
    private Scanner scanner;

    public MemberManager() {
        members = new ArrayList<>();
        nextMemberId = 1001;
        scanner = new Scanner(System.in);
    }

    public void registerNewMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        
        System.out.println("Select membership type:");
        System.out.println("1. Basic");
        System.out.println("2. Premium");
        System.out.println("3. VIP");
        System.out.print("Enter choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String membershipType;
        switch (typeChoice) {
            case 1: membershipType = "Basic"; break;
            case 2: membershipType = "Premium"; break;
            case 3: membershipType = "VIP"; break;
            default: membershipType = "Basic";
        }

        Member newMember = new Member(name, nextMemberId++, membershipType);
        members.add(newMember);
        System.out.println("Member registered successfully! Member ID: " + newMember.getMemberId());
    }

    public void displayMemberInfo() {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Member member = findMember(memberId);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }

        System.out.println("\n=== Member Information ===");
        System.out.println("Name: " + member.getName());
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Membership Type: " + member.getMembershipType());
        System.out.println("Status: " + (member.isActive() ? "Active" : "Inactive"));
    }

    public void displayAllMembers() {
        System.out.println("\n=== All Members ===");
        for (Member member : members) {
            System.out.println("ID: " + member.getMemberId() + 
                             ", Name: " + member.getName() + 
                             ", Type: " + member.getMembershipType() + 
                             ", Status: " + (member.isActive() ? "Active" : "Inactive"));
        }
    }

    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
} 