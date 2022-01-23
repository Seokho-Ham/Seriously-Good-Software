package ch3.speed;

public class SpeedContainer3 {

    private double amount;
    private SpeedContainer3 parent = this;
    private int size = 1;

    private SpeedContainer3 findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }
        return parent;
    }

    public double getAmount() {
        SpeedContainer3 root = findRootAndCompress();
        return root.amount;
    }

    public void addWater(double amount) {
        SpeedContainer3 root = findRootAndCompress();
        if (root.amount + amount < 0) {
            throw new IllegalArgumentException("수조에 물이 충분히 들어있지 않습니다.");
        }
        root.amount += amount / root.size;
    }

    public void connectTo(SpeedContainer3 other) {
        SpeedContainer3 thisRoot = findRootAndCompress();
        SpeedContainer3 otherRoot = other.findRootAndCompress();
        if (thisRoot == otherRoot) {
            return;
        }
        int thisSize = thisRoot.size;
        int otherSize = otherRoot.size;
        double newAmount =
            ((thisRoot.amount * thisSize) + (otherRoot.amount * otherSize))
                / (thisSize + otherSize);
        if (thisSize <= otherSize) {
            this.parent = other;
            other.amount = newAmount;
            other.size += this.size;
        } else {
            other.parent = this.parent;
            this.amount = newAmount;
            this.size += other.size;
        }
    }

    public int groupSize() {
        return findRootAndCompress().size;
    }

    public void flush() {
        findRootAndCompress().amount = 0;
    }
}
