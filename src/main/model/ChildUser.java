package main.model;

/**
 * Represents a child user who must have a guardian.
 * Limited borrowing rights (max 3 items, 7-day loans, no renewals).
 */
public class ChildUser extends User {
    private AdultUser guardian;

    public ChildUser(int userId, String name, String email) {
        super(userId, name, email);
    }

    public void setGuardian(AdultUser guardian) {
        this.guardian = guardian;
    }

    public AdultUser getGuardian() {
        return guardian;
    }

    /**
     * Child users require a guardian, can borrow up to 3 items,
     * and have shorter, non-renewable loan periods.
     */
    @Override
    public boolean borrowProduct(Product product, Policy policy) {
        if (guardian == null) {
            System.out.println("Cannot borrow without a guardian.");
            return false;
        }
        if (loans.size() >= 3) {
            System.out.println("Borrowing limit reached (3 items max).");
            return false;
        }

        // Override default policy: 7 days, 0 renewals
        Policy childPolicy = new Policy(7, 0, policy.getDailyFine());
        return super.borrowProduct(product, childPolicy);
    }

    @Override
    public String toString() {
        return "ChildUser: " + name + " | Guardian: " +
                (guardian != null ? guardian.getName() : "None");
    }
}
