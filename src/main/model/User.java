package main.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Base abstract class for library users. */
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

    /**
     * Handles borrowing a product.
     * The Loan object is created externally (in LibrarySystem) to maintain consistent IDs.
     */
    public boolean borrowProduct(Product product, Policy policy) {
        if (!product.isAvailable()) {
            System.out.println("Product not available.");
            return false;
        }
        product.setAvailable(false);
        System.out.println("Borrowed successfully: " + product.getTitle());
        return true;
    }

    /**
     * Returns a borrowed product if found and removes the corresponding loan.
     */
    public boolean returnProduct(Product product) {
        Iterator<Loan> iterator = loans.iterator();
        while (iterator.hasNext()) {
            Loan loan = iterator.next();
            if (loan.getItem().equals(product)) {
                loan.setReturnDate(java.time.LocalDate.now());
                product.setAvailable(true);
                iterator.remove(); // remove from user's list
                System.out.println("Returned: " + product.getTitle());
                return true;
            }
        }
        System.out.println("Loan not found for: " + product.getTitle());
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
