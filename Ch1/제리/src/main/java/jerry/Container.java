package jerry;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Container {

	private double amount;
	private final Set<Container> directConnectedSet;
	private final Set<Container> inDirectConnectedSet;


	public Container() {
		this.amount = 0;
		directConnectedSet = new HashSet<>();
		inDirectConnectedSet = new HashSet<>();
		directConnectedSet.add(this);
		inDirectConnectedSet.add(this);
	}

	public void connectTo(Container other) {
		this.directConnectedSet.add(other);
		other.directConnectedSet.add(this);

		Iterator<Container> iterator = directConnectedSet.iterator();

		while (iterator.hasNext()) {
			inDirectConnectedSet.addAll(iterator.next().directConnectedSet);
		}

		iterator = inDirectConnectedSet.iterator();

		while (iterator.hasNext()) {
			iterator.next().inDirectConnectedSet.addAll(inDirectConnectedSet);
		}

		double totalAmount = inDirectConnectedSet.stream().mapToDouble(container -> container.amount).sum();

		iterator = inDirectConnectedSet.iterator();
		double average = totalAmount / inDirectConnectedSet.size();

		while (iterator.hasNext()) {
			iterator.next().amount = average;
		}

	}


	public void addWater(double amount) {

		if (this.amount + amount < 0) {
			throw new IllegalArgumentException("물의 양이 충분하지 않습니다.");
		}

		Iterator<Container> iterator = inDirectConnectedSet.iterator();

		double divideAmount = amount / inDirectConnectedSet.size();

		while (iterator.hasNext()) {
			iterator.next().amount += divideAmount;
		}
	}

	public double getAmount() {
		return amount;
	}

}
