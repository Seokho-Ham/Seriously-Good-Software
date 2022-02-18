package ch6.refer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContainerTest {

	// Test fixtures
	private Container a, b;

	@BeforeEach
	void setUp() {
		a = new Container();
		b = new Container();
	}

	// Constructor

	@Test
	void testNewContainerIsEmpty() {
		assertEquals(0, a.getAmount(), "new container is not empty");
	}

	private static final double DELTA = 0;

	@Test
	void testNewContainerIsEmptyWithEquals() {
		assertEquals(0, a.getAmount(), DELTA, "new container is not empty");
	}

	@Test
	void testNewContainerIsEmptyWithHamcrest() {
		//Hamcrest
		assertThat("new container is not empty", a.getAmount(), closeTo(0, DELTA));

		assertEquals(0, a.getAmount(), 0, "new container is not empty");
	}


	// AddWater

	@Test
	void testAddPositiveToIsolated() {
		a.addWater(1);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

	@Test
	void testAddZeroToIsolated() {
		a.addWater(0);
		assertEquals(0, a.getAmount(), "should be 0");
	}

	@Test
	void testAddValidNegativeToIsolated() {
		a.addWater(10.5);
		a.addWater(-2.5);
		assertEquals(8, a.getAmount(), "should be 8");
	}

	@Test
	void testAddPositiveToConnected() {
		a.connectTo(b);
		a.addWater(2);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

	@Test
	void testAddZeroToConnected() {
		a.connectTo(b);
		a.addWater(0);
		assertEquals(0, a.getAmount(), "should be 0");
	}

	@Test
	void testAddValidNegativeToConnected() {
		a.connectTo(b);
		a.addWater(10);
		a.addWater(-4);
		assertEquals(3, a.getAmount(), "should be 3");
	}

//	@Test(expected = IllegalArgumentException.class)
//	void testAddInvalidNegativeToConnected() {
//		a.connectTo(b);
//		a.addWater(-1);
//	}

	// Connect

	@Test
	void testConnectOtherOneOne() { // #1
		a.connectTo(b);
		a.addWater(2);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

	@Test
	void testConnectOtherTwoOne() { // #2
		Container c = new Container();
		a.connectTo(b);
		a.connectTo(c);
		a.addWater(3);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

	@Test
	void testConnectOtherOneTwo() { // #3
		Container c = new Container();
		b.connectTo(c);
		a.connectTo(b);
		a.addWater(3);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

	@Test
	void testConnectOtherTwoTwo() { // #4
		Container c = new Container(), d = new Container();
		a.connectTo(b);
		c.connectTo(d);
		a.connectTo(c);
		a.addWater(4);
		assertEquals(1, a.getAmount(), "should be 1.0");
		assertEquals(1, c.getAmount(), "should be 1.0");
	}

	@Test
	void testConnectOtherAlreadyConnected() { // #5
		Container c = new Container();
		a.connectTo(b);
		b.connectTo(c);
		a.addWater(3);
		a.connectTo(c);
		assertEquals(1, a.getAmount(), "should be 1.0");
		assertEquals(1, c.getAmount(), "should be 1.0");
	}

	@Test
	void testConnectSelfOne() { // #6
		a.addWater(1);
		a.connectTo(a);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

	@Test
	void testConnectSelfTwo() { // #7
		a.addWater(2);
		a.connectTo(b);
		a.connectTo(a);
		assertEquals(1, a.getAmount(), "should be 1.0");
	}

//	@Test(expected = NullPointerException.class)
//	void testConnectNull() { // #8
//		a.connectTo(null);
//	}

	// Extra

	@Test
	void testTransitivity() {
		Container c1 = new Container(), c2 = new Container(),
			c3 = new Container(), c4 = new Container();
		c1.connectTo(c2);
		c2.addWater(6);
		c3.connectTo(c4);
		c3.addWater(12);
		c4.connectTo(c1);
		assertEquals(4.5, c1.getAmount(), "should be 4.5");
		assertEquals(4.5, c2.getAmount(), "should be 4.5");
		assertEquals(4.5, c3.getAmount(), "should be 4.5");
		assertEquals(4.5, c4.getAmount(), "should be 4.5");
	}

	@Test
	void testCorrectPickupFromGroup() {
		a.connectTo(b);
		a.addWater(4);
		assertEquals(2, a.getAmount(), "should be 2");
		a.addWater(-3);
		assertEquals(0.5, a.getAmount(), "should be 0.5");
	}
}
