package main.model;

/**
 * Represents a DVD (Digital Versatile Disc) item in the library system.
 * <p>
 * This class extends {@link Product} and adds a specific attribute
 * for the director of the film. It demonstrates inheritance and
 * polymorphism by customising the {@link #getInfo()} method to
 * include DVD-specific information.
 * </p>
 */
public class DVD extends Product {

    /** The name of the director responsible for the film. */
    private String director;

    /**
     * Constructs a new {@code DVD} instance with the specified details.
     *
     * @param id        unique identifier for the DVD
     * @param title     title of the DVD or film
     * @param director  director of the film
     */
    public DVD(int id, String title, String director) {
        // Call the superclass constructor to initialise shared Product fields.
        super(id, title);
        this.director = director;
    }

    /**
     * Returns a formatted string describing this DVD.
     * <p>
     * Overrides {@link Product#getInfo()} to append the director’s name,
     * providing film-specific information. Demonstrates runtime
     * polymorphism and method overriding.
     * </p>
     *
     * @return a descriptive string combining generic and DVD-specific details
     */
    @Override
    public String getInfo() {
        // Extend the base Product information with the director field.
        return super.getInfo() + " | Director: " + director;
    }
}
