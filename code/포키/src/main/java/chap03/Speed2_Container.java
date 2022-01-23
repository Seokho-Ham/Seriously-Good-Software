package chap03;

public class Speed2_Container {

    private Speed2_Container next = this;
    private double amount;

    public void connectTo(Speed2_Container other) {
        Speed2_Container oldNext = next;
        next = other.next;
        other.next = oldNext;
    }

    public double getAmount() {
        return amount;
    }

    public void addWater(double amount) {
        this.amount += amount;
    }

    //현재 그룹의 사이즈
    public int groupSize() {
        Speed2_Container current = this;
        int groupSize = 0;

        do {
            groupSize++;
            current = current.next;
        } while (current != this);

        return groupSize;
    }

    //모든 물을 0으로!!
    public void flush() {
        Speed2_Container current = this;
        do {
            current.amount = 0;
            current = current.next;
        } while (current != this);
    }

    private void updateGroup() {
        Speed2_Container current = this;
        double totalAmount = 0;
        int groupSize = 0;

        do {
            totalAmount += current.amount;
            groupSize++;
            current = current.next;
        } while (current != this);

        double newAmount = totalAmount / groupSize;

        current = this;
        do {
            current.amount = newAmount;
            current = current.next;
        } while (current != this);
    }


}
