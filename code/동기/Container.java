import java.util.HashSet;

public class Container {

    double amount;   // 수조에 담긴 물의 양
    HashSet<Container> group; // 연결된 수조의 그룹
    int size = group.size();   // 연결된 수조의 개수

    public Container() {
        group = new HashSet<>();
        group.add(this);
        amount = 0;
    }

    public double getAmount () {
        return amount;
    }

    public void addWater(double amount) {
        double water = amount / size;

        for (int i = 0; i < size; i++) {
//            group.add(water);       // --> 물을 컨테이너 수만큼 나눈 걸 각각의 컨테이너에 넣고 싶은데 어떻게 하면 될까요...
        }
    }

    public void connectTo(Container newContainer) {
        double newAmount = getNewAmount(newContainer);  // 병합 후 수조 하나에 담길 물의 양

//        for (int i = 0; i < size; i++) {   // 첫 번째 그룹의 수조 g[i]에 대해
//            for (int j = 0; j < newContainer.size; j++) { // 두 번째 그룹의 수조 c.g[j] 에 대 해
//                group[i].group[size + j] = newContainer.group[j]; // c.g[j]를 g[i]의 그룹에 추가
//                newContainer.group[j].group[newContainer.size + i] = group[i]; // g[i] 를 c.g[j]의 그룹에 추가
//            }
//        }
//
//        size += newContainer.size;  // 연결할 수조는 1개이니 그냥 size++; 해줘도 될까요?
//
//        for (int i = 0; i < size; i++) {   // 그룹의 크기와 물의 양 갱신
//            group[i].n = size;
//            group[i].x = newAmount;
//        }
    }

    private double getNewAmount(Container newContainer) {
        double newAmount = (amount * size + newContainer.amount * newContainer.size)
                / (size + newContainer.size);
        return newAmount;
    }
}
