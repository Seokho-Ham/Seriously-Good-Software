import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container a;
    Container b;
    Container c;
    Container d;

    @BeforeEach
    void beforeEach() {
        a = new Container();
        b = new Container();
        c = new Container();
        d = new Container();
    }

    @Test
    void useCase() {
        a.addWater(12);
        a.connectTo(b);
        d.addWater(8);

        assertEquals(0, a.getAmount());
        assertEquals(0, b.getAmount());
        assertEquals(0, c.getAmount());
        assertEquals(0, d.getAmount());

        b.connectTo(c);

        assertEquals(0, a.getAmount());
        assertEquals(0, b.getAmount());
        assertEquals(0, c.getAmount());
        assertEquals(0, d.getAmount());

        b.connectTo(d);

        assertEquals(0, a.getAmount());
        assertEquals(0, b.getAmount());
        assertEquals(0, c.getAmount());
        assertEquals(0, d.getAmount());
    }
}