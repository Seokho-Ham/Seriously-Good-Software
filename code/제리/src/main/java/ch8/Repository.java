package ch8;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

	private final List<T> elements;
	private final List<Object> monitors;

	public Repository(int n) {
		elements = new ArrayList<>(n);
		monitors = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			elements.add(null);
			monitors.add(new Object());
		}
	}

	public T set(int i, T elem) {
		synchronized (monitors.get(i)) {
			return elements.set(i, elem);
		}
	}

	public void swap(int i, int j) {
		Object first;
		Object second;
		if (i < j) {
			first = monitors.get(j);
			second = monitors.get(i);
		} else {
			first = monitors.get(i);
			second = monitors.get(j);
		}

		synchronized (first) {
			synchronized (second) {
				T tI = elements.get(i);
				T tJ = elements.get(j);
				elements.set(i, tJ);
				elements.set(j, tI);
			}
		}

	}

}
