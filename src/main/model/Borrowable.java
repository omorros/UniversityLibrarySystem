package main.model;

/**
 * Defines the common behaviour required for any borrowable item
 * within the library system.
 * <p>
 * This interface establishes a consistent contract for all
 * {@link Product} types (e.g., {@link Book}, {@link CD},
 * {@link DVD}, {@link Audiobook}), ensuring that they
 * expose methods to check and update availability as well
 * as to return general product information.
 * </p>
 * <p>
 * By using an interface, the system promotes abstraction
 * and polymorphism — allowing different media types to be
 * treated uniformly when managing borrowing operations.
 * </p>
 */
public interface Borrowable {

    /**
     * Checks whether the item is currently available to be borrowed.
     *
     * @return {@code true} if the item is available, {@code false} if it is checked out
     */
    boolean isAvailable();

    /**
     * Updates the availability status of the item.
     *
     * @param available {@code true} to mark the item as available,
     *                  {@code false} to mark it as checked out
     */
    void setAvailable(boolean available);

    /**
     * Retrieves general descriptive information about the item.
     * <p>
     * Implementing classes typically return a formatted string
     * including title, ID, and current availability status.
     * </p>
     *
     * @return a string containing key details about the borrowable item
     */
    String getInfo();
}
