package ch5.boundedset;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContractBoundedSetTest {

    private ContractBoundedSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = new ContractBoundedSet<>(3);
    }

    @Test
    public void testSingleElement() {
        set.add(1);
        assertTrue(set.contains(1));
    }

    @Test
    public void testRepeatedElement() {
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(1);
        assertTrue(set.contains(1));
    }

    @Test
    public void testOverflowKeepsSecond() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertTrue(set.contains(2));
    }

    @Test
    public void testOverflowRemovesOldest() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertFalse(set.contains(1));
    }

    @Test
    public void testOverflowKeepsNewest() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertTrue(set.contains(4));
    }

    @Test
    public void testRenewal() {
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(3);
        set.add(4);
        assertTrue(set.contains(1));
    }
}
