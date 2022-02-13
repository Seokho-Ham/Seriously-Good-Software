package ch5.contracts;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Container {

    private static class ConnectPostData {

        Set<Container> group1, group2;
        double amount1, amount2;
    }

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
        if (group == other.group) {
            return;
        }
        // 2. 사후 조건 검사를 위한 데이터 준비
        ConnectPostData postData = null;
        assert (postData = saveConnectPostData(other)) != null;
        // 3. 실제 작업 수행
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
        // 4. 사후 조건 검사
        assert postConnect(postData) : "connectTo failed its post-condition!";
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        // 1. 사전 조건 검사
        if (this.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException(
                "Not enough water to match the addWater request."
            );
        }
        // 2. 사후 조건 검사를 위한 데이터 저장
        double oldTotal = 0;
        assert (oldTotal = groupAmount()) >= 0;
        // 3. 실제 작업 수행
        for (Container c : group) {
            c.amount += amountPerContainer;
        }
        // 4. 사후 조건 검사
        assert postAddWater(oldTotal, amount) :
            "addWater failed its post condition!";
    }

    private double groupAmount() {
        double total = 0;
        for (Container c : group) {
            total += c.amount;
        }
        return total;
    }

    private boolean postAddWater(double oldTotal, double addedAmount) {
        return isGroupBalanced() && almostEqual(groupAmount(), oldTotal + addedAmount);
    }

    private boolean isGroupBalanced() {
        for (Container c : group) {
            if (c.amount != amount) {
                return false;
            }
        }
        return true;
    }

    private static boolean almostEqual(double x, double y) {
        final double EPSILON = 1E-4;
        return Math.abs(x - y) < EPSILON;
    }

    private ConnectPostData saveConnectPostData(Container other) {
        ConnectPostData data = new ConnectPostData();
        data.group1 = new HashSet<>(group);
        data.group2 = new HashSet<>(other.group);
        data.amount1 = amount;
        data.amount2 = other.amount;
        return data;
    }

    private boolean postConnect(ConnectPostData postData) {
        return areGroupMembersCorrect(postData)
            && isGroupAmountCorrect(postData)
            && isGroupBalanced()
            && isGroupConsistent();
    }

    private boolean areGroupMembersCorrect(ConnectPostData postData) {
        return group.containsAll(postData.group1)
            && group.containsAll(postData.group2)
            && group.size() == postData.group1.size() + postData.group2.size();
    }

    private boolean isGroupAmountCorrect(ConnectPostData postData) {
        int size1 = postData.group1.size();
        int size2 = postData.group2.size();
        double total1 = postData.amount1 * size1;
        double total2 = postData.amount2 * size2;
        double newAmount = (total1 + total2) / (size1 + size2);
        return almostEqual(amount, newAmount);
    }

    private boolean isGroupConsistent() {
        for (Container c : group) {
            if (c.group != group) {
                return false;
            }
        }
        return true;
    }
}
