package main.model;

/**
 * Represents a student user with extended loan privileges.
 * Can borrow up to 5 items and has longer loan periods (21 days, 1 renewal).
 */
public class Student extends User {
    private String course;
    private int year;

    public Student(int userId, String name, String email, String course, int year) {
        super(userId, name, email);
        this.course = course;
        this.year = year;
    }

    public String getCourse() { return course; }
    public int getYear() { return year; }

    /**
     * Students have moderate borrowing rights: up to 5 items,
     * 21-day loan period, and 1 renewal allowed.
     */
    @Override
    public boolean borrowProduct(Product product, Policy policy) {
        if (loans.size() >= 5) {
            System.out.println("Borrowing limit reached (5 items max).");
            return false;
        }

        // Extended student policy
        Policy studentPolicy = new Policy(21, 1, policy.getDailyFine());
        return super.borrowProduct(product, studentPolicy);
    }

    @Override
    public String toString() {
        return "Student: " + name + " | Course: " + course + " | Year: " + year;
    }
}
