package ch3.speed3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContainerTest {

	@Test
	void test() {
		Container a = new Container();
		Container b = new Container();
		Container c = new Container();
		Container d = new Container();

		a.addWater(12);
		a.connectTo(b);
		a.connectTo(c);
		a.connectTo(d);

		assertEquals(3, d.getAmount());
		assertEquals(4, d.getSize());

		d.flush();
		assertEquals(0, a.getAmount());

	}

}