package ch3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntStats {
    private int sum;
    private List<Integer> numbers = new ArrayList<>();

    public void insert(int n) {
        numbers.add(n);
        sum += n;
    }

    public double getAverage() {
        return (double) sum / numbers.size();
    }

    public double getMedian() {
        Collections.sort(numbers);
        int size = numbers.size();
        if (size == 0) {
            throw new IllegalStateException("Empty list");
        }
        if (size % 2 == 1) {
            return numbers.get(size / 2);
        } else {
            return (double) (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2;
        }
    }

    public static void main(String ... args) {
        IntStats is = new IntStats();
        is.insert(10);
        is.insert(15);
        is.insert(5);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
        is.insert(20);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
    }
}
