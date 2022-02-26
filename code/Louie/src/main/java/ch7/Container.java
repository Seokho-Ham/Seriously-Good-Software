package ch7;

import java.util.HashSet;
import java.util.Set;

/**
 *  A <code>Container</code> represents a water container
 *  with virtually unlimited capacity.
 *  <p>
 *  Water can be added or removed.
 *  Two containers can be connected with a permanent pipe.
 *  When two containers are connected, directly or indirectly,
 *  they become communicating vessels, and water will <b>distribute
 *  equally</b> among all of them.
 *  <p>
 *  The set of all containers connected to this one is called the
 *  <i>group</i> of this container.
 *
 *  @author Marco Faella
 *  @version 1.0
 */

public class Container {

    private Set<Container> group;
    private double amount;

    /** 빈 수조를 만든다. */
    public Container() {
        group = new HashSet<>();
        group.add(this);
    }

    /** 수조에 현재 담겨 있는 물의 양을 리턴한다.
     *
     * @return 수조에 현재 담겨 있는 물의 양을 리턴한다.
     */
    public double getAmount() { return amount; }

    /** 이 수조를 다른 수조에 연결한다.
     *
     * @param other 이 수조에 연결할 수조
     */
    public void connectToV1(Container other) {
//        이미 연결됐으면 아무 것도 하지 않는다.
        if (group == other.group) {
            return;
        }
        int size1 = group.size();
        int size2 = other.group.size();
        double total1 = amount * size1;
        double total2 = other.amount * size2;
        double newAmount = (total1 + total2) / (size1 + size2);

//        두 그룹을 병합
        group.addAll(other.group);
        other.group.forEach(c -> c.group = group);

//        모든 수조의 물의 양 수정정
        group.forEach(c -> c.amount = newAmount);
    }

    public void connectToV2(Container other) {
        if (isConnectedTo(other)) {
            return;
        }
        double newAmount = (groupAmount() + other.groupAmount()) / (groupSize() + other.groupSize());

        mergeGroupWith(other.group);
        setAllAmountsTo(newAmount);
    }

    /** 이 수조에 물을 더한다.
     * <code>amount</code>가 음수이면 물을 뺀다.
     * 그러한 경우 요청을 만족시키기에 충분한 물이
     * 그룹에 담겨 있어야한다.
     *
     * @param amount 더할 물의 양
     */
    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        group.forEach(c -> c.amount += amountPerContainer);
    }

    private void mergeGroupWith(Set<Container> otherGroup) {
        group.addAll(otherGroup);
        for (Container x: otherGroup) {
            x.group = group;
        }
    }

    private void setAllAmountsTo(double amount) {
        for (Container x: group) {
            x.amount = amount;
        }
    }

    public boolean isConnectedTo(Container other) {
        return group == other.group;
    }

    public int groupSize() {
        return group.size();
    }

    public double groupAmount() {
        return amount * group.size();
    }
}