import java.util.HashSet;
import java.util.Set;

public class Container {
    private double amount;
    private Set<Container> connectedGroup = new HashSet<>();

    public Container() {

    }

    public void connectTo(Container other) {

    }

    public void addWater(double amount) {
        
    }

    public double getAmount() {
        return amount;
    }
}
