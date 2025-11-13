package main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an adult library user.
 * <p>
 * Adult users can borrow up to ten items and may act as guardians
 * for dependent {@link ChildUser} objects. This class demonstrates
 * inheritance, polymorphism and encapsulation by extending {@link User}
 * and managing its own collection of dependent child users.
 * </p>
 */
public class AdultUser extends User {

    /**
     * A list of child users who are dependants of this adult.
     * Demonstrates composition: an AdultUser "has-many" ChildUsers.
     */
    private List<ChildUser> dependants;

    /**
     * Constructs a new {@code AdultUser} with the specified details.
     *
     * @param userId unique identifier of the user
     * @param name   full name of the adult user
     * @param email  contact email address of the user
     */
    public AdultUser(int userId, String name, String email) {
        super(userId, name, email);
        this.dependants = new ArrayList<>();
    }

    /**
     * Adds a dependent {@link ChildUser} to this adult user and sets this
     * adult as the child's guardian.
     *
     * @param child the {@link ChildUser} to be linked to this guardian
     */
    public void addChild(ChildUser child) {
        // Establish a two-way relationship between guardian and child.
        dependants.add(child);
        child.setGuardian(this);
    }

    /**
     * Retrieves the list of dependent child users associated with this adult.
     *
     * @return list of dependants
     */
    public List<ChildUser> getDependants() {
        return dependants;
    }

    /**
     * Allows an adult user to borrow a product under the library's loan policy.
     * <p>
     * Adult users are permitted to borrow up to ten items at any given time.
     * If the user already has ten active loans, the request is denied.
     * </p>
     *
     * @param product the {@link Product} to be borrowed
     * @param policy  the {@link Policy} defining loan period, renewals and fines
     * @return {@code true} if borrowing is successful, otherwise {@code false}
     */
    @Override
    public boolean borrowProduct(Product product, Policy policy) {
        // Check borrowing limit before delegating to superclass logic.
        if (loans.size() >= 10) {
            System.out.println("Borrowing limit reached (10 items max).");
            return false;
        }
        // Call superclass implementation to register the loan.
        return super.borrowProduct(product, policy);
    }

    /**
     * Returns a formatted string representation of this user,
     * including the number of dependants.
     *
     * @return string containing basic adult user information
     */
    @Override
    public String toString() {
        return "AdultUser: " + name + " (" + email + "), Dependants: " + dependants.size();
    }
}
