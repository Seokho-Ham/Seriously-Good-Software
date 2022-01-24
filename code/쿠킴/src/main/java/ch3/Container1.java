package ch3;

import java.util.HashSet;
import java.util.Set;

public class Container1 {
    // getAmount O(1)
    // connectTo O(n)
    // addWater O(1)
    // getSize  O(1)
    // flush() O(1)

    private Group group = new Group(this);

    private static class Group {
        double amountPerContainer;
        Set<Container1> members;

        Group(Container1 c) {
            members = new HashSet<>();
            members.add(c);
        }
    }

    public double getAmount() { return group.amountPerContainer; }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.members.size();
        group.amountPerContainer += amountPerContainer;
    }

    public void connectTo(Container1 other) {

        if (group==other.group) return;

        int size1 =       group.members.size(),
                size2 = other.group.members.size();
        double tot1 =       group.amountPerContainer * size1,
                tot2 = other.group.amountPerContainer * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.members.addAll(other.group.members);
        group.amountPerContainer = newAmount;
        for (Container1 x: other.group.members) x.group = group;
    }

    public int groupSize() {
        return group.members.size();
    }

    public void flush() {
        group.amountPerContainer = 0;
    }
}
