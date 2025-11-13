package main.model;

/**
 * Represents an Audiobook available in the library system.
 * <p>
 * This class extends {@link Product} and adds an additional field to store
 * the name of the narrator. It demonstrates inheritance and polymorphism by
 * providing its own implementation of the {@code getInfo()} method to
 * include audiobook details.
 * </p>
 */
public class Audiobook extends Product {

    /**
     * The name of the audiobook's narrator.
     * Demonstrates encapsulation through private access.
     */
    private String narrator;

    /**
     * Constructs a new {@code Audiobook} object with a unique identifier,
     * title, and narrator name.
     *
     * @param id        unique identifier for the audiobook
     * @param title     the title of the audiobook
     * @param narrator  the narrator of the audiobook
     */
    public Audiobook(int id, String title, String narrator) {
        // Call superclass constructor to initialize inherited Product attributes.
        super(id, title);
        this.narrator = narrator;
    }

    /**
     * Returns a formatted string containing audiobook information.
     * <p>
     * This method overrides the {@link Product#getInfo()} method and extends
     * it with audiobook specific data (narrator). This demonstrates runtime
     * polymorphism.
     * </p>
     *
     * @return a descriptive string containing product details and narrator
     */
    @Override
    public String getInfo() {
        // Append audiobook-specific information to the generic product details.
        return super.getInfo() + " | Narrator: " + narrator;
    }
}
