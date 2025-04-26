package gym_management_system;

import java.util.ArrayList;
import java.util.List;

public class WorkoutManager {
    private List<Workout> workouts;
    private MemberManager memberManager;
    private int nextWorkoutId;

    public WorkoutManager(MemberManager memberManager) {
        this.memberManager = memberManager;
        this.workouts = DataStorage.loadWorkouts();
        this.nextWorkoutId = workouts.stream()
                                   .mapToInt(Workout::getId)
                                   .max()
                                   .orElse(0) + 1;
    }

    public void recordWorkout(int memberId, String exercise, int sets) {
        try {
            // Verify member exists
            memberManager.getMember(memberId);
            
            // Create and save workout
            Workout workout = new Workout(nextWorkoutId++, memberId, exercise, sets);
            workouts.add(workout);
            DataStorage.saveWorkouts(workouts);
        } catch (Exception e) {
            throw new RuntimeException("Failed to record workout: " + e.getMessage());
        }
    }

    public String getWorkoutHistory(int memberId) {
        StringBuilder history = new StringBuilder();
        workouts.stream()
                .filter(w -> w.getMemberId() == memberId)
                .forEach(w -> history.append(w.toString()).append("\n\n"));
        
        return history.length() > 0 ? history.toString() : "No workout history found.";
    }

    public List<Workout> getWorkouts() {
        return new ArrayList<>(workouts);
    }
} 