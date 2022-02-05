package code.포키.src.main.java.chap05;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Container {
    private Set<Container> group;
    private double amount;

    //사전 조건 : 인자가 음수일 경우, 그룹에 충분한 물이 존재한다.
    //사후 조건 : 그룹에 속하는 모든 수조에 같은 양의 물을 분배한다.
    //페널티 : IllegalArgumentException을 던진다.
    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();

        if (this.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException("물이 부족해용");
        }
        //2번
        double oldTotal = 0;
        assert (oldTotal = groupAmount()) >= 0;

        for (Container c : group) {
            c.amount += amountPerContainer;
        }
        //4번
        assert postAddWater(oldTotal, amount) : "addWater failed its postcondition";
    }

    public void connectTo(Container other) {
        Objects.requireNonNull(other,"null이면 안돼요");
        if(group==other.group) return;

        ConnectPostData postData = null;
        assert (postData = saveConnectPostData(other)) != null;

        int size1 = group.size(),
                size2 = other.group.size();
        double tot1 = amount * size1,
                tot2 = other.amount * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.addAll(other.group);
        for (Container c: other.group) c.group = group;
        for (Container c: group) c.amount = newAmount;

        assert postConnect(postData) : "connectTo failed its postcondition";

    }

    public void addWater_v2(double amount) {
        double amountPerContainer = amount / group.size();

        if (this.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException("물이 부족해용");
        }

        for (Container c : group) {
            c.amount += amountPerContainer;
        }
        //4번
        assert invariantsArePreservedByAddWater() : "addWater failed its postcondition";
    }

    private boolean invariantsArePreservedByAddWater() {
        return isGroupNonNegative() && isGroupBalanced();
    }

    public void connectTo_v2(Container other) {
        Objects.requireNonNull(other,"null 이면 안돼요!");
        if(group==other.group) return;

        int size1 = group.size(),
                size2 = other.group.size();
        double tot1 = amount * size1,
                tot2 = other.amount * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.addAll(other.group);
        for (Container c: other.group) c.group = group;
        for (Container c: group) c.amount = newAmount;

        assert invariantsArePreservedByConnectTo(other) : "connectTo failed its postcondition";

    }

    private boolean invariantsArePreservedByConnectTo(Container other) {
        return group == other &&
                isGroupNonNegative()&&
                isGroupBalanced()&&
                isGroupConsistent();
    }

    private boolean isGroupNonNegative() {
        for (Container c : group) {
            if(c.amount < 0) return false;
        }
        return true;
    }

    private boolean postConnect(ConnectPostData postData) {
        return areGroupMembersCorrect(postData)
                && isGroupAmountCorrect(postData)
                && isGroupBalanced()
                && isGroupConsistent();
    }

    private boolean isGroupConsistent() {
        for (Container c : group) {
            if(c.group != group) return false;
        }
        return true;
    }

    private boolean isGroupAmountCorrect(ConnectPostData postData) {
        return amount == postData.amount1 + postData.amount2;
    }

    private boolean areGroupMembersCorrect(ConnectPostData postData) {
        return group.containsAll(postData.group1)
                && group.containsAll(postData.group2)
                && group.size() == postData.group1.size() + postData.group2.size();
    }

    private ConnectPostData saveConnectPostData(Container other) {
        ConnectPostData data = new ConnectPostData();
        data.group1 = new HashSet<>(group);
        data.group2 = new HashSet<>(other.group);
        data.amount1 = amount;
        data.amount2 = other.amount;

        return data;
    }

    private boolean postAddWater(double oldTotal, double amount) {
        return isGroupBalanced() && almostEqual(groupAmount(),oldTotal + amount);
    }

    private boolean almostEqual(double x, double y) {
        final double EPSLION = 1E-4;
        return Math.abs(x - y) < EPSLION;
    }


    private boolean isGroupBalanced() {
        for (Container x : group) {
            if(x.amount != amount) return false;
        }
        return true;
    }

    private double groupAmount() {
        double total = 0;
        for (Container c : group) {
            total += c.amount;
        }
        return total;
    }

}
