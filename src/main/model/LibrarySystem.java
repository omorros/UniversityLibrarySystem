package main.model;

import java.util.ArrayList;
import java.util.List;

/** Main class controlling the library operations. */
public class LibrarySystem {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private Policy policy = new Policy(14, 2, 0.5);
    private User demoUser;

    public void addProduct(Product p) { products.add(p); }
    public void addUser(User u) { users.add(u); }
    public void setDemoUser(User u) { demoUser = u; }
    public User getDemoUser() { return demoUser; }

    public Product findProductById(int id) {
        return products.stream().filter(p -> p.getProductId() == id).findFirst().orElse(null);
    }

    public void handleBorrow(User user, int productId) {
        Product product = findProductById(productId);
        if (product == null) { System.out.println("Product not found!"); return; }
        if (!product.isAvailable()) { System.out.println("Product not available!"); return; }

        if (user.borrowProduct(product, policy)) {
            Loan loan = new Loan(IDGenerator.nextId(), user, product, policy);
            loans.add(loan);
        }
    }

    public void handleReturn(User user, int productId) {
        Product p = findProductById(productId);
        if (p == null) { System.out.println("Product not found!"); return; }
        user.returnProduct(p);
    }

    public void displayAllProducts() {
        System.out.println("\nAvailable Products:");
        products.forEach(p -> System.out.println(p.getInfo()));
    }

    public void displayAllLoans() {
        System.out.println("\nAll Loans:");
        loans.forEach(l -> System.out.println(l.getInfo()));
    }
}
