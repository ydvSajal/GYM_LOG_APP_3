package gym_management_system;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final String MEMBERS_FILE = "members.txt";
    private static final String WORKOUTS_FILE = "workouts.txt";

    public static void saveMembers(List<Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : members) {
                writer.println("Member ID: " + member.getId());
                writer.println("Name: " + member.getName());
                writer.println("Age: " + member.getAge());
                writer.println("Membership Type: " + member.getMembershipType());
                writer.println("Height: " + member.getHeight() + "m");
                writer.println("Weight: " + member.getWeight() + "kg");
                writer.println("BMI: " + member.getBMI() + " (" + member.getBMIStatus() + ")");
                writer.println("Join Date: " + member.getJoinDate());
                writer.println("Status: " + member.getStatus());
                writer.println("---"); // Separator between members
            }
        } catch (IOException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        File file = new File(MEMBERS_FILE);
        
        if (!file.exists()) {
            System.err.println("Error loading members: " + MEMBERS_FILE + " (The system cannot find the file specified)");
            return members;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            Member currentMember = null;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Member ID: ")) {
                    int id = Integer.parseInt(line.substring(11));
                    String name = reader.readLine().substring(6);
                    int age = Integer.parseInt(reader.readLine().substring(5));
                    String membershipType = reader.readLine().substring(16);
                    double height = Double.parseDouble(reader.readLine().substring(8).replace("m", ""));
                    double weight = Double.parseDouble(reader.readLine().substring(8).replace("kg", ""));
                    reader.readLine(); // Skip BMI line as it will be recalculated
                    String joinDate = reader.readLine().substring(11);
                    String status = reader.readLine().substring(8);
                    reader.readLine(); // Skip separator line
                    
                    currentMember = new Member(id, name, age, membershipType);
                    currentMember.setHeight(height);
                    currentMember.setWeight(weight);
                    currentMember.setJoinDate(joinDate);
                    currentMember.setStatus(status);
                    members.add(currentMember);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }
        return members;
    }

    public static void saveWorkouts(List<Workout> workouts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(WORKOUTS_FILE))) {
            for (Workout workout : workouts) {
                writer.println("Workout ID: " + workout.getId());
                writer.println("Member ID: " + workout.getMemberId());
                writer.println("Exercise: " + workout.getExercise());
                writer.println("Sets: " + workout.getSets());
                writer.println("Date: " + workout.getDate());
                writer.println("---"); // Separator between workouts
            }
        } catch (IOException e) {
            System.err.println("Error saving workouts: " + e.getMessage());
        }
    }

    public static List<Workout> loadWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        File file = new File(WORKOUTS_FILE);
        
        if (!file.exists()) {
            return workouts;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(WORKOUTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Workout ID: ")) {
                    int id = Integer.parseInt(line.substring(12));
                    int memberId = Integer.parseInt(reader.readLine().substring(11));
                    String exercise = reader.readLine().substring(10);
                    int sets = Integer.parseInt(reader.readLine().substring(6));
                    reader.readLine(); // Skip date line
                    reader.readLine(); // Skip separator line
                    
                    Workout workout = new Workout(id, memberId, exercise, sets);
                    workouts.add(workout);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading workouts: " + e.getMessage());
        }
        return workouts;
    }
} 