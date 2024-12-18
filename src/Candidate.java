import java.util.*;

public class Candidate {
    private String name;
    private int age;
    private String email;

    public Candidate(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayDetails() {
        System.out.println("Candidate Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }

    @Override
    public String toString() {
        return "Candidate: " + name + ", Age: " + age + ", Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Candidate candidate = (Candidate) obj;
        return age == candidate.age &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(email, candidate.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, email);
    }
}
