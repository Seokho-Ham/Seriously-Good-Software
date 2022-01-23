package ch3.빠른조회;

import static org.junit.jupiter.api.Assertions.*;

import ch3.빠른삽입.IntStats;
import org.junit.jupiter.api.Test;

class IntStatsTest {

	@Test
	void test() {
		IntStats intStats = new IntStats();
		intStats.insert(5);
		intStats.insert(10);
		intStats.insert(12);

		assertEquals(9, intStats.getAverage());
		assertEquals(10, intStats.getMedian());

		intStats.insert(9);
		assertEquals(9, intStats.getAverage());
		assertEquals(9.5, intStats.getMedian());

	}

}