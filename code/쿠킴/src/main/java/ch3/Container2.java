package ch3;

public class Container2 {
    // getAmount O(n)
    // connectTo O(1)
    // addWater O(1)
    // getSize  O(n)
    // flush() O(n)

    private double amount;
    private Container2 next = this;

    public void connectTo(Container2 other) {
        Container2 oldNext = next;
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
        Container2 current = this;
        double totalAmount = 0;
        int groupSize = 0;
        // First pass: collect amounts and count
        do {
            totalAmount += current.amount;
            groupSize++;
            current = current.next;
        } while (current != this);
        double newAmount = totalAmount / groupSize;
        current = this;
        // Second pass: update amounts
        do {
            current.amount = newAmount;
            current = current.next;
        } while (current != this);
    }

    public int groupSize() {
        int count = 1;
        Container2 tmp = this;
        while (tmp != this) {
            count += 1;
            tmp = tmp.next;
        }
        return count;
    }

    public void flush() {
        Container2 tmp = this;
        tmp.amount = 0;
        while (tmp != this) {
            tmp = tmp.next;
            tmp.amount = 0;
        }
    }
}
