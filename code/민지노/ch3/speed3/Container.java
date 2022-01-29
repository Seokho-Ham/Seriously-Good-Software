package speed3;


public class Container {

    private double amount;
    private Container parent = this;
    private int size = 1;

    private Container findRootAndCompress() {
        if (parent != this)
            parent = parent.findRootAndCompress();
        return parent;
    }

    public double getAmount() {
        Container root = findRootAndCompress();
        return root.amount;
    }
    public void addWater(double amount) {
        Container root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Container other) {
        Container root1 = findRootAndCompress(), // 두 트리의 루트 찾기
                root2 = other.findRootAndCompress();
        if (root1==root2) {
            return;  // 두 루트가 같은지 확인
        }
        int size1 = root1.size, size2 = root2.size;
        double newAmount = ((root1.amount * size1) +
                (root2.amount * size2)) / (size1 + size2);

        if (size1 <= size2) { // 트리의 사이즈 비교 후 작은 사이즈가 큰 루트 아래로 이동
            root1.parent = root2; // 첫 번째 트리가 작을 경우 두번 째 트리의 루트와 연결
            root2.amount = newAmount;
            root2.size  += size1;
        } else {
            root2.parent = root1; //반재로 두번째 트리가 작을 경우
            root1.amount = newAmount;
            root1.size  += size2;
        }
    }
}

