package ch1;

import java.util.HashSet;
import java.util.Set;

public class Container {

    private Set<Container> group;
    double amount;

    public Container() {
        group = new HashSet<>();
        group.add(this);
    }

    public double getAmount() { return amount; }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (Container con : group) {
            con.amount += amountPerContainer;
        }
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

        for (Container con : other.group) {
            con.group = group;
        }

        for (Container con : group) {
            con.amount = newAmount;
        }
    }
}