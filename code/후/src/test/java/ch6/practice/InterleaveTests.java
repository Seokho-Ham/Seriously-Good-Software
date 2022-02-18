package ch6.practice;

import static org.assertj.core.api.Assertions.*;

import ch5.practice.InterleaveLists;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InterleaveTests {

    private List<Integer> a, b, result;

    @BeforeEach
    public void setUp() {
        a = List.of(1, 2, 3);
        b = List.of(4, 5, 6);
    }

    @Test
    @DisplayName("첫 번째 리스트가 null인 경우, NPE")
    void testFirstNull() {
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(null, b))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("두 번째 리스트가 null인 경우, NPE")
    void testSecondNull() {
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("두 리스트가 모두 비어 있는 경우, 빈 리스트 리턴")
    void testBothEmpty() {
        a = List.of();
        b = List.of();
        result = InterleaveLists.interleaveLists(a, b);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("첫 번째 리스트가 비어 있는 경우")
    void testFirstEmpty() {
        a = List.of();
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, b))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 번째 리스트가 비어 있는 경우")
    void testSecondEmpty() {
        b = List.of();
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, b))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 리스트의 길이가 다른 경우")
    void testBothNonEmptyDifferentLength() {
        b = List.of(4, 5);
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, b))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 리스트의 길이가 같은 경우")
    public void testBothNonEmptySameLength() {
        result = InterleaveLists.interleaveLists(a, b);
        List<Integer> expected = List.of(1, 4, 2, 5, 3, 6);
        assertThat(result).isEqualTo(expected);
    }
}
