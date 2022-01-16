import java.util.HashSet;
import java.util.Set;

public class Container {
    private double amount;
    private Set<Container> directConnectionGroup = new HashSet<>();
    private Set<Container> indirectConnectionGroup = new HashSet<>();

    public double getAmount() {
        return amount;
    }

    public void connectTo(Container other) {
        makeDirectConnection(other);
        makeIndirectConnection(other);
        redistributeWater(other);
    }

    public void addWater(double amount) {
        this.amount += amount;
    }

    private void makeDirectConnection(Container other) {
        directConnectionGroup.add(other);
        other.directConnectionGroup.add(this);
    }

    private void makeIndirectConnection(Container other) {
        if (indirectConnectionGroup.size() != 0) {
            indirectConnectionGroup.forEach(s -> {
                s.indirectConnectionGroup.add(other);
                other.indirectConnectionGroup.add(s);
            });
        }
    }

    private void redistributeWater(Container other) {
        int connectedContainerCount = directConnectionGroup.size() + indirectConnectionGroup.size();
        double newAmount = ((amount * connectedContainerCount) + other.amount) / (connectedContainerCount+1);
        amount = newAmount;
        directConnectionGroup.forEach(s -> s.amount = newAmount);
        indirectConnectionGroup.forEach(s -> s.amount = newAmount);
    }
}

//요구사항
// - 서로 연결 가능한 수조를 만든다.
// - 각각의 수조는 하나의 인스턴스.
//      - 물을 담을 수 있는 변수를 가지고 있다.
//      - 서로를 연결할 수 있는 파이프 메서드가 존재한다.
//      - 연결이 되면 가지고 서로 가지고 있는 물을 같은 값으로 나눠 갖는다.
//      - 직접 연결, 간접 연결된 데이터를 저장해야한다.
