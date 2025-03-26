package GYM_MANAGEMENT_SYSTEM.src;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutManager {
    private ArrayList<Workout> workouts;
    private Scanner scanner;
    private MemberManager memberManager;

    public WorkoutManager(MemberManager memberManager) {
        workouts = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.memberManager = memberManager;
    }

    public void recordWorkout() {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (memberManager.findMember(memberId) == null) {
            System.out.println("Member not found!");
            return;
        }

        System.out.print("Enter exercise name: ");
        String exercise = scanner.nextLine();
        
        System.out.print("Enter duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String date = java.time.LocalDate.now().toString();
        Workout workout = new Workout(memberId, date, exercise, duration);
        workouts.add(workout);
        System.out.println("Workout recorded successfully!");
    }

    public void displayMemberWorkouts(int memberId) {
        System.out.println("\nRecent Workouts:");
        for (Workout workout : workouts) {
            if (workout.getMemberId() == memberId) {
                System.out.println("Date: " + workout.getDate() + 
                                 ", Exercise: " + workout.getExercise() + 
                                 ", Duration: " + workout.getDuration() + " minutes");
            }
        }
    }
} 