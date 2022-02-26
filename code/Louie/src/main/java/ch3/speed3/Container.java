package ch3.speed3;

public class Container {
    private double amount;
    private Container parent = this;
    private int size = 1;

    private Container findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }
        return parent;
    }

    public double getAmount() {
        return findRootAndCompress().amount;
    }

    public void addWater(double amount) {
        Container root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Container other) {
        Container root = findRootAndCompress();
        Container otherRoot = other.findRootAndCompress();

        if (root == otherRoot) {
            return;
        }

        int rootSize = root.size;
        int otherRootSize = otherRoot.size;

        double rootTotalAmount = rootSize * root.amount;
        double otherRootTotalAmount = otherRootSize * otherRoot.amount;

        double resultAmount = (rootTotalAmount + otherRootTotalAmount) / (rootSize + otherRootSize);

        if (rootSize > otherRootSize) {
            otherRoot.parent = root;
            root.size += otherRootSize;
            root.amount = resultAmount;
        } else {
            root.parent = otherRoot;
            otherRoot.size += rootSize;
            otherRoot.amount = resultAmount;
        }
    }

    public int groupSize() {
        return findRootAndCompress().size;
    }

    public void flush() {
        findRootAndCompress().amount = 0;
    }
}