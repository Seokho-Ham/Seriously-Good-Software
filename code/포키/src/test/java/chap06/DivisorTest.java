package chap06;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisorTest {
    private static Ex1 sut;

    @BeforeAll
    public static void setUp() {
        sut = new Ex1();
    }

    @Test
    @DisplayName("인자가 0인 테스트")
    public void test1() {
        assertThat(sut.getDivisors(0)).isEmpty();
    }

    @Test
    @DisplayName("인자가 1인 테스트")
    public void test2() {
        assertThat(sut.getDivisors(1).contains(1)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    @DisplayName("인자가 2인 테스트")
    public void test3(int number) {
        assertThat(sut.getDivisors(2).contains(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,5,10})
    @DisplayName("인자가 10인 테스트")
    public void test4(int number) {
        assertThat(sut.getDivisors(10).contains(number)).isTrue();
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,6,12})
    @DisplayName("인자가 -12인 테스트")
    public void test5(int number) {
        assertThat(sut.getDivisors(-12).contains(number)).isTrue();
    }
}
