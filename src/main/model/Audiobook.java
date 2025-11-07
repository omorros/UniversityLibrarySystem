package main.model;

/** Represents an Audiobook. */
public class Audiobook extends Product {
    private String narrator;

    public Audiobook(int id, String title, String narrator) {
        super(id, title);
        this.narrator = narrator;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Narrator: " + narrator;
    }
}
