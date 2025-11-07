package main.model;

/** Represents a DVD. */
public class DVD extends Product {
    private String director;

    public DVD(int id, String title, String director) {
        super(id, title);
        this.director = director;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Director: " + director;
    }
}
