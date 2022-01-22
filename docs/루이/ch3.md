## 연습문제 1

groupSize 메서드를 추가하자, 단 필드를 추가하거나 기존 메서드를 변경하지 않는다.

### speed1

```java
public int groupSize() {
    return group.members.size();
}
```

### speed2

```java
public int groupSize() {
    int groupSize = 0;
    Container current = this;
    do {
        groupSize++;
        current = current.next;
    } while (current != this);
    return groupSize;
}
```

### speed3

```java
public int groupSize() {
    return findRootAndCompress().size();
}
```

- 3가지 경우에 최악의 경우 점근적 복잡도는 어떠한가?

  - speed1 : O(1)

  - speed2 : O(N)

  - speed3 : O(log2 N)

- 다른 메서드의 점근적 복잡도를 증가시키지 않고 groupSize가 상수 시간에 수행되도록 Speed2를 수정할 수 있는가?
  - 불가능한 것 같다.

## 연습문제 2

flush 메서드를 추가하자, 단 기존 메서드를 변경하지 않는다.

### speed1

```java
public void flush() {
    group.amountPerContainer = 0;
}
```

### speed2

```java
public void flush() {
    Container current = this;
    do {
        current.amount = 0;
        current = current.next;
    } while (current != this);
}
```

### speed3

```java
public void flush() {
    findRootAndCompress().amount = 0;
}
```

- 3가지 경우에 최악의 경우 점근적 복잡도는 어떠한가?

  - speed1 : O(1)

  - speed2 : O(N)

  - speed3 : O(log2 N)

- 다른 메서드의 점근적 복잡도를 증가시키지 않고 flust가 상수 시간에 수행되도록 Speed2를 수정할 수 있는가?
  - 잘 모르겠다

## 연습문제 3

- 요구사항을 만족하는 Gird와 Appliance 클래스를 작성하라.

### Grid

```java
public class Grid {
    private int maxEnergy;
    private int nowEnergy;

    public Grid(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.nowEnergy = maxEnergy;
    }

    public int residualPower() {
        return nowEnergy;
    }

    public void on(int useEnergy) {
        if (useEnergy > nowEnergy) {
            throw new RuntimeException("전기가 부족합니다. 사용하지 않는 전자장치를 꺼주세요");
        }
        nowEnergy -= useEnergy;
    }

    public void off(int useEnergy) {
        if (maxEnergy > nowEnergy + useEnergy) {
            throw new RuntimeException("전기가 부족합니다. 사용하지 않는 전자장치를 꺼주세요");
        }
        nowEnergy += useEnergy;
    }
}
```

### Appliance

```java
public class Appliance {
    private int useEnergy;
    private Grid grid;
    private boolean power;

    public Appliance(int useEnergy) {
        this.useEnergy = useEnergy;
    }

    public void plugInto(Grid grid) {
        this.grid = grid;
    }

    public void on() {
        gridNullCheck();
        if (!power) {
            power = true;
            grid.on(useEnergy);
        }
    }

    public void off() {
        gridNullCheck();
        if (power) {
            power = true;
            grid.off(useEnergy);
        }
    }

    private void gridNullCheck() {
        if (grid == null) {
            throw new RuntimeException("그리드를 연결해 주세요");
        }
    }

    public static void main(String...args) {
        Appliance tv = new Appliance(150), radio = new Appliance(30);
        Grid grid = new Grid(3000);

        tv.plugInto(grid);
        radio.plugInto(grid);
        System.out.println(grid.residualPower());
        tv.on();
        System.out.println(grid.residualPower());
        radio.on();
        System.out.println(grid.residualPower());
    }
}
```

- 모든 메서드가 상수 시간에 실행되도록 두 클래스를 설계할 수 있는가?
  - 예

## 연습문제 4

- ArrayList에서 배열이 가득 찰 때마다 용량을 10%씩 증가시킨다면 add의 분할상환 복잡도는 여전히 상수 시간인가?
  - 잘 모르겠다 ㅠ
- 이렇게 하면 배열의 용량을 확장하는 일이 더 자주 벌어진다. 정확하게 얼마나 자주 확장을 수행하는가?
  - 약 5배 정도 더 많이 수행할 것 같다.
