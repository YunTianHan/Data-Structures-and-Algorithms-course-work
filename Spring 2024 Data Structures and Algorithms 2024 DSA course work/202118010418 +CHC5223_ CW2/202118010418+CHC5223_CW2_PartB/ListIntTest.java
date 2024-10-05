import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListIntTest {
    private ListInt list;

    @Before
    public void setUp() {
        list = new ListInt(10);
    }

    @Test
    public void testAppend() {
        list.append(1);
        assertTrue(list.contains(1));
    }

    @Test(expected = AssertionError.class)
    public void testAppendBeyondCapacity() {
        for (int i = 0; i < 11; i++) {
            list.append(i);
        }
    }

    @Test
    public void testContains() {
        list.append(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test(expected = AssertionError.class)
    public void testContainsOnEmptyList() {
        list.contains(1);
    }
}