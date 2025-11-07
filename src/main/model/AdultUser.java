package main.model;

import java.util.ArrayList;
import java.util.List;

/** Represents an adult user who can have dependents. */
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
        return "AdultUser: " + name + " | Dependants: " + dependants.size();
    }
}
