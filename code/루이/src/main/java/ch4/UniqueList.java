package ch4;

import java.util.ArrayList;
import java.util.List;

public class UniqueList<E> {
    private List<E> list;

    public UniqueList(int capacity) {
        list = new ArrayList<>(capacity);
    }

    public boolean set(int index, E element) {
        if (list.contains(element)) {
            return false;
        }
        try {
            list.add(index, element);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public E get(int index) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        UniqueList<Object> list = new UniqueList<>(10);
        System.out.println(list.set(100, 1));
        System.out.println(list.get(100));
        for (Object o : list.list) {
            System.out.println(o);
        }
    }
}
