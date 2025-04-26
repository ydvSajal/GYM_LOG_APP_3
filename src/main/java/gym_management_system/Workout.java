package gym_management_system;

import java.time.LocalDate;

public class Workout {
    private int id;
    private int memberId;
    private String exercise;
    private int sets;
    private String date;

    public Workout(int id, int memberId, String exercise, int sets) {
        this.id = id;
        this.memberId = memberId;
        this.exercise = exercise;
        this.sets = sets;
        this.date = LocalDate.now().toString();
    }

    public Workout(String exercise, int sets) {
        this(0, 0, exercise, sets);
    }

    public Workout(int memberId, String exercise, int sets) {
        this.memberId = memberId;
        this.exercise = exercise;
        this.sets = sets;
        this.date = LocalDate.now().toString();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Workout ID: %d\nExercise: %s\nSets: %d\nDate: %s",
                id, exercise, sets, date);
    }
} 