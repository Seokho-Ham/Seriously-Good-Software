package ch4;

public class UniqueList<E> {

	private final Object[] data;

	public UniqueList(int capacity) {
		data = new Object[capacity];
		for (Object datum : data) {
			datum = null;
		}
	}

	public boolean set(int index, E elem) {

		if (index < 0 || index >= data.length) {
			return false;
		}

		if (data[index] != null) {
			return false;
		}

		data[index] = elem;
		return true;
	}

	public E get(int index) {
		if (index < 0 || index >= data.length) {
			return null;
		}

		if (data[index] == null) {
			return null;
		}
		return (E) data[index];
	}

}
