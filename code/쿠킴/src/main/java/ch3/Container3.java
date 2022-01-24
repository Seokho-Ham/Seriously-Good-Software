package ch3;

public class Container3 {
    // getAmount O(log n)
    // connectTo O(log n)
    // addWater O(log n)
    // getSize  O(log n)
    // flush() O(log n)


    private double amount;
    private Container3 parent = this;
    private int size = 1;

    private Container3 findRootAndCompress() {
        if (parent != this)
            parent = parent.findRootAndCompress();
        return parent;
    }

    public double getAmount() {
        Container3 root = findRootAndCompress();
        return root.amount;
    }
    public void addWater(double amount) {
        Container3 root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Container3 other) {
        Container3 root1 = findRootAndCompress(),
                root2 = other.findRootAndCompress();
        if (root1==root2) return;
        int size1 = root1.size, size2 = root2.size;
        double newAmount = ((root1.amount * size1) +
                (root2.amount * size2)) / (size1 + size2);

        if (size1 <= size2) {
            root1.parent = root2;
            root2.amount = newAmount;
            root2.size  += size1;
        } else {
            root2.parent = root1;
            root1.amount = newAmount;
            root1.size  += size2;
        }
    }

    public int groupSize() {
        Container3 root = findRootAndCompress();
        return root.size;
    }

    public void flush() {
        Container3 root = findRootAndCompress();
        root.amount = 0;
    }
}
