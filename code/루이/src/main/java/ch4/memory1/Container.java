package ch4.memory1;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Container> group;
    private float amount;

    public float getAmount() { return amount; }

    public void addWater(float amount) {
        if (group == null) {
            this.amount += amount;
        } else {
            float amountPerContainer = amount / group.size();
            for (Container con : group) {
                con.amount += amountPerContainer;
            }
        }
    }

    public void connectTo(Container other) {
        if (group == null) {
            group = new ArrayList<>();
            group.add(this);
        }
        if (other.group == null) {
            other.group = new ArrayList<>();
            other.group.add(other);
        }
        if (group == other.group) {
            return;
        }

        int size1 = group.size();
        int size2 = other.group.size();
        float total1 = amount * size1;
        float total2 = other.amount * size2;

        float newAmount = (total1 + total2) / (size1 + size2);

        group.addAll(other.group);

        for (Container con : other.group) {
            con.group = group;
        }

        for (Container con : group) {
            con.amount = newAmount;
        }
    }
}