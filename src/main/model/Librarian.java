package main.model;

import java.util.List;

/** Represents a librarian responsible for managing users and products. */
public class Librarian extends User {

    public Librarian(int userId, String name, String email) {
        super(userId, name, email);
    }

    public void registerUser(List<User> users, User newUser) {
        users.add(newUser);
        System.out.println("User registered: " + newUser.getName());
    }

    public boolean removeUser(List<User> users, int id) {
        return users.removeIf(u -> u.getUserId() == id);
    }

    public void addProduct(List<Product> products, Product p) {
        products.add(p);
        System.out.println("Product added: " + p.getTitle());
    }

    public boolean removeProduct(List<Product> products, int id) {
        return products.removeIf(p -> p.getProductId() == id);
    }

    public String generateReport(List<Loan> loans) {
        StringBuilder sb = new StringBuilder("Library Loan Report:\n");
        for (Loan loan : loans) {
            sb.append(loan.getInfo()).append("\n");
        }
        return sb.toString();
    }
}
