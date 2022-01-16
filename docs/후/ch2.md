# 2장_레퍼런 구현

### ✔️ check point

- 표준 컬렉션 사용
- 소프트웨어 설계를 표현하는 다이어그램 그리기
- big-O 표현법으로 성능 표현하기
- 클래스의 메모리 사용량 측정하기

---

## JCF(Java Collection Framework)

![CollectionsFramework](https://user-images.githubusercontent.com/68011320/149615048-53770627-1abc-46ba-9177-db41110f190b.png)

## 소프트웨어 설계를 표현하는 다이어그램

- UML(Unified Modeling Language, 범용 모델링 언어) : 소프트웨어 시스템의 다양한 측면을 표현할 수 있는 풍부한 다이어그램을 제공하는 표준. 클래스
  다이어그램과 시퀀스 다이어그램이 가장 많이 쓰인다

### UML 클래스 다이어그램

- 상속과 포함을 비롯한 클래스 사이의 관계를 보여줌
- Class Box : 필드와 메서드, 가시성을 포함. 자기설명적
- Class Box 사이의 선 : 클래스 간 연관 관계를 나타냄
- 시스템의 모델을 기술할 뿐 시스템 자체를 기술하지는 X

### UML 객체 다이어그램

- 클래스 다이어그램과 비슷하지만, 클래스와 달리 각자 이름이 부여되며 실행한 후의 상태를 보여줌

### 메모리 레이아웃 다이어그램

- 각 메모리 표현을 이해할 수 있도록 데이터가 메모리에 실체화된 모습을 도식화하여 표현한 것
- UML 객체 다이어그램과 비슷하며, 두 다이어그램 모두 객체의 필드와 그들 사이의 관계를 포함한 객체의 스냅샷을 표현

## 메모리

- 객체의 정확한 크기를 계산하는 데 필요한 3가지 요소 → 아키텍처와 JDK 제공자마다 다름
    1. 참조의 크기
        - 언어 명세에 명시X. `32bit` 또는 `64bit`
        - Compressed OOPs(Ordinary Object Pointers, 일반 객체 포인터) 기술 때문에 64비트 프로세서에서도 32비트일 수 있음(이 경우
          해당 참조가 접근 가능한 힙 공간은 32GB로 제한)
    2. 객체 헤더(object header)
        - 모든 객체의 메모리 레이아웃은 JVM이 요구하는 표준 정보를 포함하기 때문에 필드가 없는 객체(빈 객체)라도 메모리 차지
        - 헤더의 구성은 리플렉션, 멀티스레딩, 가비지 컬렉션과 주로 관련 있음
    3. 패딩(padding)
        - 2의 제곱수의 배수(보통 4나 8)인 주소를 사용해야 하드웨어가 효율적으로 작동하기 때문에 객체의 각 필드와 객체 자체의 크기가 `word` 단위에 정렬되도록
          삽입하는 빈 공간
            - `word` : 컴퓨터가 한번 처리할 수 있는 명령단위
- 이 요소들이 객체의 크기에 영향을 미치는 방식도 JVM에 따라 다름
- 메모리 사용량을 분석할 때는 오라클의 표준 JVMdls HotSpot을 기준으로,
    - 참조 크기를 `32bit`로 가정
    - 객체 하나당 `12byte`의 공간을 차지한다고 가정. 배열은 `16byte`로 가정(배열의 길이 포함)
    -
        + 패딩(책에서는 무시)
- `JDK`는 주어진 클래스의 객체에 대해 객체 헤더를 포함한 내부적인 메모리 레이아웃을 조사할 수 있는 도구인 `JOL`(Java Object Layout)을 포함

## 시간 복잡도

- 실행 시간은 같은 프로그램이라도 어떠한 컴퓨터에서 실행되느냐에 따라 매우 다르게 측정되기 때문에 실제 실행 시간을 측정하는 대신 프로그램의 basic step을 실행하는 횟수를
  측정
    - basic step : 실행하는데 상수 시간이 소요되는 모든 연산(ex. 산술 연산, 비교 연산)
- 입력에 따라 같은 함수라도 수행해야 할 basic step 수가 달라질 수 있기 때문에 최대한 많은 단계가 실행되는 최악의 경우만 고려 → big-O
    - O(1) : 상수 시간
        - ex) 배열의 첫 번째 요소가 0인지 확인
    - O(log n) : 로그 시간
        - ex) 이진 탐색
    - O(n) : 선형 시간
        - ex) 정렬되지 않은 배열에서 최대값 찾기(배열 모든 요소를 한번 탐색해야함)
    - O(n log n) : 유사 선형 시간
        - ex) 병합 정렬로 배열 정렬하기
    - O(n^2) : 이차 시간
        - ex) 버블 정렬로 배열 정렬하기

## 퀴즈 & 연습 문제

### ‼️️ **[돌발 퀴즈 1]** 스마트폰의 연락처를 저장한다면 어떠한 컬렉션 인터페이스와 클래스를 선택해야 할까?

→ `Map`, `HashMap`

- 연락처를 저장할 때에는 이름, 번호를 필수로 포함하고 부가정보를 선택적으로 저장
- 이 때 이름은 중복될 수도 있으나 번호는 중복 허용 X
- 따라서 `key-value` 값을 가지는 `Map`을 선택하여 번호를 `key`, 이름이나 부가정보를 포함한 객체를 `value`로 가지는 `HashMap`을 선택한다. (
  검색에 용이하기 때문에)

⇒ `SortedSet`, `TreeSet`

- 일반적으로 이름의 알파벳 순으로 연락처 목록을 정리하기 때문에 `SortedSet`으로 표현하고 `TreeSet` 클래스로 구현

### ‼️ **[돌발 퀴즈 2]** 클래스 다이어그램으로 자바 클래스의 주요 속성과 메서드, 클래스의 관계를 표현하자.

→ 자바 클래스가 의미하는게 뭘까요? 표준 라이브러리들 말하는건가ㅎㅎ

⇒ 클래스와 메서드의 관계를 클래스 다이어그램으로 나타내보는 것이였군요... 정답 보고 따라 그려봤습니다.

![Class-Method](https://user-images.githubusercontent.com/68011320/149615051-75fd30e0-f4c5-40ff-9c43-bd07baf5e784.jpeg)

### ‼️ **[돌발 퀴즈 3]** `android.graphics.Rect` 클래스는 `int` 타입의 `public` 필드 4개를 포함한다. `Rect` 객체는 몇 바이트를 차지할까?

→ `28byte` (패딩 제외)

- 객체 헤더 `12byte`
- `int` 타입의 필드 4개 `4byte` * 4 = `16byte`
- 패딩 `4byte`(32 - (12 + 16))

### ‼️ **[돌발 퀴즈 4]** 정렬되지 않은 정수 배열에 대해 배열 요소들이 palindrome을 형성하는지 확인하는 작업의 복잡도는 어떠한가?

→ $O(log n)$ : 선형 시간

- 1/2 n 예상합니다... 배열의 앞 뒤를 한번에 하나씩 비교해서 절반까지

### [연습 문제 1]

1. 다음 메서드의 복잡도는?

    ```java
    public static int[][] identityMatrix(int n) { // n^2+n+3 
    	int[][] result = new int[n][n]; // 1
    	for (int i = 0; i < n; i++) { // n
    		for (int j = 0; j < n; j++ { // n*n
    			if (i==j) { // 1
    				result[i][j] = 1; // 1
    			}
    		}
    	}
    	return result;
    }
    ```

   → $O(n^2)$

2. 메서드의 결과에 영향을 미치지 않고 더 효율적으로 만들 수 있는가?

   → 행과 열의 인덱스가 같은 요소만 1을 할당하는 메서드이므로 반복문 중첩을 하나 없앨 수 있다.

    ```java
    public static int[][] identityMatrix(int n) {
    	int[][] result = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		result[i][i] = 1;
    	}
    	return result;
    }
    ```

3. 더 효율적인 버전을 만들었다면 더 낮은 복잡도를 보이는가?

   → 개선된 메서드는 $O(n)$의 복잡도를 가진다.

   ⇒ 복잡도는 여전히 $O(n^2)$이다. 두 번째 행에서 배열을 할당할 때 암묵적으로 배열의 모든 요소 $n^2$개를 0으로 초기화하기 때문(이럴수가...)

### [연습 문제 2]

`java.util.LinkedList<T>` 클래스는 `T` 타입 객체의 참조를 포함하는 이중 연결 리스트를 구현한다. 소스 코드를 확인한 후 요소 개수가 n일
때 `LinkedList`의 크기를 바이트 단위로 평가하라(n개 객체가 차지하는 공간은 제외)

→ `28byte` + `24byte` * n

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    // protected transient int modCount = 0; <- 상속

	...

    private static class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

	...

}
```

- 객체 헤더 : `12byte`
- 참조 크기 : `16byte`
    - int size `4byte`
    - Node first `4byte`
    - Node last `4byte`
    - 상속된 int modeCount `4byte`
- 각 요소(Node) : `24byte`
    - 객체 헤더 : `12byte`
    - 참조 크기 : `4byte` * 3 = `12byte`

### [연습 문제 3] 미니 프로젝트

소셜 네트워크 개인을 나타내는 `User` 클래스를 만들고 다음과 같은 기능을 제공하자.

- 각 사용자는 이름이 있다. 이름을 받아들이는 `public` 생성자를 만들자.
- 다음과 같은 메서드로 친구 관계를 맺을 수 있다.
    - `public void befriend(User other)`
    - 친구 관계는 대칭적으로, `a.befriend(b)`는 `b.befriend(a)`와 같다.
- 클라이언트는 다음과 같은 메서드를 이용해 주어진 두 사용자가 직접적인 친구인지, 간접적인 친구(친구의 친구)인지 알 수 있다.
    - `public boolean isDirectFriendOf(User other)`
    - `public boolean isIndirectFriendOf(User other)`
