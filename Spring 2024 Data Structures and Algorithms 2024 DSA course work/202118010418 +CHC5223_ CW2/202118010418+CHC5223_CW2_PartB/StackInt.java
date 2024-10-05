/**
 * StackInt is a concrete implementation of the abstract class AbsStackInt.
 * It represents a stack of integers with a fixed capacity.
 * The stack is implemented using an array.
 */
public class StackInt extends AbsStackInt {

    /**
     * Constructs a new StackInt with the specified capacity.
     *
     * @param capacity the maximum number of elements the stack can hold
     */
    public StackInt(int capacity) {
        super(capacity);
    }

    /**
     * Pushes an integer onto the top of the stack.
     * If the stack is full, an AssertionError is thrown.
     *
     * @param n the integer to be pushed onto the stack
     */
    @Override
    public void push(int n) {
        assert getSize() != getCapacity() : "Stack is full";
        stack[size++] = n;
    }

    /**
     * Removes and returns the integer at the top of the stack.
     * If the stack is empty, an AssertionError is thrown.
     *
     * @return the integer at the top of the stack
     */
    @Override
    public int pop() {
        assert getSize() != 0 : "Stack is empty";
        return stack[--size];
    }

    /**
     * Returns the integer at the top of the stack without removing it.
     * If the stack is empty, an AssertionError is thrown.
     *
     * @return the integer at the top of the stack
     */
    @Override
    public int peek() {
        assert getSize() != 0 : "Stack is empty";
        return stack[size - 1];
    }
}