package ch3.speed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SpeedContainer3Test {

    @Test
    void runExampleScenario() {
        SpeedContainer3 a = new SpeedContainer3();
        SpeedContainer3 b = new SpeedContainer3();
        SpeedContainer3 c = new SpeedContainer3();
        SpeedContainer3 d = new SpeedContainer3();

        a.addWater(12);
        d.addWater(8);
        a.connectTo(b);
        assertEquals(6.0, a.getAmount());
        assertEquals(6.0, b.getAmount());
        assertEquals(0.0, c.getAmount());
        assertEquals(8.0, d.getAmount());

        b.connectTo(c);
        assertEquals(4.0, a.getAmount());
        assertEquals(4.0, b.getAmount());
        assertEquals(4.0, c.getAmount());
        assertEquals(8.0, d.getAmount());

        b.connectTo(d);
        assertEquals(5.0, a.getAmount());
        assertEquals(5.0, b.getAmount());
        assertEquals(5.0, c.getAmount());
        assertEquals(5.0, d.getAmount());
    }

    @Test
    void groupSize() {
        SpeedContainer3 a = new SpeedContainer3();
        SpeedContainer3 b = new SpeedContainer3();
        SpeedContainer3 c = new SpeedContainer3();
        SpeedContainer3 d = new SpeedContainer3();

        a.connectTo(b);
        b.connectTo(c);

        assertEquals(3, a.groupSize());
        assertEquals(1, d.groupSize());
    }

    @Test
    void flush() {
        SpeedContainer3 a = new SpeedContainer3();
        SpeedContainer3 b = new SpeedContainer3();
        SpeedContainer3 c = new SpeedContainer3();
        SpeedContainer3 d = new SpeedContainer3();

        a.addWater(12);
        d.addWater(8);

        a.connectTo(b);
        b.connectTo(c);
        b.connectTo(d);

        a.flush();
        assertEquals(0, a.getAmount());
        assertEquals(0, b.getAmount());
        assertEquals(0, c.getAmount());
        assertEquals(0, d.getAmount());
    }
}
