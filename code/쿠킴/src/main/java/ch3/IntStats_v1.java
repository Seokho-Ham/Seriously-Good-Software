package ch3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntStats_v1 {

    private long sum;
    private List<Integer> numbers = new ArrayList<>();

    public void insert(int n) {
        numbers.add(n);
        sum += n;
    }

    public double getAverage() {
        return sum / numbers.size();
    }

    public double getMedian() {
        Collections.sort(numbers);
        int size = numbers.size();

        if (size % 2 == 1) {
            return numbers.get(size / 2);
        } else {
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        }
    }
}
