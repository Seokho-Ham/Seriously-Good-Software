package ch3.intstatistics;

import java.util.*;

public class QuickInsertIntStats implements IntStats {

    private List<Integer> numbers = new ArrayList<>();
    private int total = 0;

    @Override
    public void insert(int n) {
        numbers.add(n);
        total += n;
    }

    @Override
    public double getAverage() {
        return (double) total / numbers.size();
    }

    @Override
    public double getMedian() throws IllegalStateException {
        Collections.sort(numbers);
        final int size = numbers.size();
        if (size == 0) {
            throw new IllegalStateException("리스트가 비어있습니다.");
        }
        if (size % 2 == 0) {
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        }
        return numbers.get(size / 2);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getTotal() {
        return total;
    }
}
