package ch6.practice;

import java.util.ArrayList;
import java.util.List;

public class Divisors {

    public Divisors() {
    }

    public List<Integer> getDivisors(int n) {
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        if (n < 0) {
            n *= -1;
        }
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
