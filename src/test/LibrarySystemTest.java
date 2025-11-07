package test;

import main.model.*;

public class LibrarySystemTest {
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        Book book = new Book(1, "Test Book", "Author", "123", "Fiction");
        system.addProduct(book);

        AdultUser user = new AdultUser(1, "Test User", "user@test.com");
        system.handleBorrow(user, 1);
        assert !book.isAvailable() : "Book should be unavailable after borrowing";

        system.handleReturn(user, 1);
        assert book.isAvailable() : "Book should be available after returning";

        System.out.println("✅ All tests passed!");
    }
}
