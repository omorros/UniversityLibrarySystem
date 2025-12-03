import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class FindProductByIdTest {

    @Test
    public void testFindProductById() {

        // Create a new LibrarySystem
        LibrarySystem system = new LibrarySystem();

        // Create a product manually (bypassing CSV loader)
        Product book = new Book(1, "Algorithms", "Donald Knuth", "12345", "CS");

        // Add the product to the system
        system.addProduct(book);

        // Search for the product by ID
        Product result = system.findProductById(1);

        // Assertions
        assertNotNull(result);        // Product must be found
        assertEquals(book, result);   // The exact product must match
    }
}
