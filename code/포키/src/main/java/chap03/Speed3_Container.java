package chap03;

public class Speed3_Container {
    private double amount;
    private Speed3_Container parent = this;
    private int size = 1;


    public double getAmount() {
        Speed3_Container root = findRootAndCompress();
        return root.amount;
    }

    public void addWater(double amount) {
        Speed3_Container root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Speed3_Container other) {
        Speed3_Container root1 = findRootAndCompress();
        Speed3_Container root2 = other.findRootAndCompress();

        if(root1 == root2) return;

        int size1 = root1.size;
        int size2 = root2.size;

        double newAmount = (root1.amount * size) + (root2.amount * size2) / (size1 + size2);

        if (size1 <= size2) {
            root1.parent = root2;
            root2.amount = newAmount;
            root2.size += size1;
        }else{
            root2.parent = root1;
            root1.amount = newAmount;
            root1.size += size2;
        }
    }

    //현재 그룹의 사이즈
    public int groupSize() {
        return size;
    }

    //모든 물을 0으로!!
    public void flush() {
        Speed3_Container root = findRootAndCompress();
        root.amount = 0;
    }



    //현재 노드부터 시작해서 루트에 도달하기 전까지 부모의 frac 함수를 호출.
    private Speed3_Container findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }
        return parent;
    }
}
