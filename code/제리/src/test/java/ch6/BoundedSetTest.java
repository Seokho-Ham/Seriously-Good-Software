package ch6;

import static org.junit.jupiter.api.Assertions.*;

import ch5.BoundedSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoundedSetTest {

	private BoundedSet<Integer> set;

	@BeforeEach
	void setUp() {
		set = new BoundedSet<>(3);
	}

	@Test
	@DisplayName("null 입력")
	void inputNullTest() {
		assertThrows(NullPointerException.class, () -> set.add(null));
	}

	@Test
	@DisplayName("add 테스트")
	void addTest() {
		assertNull(set.add(1));
		assertNull(set.add(2));
		assertNull(set.add(3));
		assertNull(set.add(1));
		assertEquals(2, set.add(5));
	}

}