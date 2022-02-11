package ch5;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class BoundedSet<T> {

	private final int maxSize;
	private final Deque<T> deque;

	public BoundedSet(int maxSize) {
		if (maxSize <= 0) {
			throw new IllegalArgumentException();
		}
		this.maxSize = maxSize;
		this.deque = new LinkedList<>();
	}

	public void add(T elem) {
		if (Objects.isNull(elem)) {
			throw new NullPointerException();
		}
		if (deque.size() == maxSize) {
			if (deque.contains(elem)) {
				deque.remove(elem);
				deque.addLast(elem);
			} else {
				deque.poll();
				deque.addLast(elem);
			}
		} else {
			deque.addLast(elem);
		}
	}

	public boolean contains(T elem) {
		if (Objects.isNull(elem)) {
			throw new NullPointerException();
		}
		return deque.contains(elem);
	}

}
