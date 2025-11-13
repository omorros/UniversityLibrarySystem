package main.model;

/**
 * Abstract base class representing a general product in the library system.
 * <p>
 * The {@code Product} class provides shared attributes and behaviour
 * for all library items such as {@link Book}, {@link CD}, {@link DVD},
 * and {@link Audiobook}. It implements the {@link Borrowable} interface
 * to define consistent methods for checking and updating availability.
 * </p>
 * <p>
 * This class demonstrates several core OOP principles:
 * <ul>
 *   <li><b>Abstraction:</b> Provides a generic blueprint for all media types.</li>
 *   <li><b>Encapsulation:</b> Uses private/protected fields with public accessors.</li>
 *   <li><b>Inheritance:</b> Allows subclasses to reuse and extend shared logic.</li>
 *   <li><b>Polymorphism:</b> Enables overriding of {@link #getInfo()} to display item-specific data.</li>
 * </ul>
 * </p>
 */
public abstract class Product implements Borrowable {

    /** Unique identifier for the product. */
    protected int productId;

    /** The title of the product (e.g., book title, album name, film name). */
    protected String title;

    /** Indicates whether the product is currently available for borrowing. */
    protected boolean available;

    /**
     * Constructs a new {@code Product} with a unique ID and title.
     * <p>
     * All new products are initially marked as available by default.
     * </p>
     *
     * @param productId unique identifier for the product
     * @param title     descriptive title of the product
     */
    public Product(int productId, String title) {
        this.productId = productId;
        this.title = title;
        this.available = true;
    }

    // -----------------------------------
    // PRODUCT-SPECIFIC METHODS
    // -----------------------------------

    /**
     * Retrieves the product's unique identifier.
     *
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Retrieves the product title.
     *
     * @return the title of the product
     */
    public String getTitle() {
        return title;
    }

    // -----------------------------------
    // BORROWABLE INTERFACE IMPLEMENTATION
    // -----------------------------------

    /**
     * Checks if this product is currently available for borrowing.
     *
     * @return {@code true} if available; {@code false} if checked out
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * Updates the availability status of this product.
     *
     * @param available {@code true} to mark as available,
     *                  {@code false} to mark as checked out
     */
    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns formatted information about this product.
     * <p>
     * The string includes the product’s ID, title, and current availability
     * status. This method can be overridden in subclasses to add
     * type-specific details (e.g., author, director, composer).
     * </p>
     *
     * @return a formatted product information string
     */
    @Override
    public String getInfo() {
        String status = available ? "Available" : "Checked Out";
        return "ID: " + productId + " | Title: " + title + " | Status: " + status;
    }

    /**
     * Returns a string representation of this product.
     * <p>
     * Delegates to {@link #getInfo()} for readability and uniformity.
     * </p>
     *
     * @return a descriptive string representation of the product
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
