## 요약, 정리 

<br>

인터페이스에 프로그램하라

→ 다형성을 이용 → Set으로 선언 시 HashSet, TreeSet 등 Set을 구현한 클래스로 변경이 자유로움

<br>

UML 클래스 다이어그램

- 일련의 클래스의 정적 속성을 기술
- 상속과 포함을 비롯한 클래스 사이의 관계를 나타냄
- 너무 자세하게 할 필요는 없음(적당히 추상화) → 큰 그림만 그리고 소스 코드로 자세하게 작성하는 것

UML 객체 다이어그램

- 객체에 대해서 UML 다이어그램을 그린 것

<br>

❗클래스 다이어그램

- 구조
    - 클래스는 1. 클래스명, 2. 멤버변수, 3. 메서드로 구성
    - 인터페이스는 <<interface>> 이후에 인터페이스명을 적음
    - 추상클래스는 {Abstract} 이후에 클래스명을 적음
- 관계
    - 연관 관계
        - 실선이나 화살표
        - 클래스들이 연결되었음을 나타냄
    - 의존 관계
        - 점선 화살표
        - 한 클래스에서 다른 클래스에서 제공하는 기능을 사용할 때 사용
        - 두 클래스의 관계가 한 메서드를 실행하는 동안(짧은 시간동안)만 유지되는 경우
        - 의존은 연관의 일종임
    - 상속 관계
        - 실선의 삼각형 화살표
    - 인터페이스
        - 점선의 삼각형 화살표
    - 집약 관계
        - 속이 빈 다이어몬드
        - 클래스 사이의 전체 또는 부분 같은 관계를 나타냄. 전체 객체의 라이프타임과 부분 객체의 라이프타임은 독립적
    - 합성 관계
        - 속이 찬 다이아몬드
        - 한 클래스가 다른 클래스를 포함하는 관계
        - 전체에 해당하는 객체의 라이프타임과 부분 객체의 라이프타임이 종속적 (전체 객체가 사라지면 부분 객체도 사라짐)

<br>

UML Diagram이 사용되는 곳
- 다른 사람들과의 의사소통 또는 설계 논의
- 전체 시스템의 구조 및 클래스의 의존성 파악
- 유지보수를 위한 설계 문서

<br>

=============Toggle=============

<details>
<summary> UML 클래스 다이어그램 실습   ← Click!!  </summary>
<div markdown="1">

```java
    public class BurgerKing {

	    public static void main(String[] args) {
	        Burger burger = new Burger(new ChickenPatty());
	        burger.eat();
         }
   
    }
```

```java
    public interface Eatable {
    
    	public void eat();
    
    }
```

```java
    public class Burger implements Eatable {
    
    	private final Patty patty;
    
    	public Burger(Patty patty) {
    		this.patty = patty;
    	}
    
    	@Override
    	public void eat() {
    		patty.getPattyName();
    		System.out.println("버거 먹고싶다");
    	}
    }
```

```java
    public abstract class Patty {
    
    	abstract void getPattyName();
    
    }
```

```java
    public class ChickenPatty extends Patty {
    
    	@Override
    	void getPattyName() {
    		System.out.print("치킨");
    	}
    }
```

```java
    public class BeefPatty extends Patty {
    
    	@Override
    	void getPattyName() {
    		System.out.print("비프");
    	}
    }
```

**Intellij UML Class Diagram**

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/44cfb39c-ccfb-4a1a-9f36-3be527e69fbd/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220115%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220115T081902Z&X-Amz-Expires=86400&X-Amz-Signature=31cf642e52437f17cc05cbf753aa89b4cc9845d454cc920502b5aa0ce4153f40&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

`Eatable` Interface를 `Burger` Class가 상속하고 있으므로 삼각형의 점선 화살표로 나타낸다.

`Patty` abstract Class를 `ChickenPatty`, `BeefPatty` Class가 상속하고 있으므로 삼각형의 실선 화살표로 나타낸다.

`Burger` Class는 멤버변수로 `Patty patty;` 를 가지고 있고 생성자로 `Patty` abstract Class를 받고 있으므로 `Burger`와 `Patty`는 연관 관계가 있다. 버거 하나당 패티 하나씩 생성되므로 숫자 1을 표시한다.

또한 `Burger` 는 `Patty`를 잠깐 사용하고 있기 때문에 의존 관계를 갖고 있다. 따라서 화살표 모양의 점선으로 나타내는 선도 있다.

`Patty`는 `Burger` 객체가 사라져도 계속 메모리에 존재할 수 있기 때문에 `Patty`와 `Burger`는 집약관계이다. 속이 빈 마름모로 나타나야 할 것으로 보이는데 Intellij 에서는 속이 차 있는 마름모(합성 관계)로 나타내고 있다. → 이해 안됨.

`BurgerKing` Class는 `Burger` Class와 `ChickenPatty` Class에서 new 를 통해서 객체를 생성하고 있다. 또한 아주 잠깐 사용하고 있기 때문에 `BurgerKing` 과 `Burger, ChickenPatty` 는 서로 의존 관계이다. 의존관계이므로 화살표 모양의 점선으로 나타낸다.


</div>
</details>

=============Toggle=============

<br>

메모리 요구 사항

- 참조의 크기
- 객체 헤더
- 패딩

객체 헤더

1. 리플렉션
2. 멀티스레딩 → 객체마다 모니터가 할당
3. 가비지 컬렉션 → 객체에 대한정보를 가지고 있어야 함

→ 3가지 기능을 하기 위해서 필드가 없는 객체라도 12byte 메모리를 차지한다. `Person jerry;` → 12byte

<br>

배열의 메모리 요구 사항 → 16byte   `int[] array` → `array` 는 16byte

→ 기본적인 12byte 오버헤드 + 배열의 길이 (int → 4byte)

<br>

시간 복잡도

- big-O : O(1), O(n), ...

<br>

**돌발 퀴즈1**

Q. 여러분 스마트폰의 연락처를 저장한다면 어떠한 컬렉션 인터페이스와 클래스를 선택해야 할까?

`Map<String, String> number = new TreeMap<>();`

사람 이름, 핸드폰번호만을 저장한다고 생각하고 `이름 → key, 핸드폰 번호 → value` 로 관리할 것 같다.

<br>

**돌발 퀴즈2**

Q. 클래스 다이어그램으로 자바 클래스의 주요 속성과 메서드, 클래스의 관계를 표현하자.

못그리겠음..

<br>

**돌발 퀴즈3**

Q. int 타입의 public 필드 4개를 포함하는 클래스의 객체는 몇 바이트의 메모리를 차지할까?

→ 4byte * 4 + 12 byte = 28byte

<br>

**돌발 퀴즈4**

Q. 정렬되지 않은 정수 배열에 대해 배열 요소들이 회문을 형성하는지 확인하는 작업의 복잡도는?

❗회문 : palindrome → 거꾸로 해도 똑같은 것

O(n)

→ 0번째 인덱스와 length -1 인덱스와 비교 → 1번째 length - 2 → ...

n / 2 번 반복 → O(n/2) → O(n)

<br>

**연습 문제1**

1. 복잡도?
    - O(n^2)
2. 메서드의 결과에 영향을 미치지 않고 더 효율적으로 만들 수 있는가?

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
    - ~~O(n)~~
    - O(n^2) → 배열 생성할 때 2차원 배열이기 때문에.. ㅋㅋ 생각치도 못함

<br>

**연습 문제2**

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2cbf97f4-0326-4145-9792-f84c438ec394/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220115%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220115T081926Z&X-Amz-Expires=86400&X-Amz-Signature=9703064954529982bfda14ab85796c3faae2056203da4cbe43578732dd92da23&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

객체 헤더 → 12byte

`transient int size = 0;` → 4byte

`transient Node<E> first;` → 4byte

`transient Node<E> last;` → 4byte

❗extends 를 하고 있기 때문에 상위 클래스의 인스턴스 변수까지 다 포함시켜야 함.

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f4269a22-296c-422b-a482-53959d33db74/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220115%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220115T081937Z&X-Amz-Expires=86400&X-Amz-Signature=0dfc1237543ff606b0fd126ab6b62d43afc615844e78a8026d061bf384441077&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

객체 헤더 → 12byte

`E item;` → 4byte

`Node<E> next;` → 4byte

`Node<E> prev;` → 4byte

~~LinkedList 크기 : 24 + 24 * n  byte~~

LinkedList 크기 : 28 + 24 * n  byte

<br>

**연습 문제3**

src/main/java/mission3 에 풀어서 업로드했다.

src/test/java/mission3 에 테스트로 검증했다.


## 고민 사항 / 중점적으로 리뷰받고 싶은 부분

간단한 UML은 보고 이해할 수 있지만 직접 UML을 그릴려고 하면 어떻게 해야 할 지 모르겠습니다. Intellij의 Class Diagram 같은 기능이 있는데도 직접 그리는 방법까지 익혀야 하는 걸까요??

_<!-- 함께 고민해주었으면 하는 부분 -->_
