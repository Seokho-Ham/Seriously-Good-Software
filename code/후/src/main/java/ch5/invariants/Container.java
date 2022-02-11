package ch5.invariants;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Container {

    private Set<Container> group;
    private double amount;

    public Container() {
        group = new HashSet<>();
        group.add(this);
    }

    public double getAmount() {
        return amount;
    }

    public void connectTo(Container other) {
        // 1. 사전 조건 검사
        Objects.requireNonNull(other,
            "Cannot connect to a null container.");
        // 2. 실제 작업 수행
        if (group == other.group) {
            return;
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
        // 3. 불변 조건 검사
        assert invariantsArePreservedByConnectTo(other) : "connectTo broke an invariant!";
    }

    private boolean invariantsArePreservedByConnectTo(Container other) {
        return group == other.group &&
            isGroupNonNegative() &&
            isGroupBalanced() &&
            isGroupConsistent();
    }

    private boolean isGroupNonNegative() { // Invariant I1
        for (Container x : group) {
            if (x.amount < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isGroupConsistent() { // Invariants I2, I3
        for (Container x : group) {
            if (x.group != group) {
                return false;
            }
        }
        return true;
    }

    private boolean isGroupBalanced() { // Invariant I4
        for (Container x : group) {
            if (x.amount != amount) {
                return false;
            }
        }
        return true;
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        // 1. 사전 조건 검사
        if (this.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException(
                "Not enough water to match the addWater request."
            );
        }
        // 2. 실제 작업 수행
        for (Container c : group) {
            c.amount += amountPerContainer;
        }
        // 3. 불변 조건 검사
        assert invariantsArePreservedByAddWater() : "addWater broke an invariant!";
    }

    private boolean invariantsArePreservedByAddWater() {
        return isGroupNonNegative() && isGroupBalanced();
    }
}
