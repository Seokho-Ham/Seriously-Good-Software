package chap06;

import java.util.*;

public class BoundedSet<T> {
    private final LinkedList<T> data;
    private final int capacity;

    public BoundedSet(int capacity) {
        this.data = new LinkedList<>();
        this.capacity = capacity;
    }

    public BoundedSet(BoundedSet<T> other) {
        data = new LinkedList<>(other.data);
        capacity = other.capacity;
    }

    public T add(T elem) {
        if (elem==null)
            throw new NullPointerException();
        T removed = null;
        data.remove(elem);

        if (data.size() == capacity) {
            removed = data.removeFirst();
        }
        data.addLast(elem);
        return removed;
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    public List<T> content() {
        return Collections.unmodifiableList(data);
    }
}

