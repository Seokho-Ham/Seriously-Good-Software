package chap06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BoundedSetTest {
    private BoundedSet<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new BoundedSet<>(3);
    }

    @Test
    public void testAddNull() {
        assertThatThrownBy(() -> set.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testAddOnEmpty() {
        Integer result = set.add(1);
        assertThat(result).isNull();
        assertThat(set.content().contains(1)).isTrue();
    }

    @Test
    public void testAddAbsentOnNonFull() {
        set.add(1);
        Integer result = set.add(2);
        assertThat(result).isNull();
        assertThat(set.content().contains(1)).isTrue();
        assertThat(set.content().contains(2)).isTrue();
    }
}
