package main.model;

/**
 * Represents a student user within the library system.
 * <p>
 * The {@code Student} class extends {@link User} and introduces
 * additional fields for academic course and year of study. Student users
 * have moderate borrowing privileges — they can borrow up to five items,
 * keep them for 21 days, and renew once. This class demonstrates
 * inheritance, encapsulation, and method overriding.
 * </p>
 */
public class Student extends User {

    /** The name of the course the student is enrolled in. */
    private String course;

    /** The academic year of the student. */
    private int year;

    /**
     * Constructs a new {@code Student} object with specified attributes.
     *
     * @param userId unique identifier for the student
     * @param name   full name of the student
     * @param email  contact email of the student
     * @param course the course name the student is studying
     * @param year   the current year of study
     */
    public Student(int userId, String name, String email, String course, int year) {
        // Call the superclass constructor to initialise User fields.
        super(userId, name, email);
        this.course = course;
        this.year = year;
    }

    // -------------------------------------------
    // GETTERS
    // -------------------------------------------

    /**
     * Retrieves the course the student is enrolled in.
     *
     * @return the student's course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * Retrieves the academic year of the student.
     *
     * @return the current year of study
     */
    public int getYear() {
        return year;
    }

    // -------------------------------------------
    // BORROWING BEHAVIOUR
    // -------------------------------------------

    /**
     * Allows the student to borrow a {@link Product} under specific conditions.
     * <p>
     * Students have the following borrowing rules:
     * <ul>
     *   <li>May borrow up to five items at a time</li>
     *   <li>Each loan lasts for 21 days</li>
     *   <li>Can renew each item once</li>
     * </ul>
     * This method overrides {@link User#borrowProduct(Product, Policy)}
     * to enforce these student-specific constraints.
     * </p>
     *
     * @param product the product the student wishes to borrow
     * @param policy  the base {@link Policy} to reference fine details
     * @return {@code true} if the borrow was successful; {@code false} otherwise
     */
    @Override
    public boolean borrowProduct(Product product, Policy policy) {
        // Check borrowing limit (5 items maximum).
        if (loans.size() >= 5) {
            System.out.println("Borrowing limit reached (5 items max).");
            return false;
        }

        // Apply a custom student loan policy (21 days, 1 renewal).
        Policy studentPolicy = new Policy(21, 1, policy.getDailyFine());
        return super.borrowProduct(product, studentPolicy);
    }

    // -------------------------------------------
    // STRING REPRESENTATION
    // -------------------------------------------

    /**
     * Returns a formatted string representation of this student.
     *
     * @return formatted details including name, course, and year
     */
    @Override
    public String toString() {
        return "Student: " + name + " | Course: " + course + " | Year: " + year;
    }
}
