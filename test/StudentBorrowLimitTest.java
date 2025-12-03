import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class StudentBorrowLimitTest {

    @Test
    public void testStudentBorrowLimit() {

        Student student = new Student(
                1,
                "Alice",
                "alice@mail.com",
                "Computer Science",
                1
        );

        Policy basePolicy = new Policy(14, 2, 0.5); // Only fine is reused

        // Borrow 5 items – should all succeed
        for (int i = 1; i <= 5; i++) {
            Product p = new Book(i, "Book " + i, "Author", "ISBN" + i, "Fiction");
            boolean success = student.borrowProduct(p, basePolicy);
            assertTrue("Borrow attempt " + i + " should succeed", success);
            assertFalse("Product " + i + " should become unavailable", p.isAvailable());
        }

        // 6th product – MUST FAIL
        Product extra = new Book(6, "Extra Book", "Author", "ISBN6", "Fiction");
        boolean result = student.borrowProduct(extra, basePolicy);

        assertFalse("6th borrow must fail for student", result);
        assertTrue("Extra book should remain AVAILABLE", extra.isAvailable());

        // Student should still have exactly 5 loans
        assertEquals("Student should have exactly 5 active loans", 5, student.viewLoans().size());
    }
}
