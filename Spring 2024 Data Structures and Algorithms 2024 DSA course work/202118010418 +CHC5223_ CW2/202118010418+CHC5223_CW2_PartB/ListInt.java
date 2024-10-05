/**
 * ListInt is a concrete implementation of the abstract class AbsListInt.
 * It represents a list of integers with a fixed capacity.
 * The list is implemented using an array.
 */
public class ListInt extends AbsListInt {

    /**
     * Constructs a new ListInt with the specified capacity.
     *
     * @param capacity the maximum number of elements the list can hold
     */
    public ListInt(int capacity) {
        super(capacity);
    }

    /**
     * Appends an integer to the end of the list.
     * If the list is full, an AssertionError is thrown.
     *
     * @param n the integer to be appended to the list
     */
    @Override
    public void append(int n) {
        assert getSize() != getCapacity() : "List is full";
        list[size] = n;
        size++;
    }

    /**
     * Checks if the list contains a specific integer.
     * If the list is empty, an AssertionError is thrown.
     *
     * @param x the integer to be checked
     * @return true if the list contains the integer, false otherwise
     */
    @Override
    public boolean contains(int x) {
        assert getSize() != 0 : "List is empty";
        for (int i = 0; i < getSize(); i++) {
            if (list[i] == x) {
                return true;
            }
        }
        return false;
    }
}