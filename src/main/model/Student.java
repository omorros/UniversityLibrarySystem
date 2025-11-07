package main.model;

/** Represents a Student user (inherits from User). */
public class Student extends User {
    private String course;
    private int year;

    public Student(int userId, String name, String email, String course, int year) {
        super(userId, name, email);
        this.course = course;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student: " + name + " | Course: " + course + " | Year: " + year;
    }
}
