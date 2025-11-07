package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;
    protected List<Loan> loans = new ArrayList<>();

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public boolean borrowProduct(Product product, Policy policy) {
        if (!product.isAvailable()) {
            System.out.println("Product not available.");
            return false;
        }
        Loan loan = new Loan(IDGenerator.nextId(), this, product, policy);
        loans.add(loan);
        product.setAvailable(false);
        System.out.println("Borrow successful: " + product.getTitle());
        return true;
    }

    public boolean returnProduct(Product product) {
        for (Loan loan : loans) {
            if (loan.getItem().equals(product)) {
                loan.setReturnDate(java.time.LocalDate.now());
                product.setAvailable(true);
                System.out.println("Returned: " + product.getTitle());
                return true;
            }
        }
        return false;
    }

    public List<Loan> viewLoans() {
        return loans;
    }

    @Override
    public String toString() {
        return userId + " - " + name + " (" + email + ")";
    }
}
