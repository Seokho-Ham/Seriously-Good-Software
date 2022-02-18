package ch6.testable;

import java.util.Set;

public class Container {

    private Set<Container> group;
    private double amount;

    public Container(Class<? extends Set<Container>> setType)
        throws ReflectiveOperationException {
        group = setType.getDeclaredConstructor()
            .newInstance();
        group.add(this);
    }

    public double getAmount() {
        return amount;
    }

    public boolean isConnectedTo(Container other) {
        return group == other.group;
    }

    public boolean connectTo(Container other) {
        if (isConnectedTo(other)) {
            return false;
        }

        int size1 = group.size(),
            size2 = other.group.size();
        double total1 = amount * size1,
            total2 = amount * size2,
            newAmount = (total1 + total2) / (size1 + size2);

        group.addAll(other.group);
        for (Container c : other.group) {
            c.group = group;
        }
        for (Container c : group) {
            c.amount = newAmount;
        }
        return true;
    }

    public double addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (Container c : group) {
            c.amount += amountPerContainer;
        }
        return this.amount;
    }
}
