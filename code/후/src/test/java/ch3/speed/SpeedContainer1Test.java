package ch3.speed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SpeedContainer1Test {

    @Test
    void runExampleScenario() {
        SpeedContainer1 a = new SpeedContainer1();
        SpeedContainer1 b = new SpeedContainer1();
        SpeedContainer1 c = new SpeedContainer1();
        SpeedContainer1 d = new SpeedContainer1();

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
        SpeedContainer1 a = new SpeedContainer1();
        SpeedContainer1 b = new SpeedContainer1();
        SpeedContainer1 c = new SpeedContainer1();
        SpeedContainer1 d = new SpeedContainer1();

        a.connectTo(b);
        b.connectTo(c);

        assertEquals(3, a.groupSize());
        assertEquals(1, d.groupSize());
    }

    @Test
    void flush() {
        SpeedContainer1 a = new SpeedContainer1();
        SpeedContainer1 b = new SpeedContainer1();
        SpeedContainer1 c = new SpeedContainer1();
        SpeedContainer1 d = new SpeedContainer1();

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
