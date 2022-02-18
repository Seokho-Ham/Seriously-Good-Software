package ch6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IndexOfTest {
    private MyString hello;

    @BeforeEach
    void before() {
        hello = new MyString("hello");
    }

    @Test
    @DisplayName("String에 ch가 포함되었다면 해당 index 위치를 반환한다.")
    void chIsContainsReturnIndex() {
        assertEquals(hello.indexOf('o', 0), 4);
    }

    @Test
    @DisplayName("String에 ch가 포함되어있지 않다면 -1을 반환한다.")
    void chIsNotContainsReturnNegative() {
        assertEquals(hello.indexOf('a', 0), -1);
    }

    @Test
    @DisplayName("fromIndex가 음수라면 IndexOutOfBoundsException 예외를 발생시킨다.")
    void fromIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> hello.indexOf('o', -1));
    }
}
