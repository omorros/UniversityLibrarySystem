import org.junit.Test;
import static org.junit.Assert.*;
import main.model.IDGenerator;

public class IDGeneratorTest {

    @Test
    public void testSequentialIDs() {
        int id1 = IDGenerator.nextId();
        int id2 = IDGenerator.nextId();
        assertEquals(id1 + 1, id2);
    }
}
