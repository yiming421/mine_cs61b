public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T first;
        private Node prev;
        private Node next;
    }

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node temp = sentinel.next;
        sentinel.next = new Node();
        sentinel.next.first = item;
        sentinel.next.prev = sentinel;
        sentinel.next.next = temp;
        temp.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node temp = sentinel.prev;
        sentinel.prev = new Node();
        sentinel.prev.first = item;
        sentinel.prev.next = sentinel;
        sentinel.prev.prev = temp;
        temp.next = sentinel.prev;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node now = sentinel.next;
        while (now != sentinel) {
            System.out.print(now.first + " ");
            now = now.next;
        }
        System.out.print("\n");
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.next.first;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.prev.first;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        int cnt = 0;
        Node now = sentinel.next;
        while (now != sentinel) {
            if (cnt == index) {
                return now.first;
            }
            cnt++;
            now = now.next;
        }
        return null;
    }
    private T getHelper(int index, Node now) {
        if (index == 0) {
            return now.first;
        }
        if (now == sentinel) {
            return null;
        }
        return getHelper(index - 1, now.next);
    }
    public T getRecursive(int index) {
        return getHelper(index, sentinel.next);
    }
}
