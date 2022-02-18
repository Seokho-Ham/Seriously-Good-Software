## 입력 도메인 모델링

- 서로 연관된 입력의 특성을 몇 가지로 정의한다.
  - 특성은 입력의 종류나 메서드 계약을 바탕으로 도출 할 수 있다.
  - 예를 들어 정수 입력은 일반적으로 세 블록인 음수, 0, 양수로 나눌 수 있다.
- 이러한 특성을 조합해 유한한 집합을 만든다. 
  - 예를 들어 그림 6.2는 int 타입의 2가지 특성을 보여주는데 이를 바탕으로 가능한 조합 6가지를 만들었다.
- 각 조합에서 선택한 입력 값으로 테스트를 구성한다             . 

### 책에 나오는 JUnit4 코드와 같은 기능을 하는 JUnit5 코드

| JUnit4          | JUnit5         |
| --------------- | -------------- |
| @Test(expected) | assertThrows() |
| @BeforeClass    | @BeforeAll     |
| @Before         | @BeforeEach    |

- JUnit4에서는 테스트 메서드의 첫번째 매개변수에 메시지를 넣어준다면 JUnit5에서는 마지막 매개변수에 메시지를 넣어주는 것 같다. 
- 예시
  - JUnit4 : assertTrue(String message, boolean condition) 
  - JUnit5 : assertTrue(boolean condition, String message)

## 돌발 퀴즈

### 돌발 퀴즈 1

Q) 메서드 계약의 어느 부분이 메서드 테스트와 관련 있는가?

A) 사전 조건을 만족하지 못했을 때 발생하는 페널티와 사후 조건이 관련있다. 

### 돌발 퀴즈 2

Q) 날짜를 표현하는 데이터 타입에는 어떠한 특성이 있을까?

A)  

### 돌발 퀴즈 3

Q) 독립적인 세 특성이 존재하고 각 특성에 따른 블록 개수가 n1, n2, n3이라면 전체 조합 커버리지를 달성하는 데 몇 개의 테스트가 필요한가? 

 	개별 선택 커버리지와 기반 선택 커버리지에는 몇 개의 테스트가 필요한가?

A) 전체 조합 커버리지 : n1 * n2 * n3개

### 돌발 퀴즈 4 

Q) 프로그램이 assert 구문을 포함한다면 테스트할 때 어서션을 활성화해야 할까, 비활성화해야 할까?

A) 

### 돌발 퀴즈 5

Q) 파일에서 수조 객체를 읽는(역직렬화) 정적 메서드를 Container 클래스에 추가하면 테스트 용이성에 어떠한 영향을 미칠까?

A) 

### 돌발 퀴즈 6

Q) 현재 수조에 직 / 간접적으로 연결된 수조의 개수를 리턴하는 public 메서드를 Container 클래스에 추가하면 테스트 용이성에 어떠한 영향을 미칠까?

A) 

## 연습 문제 

### 연습 문제 1

Q) 다음과 같은 계약을 따르는 getDicisors 메서드의 테스트 계획을 수립해 실행하라.

| 사전 조건                              | 사후 조건                                                    | 페널티 |
| -------------------------------------- | ------------------------------------------------------------ | ------ |
| 메서드의 유일한 파라미터는 정수 n이다. | 메서드는 모든 약수를 List<Integer>에 담아 리턴한다. <br />n==0이면 빈 리스트를 리턴한다.<br />n이 음수이면 그 반대 부호를 취한 후 약수를 리턴한다. 예를 들어 12와 -12에 대해 같은 결과 [1, 2, 3, 4, 6, 12]를 리턴한다. | 없다.  |

A) 

```java
public class GetDivisorsTest {
    @Test
    @DisplayName("메서드는 모든 약수를 Integer의 List에 담아 리턴한다.")
    void integerListOfAllMeasure() {
        List<Integer> positiveResults = getDivisors(0);
        assertTrue(positiveResults.isEmpty());
    }

    @Test
    @DisplayName("n==0이면 빈 리스트를 리턴한다.")
    void nIsZeroReturnEmptyList() {
        List<Integer> positiveResults = getDivisors(0);
        assertTrue(positiveResults.isEmpty());
    }

    @Test
    @DisplayName("n의 절대값이 같으면 결과도 똑같다.")
    void negativeAndPositiveEqualsResult() {
        List<Integer> positiveResults = getDivisors(12);
        List<Integer> negativeResults = getDivisors(-12);
        assertEquals(positiveResults, negativeResults);
    }

    public List<Integer> getDivisors(int n) {
        n = Math.abs(n);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                list.add(n / i);
            }
        }
        return list;
    }
}
```

### 연습 문제 2

Q) 입력 도메인 모델 방법론을 이용해 String 클래스의 다음 메서드를 위한 테스트 계획을 수립해 실행하라.

```java
public int indexOf(int ch, int fromIndex)
```

A) 입력 도메인 모델 방법론을 스스로 정의할 만큼 이해하지는 못했다. 일단 메서드 계약을 정의하고 기회가 될 때 입력 도메인 모델 방법론을 이용해 보겠다.  

| 사전 조건                  | 사후 조건                                                    | 페널티                                                       |
| -------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| fromIndex가 음수면 안된다. | String에 ch가 포함되었다면 해당 index 위치를 반환한다.<br />String에 ch가 포함되어있지 않다면 -1을 반환한다. | fromIndex가 음수라면 IndexOutOfBoundsException 예외를 발생시킨다. |

```java
public class IndexOfTest {
    private MyString hello;

    @BeforeEach
    void before() {
        hello = new MyString("hello");
    }

    @Test
    @DisplayName("String에 ch가 포함되었다면 해당 index 위치를 반환한다.")
    void chIsContainsReturnIndex() {
        assertEquals(hello.indexOf('o', 0), 4);
    }

    @Test
    @DisplayName("String에 ch가 포함되어있지 않다면 -1을 반환한다.")
    void chIsNotContainsReturnNegative() {
        assertEquals(hello.indexOf('a', 0), -1);
    }

    @Test
    @DisplayName("fromIndex가 음수라면 IndexOutOfBoundsException 예외를 발생시킨다.")
    void fromIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> hello.indexOf('o', -1));
    }

    private class MyString {
        private String string;

        public MyString(String string) {
            this.string = string;
        }

        public int indexOf(int ch, int fromIndex) {
            if (fromIndex < 0) {
                throw new IndexOutOfBoundsException();
            }

            for (int i = fromIndex; i < string.length(); i++) {
                if (string.charAt(i) == ch) {
                    return i;
                }
            }
            return -1;
        }
    }
}
```



### 연습 문제 3

Q) 입력 도메인 모델 방법론을 이용해 interleaveLists 메서드를 위한 테스트 계획을 수립해 실행하라.

​	 메서드 계약은 다음과 같다. 

| 사전 조건                             | 사후 조건                                                | 페널티                                                       |
| ------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| 같은 길이의 List 2개를 인자로 받는다. | 두 리스트의 요소가 번갈아 저장된 새로운 List를 리턴한다. | 주어진 두 리스트 중 하나가 null이면 메서드는 NPE를 던진다.<br />두 리스트의 길이가 다르면 IAE를 던진다. |

A)  2번 답변과 동일하게 메서드 계약으로 풀겠다.

```java
public class InterleaveListsTest {
    ArrayList<Object> list1;
    ArrayList<Object> list2;

    @BeforeEach
    void beforeEach() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    @Test
    @DisplayName("주어진 두 리스트 중 하나가 null이면 메서드는 NPE를 던진다.")
    void parameterEmptyThrowNPE() {
        assertThrows(NullPointerException.class, () -> interleaveLists(null, list2));
    }

    @Test
    @DisplayName("두 리스트의 길이가 다르면 IAE를 던진다.")
    void twoListNotEqualsSize() {
        list1.add("a");
        list2.add("b");
        list2.add("c");

        assertThrows(IllegalArgumentException.class, () -> interleaveLists(list1, list2));
    }

    @Test
    @DisplayName("두 시르트의 요소가 번갈아 저장된 새로운 List를 리턴한다.")
    void returnTwoListAlternateSaveList() {
        list1.add("a");
        list1.add("b");
        list2.add("c");
        list2.add("d");

        List<Object> result = interleaveLists(list1, list2);
        List<Object> totalList = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());

        for (Object o : totalList) {
            assertThat(result).contains(o);
        }
}
```

Q) 여러분이 수립한 테스트 계획의 코드 커버리지를 측정하자. 수동으로 특정하거나 커버리지 도구를 이용하자.

A) Jacoco가 작동하지 않아서 Intellij의 Code Coverage를 사용했다.

![image-20220212205051329](C:\Users\ABC\AppData\Roaming\Typora\typora-user-images\image-20220212205051329.png)

### 연습 문제 4

Q) 크기가 동적으로 커지는 T 타입 집합에 대한 인기도 컨테스트를 표현하는 제네릭 인터페이스 PopularityContest<T>의 테스트 용이성을 개선하자.

​	 인터페이스는 다음과 같은 메서드를 포함한다.

```java
void addContestant(T contestant); //새로운 참가자를 추가한다. 중복된 참가자는 무시한다. 

void voteFor(T contestant); 	 //주어진 참가자에게 투표한다. 해당 참가자가 존재하지 않으면 IllegalArgumentException을 던진다.

T getMostVoted(); 				//가장 많이 득표한 참가자를 리턴한다. 참가자가 없으면 IllegalStateException을 던진다.
```



A) 