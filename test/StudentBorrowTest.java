import org.junit.Test;
import static org.junit.Assert.*;
import main.model.*;

public class StudentBorrowTest {

    @Test
    public void testStudentBorrowAppliesPolicy() {
        Student s = new Student(1, "Oriol", "omorros@mail.com", "SE", 3);
        Product audio = new Audiobook(10, "AI Basics", "Narrator");
        Policy basePolicy = new Policy(14, 2, 0.5);

        boolean success = s.borrowProduct(audio, basePolicy);

        assertTrue(success);
        assertFalse(audio.isAvailable());   // Product should be checked out
    }
}
