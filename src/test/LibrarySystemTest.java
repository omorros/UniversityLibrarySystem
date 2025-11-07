package test;

import main.model.*;

/** Basic unit tests for Library System. */
public class LibrarySystemTest {
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        Book b = new Book(1, "Test Book", "Author", "123", "Fiction");
        system.addProduct(b);
        AdultUser user = new AdultUser(1, "John", "john@test.com");

        system.handleBorrow(user, 1);
        assert !b.isAvailable() : "Book should be unavailable after borrow.";

        system.handleReturn(user, 1);
        assert b.isAvailable() : "Book should be available after return.";

        System.out.println("All tests passed!");
    }
}

