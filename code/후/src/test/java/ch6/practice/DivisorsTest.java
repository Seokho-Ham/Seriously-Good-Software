package ch6.practice;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DivisorsTest {

    private Divisors d = new Divisors();

    @Test
    @DisplayName("0을 입력한 경우 빈 리스트 리턴")
    void testZero() {
        List<Integer> result = d.getDivisors(0);
        assertThat(result).hasSize(0);
    }

    @Test
    @DisplayName("-1을 입력한 경우 {1} 리턴")
    void testMinusOne() {
        List<Integer> result = d.getDivisors(-1);
        List<Integer> expected = List.of(1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("1을 입력한 경우 {1} 리턴")
    void testOne() {
        List<Integer> result = d.getDivisors(1);
        List<Integer> expected = List.of(1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("소수를 입력한 경우 {1, n}리턴")
    void testPrime() {
        List<Integer> result = d.getDivisors(7);
        List<Integer> expected = List.of(1, 7);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("소수의 마이너스 부호를 입력한 경우 {1, -n}리턴")
    void testMinusPrime() {
        List<Integer> result = d.getDivisors(-7);
        List<Integer> expected = List.of(1, 7);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("약수가 3이상인 양수를 입력한 경우")
    void testNonPrime() {
        List<Integer> result = d.getDivisors(4);
        List<Integer> expected = List.of(1, 2, 4);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("약수가 3이상인 음수를 입력한 경우")
    void testMinusNonPrime() {
        List<Integer> result = d.getDivisors(-4);
        List<Integer> expected = List.of(1, 2, 4);
        assertThat(result).isEqualTo(expected);
    }
}
