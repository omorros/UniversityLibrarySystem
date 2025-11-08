package main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class controlling the core operations of the University Library System.
 * Manages users, products, and loan transactions.
 */
public class LibrarySystem {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private Policy policy = new Policy(14, 2, 0.5);
    private User demoUser;

    // -------------------------------
    // Basic Getters & Setters
    // -------------------------------
    public void addProduct(Product p) { products.add(p); }
    public void addUser(User u) { users.add(u); }
    public void setDemoUser(User u) { demoUser = u; }
    public User getDemoUser() { return demoUser; }

    // -------------------------------
    // Load Data from CSV Files
    // -------------------------------
    public void loadAllData() {
        // Clear previous data to avoid duplicates
        products.clear();

        products.addAll(DataLoader.loadBooks());
        products.addAll(DataLoader.loadCDs());
        products.addAll(DataLoader.loadDVDs());
        products.addAll(DataLoader.loadAudiobooks());

        System.out.println("Data successfully loaded from CSV files. Total products: " + products.size());
    }

    // -------------------------------
    // Core Logic
    // -------------------------------
    public Product findProductById(int id) {
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
    }

    public void handleBorrow(User user, int productId) {
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        if (!product.isAvailable()) {
            System.out.println("Product is currently not available.");
            return;
        }

        if (user.borrowProduct(product, policy)) {
            Loan loan = new Loan(IDGenerator.nextId(), user, product, policy);
            loans.add(loan);
            product.setAvailable(false);
            System.out.println(user.getName() + " borrowed: " + product.getTitle());
        }
    }

    public void handleReturn(User user, int productId) {
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (user.returnProduct(product)) {
            product.setAvailable(true);
            System.out.println(product.getTitle() + " returned successfully.");
        }
    }

    // -------------------------------
    // Display Functions
    // -------------------------------
    public void displayAllProducts() {
        System.out.println("\nAvailable Products:");
        if (products.isEmpty()) {
            System.out.println("No products loaded.");
        } else {
            products.forEach(p -> System.out.println(p.getInfo()));
        }
    }

    public void displayAllLoans() {
        System.out.println("\nAll Loans:");
        if (loans.isEmpty()) {
            System.out.println("No loans currently registered.");
        } else {
            loans.forEach(l -> System.out.println(l.getInfo()));
        }
    }

    // -------------------------------
    // Optional Utility
    // -------------------------------
    public void reloadData() {
        System.out.println("Reloading data from CSV files...");
        loadAllData();
    }
}
