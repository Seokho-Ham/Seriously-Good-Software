package myFirstImplementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContainerTest {

    @Test
    void runExampleScenario() {
        Container a = new Container();
        Container b = new Container();
        Container c = new Container();
        Container d = new Container();

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
}
