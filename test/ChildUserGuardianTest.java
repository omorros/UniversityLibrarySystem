import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class ChildUserGuardianTest {

    @Test
    public void testChildCannotBorrowWithoutGuardian() {
        ChildUser child = new ChildUser(5, "Sara", "sara@mail.com");
        Product book = new Book(3, "Story", "Ana", "111", "Kids");
        Policy p = new Policy(14, 2, 0.5);

        boolean result = child.borrowProduct(book, p);

        assertFalse(result);  // Child must have guardian before borrowing
    }
}
