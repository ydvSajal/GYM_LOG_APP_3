package GYM_MANAGEMENT_SYSTEM.src;

import java.util.Scanner;

public class GymManagementSystem {
    private static MemberManager memberManager;
    private static WorkoutManager workoutManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        memberManager = new MemberManager();
        workoutManager = new WorkoutManager(memberManager);
        scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    memberManager.registerNewMember();
                    break;
                case 2:
                    workoutManager.recordWorkout();
                    break;
                case 3:
                    memberManager.displayMemberInfo();
                    break;
                case 4:
                    memberManager.displayAllMembers();
                    break;
                case 5:
                    System.out.println("Thank you for using Gym Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Gym Management System ===");
        System.out.println("1. Register New Member");
        System.out.println("2. Record Workout");
        System.out.println("3. Display Member Information");
        System.out.println("4. Display All Members");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
} 