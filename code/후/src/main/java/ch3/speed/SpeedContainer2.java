package ch3.speed;

public class SpeedContainer2 {

    private double amount;
    private SpeedContainer2 next = this;

    private void updateGroup() {
        SpeedContainer2 current = this;
        double totalAmount = 0;
        int groupSize = 0;

        do { // 첫 번째 순회: 물의 총량과 수조 개수를 구함
            totalAmount += current.amount;
            groupSize++;
            current = current.next;
        } while (current != this);
        double newAmount = totalAmount / groupSize;

        current = this;
        do { // 두 번째 순회: 물의 양을 갱신
            current.amount = newAmount;
            current = current.next;
        } while (current != this);
    }

    public double getAmount() {
        updateGroup(); // 지원 메서드에서 물의 양을 분배
        return amount;
    }

    public void connectTo(SpeedContainer2 other) {
        // 물 재분배 하지 않음
        SpeedContainer2 oldNext = next;
        next = other.next;
        other.next = oldNext;
    }

    public void addWater(double amount) {
        //현재 수조만 갱신
        this.amount += amount;
    }

    public int groupSize() {
        int groupSize = 1;
        SpeedContainer2 current = this;
        while (current.next != this) {
            groupSize++;
            current = current.next;
        }
        return groupSize;
    }

    public void flush() {
        SpeedContainer2 current = this;
        while (current.next != this) {
            current.amount = 0;
            current = current.next;
        }
    }
}
