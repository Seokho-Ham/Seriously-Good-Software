package myFirstImplementation;

import java.util.HashSet;
import java.util.Set;

public class Container {

    private double amount;
    private Set<Container> connectedContainer;

    public Container() {
        amount = 0;
        connectedContainer = new HashSet<>();
        connectedContainer.add(this);
    }

    /**
     * 현재 이 컨테이너에 담긴 물의 양을 리턴한다.
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 이 수조를 다른 수조 other 에 영구적으로 연결한다.
     *
     * @param other
     */
    public void connectTo(Container other) {
        if (connectedContainer.add(other) && other.connectedContainer.add(this)) {
            divideWater(connectedContainer);
        }
    }

    /**
     * amount 만큼의 물을 이 수조에 넣거나 뺀다.
     *
     * @param amount
     */
    public void addWater(double amount) {
        if (amount < 0 && this.amount < Math.abs(amount)) {
            throw new IllegalArgumentException("수조에 물이 충분히 들어있지 않습니다.");
        }
        double dividedAmount = amount / connectedContainer.size();
        connectedContainer.forEach(c -> c.amount += dividedAmount);
    }

    /**
     * 연결된 수조들의 물을 공평하게 분산시킨다.
     *
     * @param connectedContainer
     */
    private void divideWater(Set<Container> connectedContainer) {
        Set<Container> group = new HashSet<>();
        connectedContainer.forEach(c -> group.addAll(c.connectedContainer));
        double avg = group.stream().mapToDouble(c -> c.amount).average().getAsDouble();
        group.forEach(c -> c.amount = avg);
    }
}
