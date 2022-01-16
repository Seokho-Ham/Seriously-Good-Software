import java.util.HashSet;
import java.util.Set;

public class Container {
    private double amount;
    private Set<Container> connectedGroup = new HashSet<>();

    public Container() {
        connectedGroup.add(this);
    }

    public void connectTo(Container other) {
        if (connectedGroup == other.connectedGroup) {
            return;
        }

        int size1 = connectedGroup.size();
        int size2 = other.connectedGroup.size();
        double total1 = amount * size1;
        double total2 = other.amount * size2;
        double averageAmount = (total1 + total2) / (size1 + size2);

        connectedGroup.addAll(other.connectedGroup);

        for (Container container : other.connectedGroup) {
            container.connectedGroup = connectedGroup;
        }

        for (Container container : connectedGroup) {
            container.amount = averageAmount;
        }
    }

    public void addWater(double amount) {
        double divideSumAmount = amount / connectedGroup.size();
        for (Container container : connectedGroup) {
            container.amount += divideSumAmount;
        }
    }

    public double getAmount() {
        return amount;
    }
}
