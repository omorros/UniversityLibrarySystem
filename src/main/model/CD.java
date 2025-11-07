package main.model;

/** Represents a music CD. */
public class CD extends Product {
    private String composer;

    public CD(int id, String title, String composer) {
        super(id, title);
        this.composer = composer;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Composer: " + composer;
    }
}
