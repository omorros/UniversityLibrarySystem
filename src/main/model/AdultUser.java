package main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an adult library member who can have child dependents.
 */
public class AdultUser extends User {
    private List<ChildUser> dependants;

    public AdultUser(int userId, String name, String email) {
        super(userId, name, email);
        this.dependants = new ArrayList<>();
    }

    public void addChild(ChildUser child) {
        dependants.add(child);
        child.setGuardian(this);
    }

    public List<ChildUser> getDependants() {
        return dependants;
    }

    @Override
    public String toString() {
        return "AdultUser: " + name + " (" + email + "), Dependants: " + dependants.size();
    }
}
