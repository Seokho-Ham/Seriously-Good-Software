package ch4.memory2;

public class Container {

    private Container[] group;
    private float amount;

    public float getAmount() { return amount; }

    public void addWater(float amount) {
        if (group == null) {
            this.amount += amount;
        } else {
            float amountPerContainer = amount / group.length;
            for (Container con : group) {
                con.amount += amountPerContainer;
            }
        }
    }

    public void connectTo(Container other) {
        if (group == null) {
            group = new Container[]{this};
        }
        if (other.group == null) {
            other.group = new Container[]{other};
        }
        if (group == other.group) {
            return;
        }

        int size1 = group.length;
        int size2 = other.group.length;
        float total1 = amount * size1;
        float total2 = other.amount * size2;

        float newAmount = (total1 + total2) / (size1 + size2);
        Container[] newGroup = new Container[size1 + size2];

        int i = 0;
        for (Container con : other.group) {
            con.group = newGroup;
            con.amount = newAmount;
            newGroup[i++] = con;
        }

        for (Container con : group) {
            con.group = newGroup;
            con.amount = newAmount;
            newGroup[i++] = con;
        }
    }
}