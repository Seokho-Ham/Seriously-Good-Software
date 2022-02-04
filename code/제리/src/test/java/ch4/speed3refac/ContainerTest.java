package ch4.speed3refac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContainerTest {

	@Test
	@DisplayName("연습문제3 구현 테스트")
	void test() {
		Container a = new Container();
		Container b = new Container();
		Container c = new Container();
		Container d = new Container();

		a.addWater(12);
		a.connectTo(b);

		assertEquals(6, a.getAmount());
		assertEquals(6, b.getAmount());

		c.addWater(24);
		d.connectTo(c);

		assertEquals(12, c.getAmount());
		assertEquals(12, d.getAmount());

		d.connectTo(b);

		assertEquals(9, a.getAmount());


	}

}