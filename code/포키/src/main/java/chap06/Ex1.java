package chap06;

import java.util.ArrayList;
import java.util.List;

public class Ex1 {
    public List<Integer> getDivisors(int n) {
        List<Integer> result = new ArrayList<>();

        if (n == 0) {
            return result;
        }
        if (n < 0) {
            n = Math.abs(n);
        }

        for (int i = 1; i <= n; i++) {
            if ((n % i) == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
