package main.model;

public class Book extends Product {
    private String author;
    private String isbn;
    private String genre;

    public Book(int id, String title, String author, String isbn, String genre) {
        super(id, title);
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Book by " + author + " (" + genre + ")";
    }
}
