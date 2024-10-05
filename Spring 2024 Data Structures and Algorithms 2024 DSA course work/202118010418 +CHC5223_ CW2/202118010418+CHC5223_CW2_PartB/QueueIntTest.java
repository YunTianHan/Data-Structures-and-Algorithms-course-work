import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueIntTest {
    private QueueInt queue;

    @Before
    public void setUp() {
        queue = new QueueInt(10);
    }

    @Test
    public void testAddToBackAndRemovefromFront() {
        queue.addToBack(1);
        queue.addToBack(2);
        queue.addToBack(3);
        assertEquals(1, queue.removefromFront());
    }

    @Test(expected = AssertionError.class)
    public void testRemovefromFrontOnEmptyQueue() {
        queue.removefromFront();
    }

    @Test(expected = AssertionError.class)
    public void testAddToBackBeyondCapacity() {
        for (int i = 0; i < 11; i++) {
            queue.addToBack(i);
        }
    }
}
