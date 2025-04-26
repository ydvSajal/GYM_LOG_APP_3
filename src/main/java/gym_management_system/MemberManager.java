package gym_management_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberManager {
    private List<Member> members;
    private int nextId;

    public MemberManager() {
        members = DataStorage.loadMembers();
        nextId = members.stream()
                       .mapToInt(Member::getId)
                       .max()
                       .orElse(0) + 1;
    }

    public void registerNewMember(String name, int age, String membershipType) {
        Member member = new Member(nextId++, name, age, membershipType);
        members.add(member);
        DataStorage.saveMembers(members);
    }

    public Member getMember(int id) {
        Optional<Member> member = members.stream()
                                       .filter(m -> m.getId() == id)
                                       .findFirst();
        return member.orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public String getAllMembersInfo() {
        StringBuilder sb = new StringBuilder();
        for (Member member : members) {
            sb.append(member.toString()).append("\n");
        }
        return sb.toString();
    }

    public String getMemberInfo(int id) {
        Member member = getMember(id);
        return member.toString();
    }

    public void updateMember(int memberId, String name, int age, String membershipType) {
        Member member = getMember(memberId);
        member.setName(name);
        member.setAge(age);
        member.setMembershipType(membershipType);
        DataStorage.saveMembers(members);
    }

    public void deactivateMember(int memberId) {
        Member member = getMember(memberId);
        member.setStatus("Inactive");
        DataStorage.saveMembers(members);
    }

    public void activateMember(int memberId) {
        Member member = getMember(memberId);
        member.setStatus("Active");
        DataStorage.saveMembers(members);
    }

    public void addMember(Member member) {
        members.add(member);
        DataStorage.saveMembers(members);
    }

    public void removeMember(Member member) {
        members.remove(member);
        DataStorage.saveMembers(members);
    }
} 