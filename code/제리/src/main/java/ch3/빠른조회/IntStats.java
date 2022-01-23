package ch3.빠른조회;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntStats {

	// getAverage(), getMedian -> O(1)
	private final List<Integer> list;
	private int total;

	public IntStats() {
		list = new ArrayList<>();
		total = 0;
	}

	public void insert(int n) {
		total += n;
		list.add(n);
		Collections.sort(list);
	}

	public double getAverage() {
		return (double) total / list.size();
	}

	public double getMedian() {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("비어있는 리스트입니다.");
		}
		if (list.size() % 2 == 1) {
			return list.get(list.size() / 2);
		}
		return ((double) list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
	}

}
