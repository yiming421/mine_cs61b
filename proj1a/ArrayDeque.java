import static java.lang.System.arraycopy;

public class ArrayDeque<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    ArrayDeque(){
        items = (T []) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst += items.length;
        }
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast++;
        if (nextLast >= items.length) {
            nextLast -= items.length;
        }
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int start = nextFirst;
        for (int i = 1; i <= size; ++i) {
            if (start + i >= items.length) {
                start -= items.length;
            }
            System.out.println(items[start + i]);
            System.out.println(" ");
        }
    }

    public T removeFirst() {
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        nextFirst++;
        if (nextFirst >= items.length) {
            nextFirst -= items.length;
        }
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return temp;
    }

    public T removeLast() {
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        nextLast--;
        if (nextLast < 0) {
            nextLast += items.length;
        }
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
        return temp;
    }

    public T get(int index) {
        int idx = (index + nextFirst + 1) % items.length;
        return items[idx];
    }

    private void resize(int _size) {
        T [] newItems = (T []) new Object[_size];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }
}
