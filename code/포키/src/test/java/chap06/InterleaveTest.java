package chap06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InterleaveTest {
    private List<Integer> a, b;

    @BeforeEach
    public void setUp() {
        a = List.of(1, 2, 3);
        b = List.of(4, 5, 6);
    }

    @Test
    public void testFirstNull() {
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(null, b)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testSecondNull() {
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testBothEmpty() {
        assertThat(InterleaveLists.interleaveLists(List.of(), List.of())).isEmpty();
    }

    @Test
    public void testFirstEmpty() { // 4
        a = List.of();
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(List.of(),b)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testSecondEmpty() { // 5
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, List.of())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testBothNonEmptyDifferentLength() { // 6
        assertThatThrownBy(() -> InterleaveLists.interleaveLists(a, List.of(1,2))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testBothNonEmptySameLength() { // 7
        assertThat(InterleaveLists.interleaveLists(a, b)).isEqualTo(List.of(1,4,2,5,3,6));
    }
}
