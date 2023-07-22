public class LinkedListDeque<T> {
    private node sentinel;
    private int size;

    public class node{
        public T first;
        public node prev;
        public node next;
    }

    public LinkedListDeque() {
        sentinel = new node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        node temp = sentinel.next;
        sentinel.next = new node();
        sentinel.next.first = item;
        sentinel.next.prev = sentinel;
        sentinel.next.next = temp;
        size++;
    }

    public void addLast(T item) {
        node temp = sentinel.prev;
        sentinel.prev = new node();
        sentinel.prev.first = item;
        sentinel.prev.next = sentinel;
        sentinel.prev.prev = temp;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        node now = sentinel.next;
        while (now != sentinel) {
            System.out.println(now.first);
            System.out.println(" ");
            now = now.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.next.first;
        sentinel.next = sentinel.next.next;//
        size--;
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.prev.first;
        sentinel.prev = sentinel.prev.prev;//
        size--;
        return temp;
    }

    public T get(int index) {
        int cnt = 0;
        node now = sentinel.next;
        while (now != sentinel) {
            if (cnt == index) {
                return now.first;
            }
        }
        return null;
    }
    private T getHelper(int index, node now) {
        if (index == 0) {
            return now.first;
        }
        if(now == sentinel){
            return null;
        }
        return getHelper(index - 1, now.next);
    }
    public T getRecursive(int index) {
        return getHelper(index, sentinel.next);
    }
}
