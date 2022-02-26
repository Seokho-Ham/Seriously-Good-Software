package ch3;

import java.util.ArrayList;
import java.util.List;

public class IntStatsFastSelect {
    private int sum;
    private List<Integer> numbers = new ArrayList<>();

    public void insert(int n) {
        for (int i = 0; i < numbers.size(); i++) {
            if (n < numbers.get(i)) {
                numbers.add(i, n);
                break;
            }
        }
        sum += n;
    }

    public double getAverage() {
        return (double) sum / numbers.size();
    }

    public double getMedian() {
        int size = numbers.size();
        if (size == 0) {
            throw new IllegalStateException("Empty list");
        }
        if (size % 2 == 1) {
            return numbers.get(size / 2);
        }
        return (double) (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2;
    }

    public static void main(String ... args) {
        IntStatsFastSelect is = new IntStatsFastSelect();
        is.insert(10);
        is.insert(15);
        is.insert(5);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
        is.insert(20);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
    }
}
