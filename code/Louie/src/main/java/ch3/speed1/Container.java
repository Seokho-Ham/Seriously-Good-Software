package ch3.speed1;

import java.util.HashSet;
import java.util.Set;

public class Container {

    private Group group = new Group(this);

    private static class Group {

        private Set<Container> members = new HashSet<>();
        private double amountPerContainer;

        private Group(Container container) {
            members.add(container);
        }
    }

    public double getAmount() { return group.amountPerContainer; }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.members.size();
        group.amountPerContainer += amountPerContainer;
    }

    public void connectTo(Container other) {
        if (group == other.group) {
            return;
        }

        int size1 = group.members.size();
        int size2 = other.group.members.size();
        double total1 = group.amountPerContainer * size1;
        double total2 = other.group.amountPerContainer * size2;

        group.amountPerContainer = (total1 + total2) / (size1 + size2);
        group.members.addAll(other.group.members);

        for (Container container : other.group.members) {
            container.group = group;
        }
    }

    public int groupSize() {
        return group.members.size();
    }

    public void flush() {
        group.amountPerContainer = 0;
    }
}