package ch3.speed;

import java.util.*;

public class SpeedContainer1 {

    private static class Group {

        double amountPerContainer;
        Set<SpeedContainer1> members;

        Group(SpeedContainer1 c) {
            members = new HashSet<>();
            members.add(c);
        }
    }

    private Group group = new Group(this);

    public double getAmount() {
        return group.amountPerContainer;
    }

    public void connectTo(SpeedContainer1 other) {
        if (group == other.group) {
            return;
        }

        int size1 = group.members.size();
        int size2 = other.group.members.size();
        double total1 = group.amountPerContainer * size1;
        double total2 = other.group.amountPerContainer * size2;
        double newAmount = (total1 + total2) / (size1 + size2);

        group.members.addAll(other.group.members);
        group.amountPerContainer = newAmount;
        for (SpeedContainer1 c : other.group.members) {
            c.group = group;
        }
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.members.size();
        group.amountPerContainer += amountPerContainer;
    }

    public int groupSize() {
        return group.members.size();
    }

    public void flush() {
        group.amountPerContainer = 0;
    }
}
