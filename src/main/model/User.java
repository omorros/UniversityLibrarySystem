package main.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class representing a user of the library system.
 * <p>
 * The {@code User} class provides shared properties and behaviours
 * for all types of library users (e.g., {@link AdultUser},
 * {@link ChildUser}, {@link Student}, {@link Librarian}).
 * </p>
 * <p>
 * This class demonstrates several key OOP principles:
 * <ul>
 *   <li><b>Abstraction:</b> Defines generic user behaviour for all subclasses.</li>
 *   <li><b>Encapsulation:</b> Protects user data with controlled access through getters.</li>
 *   <li><b>Polymorphism:</b> Enables subclasses to override methods such as
 *       {@link #borrowProduct(Product, Policy)} for custom borrowing rules.</li>
 * </ul>
 * </p>
 */
public abstract class User {

    /** Unique identifier for the user. */
    protected int userId;

    /** Full name of the user. */
    protected String name;

    /** Contact email of the user. */
    protected String email;

    /** List of loans currently held by this user. */
    protected List<Loan> loans = new ArrayList<>();

    /**
     * Constructs a new {@code User} object with the specified attributes.
     *
     * @param userId unique identifier for the user
     * @param name   the full name of the user
     * @param email  the contact email address of the user
     */
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // -------------------------------------------
    // GETTERS
    // -------------------------------------------

    /** Retrieves the unique identifier of the user. */
    public int getUserId() { return userId; }

    /** Retrieves the user's full name. */
    public String getName() { return name; }

    /** Retrieves the user's email address. */
    public String getEmail() { return email; }

    // -------------------------------------------
    // BORROWING BEHAVIOUR
    // -------------------------------------------

    /**
     * Handles the process of borrowing a product.
     * <p>
     * By default, this method checks the availability of the item and marks it
     * as borrowed. Subclasses such as {@link ChildUser} or {@link Student}
     * override this method to apply role-specific borrowing limits and policies.
     * </p>
     * <p>
     * The corresponding {@link Loan} object is now created directly inside this method
     * to ensure consistent behaviour across the entire system, including when tests
     * or other components call this method without using {@link LibrarySystem}.
     * </p>
     *
     * @param product the {@link Product} being borrowed
     * @param policy  the {@link Policy} defining the loan rules
     * @return {@code true} if borrowing succeeds; {@code false} if unavailable
     */
    public boolean borrowProduct(Product product, Policy policy) {
        if (!product.isAvailable()) {
            System.out.println("Product not available.");
            return false;
        }

        // Mark the product as unavailable once borrowed.
        product.setAvailable(false);

        // Create a loan and store it in the user's loan list.
        Loan loan = new Loan(IDGenerator.nextId(), this, product, policy);
        loans.add(loan);

        System.out.println("Borrowed successfully: " + product.getTitle());
        return true;
    }

    // -------------------------------------------
    // RETURNING BEHAVIOUR
    // -------------------------------------------

    /**
     * Handles the return of a borrowed product.
     * <p>
     * When a product is returned, it is marked as available again and the
     * corresponding {@link Loan} record is removed from this user's loan list.
     * </p>
     *
     * @param product the {@link Product} to be returned
     * @return {@code true} if the product was successfully returned,
     *         {@code false} if the loan record was not found
     */
    public boolean returnProduct(Product product) {
        Iterator<Loan> iterator = loans.iterator();

        // Search for a matching loan in the user's loan list.
        while (iterator.hasNext()) {
            Loan loan = iterator.next();
            if (loan.getItem().equals(product)) {
                // Update return date and availability status.
                loan.setReturnDate(java.time.LocalDate.now());
                product.setAvailable(true);
                iterator.remove(); // Remove from user's loan list
                System.out.println("Returned: " + product.getTitle());
                return true;
            }
        }

        System.out.println("Loan not found for: " + product.getTitle());
        return false;
    }

    // -------------------------------------------
    // LOAN VIEWING
    // -------------------------------------------

    /** Retrieves all loans currently held by the user. */
    public List<Loan> viewLoans() { return loans; }

    // -------------------------------------------
    // STRING REPRESENTATION
    // -------------------------------------------

    /** Returns a formatted string representation of this user. */
    @Override
    public String toString() {
        return userId + " - " + name + " (" + email + ")";
    }
}
