import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SetIntTest {
    private SetInt set;

    @Before
    public void setUp() {
        set = new SetInt(10);
    }

    @Test
    public void testIncludeAndContains() {
        set.include(1);
        assertTrue(set.contains(1));
    }

    @Test(expected = AssertionError.class)
    public void testIncludeBeyondCapacity() {
        for (int i = 0; i < 11; i++) {
            set.include(i);
        }
    }

    @Test
    public void testExclude() {
        set.include(1);
        set.exclude(1);
        assertFalse(set.contains(1));
    }

    @Test(expected = AssertionError.class)
    public void testExcludeNonExistentElement() {
        set.exclude(1);
    }
}
