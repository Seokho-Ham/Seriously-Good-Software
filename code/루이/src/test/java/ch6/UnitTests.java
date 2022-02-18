package ch6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    private Container a, b;

    @BeforeEach
    void beforeEach() {
        a = new Container();
        b = new Container();
    }

    @Test
    void newContainerIsEmpty() {
        String message = "new container is not empty";

        assertTrue(a.getAmount() == 0, message);
        assertEquals(a.getAmount(), 0,  0, message);
        assertThat(message, a.getAmount(), closeTo(0, 0));
    }

    @Test
    void addValidNegativeToConnected() {
        a.connectTo(b);
        a.addWater(10);
        a.addWater(-4);
        assertTrue(a.getAmount() == 3, "should be 3");
    }

    @Test
    void addPositiveToIsolated() {
        a.addWater(1);
        assertTrue(a.getAmount() == 1, "should be 1.0");
    }

    @Test
    void addZeroToIsolated() {
        a.addWater(0);
        assertTrue(a.getAmount() == 0, "should be 0");
    }

    @Test
    void addValidNegativeToIsolated() {
        a.addWater(10.5);
        a.addWater(-2.5);
        assertTrue(a.getAmount() == 8, "should be 8");
    }

    @Test
    void addInvalidNegativeToIsolated() {
        assertThrows(IllegalArgumentException.class, () -> a.addWater(-1));
    }

    @Test
    void connectOtherOneOne() { //C1=other, C2=1, C3=1, C4=false
        a.connectTo(b);
        a.addWater(2);
        assertTrue(a.getAmount() == 1, "should be 1.0");
    }

    @Test
    void connectOtherTowOne() {
        Container c = new Container();
        a.connectTo(b);
        a.connectTo(c);
        a.addWater(3);
        assertTrue(a.getAmount() == 1, "should be 1.0");
    }

    @Test
    void connectOtherOneTwo() {
        Container c = new Container();
        b.connectTo(c);
        a.connectTo(b);
        a.addWater(3);
        assertTrue(a.getAmount() == 1, "should be 1.0");
    }


}
