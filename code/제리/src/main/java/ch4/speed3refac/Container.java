package ch4.speed3refac;

/** A water container, with all methods in amortized almost-constant time.
 *
 *  Based on union-find trees with link-by-size and path compression.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private Container parent;

    public Container(boolean dummy) {
    }

    public Container() {
        parent = new RootContainer();
    }

    private RootContainer findRootAndCompress() {
        if (this.parent == null || this.parent.parent == null) {
            return (RootContainer) this.parent;
        }
        parent = parent.findRootAndCompress();
        return (RootContainer) parent;
    }
    
    public double getAmount() {
        RootContainer root = findRootAndCompress();
        return root.amount;
    }
    public void addWater(double amount) {
        RootContainer root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Container other) {
        RootContainer root1 = findRootAndCompress(),
                  root2 = other.findRootAndCompress();
        if (root1==root2) return;
        int size1 = root1.size, size2 = root2.size;
        float newAmount = ((root1.amount * size1) +
                            (root2.amount * size2)) / (size1 + size2);

        if (size1 <= size2) {
            this.parent = root2;
            root2.amount = newAmount;
            root2.size  += size1;
        } else {
            other.parent = root1;
            root1.amount = newAmount;
            root1.size  += size2;
        }
    }

    public int getSize() {
        RootContainer root = findRootAndCompress();
        return root.size;
    }

    public void flush() {
        RootContainer root = findRootAndCompress();
        root.amount = 0;
    }

}

