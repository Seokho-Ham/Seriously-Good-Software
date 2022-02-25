package ch7.readable;

import java.util.HashSet;
import java.util.Set;

/**
 * A <code>Container</code>는 사실상 용량 제한이 없는 수조를 나타낸다.
 * <p>
 * 물은 넣거나 뺄 수 있다. 두 수조는 영구적인 파이프로 연결될 수 있다. 두 수조를 직/간접적으로 연결하면 두 수조는 상호작용을 주고받으며 물은 둘 사이에
 * <b>균등하게</b> 배분된다.
 * <p>
 * 이 수조에 연결된 모든 수조의 집합을 이 수조의
 * <i>group</i> 이라고 한다.
 *
 * @author Marco Faella
 * @version 1.0
 */
public class Container {

    private Set<Container> group;
    private double amount;

    /**
     * 빈 수조를 만든다.
     */
    public Container() {
        group = new HashSet<>();
        group.add(this);
    }

    /**
     * 이 수조에 현재 담겨 있는 물의 양을 리턴한다.
     *
     * @return 이 수조에 현재 담겨 있는 물의 양을 리턴한다.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 이 수조를 다른 수조에 연결한다.
     *
     * @param other 이 수조에 연결할 수조
     */
    public void connectTo(Container other) {
        if (this.isConnectedTo(other)) {
            return;
        }

        double newAmount = (groupAmount() + other.groupAmount()) /
            (groupSize() + other.groupSize());
        mergeGroupWith(other.group);
        setAllAmountsTo(newAmount);
    }

    private void mergeGroupWith(Set<Container> otherGroup) {
        group.addAll(otherGroup);
        for (Container x : otherGroup) {
            x.group = group;
        }
    }

    private void setAllAmountsTo(double amount) {
        for (Container x : group) {
            x.amount = amount;
        }
    }

    /**
     * 이 수조가 주어진 수조와 연결됐는지 확인한다.
     *
     * @param other 연결 여부를 검사할 수조
     * @return 이 수조가 <code>other</code>에 연결됐다면 <code>true</code>
     */
    public boolean isConnectedTo(Container other) {
        return group == other.group;
    }

    /**
     * 이 수조의 그룹에 포함된 수조의 개수를 리턴
     *
     * @return 그룹의 크기
     */
    public int groupSize() {
        return group.size();
    }

    /**
     * 이 수조의 그룹에 담긴 전체 물의 양을 리턴
     *
     * @return 이 수조의 그룹에 담긴 전체 물의 양
     */
    public double groupAmount() {
        return amount * group.size();
    }

    /**
     * 이 수조에 물을 더한다.
     * <code>amount</code>가 음수이면 물을 뺀다.
     * 그 경우, 요청을 만족시키기에 충분한 물이 그룹에 담겨 있어야 한다.
     *
     * @param amount 더할 물의 양
     */
    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (Container c : group) {
            c.amount += amountPerContainer;
        }
    }
}
