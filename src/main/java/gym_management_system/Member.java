package gym_management_system;

import java.time.LocalDate;

public class Member {
    private int id;
    private String name;
    private int age;
    private String membershipType;
    private String joinDate;
    private String status;
    private double height; // in meters
    private double weight; // in kg
    private double bmi;

    public Member(int id, String name, int age, String membershipType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
        this.joinDate = LocalDate.now().toString();
        this.status = "Active";
        this.height = 0.0;
        this.weight = 0.0;
        this.bmi = 0.0;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        calculateBMI();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calculateBMI();
    }

    private void calculateBMI() {
        if (height > 0 && weight > 0) {
            this.bmi = weight / (height * height);
        }
    }

    public double getBMI() {
        return bmi;
    }

    public String getBMIStatus() {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal weight";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    @Override
    public String toString() {
        return "Member ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Age: " + age + "\n" +
               "Membership Type: " + membershipType + "\n" +
               "Height: " + String.format("%.2f", height) + "m\n" +
               "Weight: " + String.format("%.2f", weight) + "kg\n" +
               "BMI: " + String.format("%.2f", bmi) + " (" + getBMIStatus() + ")\n" +
               "Join Date: " + joinDate + "\n" +
               "Status: " + status;
    }
} 