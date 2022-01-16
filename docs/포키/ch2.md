## 요약, 정리

### 데이터를 저장할 컬렉션을 정할때 고려할 항목

1. 중복된 항목을 포함하는가?
2. 요소들 사이의 순서가 중요한가?

<br>

### 레퍼런스

- 인터페이스에 프로그램 할것. 실제 구현 클래스를 감출 수 있으며, 다른 구현 클래스를 넣어도 문제가 없기 때문에.

<br>

### UML 클래스 다이어그램

- 클래스 다이어그램 : 클래스의 정적인 속성들과 클래스간의 관계를 보여준다.
    - 필드와 메서드의 접근제어자가 public이면 +, private이면 -로 표현한다.

- 시퀀스 다이어그램

<br>

### 객체의 크기를 측정하는데 필요한 3가지 요소

1. 참조의 크기
    - 32비트 하드웨어에서는 32비트, 64비트에서는 압축된 OOPs 기술 때문에 32 or 64.
2. 객체 헤더
    - JVM이 요구하는 표준 정보를 포함하고 있다. 리플렉션, 멀티스레딩, 가비지 컬렉션과 관련이 있다.
    - 현대적인 64비트 JVM의 일반적인 관례에 따라서 하나당 12바이트의 공간을 차지한다. 배열의 경우 배열의 길이를 저장해야해서 16바이트!
3. 패딩
    - ?!??!?!
- 위의 요소들이 객체의 크기에 영향을 미치는 방식도 JVM마다 다르다.
- 보통은 Hotspot(오라클이 제공하는 공식 JVM)을 사용한다.

> HashSet의 메모리 사용량
> 연결리스트의 배열 + 여분 필드 1~2개로 구성. 단순하게 52바이트 차지.

<br>

### 시간 복잡도

- 컴퓨터의 성능에 따라 달라지기 때문에 시간 대신 단계를 실행하는 횟수로 측정한다.
- 실행하는데 상수 시간이 소요되는 모든 연산. (산술 연산, 비교 연산)
-

<br>

### 돌발 퀴즈

Q1. 스마트콘 연락처를 저장한다면 어떤 컬렉션에 저장해야 할까?  
A. 중복 x, 순서 상관 x -> HashSet?
> 책 답안 : 이름 순서대로 저장하니까 정렬된 Set인 TreeSet으로 표현하는게 가장 좋다.

Q2. 클래스 다이어그램을 이용해 자바 클래스의 주요 속성과 메서드, 클래스의 관계를 표현하자.  
A.  
<img src="https://user-images.githubusercontent.com/57708971/149614035-889bcdac-e1f1-4e70-8fc6-fff18bf875a5.jpg" width="500"/>

Q3. android.graphics.Rect 클래스는 int 타입의 public 필드를 4개 포함한다. Rect객체는 몇 바이트를 차지할까?  
A. int는 4바이트를 차지한다. 4개를 가지고 있으니 16바이트에 헤더 12바이트. => 28바이트?

Q4. 정렬되지 않은 정수 배열에 대해 배열 요소들이 회문을 형성하는지 확인하는 작업의 반복도는 어떠한가?  
A. {1,2,4,2,1}와 같은 형식?? 배열의 시작과 끝을 포인트로 잡고 비교하는 방식으로 간다면 절반 이상 도는 거니까 O(N)?

<br>

### 연습 문제 1

```java

import java.util.LinkedList;

class Practice1 {
    LinkedList

    public static int[][] identityMatrix(int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }
}
```

Q1. O(n^2)  
Q2. 그냥 for문 돌면서 index 2개 모두 i값을 넣으면 된다.
```java
class Practice1 {
    public static int[][] identityMatrix(int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }
        return result;
    }
}
```
Q3. 그렇다. O(n^2) -> O(n)
> 책 답안 : 여전히 O(n^2). 배열 선언 후 0으로 초기화하는 작업이 진행되기 때문에.
> for문만 생각했는데 초기화 과정도 포함되는줄은 몰랐다.

### 연습문제 2

- 링크드 리스트 = 헤드 12 + 세개의 필드 12(size, head, tail)
- 각 노드 = 헤드 12 + 세개의 필드 12(item, prev, next)

A. 24 + n*24
> 책 답안 : 28 + n * 24
> 상위 클래스를 훑어볼때 인스턴스 필드를 놓쳤나봄.. 내려가서 보니까 modCount가 있더라. 꼼꼼히 보자!


### 연습문제 3

> 책 답안 : 너비 우선 탐색으로 작성되어있음
> 생각해보니 현재 노드와 직접적으로 연결되지 않았어도 친구의 친구의 친구의.. 까지 검색하는 과정을 돌았어야한다. 너무 단순하게 생각했다.


<br>

## 고민 사항 / 중점적으로 리뷰받고 싶은 부분

_<!-- 함께 고민해주었으면 하는 부분 -->_

```java
import java.util.ArrayList;

class Test {
    private List<Integer> numberList;
    private List<String> stringList = new ArrayList<>();

    public Test() {
        numberList = new ArrayList<>();
    }
}

```