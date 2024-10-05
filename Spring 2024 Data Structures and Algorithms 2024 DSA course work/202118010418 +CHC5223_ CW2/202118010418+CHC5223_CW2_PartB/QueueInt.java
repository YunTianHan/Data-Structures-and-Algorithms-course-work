/**
 * QueueInt is a concrete implementation of the abstract class AbsQueueInt.
 * It represents a queue of integers with a fixed capacity.
 * The queue is implemented using an array.
 */
public class QueueInt extends AbsQueueInt {

    /**
     * Constructs a new QueueInt with the specified capacity.
     *
     * @param capacity the maximum number of elements the queue can hold
     */
    public QueueInt(int capacity) {
        super(capacity);
    }

    /**
     * Adds an integer to the back of the queue.
     * If the queue is full, an AssertionError is thrown.
     *
     * @param n the integer to be added to the back of the queue
     */
    @Override
    public void addToBack(int n) {
        assert getSize() != getCapacity() : "Queue is full";
        queue[size++] = n;
    }

    /**
     * Removes and returns the integer from the front of the queue.
     * If the queue is empty, an AssertionError is thrown.
     *
     * @return the integer from the front of the queue
     */
    @Override
    public int removefromFront() {
        assert getSize() != 0 : "Queue is empty";
        int removed = queue[0];
        for (int i = 0; i < getSize() - 1; i++) {
            queue[i] = queue[i + 1];
        }
        size--;
        return removed;
    }
}
