package ch4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniqueListTest {

	@Test
	@DisplayName("UniqueList Test")
	void test() {
		UniqueList<Integer> uniqueList = new UniqueList<>(5);

		assertTrue(uniqueList.set(1, 2));
		assertTrue(uniqueList.set(2, 5));
		assertFalse(uniqueList.set(2, 100));

		assertEquals(2, uniqueList.get(1));
		assertEquals(5, uniqueList.get(2));
		assertNull(uniqueList.get(0));

	}

}