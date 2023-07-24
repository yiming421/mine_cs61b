
public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
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
        //printDeque();
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
            System.out.print(items[start + i] + " ");
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size * 4 <= items.length) {
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
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size * 4 <= items.length) {
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

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        int nowFirst = (nextFirst + 1) % items.length;
        int nowLast = (nextLast - 1) % items.length;

        if (nowLast >= nowFirst) {
            System.arraycopy(items, nowFirst, newItems, 0, size);
        } else {
            System.arraycopy(items, nowFirst, newItems, 0, items.length - nowFirst);
            System.arraycopy(items, 0, newItems, items.length - nowFirst, nowLast + 1);
        }
        nextFirst = newSize - 1;
        nextLast = size;
        items = newItems;
        /*System.out.println(nextFirst);
        System.out.println(nextLast);
        System.out.println(items.length);
        printDeque();*/
    }
}
