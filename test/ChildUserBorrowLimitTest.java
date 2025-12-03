import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class ChildUserBorrowLimitTest {

    @Test
    public void testChildBorrowingLimit() {

        // Create child + guardian
        ChildUser child = new ChildUser(1, "Sara", "sara@mail.com");
        AdultUser guardian = new AdultUser(99, "Parent", "parent@mail.com");
        guardian.addChild(child);  // IMPORTANT: assigns guardian properly

        Policy policy = new Policy(14, 1, 0.2);

        // Borrow 3 products (allowed)
        for (int i = 1; i <= 3; i++) {
            Product p = new Book(i, "Book " + i, "Author", "ISBN" + i, "Kids");
            boolean success = child.borrowProduct(p, policy);
            assertTrue("Borrow " + i + " should succeed", success);
            assertFalse("Product " + i + " should now be unavailable", p.isAvailable());
        }

        // Attempt 4th borrow (should fail)
        Product extra = new Book(4, "Extra Book", "Author", "ISBN4", "Kids");
        boolean result = child.borrowProduct(extra, policy);

        // Assertions
        assertFalse("4th borrow MUST fail", result);
        assertEquals("Child should have only 3 active loans", 3, child.viewLoans().size());
        assertTrue("The 4th product should still be available", extra.isAvailable());
    }
}
