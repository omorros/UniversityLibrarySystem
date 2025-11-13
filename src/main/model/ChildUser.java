package main.model;

/**
 * Represents a child user in the library system.
 * <p>
 * Child users have restricted borrowing rights and must have a
 * linked {@link AdultUser} guardian before borrowing any items.
 * They can borrow up to three items at once for a maximum of seven
 * days, with no renewals allowed. This class demonstrates inheritance,
 * encapsulation, and method overriding.
 * </p>
 */
public class ChildUser extends User {

    /** Reference to the adult guardian responsible for this child user. */
    private AdultUser guardian;

    /**
     * Constructs a new {@code ChildUser} with a unique ID, name, and email.
     *
     * @param userId unique identifier for the child user
     * @param name   full name of the child
     * @param email  contact email for the child user
     */
    public ChildUser(int userId, String name, String email) {
        // Invoke superclass constructor to initialise common User fields.
        super(userId, name, email);
    }

    /**
     * Sets the adult guardian for this child user.
     *
     * @param guardian an {@link AdultUser} who will act as the child's guardian
     */
    public void setGuardian(AdultUser guardian) {
        this.guardian = guardian;
    }

    /**
     * Retrieves the {@link AdultUser} guardian assigned to this child.
     *
     * @return the guardian of this child user, or {@code null} if none assigned
     */
    public AdultUser getGuardian() {
        return guardian;
    }

    /**
     * Attempts to borrow a {@link Product} for the child user under special rules.
     * <p>
     * Child users:
     * <ul>
     *   <li>Must have an assigned guardian</li>
     *   <li>May borrow a maximum of three items at a time</li>
     *   <li>Have a shorter loan period (7 days) and zero renewals</li>
     * </ul>
     * </p>
     * This method overrides {@link User#borrowProduct(Product, Policy)}
     * and enforces the child-specific borrowing policy.
     *
     * @param product the product to borrow
     * @param policy  the general policy used as a base for fines
     * @return {@code true} if borrowing succeeds; {@code false} otherwise
     */
    @Override
    public boolean borrowProduct(Product product, Policy policy) {
        // Ensure a guardian is assigned before allowing any borrowing.
        if (guardian == null) {
            System.out.println("Cannot borrow without a guardian.");
            return false;
        }

        // Enforce child borrowing limit (maximum of three items).
        if (loans.size() >= 3) {
            System.out.println("Borrowing limit reached (3 items max).");
            return false;
        }

        // Create a specific policy for child users: 7-day loans, no renewals.
        Policy childPolicy = new Policy(7, 0, policy.getDailyFine());

        // Delegate borrowing logic to superclass with restricted policy.
        return super.borrowProduct(product, childPolicy);
    }

    /**
     * Returns a formatted string describing this child user and their guardian.
     *
     * @return formatted child user information string
     */
    @Override
    public String toString() {
        // Display guardian name if available; otherwise show "None".
        return "ChildUser: " + name + " | Guardian: " +
                (guardian != null ? guardian.getName() : "None");
    }
}
