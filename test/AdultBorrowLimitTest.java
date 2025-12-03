import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class AdultBorrowLimitTest {

    @Test
    public void testAdultBorrowingLimitReached() {

        AdultUser adult = new AdultUser(1, "John", "john@mail.com");
        Policy policy = new Policy(14, 2, 0.5);

        // -----------------------------------
        // BORROW FIRST 10 ITEMS (ALLOWED)
        // -----------------------------------

        // 5 Books
        for (int i = 1; i <= 5; i++) {
            Product p = new Book(i, "Book " + i, "Author", "ISBN" + i, "Fiction");
            boolean success = adult.borrowProduct(p, policy);
            assertTrue("Book " + i + " should be borrowed successfully", success);
        }

        // 5 CDs
        for (int i = 6; i <= 10; i++) {
            Product p = new CD(i, "CD " + i, "Composer");
            boolean success = adult.borrowProduct(p, policy);
            assertTrue("CD " + i + " should be borrowed successfully", success);
        }

        // Verify total = 10 loans
        assertEquals("Adult should have exactly 10 active loans",
                10, adult.viewLoans().size());


        // -----------------------------------
        // ATTEMPT 5 EXTRA BORROWS (MUST FAIL)
        // -----------------------------------

        for (int i = 11; i <= 15; i++) {
            Product audio = new Audiobook(i, "Audio " + i, "Narrator");
            boolean success = adult.borrowProduct(audio, policy);

            assertFalse("Borrow attempt #" + i
                            + " should FAIL because limit was reached",
                    success);
        }

        // Verify adult still has only 10 loans
        assertEquals("Loan count should remain at 10 after failed attempts",
                10, adult.viewLoans().size());
    }
}

