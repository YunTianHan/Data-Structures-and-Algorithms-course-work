import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackIntTest {
    private StackInt stack;

    @Before
    public void setUp() {
        stack = new StackInt(10);
    }

    @Test
    public void testPushAndPop() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void testPushAndPeek() {
        stack.push(1);
        assertEquals(1, stack.peek());
    }

    @Test
    public void testPushAndPopAndPeek() {
        stack.push(1);
        assertEquals(1, stack.pop());
        stack.push(2);
        assertEquals(2, stack.peek());
    }

    @Test
    public void testPushAndPopAndPeek2() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.peek());
    }

    @Test
    public void testPushAndPopAndPeek3() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.peek());
    }

    @Test(expected = AssertionError.class)
    public void testPopOnEmptyStack() {
        stack.pop();
    }

    @Test(expected = AssertionError.class)
    public void testPeekOnEmptyStack() {
        stack.peek();
    }

    @Test(expected = AssertionError.class)
    public void testPushBeyondCapacity() {
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
    }
}
