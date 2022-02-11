package ch5.prac2;

import java.util.ArrayList;
import java.util.List;

public class PracticeList<T> {

	private List<T> list = new ArrayList<>();

	public List<T> interleaveLists(List<T> list1, List<T> list2) {

		if (list1 == null || list2 == null) {
			throw new NullPointerException();
		}

		if (list1.size() != list2.size()) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < list1.size(); i++) {
			list.add(list1.get(i));
			list.add(list2.get(i));
		}

		return list;
	}

}
