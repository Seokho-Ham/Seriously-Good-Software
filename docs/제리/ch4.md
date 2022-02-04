
# 4장 소중한 메모리 : 공간효율성

프로그래머는 때때로 가능하면 작은 공간에 데이터를 저장해야 하는 경우가 있다.

1. 임베디드 시스템처럼 시스템 자체의 메모리가 너무 작은 경우
2. 저장할 데이터가 너무 많은 경우

<br>

### Memory1 검소한 버전

1. 물의 양 `double` 8바이트 → `float`  4바이트
2. group 을 지연 초기화함
3. Set 대신 List 사용

<br>

### Memory2 일반 배열

1. ArrayList 대신 배열

ArrayList를 쓰는 것과 배열을 쓰는 것의 메모리 차이는 거의 나지 않는다. 하지만 추상화 정도가 낮아져 코드가 복잡해진다.

<br>

**컬렉션의 메모리 요구 사항**

| 종류 | 객체 자체 크기 | 요소 하나당 크기 |
| --- | --- | --- |
| array | 16 | 4 |
| ArrayList | 40 | 4 |
| LinkedList | 24 | 24 |
| Hashset | 52 | 32 |

HashSet은 다른 컬렉션에 비해 많은 메모리를 요구하지만 다음과 같은 기능을 제공한다.

- 쿼리를 상수 시간에 제공 (`contains()`)
- 중복된 요소의 삽입을 상수 시간으로 방지 (`add()`)
- 임의의 요소를 상수 시간에 제거(`remove()`)

<br>

### Memory3 객체여, 안녕 (배열 → 조금 특수하게 사용)

객체는 표준헤더를 가지고 있으므로 최소 12바이트를 차지한다. 따라서 객체를 사용하지 않음으로써 메모리를 절약할 수 있다. → 배열 사용

group을 int 배열, amout를 float 배열으로 처리한다.

객체지향 원칙 위배 오로지 메모리를 절약하기 위한 구조 → 임베디드 시스템에서 사용하는 방식

```java
private static int group[] = new int[0];
private static float amount[] = new float[0];
```

길이가 0인 배열을 선언했는데 이 이유는 언제든 length를 접근 시 null 오류가 나지 않도록 하는 것.

<br>

### Memory4 블랙홀

정적 배열 하나에 모든 수조를 저장하고 각 요소를 2가지 용도로 사용

배열 자체에 값이 next인지 amout 인지 확인해야 하는데 음수를 물의 양, 양수를 next라고 하고 사용할 수 있다.

float 타입은 양의 0과 음의 0을 구분하지 않으므로 편향을 통해서 0은 amout, next는 1부터로 할당해서 사용한다.

float를 사용할 때는 float의 해상도가 가변적이므로 조심해야 한다. `float a = 1E8F` 과 같이 한 후에 `a+1 == a` 가 성립하게 된다. 왜냐하면 float으로는 표현할 수 있는 거리의 차이가 1보다 크기 때문에 +1을 해도 +1이 되지 않는 것이다.이를 무간섭 정수 범위의 값이라고 한다.

| 타입 | 최상위 비트 | 최고 10진 자릿수 | 무간섭 정수 범위 |
| --- | --- | --- | --- |
| int | 31 | 9 | 1 ~ 2 * 10^9 |
| long | 63 | 18 | 1 ~ 2 * 10^18 |
| float | 24 | 7 | 1 ~ 16 * 10^6 |
| double | 53 | 15 | 1 ~ 9 * 10^15 |

### 공간복잡도와 시간복잡도는 상충관계에 있다.

<br>

### MultiSet<T> 구현

1. {a, a, b} == {a, b, a} 순서 구별 불가
2. {a, a, b} ≠ {a, b} 중복은 허용
3. `public void add(T item)`
4. `public long count(T elem)`

Q1) 서로 다른 n개 객체를 여러 번 삽입했고 삽입 횟수를 m이라고 할 때 몇 바이트가 필요할까?

잘 모르겠음

list 4btye

ArrayList 40byte

MultiSet 오버헤드 12byte

데이터 추가 시마다 T에 해당하는 메모리 byte 추가

MultiSet 추가할 때마다 멀티셋 가르키는 참조변수 4byte

```java
public class MultiSet<T> {

	private final List<T> list = new ArrayList<>();

	public void add(T data) {
		list.add(data);
	}

	public long count(T data) {
		return list.stream()
			.filter(t -> t.equals(data))
			.count();
	}

}
```

Q2) add()와 count()의 시간 복잡도는?

`add()` → O(1)  (만약 ArrayList가 거의 다 차게 된다면 배열 늘리는 과정에서는 시간이  조금 걸림)

`count()` → O(n)


<br>

<br>


**돌발퀴즈1**

Q) 여러분의 프로그램에 `Hello World` 라는 문자열 리터럴이 10번 등장한다면 이 문자열을 저장하는데 메모리가 얼마나 소모될까?

String은 char 배열이므로 `H`, `e`, `l`, `l`, `o`, `\s`, `W`, `o`, `r`, `l`, `d` 으로 구성되어 있다. char은 1바이트이므로 문자열은 11바이트이다. 하지만 실제로 배열의 마지막을 나타내는 null이 포함되어 있어야 하므로 String은 12바이트이다. 또 똑같은 저장정보를 갖고 있는 스트링은 같은 메모리 주소를 가르키도록 설계되어 있으므로 String 자체의 12바이트 + String은 불변 `객체` 이므로 객체헤더 12바이트를 더해 24바이트가 필요해 보인다.

정답

- String 객체의 오버헤드 12바이트
- 문자 개수 11바이트
- 문자열의 해시 코드 저장하는 4바이트
- 바이트 배열을 가르키는 참조 4바이트
- 인코딩의 종류를 나타내는 플래그 1바이트
- 바이트 배열의 오버헤드 16바이트 (객체 표준헤더 12바이트 + 배열의 크기 4바이트)
- 12 + 11 + 4 + 4 + 1 + 16 = 48 바이트


<br>

**돌발퀴즈2**

Q) 타입 파라미터 T에 대해 `new T[10]`이 적합한 자바 표현식이 아닌 이유는?

?? 모르겠음

```java
T[] typeParamArray = new T[10];

for (int i = 0; i < typeParamArray.length; i++) {
		typeParamArray[i] = new T();
}
```

하고 쓰면 되지 않나? → 컴파일 에러남

정답

- 2가지 상충되는 결정 대문에 배열과 제네릭은 함께 작동이 불가능하다.
    - 컴파일러는 제약 없는 타입 파라미터를 지우고 Object로 대체한다.
    - 배열은 정적 타입을 저장한다.
    - `new T[10]` 은 결국 `new Object[10]` 과 같다 → 이것은 프로그래머가 제네릭을 사용한 이유가 아니므로 이 표현식은 적법하지 않다.

<br>

**돌발퀴즈3**

Q) Set이 HashSet 타입이라면 set.contains(x)를 호출해 다음 호출 set.contains(y)의 속도가 개선될 수 있을까? Set이 TreeSet이라면 어떨까?

HashSet은 해쉬함수를 통해서 단순히 자신의 해쉬코드를 찾아가서 있는지 체크만 할 것 같다. 따라서 다음 호출의 속도가 개선되지 않을 것 같다.

반면에 TreeSet은 contians() 호출 시 이진 트리 구조의 높이를 최소한으로 맞추기 위한 로직이 들어가 있다면 다음 contains(y)를 호출 할 때 속도가 개선될 것 같다.

정답

- 첫 번째 호출로 인해서 HashSet의 버킷 일부가 캐시에 올라감 → 두 번째 호출된 데이터가 캐시에 있을 확률이 있으므로 속도가 개선된다.
- TreeSet도 마찬가지로 첫 번째 호출로 캐시에 올라가게 되고 두 번째 호출된 데이터가 캐시에 있을 확률이 있으므로 속도가 개선된다.

<br>

**돌발퀴즈4**

Q) 딱 한 번만 인스턴스화 가능한 클래스는 어떻게 설계할까?

Singleton

```java
public class Singleton {

	private static Singleton singleton;

	private Singleton() {}

	public Singleton getInstance() {
		if (Singleton.singleton == null) {
			Singleton.singleton = new Singleton();
		}

		return Singleton.singleton;
	}

}
```

LazyHolder Singleton

```java
public class Singleton {

	private Singleton() {}

	public static Singleton getInstance() {
		return LazyHolder.INSTANCE;				
	}

	private static class LazyHolder {
		// static 이므로 클래스가 로딩될 때 딱 한번만 실행됨.
		// final이므로 혹시나 재할당되지 않게 함. (재할당 될 일도 없을 것 같긴 함)
		private static final Singleton INSTANCE = new Singleton();
	}

}
```

<br>

**돌발퀴즈5**

Q) 루프 while(x + 1 == x)이 영원하게 계속되도록 변수 x의 데이터 타입과 초기값을 정해라.

```java
float x = 10E8F;
```

<br>

**연습문제1**

HashMultiset

1. add와 count 메서드의 시간 복잡도는?

`add()` → O(1)

`count()` → O(1)

<img width="650" alt="스크린샷_2022-01-29_13 02 51" src="https://user-images.githubusercontent.com/81368630/151651403-aeda6aaf-4bc1-408e-9aba-b8de2e3d181c.png">


1. 시간과 공간 중 어느 것에 초점을 맞추었는가?

Map을 통해서 구현을 한 것을 볼 수 있는데 공간을 희생하고 빠른 시간을 얻도록 구현되어 있다.

<br>

**연습문제2**

이렇게까지 메모리를 구해야하나..?

<br>

**연습문제3, 연습문제4**

코드참고