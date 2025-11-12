package main.model;

/**
 * Interface defining the contract for all borrowable items in the library.
 * Used to show item availability status and enforce consistent behavior.
 */
public interface Borrowable {
    boolean isAvailable();
    void setAvailable(boolean available);
    String getInfo();
}