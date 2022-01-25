public class Container {

    private double amount;
    private Container next = this;

    public void connectTo(Container obj) {
        Container curNext = next; // t = a
        next = obj.next; // a = b
        obj.next = curNext; // b = t
        // Circular list?, 어느 Container 에서 getAmount() 를 호출하더라도 updateGroup() 연산이 정삭적으로 동작하기 위해서
    }

    public void addWater(double amount) {
        this.amount += amount; // 단순히 += amount?, 해당 값은 updateGroup 에서 list 순회 시에 합산된 후 재분배
    }

    public double getAmount() {
        updateGroup();
        return amount;
    }

    private void updateGroup() {
        Container cur = this;
        double totalAmount = 0;
        int groupSize = 0;
        do {
            totalAmount += cur.amount;
            groupSize++;
            cur = cur.next;
        } while (cur != this);
        double newAmount = totalAmount / groupSize;
        cur = this;
        do {
            cur.amount = newAmount;
            cur = cur.next;
        } while (cur != this);
    }

    public int groupSize() {
        int size = 0;
        Container cur = this;
        while (cur != this) {
            ++size;
            cur = cur.next;
        }
        return size;
    }

    public void flush() {
        Container cur = this;
        while (cur != this) {
            cur.amount = 0;
            cur = cur.next;
        }
    }
}
