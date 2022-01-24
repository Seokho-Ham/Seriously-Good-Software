package ch3;

import java.util.ArrayList;
import java.util.List;

public class IntStats_v2 {

    private long sum;
    private List<Integer> numbers = new ArrayList<>();

    public void insert(int n) {
        int i = 0;
        for (Integer k : numbers) {
            if (k >= n) break;
            i++;
        }
        numbers.add(i, n); // 정렬
        sum += n;
    }

    public double getAverage() {
        return sum / numbers.size();
    }

    public double getMedian() {
        int size = numbers.size();

        if (size % 2 == 1) {
            return numbers.get(size / 2);
        } else {
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        }
    }
}
