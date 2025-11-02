import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    // --- common state for all users (encapsulation) ---
    private final int userId;     // immutable once assigned
    private String name;
    private String email;

    // a user "has" loans (composition)
    private final List<Loan> loans = new ArrayList<>();

    // --- constructor used by all subclasses ---
    protected User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // --- getters/setters (encapsulation) ---
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    // --- loan helpers used by LibrarySystem ---
    /** LibrarySystem calls this when a loan is created. */
    public void addLoan(Loan loan) {
        if (loan != null) loans.add(loan);
    }

    /** LibrarySystem calls this when a loan is returned. */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    /** Read-only view used by UI to display borrowed items. */
    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + userId + ", name='" + name + "'}";
    }
}
