package ch6.refer;

import java.util.HashSet;
import java.util.Set;

public class Container {

	private Set<Container> group;
	private double amount;

	/* Creates an empty container. */
	public Container() {
		group = new HashSet<>();
		group.add(this);
	}

	/* Returns the amount of water held in this container */
	public double getAmount() { return amount; }

	/* Connects this container with other. */
	public void connectTo(Container other) {

		// If they are already connected, do nothing
		if (group==other.group) return;

		int size1 = group.size(),
			size2 = other.group.size();
		double tot1 = amount * size1,
			tot2 = other.amount * size2,
			newAmount = (tot1 + tot2) / (size1 + size2);

		// Merge the two groups
		group.addAll(other.group);
		// Update group of containers connected with other
		for (Container c: other.group) c.group = group;
		// Update amount of all newly connected containers
		for (Container c: group) c.amount = newAmount;
	}

	/* Adds water to this container. */
	public void addWater(double amount) {
		double amountPerContainer = amount / group.size();
		for (Container c: group) c.amount += amountPerContainer;
	}
}