package speed1;

import java.util.*;

public class Container {

    private Group group = new Group(this);

    private static class Group {  //static으로 선언한 이유?? 그 그룹이 생성한 수조에 영구적으로 연결될 필요가 없기때문??
        double amountPerContainer;  //그룹에 속하는 수조 하나에 담긴 물의 양
        Set<Container> members; // 그룹에 포함된 수조의 집합을 저장

        Group(Container c) { //생성자를 통해 HashSet에 컨테이너를 저장
            members = new HashSet<>();
            members.add(c);
        }
    }

    public double getAmount() { return group.amountPerContainer; }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.members.size();
        group.amountPerContainer += amountPerContainer;
    }

    public void connectTo(Container other) {

        if (group==other.group) return;

        int size1 =       group.members.size(),
                size2 = other.group.members.size();
        double tot1 =       group.amountPerContainer * size1,
                tot2 = other.group.amountPerContainer * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.members.addAll(other.group.members);
        group.amountPerContainer = newAmount;
        for (Container x: other.group.members) x.group = group;
    }
}

