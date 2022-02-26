package ch5;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

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

    public void add(T element) {
        Objects.requireNonNull(element);

        BoundedSet<T> copy = null;
        assert (copy = new BoundedSet<>(this)) != null;

        data.remove(element);
        if (data.size() == capacity) {
            data.removeFirst();
        }
        data.add(element);

        assert postAdd(copy, element);
    }

    private boolean postAdd(BoundedSet<T> copy, T element) {
        if (!data.getLast().equals(element)) {
            return false;
        }
        if (copy.data.size() == capacity && copy.data.getFirst().equals(data.getFirst())) {
            return false;
        }
        if (copy.contains(element) && copy.data.size() != data.size()) {
            return false;
        }
        return true;
    }

    public boolean contains(T element) {
        BoundedSet<T> copy = null;
        assert (copy = new BoundedSet<>(this)) != null;

        boolean result = data.contains(element);

        assert postContains(copy);

        return result;
    }

    private boolean checkInvariants() {
        if (data.size() > capacity) {
            return false;
        }
//        if (da)
        return true;
    }

    private boolean postContains(BoundedSet<T> copy) {
        return this.equals(copy);
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
        HashSet<Member> objects = new HashSet<>();
        objects.add(new Member("A", 1));
        objects.add(new Member("B", 2));
        objects.add(new Member("C", 3));
        objects.forEach(System.out::println);
    }

    private static class Member {
        private String name;
        private int age;

        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "name = " + name + " age = " + age;
        }
    }
}
