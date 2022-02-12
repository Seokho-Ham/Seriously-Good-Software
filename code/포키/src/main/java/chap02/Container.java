package chap02;

import java.util.HashSet;
import java.util.Set;

public class Container {
    private Set<Container> group;
    private double amount;


    public Container() {
        group = new HashSet<Container>();
        group.add(this);
    }


    public double getAmount() { return amount; }


    public void connectTo(Container other) {


        if (group==other.group) return;

        int size1 = group.size(),
                size2 = other.group.size();
        double tot1 = amount * size1,
                tot2 = other.amount * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.addAll(other.group);

        for (Container c: other.group) c.group = group;
        for (Container c: group) c.amount = newAmount;
    }


    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (Container c: group) c.amount += amountPerContainer;
    }
}
