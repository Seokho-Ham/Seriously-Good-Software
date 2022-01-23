# Ch3. 시간 효율성

  

속도를 최적화하는 3가지 방법

  

-> 왜 3가지 방법이나 알아야 할까?

  

상황마다 속도를 최적화하는 방법이 다르기 떄문에 다양한 방법을 알고 있어야 함

  

<br>

  

**부분 순서**

  

- (a,b) 또는 (b,a) 관계 중 하나만 성립하는 관계

- 집합들의 집합에서 포함 관계는 부분순서의 전형적인 예이다. 정수들의 집합에서 부등식 <= 도 부분순서이다.

<br>

**극대 원소**

  

- 부분 순서를이루는 관계 중 다른 항목보다 작지 않은 원소 (트리구조로 생각해서 제일 위에 있는 루트라고 생각하면 편함)


```java
public class MaximalElementsExample {
	
	public void maximalElementMethod() {
		foo();
		boo();
	}

	private void foo() {
		
	}
	private void boo() {
		woo();
	}
	private void woo() {
		
	}

	public void anotherMaximalElementMethod() {
		
	}

}
```

<img width="593" alt="스크린샷 2022-01-22 11 09 07" src="https://user-images.githubusercontent.com/81368630/150621209-0ca3fb39-b98e-4fab-b98f-e898425239cf.png">
  

<br>

**파레토 최적**

  

- 다른 메소드에게 손해가 가도록 하지 않고서는 어떤 한 메소드에게 이득이 되는 변화를 만들어내는 것이 불가능할 때를 파레토 최적이라고 함

<img width="413" alt="스크린샷 2022-01-22 11 17 51" src="https://user-images.githubusercontent.com/81368630/150621204-560eaa48-2574-4d79-bc57-bddcfcb945d6.png">
  

<img src = "https://user-images.githubusercontent.com/81368630/150564991-6fbfb378-3036-4688-9f78-b2091ccb8564.png" width="50%" height="50%">

[이미지 출처 : 파레토 분포 - 위키백과](https://ko.wikipedia.org/wiki/%ED%8C%8C%EB%A0%88%ED%86%A0_%EB%B6%84%ED%8F%AC)


<br>

  

## 상수 시간에 물 넣기


Group Class를 따로 두어 관리함 -> 물넣기를 O(1) 로 실행 가능해짐

```java
private static class Group {  
  
  double amountPerContainer;  
  Set<Container> members;  
  
  Group(Container c) {  
  members = new HashSet<>();  
  members.add(c);  
  }  
}
```

```java
void test() {
  Container a = new Container();
  Container b = new Container();
  Container c = new Container();
  // 그림1

  a.addWater(8);
  c.addWater(6);
  // 그림2
  a.connectTo(b);
  // 그림3
  c.connectTo(a);
  // 그림4
}

```

그림1

<img width="653" alt="스크린샷 2022-01-22 13 11 23" src="https://user-images.githubusercontent.com/81368630/150624683-b77fbec2-b732-4dcc-b19a-4dafca4c6251.png">

그림2

<img width="654" alt="스크린샷 2022-01-22 13 11 50" src="https://user-images.githubusercontent.com/81368630/150624690-1f2b8961-97f5-4d9c-ac5f-683e688f884e.png">

그림3

<img width="656" alt="스크린샷 2022-01-22 13 13 35" src="https://user-images.githubusercontent.com/81368630/150624694-f54370cc-8aea-422e-af59-773bbab300f5.png">


그림4

<img width="654" alt="스크린샷 2022-01-22 13 30 57" src="https://user-images.githubusercontent.com/81368630/150624699-23077965-c981-4656-af0e-969b216c9013.png">


<br>

```java
public double getAmount() { return group.amountPerContainer; }  
  
public void addWater(double amount) {  
  double amountPerContainer = amount / group.members.size();  
  group.amountPerContainer += amountPerContainer;  
}  
  
public void connectTo(Container other) {  
  
  if (group==other.group) return;  
  
  int size1 = group.members.size(),  
  size2 = other.group.members.size();  
  double tot1 = group.amountPerContainer * size1,  
  tot2 = other.group.amountPerContainer * size2,  
  newAmount = (tot1 + tot2) / (size1 + size2);  
  group.members.addAll(other.group.members);  
  group.amountPerContainer = newAmount;  
  for (Container x: other.group.members) x.group = group;  
}
```

**speed1의 시간 복잡도**

|메서드|시간 복잡도|
|--|--|
| getAmount | O(1) |
| connectTo | O(n) |
| addWater | O(1) |


<br>


## 상수 시간에 연결 추가하기

상수 시간에 연결 추가하기 위한 2가지 기법  

1. 단일 순환 연결 리스트를 통해 `connectTo()`를 구현해서 상수 시간에 연결을 추가하도록 구현했다.

  

2. 지연성 사용 (물의 양의 최대한 늦게 갱신) (`stream`에서 최종연산메서드 호출 전까지 중간연산 하지 않는 것도 지연성)



  

**단점**

  

상수 시간에 연결을 추가하려는 **효율**을 위해서 **정확성**과 **견고성**을 크게 해치는 코드이다. 이런 방식으로는 구현해서는 안된다고 생각한다.

  

a.connectTo(b);

  

b.connectTo(a); -> 연결이 끊긴다.

  
  
  

```java
@Test  
void test() {  
  Container a = new Container();  
  Container b = new Container();  
  
  a.addWater(8);
  a.connectTo(b);  
  // 그림 1의 상황
  
  assertEquals(4, a.getAmount());  
  assertEquals(4, b.getAmount());  
  
  b.connectTo(a); 
  //그림 2의 상황 
  b.addWater(4);  

  assertEquals(6, a.getAmount()); // false 실제 값 4  
  assertEquals(6, b.getAmount()); // false 실제 값 8
}
```

그림1

<img width="390" alt="스크린샷 2022-01-20 09 42 08" src="https://user-images.githubusercontent.com/81368630/150621525-7f790f54-5df7-4ca3-be8e-fa93c158b3ac.png">

그림2

<img width="400" alt="스크린샷 2022-01-20 09 42 29" src="https://user-images.githubusercontent.com/81368630/150621527-b2b8892e-9437-4907-90fe-5a7eac7b3f34.png">


```java
public void connectTo(Container other) {  
  Container oldNext = next;  
  next = other.next;  
  other.next = oldNext;  
}  
  
public void addWater(double amount) {  
  this.amount += amount;  
}  
  
public double getAmount() {  
  updateGroup();  
  return amount; 
}
  
private void updateGroup() {  
  Container current = this;  
  double totalAmount = 0;  
  int groupSize = 0;  
  // First pass: collect amounts and count  
  do {  
  totalAmount += current.amount;  
  groupSize++;  
  current = current.next;  
  } while (current != this);  
  double newAmount = totalAmount / groupSize;  
  current = this;  
  // Second pass: update amounts  
  do {  
  current.amount = newAmount;  
  current = current.next;  
  } while (current != this);  
}
```


<br>

**speed2의 시간 복잡도**

|메서드|시간 복잡도|
|--|--|
| getAmount | O(n) |
| connectTo | O(1) |
| addWater | O(1) |
  

<br>

<br>

## 최적의 균형: 합집합 찾기 알고리즘

  
**합집합 찾기 알고리즘**

- 여러 개의 노드가 존재할 때 두 개의 노드를 선택한 후 이 두 노드가 서로 같은 그래프에 있는지 판별하는 알고리즘
	- Find() : x가 속해 있는 집합의 대표를 찾는다.
		- 경로 압축 기법 사용 (`findRootAndCompress()`)
	- Union() : x와 y를 합친다.
		- 크기에 따른 연결 정책 (큰 크기가 집합의 대표를 갖고 있도록 함)

<br>

**부모 포인터 트리**

각 노드가 부모 노드 하나만을 가지고 있는 트리

**자식 포인터 트리**

각 노드가 자식의 노드를 모두 가지고 있는 트리

ex) TreeSet

<br>

```java
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
  Container root1 = findRootAndCompress(),  
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
```
  
  **speed3의 시간 복잡도**

|메서드|시간 복잡도|
|--|--|
| getAmount | O(log n) |
| connectTo | O(log n) |
| addWater | O(log n) |

<br>

<br>

## 3가지 구현에 대한 결론

속도를 최적화하는 3가지 방법

-> 왜 3가지 방법이나 알아야 할까?

상황마다 속도를 최적화하는 방법이 다르기 떄문에 다양한 방법을 알고 있어야 함

코드를 실행하고 있는 응용 환경과 클라이언트의 메서드 호출 빈도를 통해서 호출 빈도가 가장 높은 메서드의 처리 시간이 가장 낮은 구현방법을 선택하는 것이 속도를 최적화 하는데 가장 현명한 선택.

<br>

<br>

## 분할상환 시간 복잡도  

빅 오 표기법은 최악의 상황에서의 시간 복잡도를 나타낸다. 보통의 경우 최악의 상황이 아닌 경우도 많으므로 빅 오 표기법만으로 시간 복잡도를 나타내는 것은 편협한 시각으로 볼 수 있다.

분할상환 시간 복잡도는 코드를 여러번 실행하면서 현재의 비용에 따른 미래의 이득까지 고려한 시간 복잡도를 다룬다. 즉 speed3에서 O(log n)의 비용으로 `findRootAndCompress()`를 계속 호출했는데 이렇게 경로 압축(추가 비용지출)을 하면서 미래에 `getAmount`, `addWater` 시에 상수시간만으로 메서드를 호출할 수 있게 된다.

speed3에 대해 분할상환 시간 복잡도를 분석해보면 O(m * *a*(n)) 으로 O(1)으로 볼 수 있다.

<br>

<br>

## 구현 방식 비교하기

**벤치마킹 시 주의사항**
- 각 연산이 화면 출력이나 파일 쓰기처럼 가시적인 효과를 일으키도록 구현
- 시간을 측정하기 전에 측정할 코드를 미리 여러 번 실행
	-> JVM이 최적화(JIT과 같은 기능)하지 못하도록 해서 정확한 결과를 도출할 수 있음

|버전| getAmount | addWater| connectTo|
|--|--|--|--|
| Reference | O(1) |O(n) | O(n)|
| Speed1 | O(1) |O(1) | O(n)|
| Speed2 | O(n) |O(1) | O(1)|
| Speed3 | O(log n) |O(log n) | O(log n)|

<br>

**첫번째 실험**

getAmount, addWater, connectTo 모두 적절히 사용

|버전| 시간(ms)|
|--|--|
| Reference | 2300 |
| Speed1 | 26 |
| Speed2 | 505 |
| Speed3 | 6 |

<br>

**두번째 실험**

addWater, connectTo는 적절히 사용, getAmount는 한번만 실행

-> getAmount의 시간복잡도가 O(n)이고 나머지의 시간복잡도가 O(1)인 speed2에게 유리한 실험

|버전| 시간(ms)|
|--|--|
| Reference | 2300 |
| Speed1 | 25 |
| Speed2 | 4 |
| Speed3 | 5 |

<br>

<br>

## 새로운 문제에 적용

IntStats 클래스
```java
public class IntStats {  
  
  // insert(), getAverage() -> O(1)  
  private final List<Integer> list;  
  private double average;  
  
  public IntStats() {  
  list = new ArrayList<>();  
  average = 0;  
  }  
  
  public void insert(int n) {  
  average = (average * list.size() + n) / (list.size() + 1);  
  list.add(n);  
  }  
  
  public double getAverage() {  
  return average;  
  }  
  
  public double getMedian() {  
  if (list.isEmpty()) {  
  return 0;  
  }  
  Collections.sort(list);  
  if (list.size() % 2 == 1) {  
  return list.get(list.size() / 2);  
  }  
  return ((double) list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;  
  }  
  
}
```
```java
public class IntStats {  
  
  // getAverage(), getMedian -> O(1)  
  private final List<Integer> list;  
  private int average;  
  
  public IntStats() {  
  list = new ArrayList<>();  
  average = 0;  
  }  
  
  public void insert(int n) {  
  average = (average * list.size() + n) / (list.size() + 1);  
  list.add(n);  
  Collections.sort(list);  
  }  
  
  public double getAverage() {  
  return average;  
  }  
  
  public double getMedian() {  
  if (list.isEmpty()) {  
  return 0;  
  }  
  if (list.size() % 2 == 1) {  
  return list.get(list.size() / 2);  
  }  
  return ((double) list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;  
  }  
  
}
```

<br>

  

**돌발 퀴즈 1**

  

Q) 자바 프로그램에서 사용하는 클래스 사이에는 어떠한 부분 순서가 성립할까?

  

Object Class가 극대 원소이고 Object Class를 상속받는 모든 다른 클래스들과 부분 순서 관계가 성립 할 것 같다. 하지만 일반적인 클래스 사이에서는 부분 순서가 존재하지 않을 것 같다.

  

<br>

  

**돌발 퀴즈 2**

  

Q) 단일 순환 연결 리스트에서 주어진 노드를 제거하는 연산의 복잡도는 어떠한가?

  

주어진 노드를 제거하기 위해서는 노드를 일단 전체탐색 해야 함 -> O(n)

  

<br>

  

**돌발 퀴즈 3**

  

Q) 여러분의 삶에서 즉시 해야 할 2가지 일과 최대한 지연시켜야 할 2가지 일을 생각해보자.

  

?????

  

<br>

  

**돌발 퀴즈 4**

  

Q) 여러분이 자바 컴파일러를 만든다고 가정해보자. 클래스 사이의 상속 관계를 트리로 나타낸다면 부모 포인터 트리와 자식 포인터 트리 중 어느 방식을 사용하겠는가?

  

부모 포인터 트리를 사용 할 것 같다. 상위 클래스를 상속받아 구현하는 경우 상위 클래스의 정보를 하위 클래스에서 모두 접근 할 수 있어야 한다. 따라서 하위 클래스에서 상위 클래스의 정보를 가지고 있는 부모 포인터 트리형태로 정보를 가지고 있어야 하위 클래스로부터 상위 클래스로 접근이 간편하다.

  

<br>

<br>
  

**연습문제 1**

  

Q) groupSize 메소드 추가, 필드 추가하거나 기존 메서드 건드리는 것 금지

1. 3가지 경우에 최악의 점근적 복잡도?

2. 다른 메서드 건드리지 않고 groupSize가 O(1)로 수행하도록 speed2 수정 가능?

  

1 - 1번 풀이

  

**speed1**

  

```java

public  int  groupSize() {

return  group.members.size();

}

```

  

O(1)

  

<br>

  

**speed2**

  

```java

public  int  groupSize() {

int  groupSize = 0;

Container  current = this;

do {

current = current.next;

groupSize++;

} while (current != this);

return groupSize;

}

```

  

O(n)

  
  

<br>

  

**speed3**

  

```java

public  int  getSize() {

Container  root = findRootAndCompress();

return  root.size;

}

```

O(log n)

  

<br>

  
**1- 2) 다른 메서드 건드리지 않고 groupSize가 O(1)로 수행하도록 speed2 수정 가능?**

  

~~불가능~~

  

가능 -> `connectTo()`호출마다 `public int groupSize` 를 멤버변수로 선언한 후에 groupSize를 계속 갱신하면서 가지고 있으면 됨.

  

or

  

`private int gruopSize` 를 멤버변수로 갖고 `getter`를 통해서 groupSize를 계속 갱신하면서 가지고 있으면 됨

  

<br>

  

**연습문제 2**

  

flush메서드 추가, 기존 메서드 변경 금지

  

1. 3가지 경우에 점근적 복잡도?

2. flush O(1) Speed2 에서 가능?

  

2 - 1번 풀이

  

**speed1**

  

```java

public  void  flush() {

group.amountPerContainer = 0;

}

```

  

O(1)

  

<br>

  

**speed2**

  

```java

public  void  flush() {

Container  current = this;

do {

current.amount = 0;

current = current.next;

} while (current != this);

}

```

  

O(n)

  
  

<br>

  

**speed3**

  

```java

public  void  flush() {

Container  root = findRootAndCompress();

root.amount = 0;

}

```

O(log n)

  

<br>

  

**2 - 2번 풀이**

  

불가능

  

<br>

  

**연습문제 3**

  

Q1)

  

```java
public class Grid {  
  
  private int remainPower;  
  
  public Grid(int remainPower) {  
    this.remainPower = remainPower;  
  }  
  
  public int residualPower() {  
    return remainPower;  
  }  
  
  public void connected(int power) {  
    if (remainPower < power) {  
      throw new IllegalArgumentException("전원을 킬 수 없습니다.");  
    }  
    remainPower -= power;  
  }  
  
  public void disConnected(int power) {  
    remainPower += power;  
  }  
  
}
```

  

```java
public class Appliance {  
  
  private final int requiredPower;  
  private Grid grid;  
  private boolean isOn;  
  
  public Appliance(int requiredPower) {  
    this.requiredPower = requiredPower;  
    this.isOn = false;  
  }  
  
  public void plugInto(Grid grid) {  
    if (this.grid == null) {  
      this.grid = grid;  
    } else if (this.grid != grid) {  
      off();  
      this.grid = grid;  
    }  
  }  
  
  public void on() {  
    if (!isOn) {  
      this.grid.connected(requiredPower);  
      isOn = true;  
    } else {  
      System.out.println("이미 켜져 있습니다.");  
    }  
 }  
 
  public void off() {  
    if (isOn) {  
      this.grid.disConnected(requiredPower);  
      isOn = false;  
    } else {  
      System.out.println("이미 꺼져 있습니다.");  
    }  
 }  
  
}
```

  

**Q2) 상수 시간에 실행됨?**

  

yes~

  

<br>

<br>

**연습문제 4**

  

Q1) ArrayList에서 용량 10%씩 증가 -> 분활상환 복잡도 상수?

  

당연

<br>
  

Q2) 기존 50% 대비 얼마나 자주 확장?

  

대충 5배?