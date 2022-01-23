package chap03;

import java.util.HashSet;
import java.util.Set;

public class Speed1_Container {
    private Group group = new Group(this);

    private static class Group {
        double amountPerContainer;
        Set<Speed1_Container> members;

        Group(Speed1_Container c) {
            members = new HashSet<>();
            members.add(c);
        }
    }

    public double getAmount() { return group.amountPerContainer; }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.members.size();
        group.amountPerContainer += amountPerContainer;
    }

    public void connectTo(Speed1_Container other) {

        if (group==other.group) return;

        int size1 =       group.members.size(),
                size2 = other.group.members.size();
        double tot1 =       group.amountPerContainer * size1,
                tot2 = other.group.amountPerContainer * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.members.addAll(other.group.members);
        group.amountPerContainer = newAmount;
        for (Speed1_Container x: other.group.members) x.group = group;
    }

    //현재 그룹의 사이즈
    public int groupSize() {
        return group.members.size();
    }

    //모든 물을 0으로!!
    public void flush() {
        group.amountPerContainer = 0;
    }

}
