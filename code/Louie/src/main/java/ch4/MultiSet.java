package ch4;

import java.util.ArrayList;
import java.util.List;

public class MultiSet<T> {
    private List<T> elements = new ArrayList<>();
    private List<Long> repetitions = new ArrayList<>();

    public void add(T element) {
        elements.add(element);
    }

    public long count(T element) {
        return elements.stream()
                .filter(e -> e.equals(element))
                .count();
    }
}
