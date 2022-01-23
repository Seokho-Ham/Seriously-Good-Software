package ch3.speed2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContainerTest {

	@Test
	void test() {
		Container a = new Container();
		Container b = new Container();
		Container c = new Container();
		Container d = new Container();

		a.connectTo(b);
		b.addWater(22);

		assertEquals(11, a.getAmount());
		assertEquals(11, b.getAmount());
		assertEquals(2, a.groupSize());

//		b.connectTo(a);
//		b.addWater(4);
//		assertEquals(13, a.getAmount());
//		assertEquals(13, b.getAmount());

		c.addWater(10);
		d.connectTo(c);
		assertEquals(5, c.getAmount());
		assertEquals(5, d.getAmount());
		assertEquals(2, d.groupSize());

		a.connectTo(c);
//		System.out.println(a.getAmount());
//		System.out.println(b.getAmount());
//		System.out.println(c.getAmount());
//		System.out.println(d.getAmount());

		assertEquals(8, a.getAmount());
		assertEquals(8, b.getAmount());
		assertEquals(8, c.getAmount());
		assertEquals(8, d.getAmount());
		assertEquals(4, a.groupSize());

		c.flush();
		assertEquals(0, a.getAmount());

	}

}