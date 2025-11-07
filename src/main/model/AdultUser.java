package main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an adult user who can borrow up to 10 items and act as guardian
 * for dependent ChildUsers. Demonstrates polymorphism and encapsulation.
 */
public class AdultUser extends User {
    private List<ChildUser> dependants;

    public AdultUser(int userId, String name, String email) {
        super(userId, name, email);
        this.dependants = new ArrayList<>();
    }

    /**
     * Adds a dependent ChildUser and links the guardian relationship.
     */
    public void addChild(ChildUser child) {
        dependants.add(child);
        child.setGuardian(this);
    }

    public List<ChildUser> getDependants() {
        return dependants;
    }

    /**
     * Adult users can borrow up to 10 items and renew up to 2 times.
     */
    @Override
    public boolean borrowProduct(Product product, Policy policy) {
        if (loans.size() >= 10) {
            System.out.println("Borrowing limit reached (10 items max).");
            return false;
        }
        return super.borrowProduct(product, policy);
    }

    @Override
    public String toString() {
        return "AdultUser: " + name + " (" + email + "), Dependants: " + dependants.size();
    }
}
