package main.model;

/** Represents a child user with an adult guardian. */
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

    @Override
    public String toString() {
        return "ChildUser: " + name + " | Guardian: " +
                (guardian != null ? guardian.getName() : "None");
    }
}
