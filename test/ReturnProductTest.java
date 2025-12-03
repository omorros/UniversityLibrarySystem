import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class ReturnProductTest {

    @Test
    public void testReturnProductRestoresAvailability() {

        // Create user
        AdultUser user = new AdultUser(1, "Dhrew", "dhrew@mail.com");

        // Create product
        Product book = new Book(2, "Java Book", "Author", "123", "Fiction");

        // Create policy
        Policy policy = new Policy(14, 2, 0.5);

        // 1. Borrow the book
        boolean borrowSuccess = user.borrowProduct(book, policy);

        // Quick sanity checks
        assertTrue(borrowSuccess);
        assertFalse(book.isAvailable());
        assertEquals(1, user.viewLoans().size()); // loan MUST exist now

        // 2. Return the book
        boolean returnSuccess = user.returnProduct(book);

        // Assertions
        assertTrue(returnSuccess);         // returnProduct() finds the loan
        assertTrue(book.isAvailable());    // product becomes available again
        assertEquals(0, user.viewLoans().size()); // loan removed
    }
}

