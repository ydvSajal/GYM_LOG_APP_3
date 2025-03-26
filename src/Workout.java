package GYM_MANAGEMENT_SYSTEM.src;

public class Workout {
    private int memberId;
    private String date;
    private String exercise;
    private int duration;

    public Workout(int memberId, String date, String exercise, int duration) {
        this.memberId = memberId;
        this.date = date;
        this.exercise = exercise;
        this.duration = duration;
    }

    // Getters
    public int getMemberId() { return memberId; }
    public String getDate() { return date; }
    public String getExercise() { return exercise; }
    public int getDuration() { return duration; }
} 