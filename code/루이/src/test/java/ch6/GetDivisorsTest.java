package ch6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetDivisorsTest {

    @Test
    @DisplayName("메서드는 모든 약수를 Integer의 List에 담아 리턴한다.")
    void integerListOfAllMeasure() {
        List<Integer> positiveResults = getDivisors(0);
        assertTrue(positiveResults.isEmpty());
    }

    @Test
    @DisplayName("n==0이면 빈 리스트를 리턴한다.")
    void nIsZeroReturnEmptyList() {
        List<Integer> positiveResults = getDivisors(0);
        assertTrue(positiveResults.isEmpty());
    }

    @Test
    @DisplayName("n의 절대값이 같으면 결과도 똑같다.")
    void negativeAndPositiveEqualsResult() {
        List<Integer> positiveResults = getDivisors(12);
        List<Integer> negativeResults = getDivisors(-12);
        assertEquals(positiveResults, negativeResults);
    }

    public List<Integer> getDivisors(int n) {
        n = Math.abs(n);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                list.add(n / i);
            }
        }
        return list;
    }
}
