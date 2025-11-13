package main.model;

/**
 * Represents a Book within the library system.
 * <p>
 * This class extends {@link Product} and adds specific attributes
 * related to printed or digital books such as author, ISBN, and genre.
 * It demonstrates inheritance (from {@code Product}) and polymorphism
 * by overriding {@link #getInfo()} to display book-specific details.
 * </p>
 */
public class Book extends Product {

    /** The author of the book. */
    private String author;

    /** The International Standard Book Number uniquely identifying the book. */
    private String isbn;

    /** The literary genre of the book (e.g., Fiction, Science, Biography). */
    private String genre;

    /**
     * Constructs a new {@code Book} instance with complete bibliographic information.
     *
     * @param id     unique product identifier
     * @param title  title of the book
     * @param author author of the book
     * @param isbn   ISBN reference code
     * @param genre  genre or category of the book
     */
    public Book(int id, String title, String author, String isbn, String genre) {
        // Call superclass constructor to initialise the common Product fields.
        super(id, title);
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    /**
     * Returns formatted information describing this book.
     * <p>
     * This method overrides {@link Product#getInfo()} to append author and
     * genre information. Demonstrates runtime polymorphism by customising
     * inherited behaviour.
     * </p>
     *
     * @return a descriptive string combining product and book details
     */
    @Override
    public String getInfo() {
        // Combine generic product information with book-specific data.
        return super.getInfo() + " | Book by " + author + " (" + genre + ")";
    }
}
