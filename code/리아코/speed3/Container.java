public class Container {

    private double amount;
    private Container parent = this; // 연결리스트가 아닌 완전 이진 트리
    private int size = 1;

    private Container findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }
        return parent;
    }

    public double getAmount() { // O(logN)
        return findRootAndCompress().amount;
    }

    public void addWater(double amount) { // O(logN)
        Container root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Container obj) { // O(logN)
        Container root1 = findRootAndCompress(); // tree1
        Container root2 = obj.findRootAndCompress(); // tree2
        if (root1 == root2) { // 이미 연결된 상태
            return;
        }

        int size1 = root1.size;
        int size2 = root2.size;
        double newAmount = ((root1.amount * size1) + (root2.amount * size2)) / (size1 + size2);
        if (size1 <= size2) { // 노드의 개수가 더 적은 트리가 노드의 개수가 더 많은 트리의 서브 트리로 연결
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
        return findRootAndCompress().size;
    }

    public void flush() {
        findRootAndCompress().amount = 0;
    }
}
