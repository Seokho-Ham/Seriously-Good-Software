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

        assertEquals(6, a.getAmount());
        assertEquals(6, b.getAmount());
        assertEquals(0, c.getAmount());
        assertEquals(8, d.getAmount());

        b.connectTo(c);

        assertEquals(4, a.getAmount());
        assertEquals(4, b.getAmount());
        assertEquals(4, c.getAmount());
        assertEquals(8, d.getAmount());

        b.connectTo(d);

        assertEquals(5, a.getAmount());
        assertEquals(5, b.getAmount());
        assertEquals(5, c.getAmount());
        assertEquals(5, d.getAmount());
    }
}