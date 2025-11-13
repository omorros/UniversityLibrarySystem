package main.model;

/**
 * Represents a compact disc (CD) in the library system.
 * <p>
 * This class extends {@link Product} and adds a field for the
 * composer's name. It demonstrates inheritance and polymorphism
 * by specialising the generic {@code Product} behaviour to include
 * music-specific details such as the composer.
 * </p>
 */
public class CD extends Product {

    /** The composer or primary artist associated with this CD. */
    private String composer;

    /**
     * Constructs a new {@code CD} object with a unique identifier,
     * title, and composer information.
     *
     * @param id        unique product identifier
     * @param title     title of the CD
     * @param composer  name of the composer or performer
     */
    public CD(int id, String title, String composer) {
        // Initialise inherited fields using the Product constructor.
        super(id, title);
        this.composer = composer;
    }

    /**
     * Returns formatted information describing this CD.
     * <p>
     * Overrides {@link Product#getInfo()} to append composer details.
     * This demonstrates runtime polymorphism, as each subclass of
     * {@link Product} provides its own version of {@code getInfo()}.
     * </p>
     *
     * @return a descriptive string combining product and CD-specific details
     */
    @Override
    public String getInfo() {
        // Extend the base Product information with CD-specific data.
        return super.getInfo() + " | Composer: " + composer;
    }
}
