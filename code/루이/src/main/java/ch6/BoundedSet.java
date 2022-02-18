package ch6;

import java.util.*;

public class BoundedSet<T> {
    private LinkedList<T> data = new LinkedList<>();
    private final int capacity;

    public BoundedSet(int capacity) {
        this.capacity = capacity;
    }

    public BoundedSet(BoundedSet<T> other) {
        data = new LinkedList<>(other.data);
        this.capacity = other.capacity;
    }

    public T add(T element) {
        Objects.requireNonNull(element);
        data.remove(element);
        T removed = null;
        if (data.size() == capacity) {
            removed = data.removeFirst();
        }
        data.add(element);
        return removed;
    }

    public boolean contains(T element) {
        return data.contains(element);
    }

    public List<T> contentV3() {
        return Collections.unmodifiableList(data);
    }

    public Iterator<T> contentV4() {
        return Collections.unmodifiableList(data).iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoundedSet<?> that = (BoundedSet<?>) o;
        return capacity == that.capacity && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, capacity);
    }

    public static void main(String[] args) {
        BoundedSet<Integer> set = new BoundedSet<>(10);
        set.add(2);
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(2);
        set.add(1);

        set.data.forEach(System.out::println);
    }
}
