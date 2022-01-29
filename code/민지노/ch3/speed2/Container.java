package speed2;

public class Container {

    private double amount;
    private Container next = this;

    public void connectTo(Container other) { // this와 other의 next필드를 맞바꿈
        Container oldNext = next;
        next = other.next;
        other.next = oldNext;
    }

    public void addWater(double amount) {
        this.amount += amount;
    }

    public double getAmount() {
        updateGroup();
        return amount;
    }
    private void updateGroup() {
        Container current = this;
        double totalAmount = 0;
        int groupSize = 0;

        //물의 총량과 수조개수 구함
        do {
            totalAmount += current.amount;
            groupSize++;
            current = current.next;
        } while (current != this);

        double newAmount = totalAmount / groupSize;
        current = this;

        //물의 양 갱신
        do {
            current.amount = newAmount;
            current = current.next;
        } while (current != this);
    }
}

