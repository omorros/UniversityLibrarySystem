public void displayAllProducts() {
    System.out.println("\nAvailable Products:");
    products.forEach(p -> System.out.println(p.getInfo()));
}

public void displayAllLoans() {
    System.out.println("\nAll Loans:");
    loans.forEach(l -> System.out.println(l.getInfo()));
}

public void handleReturn(User user, int productId) {
    Product p = findProductById(productId);
    if (p == null) {
        System.out.println("Product not found!");
        return;
    }
    user.returnProduct(p);
}

private User demoUser; // For testing
public User getDemoUser() { return demoUser; }
public void setDemoUser(User user) { this.demoUser = user; }
