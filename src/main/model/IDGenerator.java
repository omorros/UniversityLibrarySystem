package main.model;

/**
 * Generates unique, sequential identifiers for entities within the system.
 * <p>
 * This class implements a simplified version of the <b>Singleton Design Pattern</b>.
 * It maintains a single, globally accessible counter that provides
 * unique integer IDs for objects such as {@link User}, {@link Product},
 * and {@link Loan}. By using a static field and method, the application
 * ensures that ID values remain consistent and non-duplicated across
 * all components.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     int newId = IDGenerator.nextId();
 * </pre>
 * </p>
 */
public class IDGenerator {

    /** Shared static counter used to generate incremental unique IDs. */
    private static int counter = 1;

    /**
     * Returns the next unique identifier and increments the counter.
     * <p>
     * This method provides thread-safe behaviour in single-threaded
     * applications and ensures each call returns a new, unique integer.
     * </p>
     *
     * @return a unique integer identifier for a new entity
     */
    public static int nextId() {
        // Increment and return the shared counter value.
        return counter++;
    }
}
