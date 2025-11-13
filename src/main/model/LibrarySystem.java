package main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls all core operations of the University Library System.
 * <p>
 * The {@code LibrarySystem} class manages users, products, and loans.
 * It acts as the central controller of the application, coordinating
 * interactions between the model classes ({@link User}, {@link Product},
 * {@link Loan}, and {@link Policy}). This structure follows the
 * <b>Controller / Facade design pattern</b> by providing a single
 * point of access for library management operations.
 * </p>
 * <p>
 * Responsibilities include:
 * <ul>
 *   <li>Loading product data from CSV files via {@link DataLoader}</li>
 *   <li>Handling borrowing and returning operations</li>
 *   <li>Tracking all active loans and available items</li>
 *   <li>Displaying reports of library activity</li>
 * </ul>
 * </p>
 */
public class LibrarySystem {

    /** List of all registered users in the system. */
    private List<User> users = new ArrayList<>();

    /** List of all available products (books, CDs, DVDs, audiobooks). */
    private List<Product> products = new ArrayList<>();

    /** List of all active and historical loans. */
    private List<Loan> loans = new ArrayList<>();

    /** Default borrowing policy applied across all users. */
    private Policy policy = new Policy(14, 2, 0.5);

    /** A reference user used for demonstration or current session. */
    private User demoUser;

    // -------------------------------
    // Basic Getters & Setters
    // -------------------------------

    /**
     * Adds a new product to the system's product list.
     *
     * @param p the {@link Product} to add
     */
    public void addProduct(Product p) { products.add(p); }

    /**
     * Registers a new user in the system.
     *
     * @param u the {@link User} to add
     */
    public void addUser(User u) { users.add(u); }

    /**
     * Sets the demo or currently active user.
     *
     * @param u the {@link User} currently logged in
     */
    public void setDemoUser(User u) { demoUser = u; }

    /**
     * Retrieves the currently active user.
     *
     * @return the demo or active {@link User}
     */
    public User getDemoUser() { return demoUser; }

    // -------------------------------
    // Load Data from CSV Files
    // -------------------------------

    /**
     * Loads all product data from CSV files using {@link DataLoader}.
     * <p>
     * This method clears the current product list and repopulates it with
     * items from the data files (books, CDs, DVDs, and audiobooks).
     * </p>
     */
    public void loadAllData() {
        products.clear();
        products.addAll(DataLoader.loadBooks());
        products.addAll(DataLoader.loadCDs());
        products.addAll(DataLoader.loadDVDs());
        products.addAll(DataLoader.loadAudiobooks());
        System.out.println("Data successfully loaded from CSV files. Total products: " + products.size());
    }

    // -------------------------------
    // Product Search
    // -------------------------------

    /**
     * Searches for a product by its unique identifier.
     *
     * @param id the product ID to search for
     * @return the matching {@link Product}, or {@code null} if not found
     */
    public Product findProductById(int id) {
        // Use Java Streams to filter products efficiently by ID.
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves all products belonging to a specific category type.
     *
     * @param type the product type (e.g., "Book", "CD", "DVD", "Audiobook")
     * @return a list of products matching the specified type
     */
    public List<Product> getProductsByCategory(String type) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            // Compare the class name (case-insensitive) to match type.
            if (p.getClass().getSimpleName().equalsIgnoreCase(type)) {
                result.add(p);
            }
        }
        return result;
    }

    // -------------------------------
    // Borrow / Return Handling
    // -------------------------------

    /**
     * Handles the borrowing of a product by a given user.
     * <p>
     * Verifies product availability, enforces borrowing limits,
     * and records the loan in both the system list and the user's
     * personal loan record.
     * </p>
     *
     * @param user       the {@link User} borrowing the item
     * @param productId  the ID of the {@link Product} to borrow
     */
    public void handleBorrow(User user, int productId) {
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        if (!product.isAvailable()) {
            System.out.println("Product is currently checked out.");
            return;
        }

        // Delegate to user’s borrowing method (polymorphism in action).
        if (user.borrowProduct(product, policy)) {
            product.setAvailable(false); // Mark as unavailable
            Loan loan = new Loan(IDGenerator.nextId(), user, product, policy);

            // Record the loan both globally and in the user's personal list.
            loans.add(loan);
            user.viewLoans().add(loan);

            System.out.println(user.getName() + " borrowed: " + product.getTitle());
        }
    }

    /**
     * Handles returning a product to the system.
     * <p>
     * Marks the product as available again and removes the related
     * {@link Loan} record from both the user and system lists.
     * </p>
     *
     * @param user       the {@link User} returning the item
     * @param productId  the ID of the {@link Product} being returned
     */
    public void handleReturn(User user, int productId) {
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        // If user successfully returns the item, update system records.
        if (user.returnProduct(product)) {
            product.setAvailable(true);
            loans.removeIf(l -> l.getItem().equals(product) && l.getBorrower().equals(user));
            System.out.println("Return successful: " + product.getTitle());
        } else {
            System.out.println("Return failed. Ensure you borrowed this item.");
        }
    }

    // -------------------------------
    // Display Functions
    // -------------------------------

    /**
     * Displays all products currently loaded in the system.
     * Prints each product's information to the console.
     */
    public void displayAllProducts() {
        System.out.println("\nAll Products:");
        if (products.isEmpty()) {
            System.out.println("No products loaded.");
        } else {
            // Display each product using its overridden getInfo() method.
            products.forEach(p -> System.out.println(p.getInfo()));
        }
    }

    /**
     * Displays all active loans with their corresponding reminders.
     * <p>
     * For each {@link Loan}, the system prints loan information
     * followed by a due date reminder using the nested
     * {@link Loan.Reminder} class.
     * </p>
     */
    public void displayAllLoans() {
        System.out.println("\nAll Loans:");
        if (loans.isEmpty()) {
            System.out.println("No loans currently registered.");
        } else {
            for (Loan l : loans) {
                System.out.println(l.getInfo());

                // Use nested Reminder class to display due status.
                Loan.Reminder reminder = l.new Reminder();
                reminder.showReminder();
            }
        }
    }

    // -------------------------------
    // Utility
    // -------------------------------

    /**
     * Removes a loan record that matches the given product and user.
     * <p>
     * Used when a product is returned or a user account is updated,
     * ensuring system-wide consistency of loan records.
     * </p>
     *
     * @param product the {@link Product} being returned
     * @param user    the {@link User} who borrowed the product
     */
    public void removeLoanRecord(Product product, User user) {
        loans.removeIf(l -> l.getItem().equals(product) && l.getBorrower().equals(user));
    }
}
