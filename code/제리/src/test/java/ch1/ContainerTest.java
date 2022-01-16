package ch1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContainerTest {

	@Test
	@DisplayName("하나의 수조 컴포넌트 테스트")
	void oneComponentConnectTest() {
		Container a = new Container();
		Container b = new Container();
		Container c = new Container();
		Container d = new Container();

		a.addWater(12);
		assertEquals(12.0, a.getAmount());

		a.connectTo(b);
		assertEquals(6.0, a.getAmount());
		assertEquals(6.0, b.getAmount());

		d.addWater(8);

		b.connectTo(c);
		assertEquals(4.0, a.getAmount());
		assertEquals(4.0, b.getAmount());
		assertEquals(4.0, c.getAmount());

		b.connectTo(d);
		assertEquals(5.0, a.getAmount());
		assertEquals(5.0, b.getAmount());
		assertEquals(5.0, c.getAmount());
		assertEquals(5.0, d.getAmount());

		b.addWater(4);
		assertEquals(6.0, a.getAmount());
		assertEquals(6.0, b.getAmount());
		assertEquals(6.0, c.getAmount());
		assertEquals(6.0, d.getAmount());

	}

	@Test
	@DisplayName("수조 컴포넌트과 연결 테스트")
	void connectToTest() {
		Container a = new Container();
		Container b = new Container();
		Container c = new Container();
		Container d = new Container();
		Container e = new Container();
		Container f = new Container();

		a.addWater(12);
		d.addWater(8);
		a.connectTo(b);
		b.connectTo(c);
		b.connectTo(d);

		e.addWater(16);
		e.connectTo(f);

		a.connectTo(e);

		assertEquals("6.0 6.0 6.0 6.0 6.0 6.0", a.getAmount() + " " + b.getAmount() + " " + c.getAmount() + " " + d.getAmount() + " " + e.getAmount() + " " + f.getAmount());

		c.addWater(6);

		assertEquals(7, a.getAmount());

		try {
			c.addWater(-10);
		} catch (IllegalArgumentException er) {
			assertEquals("물의 양이 충분하지 않습니다.", er.getMessage());
		}

	}

}