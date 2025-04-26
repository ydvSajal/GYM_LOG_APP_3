package gym_management_system;

import java.util.ArrayList;
import java.util.List;

public class GymManagementSystem {
    private MemberManager memberManager;
    private WorkoutManager workoutManager;

    public GymManagementSystem() {
        this.memberManager = new MemberManager();
        this.workoutManager = new WorkoutManager(memberManager);
    }

    public void addMember(Member member) {
        memberManager.addMember(member);
    }

    public void removeMember(Member member) {
        memberManager.removeMember(member);
    }

    public List<Member> getAllMembers() {
        return memberManager.getAllMembers();
    }

    public void recordWorkout(int memberId, String exercise, int duration) {
        workoutManager.recordWorkout(memberId, exercise, duration);
    }

    public String getMemberWorkouts(int memberId) {
        return workoutManager.getWorkoutHistory(memberId);
    }

    public String getWorkoutHistory(int memberId) {
        return workoutManager.getWorkoutHistory(memberId);
    }

    public MemberManager getMemberManager() {
        return memberManager;
    }

    public WorkoutManager getWorkoutManager() {
        return workoutManager;
    }

    public static void main(String[] args) {
        GymManagementGUI.launch(GymManagementGUI.class, args);
    }
} 