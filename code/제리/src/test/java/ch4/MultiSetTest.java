package ch4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultiSetTest {

	@Test
	@DisplayName("MultiSetTest")
	void test() {
		MultiSet<Integer> multiSet = new MultiSet<>();
		multiSet.add(10);
		multiSet.add(10);
		multiSet.add(20);

		Assertions.assertEquals(2, multiSet.count(10));
		Assertions.assertEquals(1, multiSet.count(20));

	}

}