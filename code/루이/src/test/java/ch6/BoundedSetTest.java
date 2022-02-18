package ch6;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class BoundedSetTest {
    private BoundedSet<Integer> set;

    @BeforeEach
    void beforeEach() {
        set = new BoundedSet<>(3);
    }

    @Test
    public void testAddNull() {
        assertThrows(NullPointerException.class, () -> set.add(null));
    }

    @Test
    public void testAddOnEmpty() {
        Integer result = set.add(1);
        assertNull(result, "Wrong return value");
        assertTrue(set.contentV3().contains(1), "Wrong set content");
//        assertThat("Wrong return value", result, is(nullValue()));
//        assertThat("Wrong set content", set.contentV3(), contains(1));
    }

    @Test
    public void testAddAbsentOnNonFull() {
        set.add(1);
        Integer result = set.add(2);  // line under test
        assertNull(result, "Wrong return value");
        assertThat(set.contentV3()).withFailMessage("Wrong set content").containsExactly(1, 2);
    }
}