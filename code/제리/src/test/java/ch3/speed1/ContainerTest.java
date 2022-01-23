package ch3.speed1;

import static org.junit.jupiter.api.Assertions.*;

import ch3.speed1.Container;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContainerTest {

	@Test
	@DisplayName("Speed1 Inner static Class Group 따로 선언 테스트")
	void test() {
		Container a = new Container();
		Container b = new Container();
		Container c = new Container();
		Container d = new Container();

		a.addWater(12);
		assertEquals(12.0, a.getAmount());

		a.connectTo(b);
		assertEquals(6.0, a.getAmount());
		assertEquals(6.0, b.getAmount());
		assertEquals(2, a.groupSize());

		d.addWater(8);

		b.connectTo(c);
		assertEquals(4.0, a.getAmount());
		assertEquals(4.0, b.getAmount());
		assertEquals(4.0, c.getAmount());
		assertEquals(3, a.groupSize());

		b.connectTo(d);
		assertEquals(5.0, a.getAmount());
		assertEquals(5.0, b.getAmount());
		assertEquals(5.0, c.getAmount());
		assertEquals(5.0, d.getAmount());
		assertEquals(4, a.groupSize());

		b.addWater(4);
		assertEquals(6.0, a.getAmount());
		assertEquals(6.0, b.getAmount());
		assertEquals(6.0, c.getAmount());
		assertEquals(6.0, d.getAmount());

		d.flush();
		assertEquals(0, a.getAmount());

	}

}