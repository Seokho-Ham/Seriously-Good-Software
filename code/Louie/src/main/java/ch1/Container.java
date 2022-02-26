package ch1;

import java.util.HashSet;
import java.util.Set;

public class Container {

    private Set<Container> group;
    private double amount;

    public Container() {
        group = new HashSet<>();
        group.add(this);
    }

    public double getAmount() { return amount; }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        if (this.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException("Not enough water to match the addWater request.");
        }
        group.forEach(c -> c.amount += amountPerContainer);
    }

    public void connectTo(Container other) {
        if (group == other.group) {
            return;
        }
        int size1 = group.size();
        int size2 = other.group.size();
        double total1 = amount * size1;
        double total2 = other.amount * size2;

        double newAmount = (total1 + total2) / (size1 + size2);

        group.addAll(other.group);
        other.group.forEach(c -> c.group = group);
        group.forEach(c -> c.amount = newAmount);
    }
}