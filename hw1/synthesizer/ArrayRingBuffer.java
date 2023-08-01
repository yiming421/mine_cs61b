package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /* iterator of the class **/
    private class BufferIterator implements Iterator<T> {
        private int ptr = first;
        private int cnt = 0;

        /* judge if the iterator can be advanced **/
        @Override
        public boolean hasNext() {
            return cnt != fillCount;
        }

        /* advance the iterator **/
        @Override
        public T next() {
            T temp = rb[ptr];
            ptr++;
            if (ptr == rb.length) {
                ptr = 0;
            }
            cnt++;
            return temp;
        }
    }

    /* return an iterator for the class **/
    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last++;
        if (last >= capacity) {
            last = 0;
        }
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        rb[first] = null;
        first++;
        if (first >= capacity) {
            first = 0;
        }
        fillCount--;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
    }
}
