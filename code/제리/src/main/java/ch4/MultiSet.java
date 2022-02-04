package ch4;

import java.util.ArrayList;
import java.util.List;

public class MultiSet<T> {

	private final List<T> list = new ArrayList<>();

	public void add(T data) {
		list.add(data);
	}

	public long count(T data) {
		return list.stream()
			.filter(t -> t.equals(data))
			.count();
	}

}
