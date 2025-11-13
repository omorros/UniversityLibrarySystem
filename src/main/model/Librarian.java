package main.model;

import java.util.List;

/**
 * Represents a librarian user responsible for managing the library system.
 * <p>
 * The {@code Librarian} class extends {@link User} and encapsulates
 * administrative responsibilities such as registering users, adding
 * and removing products, and generating loan reports. This class
 * demonstrates inheritance, encapsulation, and object interaction.
 * </p>
 */
public class Librarian extends User {

    /**
     * Constructs a new {@code Librarian} object with the specified details.
     *
     * @param userId unique identifier for the librarian
     * @param name   full name of the librarian
     * @param email  contact email of the librarian
     */
    public Librarian(int userId, String name, String email) {
        // Call the superclass constructor to initialise shared fields.
        super(userId, name, email);
    }

    // -------------------------------------------
    // USER MANAGEMENT METHODS
    // -------------------------------------------

    /**
     * Registers a new {@link User} in the library system.
     * <p>
     * Adds the provided user object to the list of existing users.
     * Demonstrates aggregation and object list manipulation.
     * </p>
     *
     * @param users   the list of all users currently in the system
     * @param newUser the new user to be registered
     */
    public void registerUser(List<User> users, User newUser) {
        users.add(newUser);
        System.out.println("User registered: " + newUser.getName());
    }

    /**
     * Removes a user from the system based on their ID.
     * <p>
     * Uses the {@link List#removeIf(java.util.function.Predicate)} method
     * to perform a functional-style removal of the matching user.
     * </p>
     *
     * @param users list of users in the system
     * @param id    unique ID of the user to remove
     * @return {@code true} if a user was removed, {@code false} otherwise
     */
    public boolean removeUser(List<User> users, int id) {
        // Use a lambda predicate for efficient list filtering.
        return users.removeIf(u -> u.getUserId() == id);
    }

    // -------------------------------------------
    // PRODUCT MANAGEMENT METHODS
    // -------------------------------------------

    /**
     * Adds a new {@link Product} to the library collection.
     * <p>
     * Demonstrates aggregation and system-wide product management.
     * </p>
     *
     * @param products list of products in the system
     * @param p        the new product to add
     */
    public void addProduct(List<Product> products, Product p) {
        products.add(p);
        System.out.println("Product added: " + p.getTitle());
    }

    /**
     * Removes a {@link Product} from the collection based on its ID.
     * <p>
     * Uses functional programming via {@code removeIf} for efficient matching.
     * </p>
     *
     * @param products list of available products
     * @param id       unique product ID to remove
     * @return {@code true} if the product was found and removed, {@code false} otherwise
     */
    public boolean removeProduct(List<Product> products, int id) {
        return products.removeIf(p -> p.getProductId() == id);
    }

    // -------------------------------------------
    // REPORT GENERATION
    // -------------------------------------------

    /**
     * Generates a textual report of all current {@link Loan} records.
     * <p>
     * The report lists each loan entry on a new line, including
     * details retrieved via {@link Loan#getInfo()}.
     * </p>
     *
     * @param loans list of all active loans
     * @return formatted string containing all loan details
     */
    public String generateReport(List<Loan> loans) {
        StringBuilder sb = new StringBuilder("Library Loan Report:\n");

        // Iterate over all loans and append details to the report.
        for (Loan loan : loans) {
            sb.append(loan.getInfo()).append("\n");
        }

        // Return the completed report string.
        return sb.toString();
    }
}
