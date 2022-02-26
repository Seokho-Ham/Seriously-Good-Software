# 7장 가독성

## 7장에서 다루는 내용

- 읽기 쉬운 코드 작성
- 자바독 주석을 이용한 계약 문서화
- 구현 관련 주석을 자기설명적 코드로 대체하기

## 가독성을 바라보는 관점

- 가독성은 소프트웨어의 신뢰성과 유지보수성, 발전 과정에 큰 영향을 미친다.

### 가독성에 영향을 미치는 소재

- 구조적 특징 : 프로그램 실행에 영향을 미치는 특징. 아키텍처와 API 선택, 흐름 제어 구문 선택 등을 예로 들 수 있다. 이러한 특징은 다음과 같은 3가지 수준으로 나눌 수 있다.
  - 아키텍처 수준 : 하나 이상의 클래스와 관련 있는 특징
  - 클래스 수준 : 한 클래스와 그 안에 포함된 모든 메서드와 관련 있는 특징
  - 메서드 수준 : 한 메서드와 관련 있는 특징
- 외부적 특징 : 주석이나 공백, 변수 명명 규칙 등 프로그램 실행에 영향을 미치지 않는 특징

## 구조적 가독성 특징

- 일반적으로 이해하기 쉬운 아키텍처는 기능적 응집력이 큰 소수의 클래스로 구성돼야 하며 클래스는 의존성이 복잡하지 않은 느슨한 네트워크로 연결돼야 한다.
- 가능하면 표준적인 디자인 패턴을 애용해야 한다.
  - 표준적인 디자인 패턴을 사용하면 대부분의 개발자가 해당 디자인 패턴에 대해 알 확률이 높기 때문에 친숙함을 느낄 수 있고 설명 하기도 편하다.

### 가독성에 영향을 미치는 구조적 코드 특성 요약

![https://user-images.githubusercontent.com/92966772/154707656-af666309-dfee-4535-8f89-dbde30774fd5.png](https://user-images.githubusercontent.com/92966772/154707656-af666309-dfee-4535-8f89-dbde30774fd5.png)

## 제어 흐름 구문

- 자바는 표준 for, forEach, while, do-while 루프를 제공한다.
- 위 루프 중 시나리오에 가장 적당한 루프 유형을 사용해야 한다.

### do-while vs while

종료 조건을 검사하는 루프의 예시이다.

```java
do {
		body
} while (condition);

// 위 코드를 아래와 같이 쓸 수 있다.
while (true) {
		body
		if (!condition) break;
}
```

### for vs while

n번 만큼 반복하는 루프의 예시이다.

```java
int n = 5;
for(int i=0; i < n; i++) {

}

// 위 코드를 아래와 같이 쓸 수 있다.
int i = 0;
int n = 5;
while (i < n) {
		i++;
}
```

## 외부적 가독성 특징

주석, 이름, 공백 3가지 외부적 특징을 이용해 가독성을 향상시킬 수 있다.

| 특징     | 개선방법                                          |
| -------- | ------------------------------------------------- |
| 주석     | 문서화 주석은 자세하게, 구현 주석은 가능하면 줄임 |
| 이름     | 설명적 이름                                       |
| 공백     | 문장 부호 역할을 하는 공백                        |
| 들여쓰기 | 일관된 들여쓰기                                   |

- 문서화, 명세 주석
  - 메서드나 클래스 전체의 계약을 설명한다.
  - 클래스의 잠재적 클라이언트가 지켜야 할 규칙을 기술하는 데 목적을 둔다.
  - 자바에서는 자바독이라는 도구를 사용해서 이러한 주석을 추출하고 알아보기 쉬운 형태로 변환한다.
- 구현 주석
  - 코드의 의도를 비롯한 내부 구현에 대한 내용을 설명한다.
  - 필드의 역할이나 헷갈리는 알고리즘의 설명을 더할 때 사용한다.

## 자바독을 이용한 클래스 헤더 문서화

- IntelliJ를 사용한다면 Tools > Generate JavaDoc을 클릭한다.
- Locale과 Other command line arguments를 아래와 같이 설정하고 OK를 눌러주면 Output directory에서 설정한 경로에 파일이 생성 된다.

![https://user-images.githubusercontent.com/92966772/154791875-9d0c620c-053e-41e5-9571-65d05d7f66e5.png](https://user-images.githubusercontent.com/92966772/154791875-9d0c620c-053e-41e5-9571-65d05d7f66e5.png)

![https://user-images.githubusercontent.com/92966772/154791921-c46cdc70-fc7e-4733-a40c-ce77542c43cd.png](https://user-images.githubusercontent.com/92966772/154791921-c46cdc70-fc7e-4733-a40c-ce77542c43cd.png)

![https://user-images.githubusercontent.com/92966772/154792088-3cb9ccdb-4b11-4952-bc17-fdc9d0c55679.png](https://user-images.githubusercontent.com/92966772/154792088-3cb9ccdb-4b11-4952-bc17-fdc9d0c55679.png)

- Java API Document와 매우 유사하고 검색 기능도 만들어져 있다.
- 하지만 눌러보면 NOT FOUND 에러가 발생하는데 경로가 잘못되어있다.

```
file:///E:/intellij-workspace/Seriously-Good-Software/code/Louie/src/main/resources/javadoc/undefined/ch7/Container.html#addWater(double)
```

- 검색창에서는 위와 같은 경로로 설정되어 있지만 실제 파일이 있는 경로는 위 경로에서 undefined를 빼준 `file:///E:/intellij-workspace/Seriously-Good-Software/code/Louie/src/main/resources/javadoc/ch7/Container.html#addWater(double)`이다.

### ConnectTo 메서드 리팩터링하기

- 283쪽에서 mergeGroupWith, setAllAmountsTo 메서드의 실행 순서에 따라 로직이 정상적으로 동작하지 않을 수 있다는 내용이 있다.

```java
public void connectTo(Container other) {
    if (isConnectedTo(other)) {
        return;
    }
    double newAmount = (groupAmount() + other.groupAmount()) / (groupSize() + other.groupSize());

    mergeGroupWith(other.group);
    setAllAmountsTo(newAmount);
}
```

- 아래와 같이 메서드를 만들어서 두 메서드의 실행 순서를 보장시켜주면 좋을 것 같다.

```java
public void connectTo(Container other) {
    if (isConnectedTo(other)) {
        return;
    }
    double newAmount = (groupAmount() + other.groupAmount()) / (groupSize() + other.groupSize());

    mergeGroupWithAndSetAllAmountsTo(other, newAmount);
}

private void mergeGroupWithAndSetAllAmountsTo(Container other, double newAmount) {
    mergeGroupWith(other.group);
    setAllAmountsTo(newAmount);
}
```

## 가독성 향상 팁

- 작업을 수행하는 데 가장 자연스럽고 구체적인 유형의 루프를 선택하라.
- 변수 추출 리팩터링 규칙 : 하위 표현식을 설명적인 이름의 새로운 지역 변수로 대체하라.
- 구현 주석은 최소화하고 문서화 주석을 선호하자. 모든 주석을 최신으로 유지하자(코드 리뷰가 도움을 줄 수 있다.)
- 설명적인 이름을 사용하고 축약어를 피하며 확립된 관례를 따르자.
- 글을 쓸 때 문단 끝에서 마침표를 찍듯이 코드에서 빈 줄을 사용하자.
- 리팩터링 규칙 메서드 추출 : 응집도가 높은 코드 블록을 서술적인 이름을 갖는 메서드로 옮기자.
- 리팩터링 규칙 임시-질의 대체 : 지역 변수를 사용하는 대신 값을 계산하는 새로운 메서드를 호출한다.

## 요약

- 가독성은 신뢰성과 유지 보수에 기여하는 주요 요소다.
- 구조적, 외부적 방법으로 가독성을 개선할 수 있다.
- 리팩터링의 일반적인 목적 중 하나는 가독성 개선이다.
- 구현 주석보다 자기문서화 주석을 사용하자.
- 문서를 쉽게 볼 수 있도록 표준 형식에 따라 문서를 자세하게 작성하자.

## 돌발 퀴즈

### 돌발 퀴즈 1

Q) 가독성의 영향을 받는 품질 속성은 무엇일까?

A)

- 사용성, 유지보수성이 가독성의 큰 영향을 받을거라고 생각한다.
- 가독성을 향상 시키기 위해 리팩터링 규칙의 메서드 추출을 통해 재사용성도 향상 시킬 수 있을 것 같다.

### 돌발 퀴즈 2

Q) n개 정수를 포함하는 배열을 0부터 n - 1까지 초기화한다면 어떠한 루프를 사용해야 할까?

A) for문을 사용할 것 같다.

### 돌발 퀴즈 3

Q) private 메서드의 동작을 서명하는 메서드는 명세 주석일까, 구현 주석일까?

A) 구현 주석이다.

### 돌발 퀴즈 4

Q) Employee 클래스에서 월급을 저장하는 필드의 이름으로 salary, s, monthly, Salary, employeeMonthlySalary 중 어느 이름이 좋을까?

A) salary

### 돌발 퀴즈 5

Q) 어떠한 public 메서드가 클래스 불변 조건을 위반하면 AssertionError를 던진다고 가정해보자. 이 사실을 메서드의 자바독 문서에 언급해야 할까?

A)

앞에서 자바독을 사용하여 문서화, 명세 주석을 보기 쉽게 나타낸다고 했다.

불변 조건은 문서화, 명세 주석에 포함된다고 생각하기 때문에 자바독 문서에 언급해야 한다고 생각한다.

## 연습 문제

### 연습문제 1

Q)

```java
List<String> names;
double[] lengths;
```

위와 같은 데이터가 주어졌을 때 다음과 같은 작업을 수행하려면 어떠한 종류의 루프를 사용해야 할까?

1. 리스트의 모든 이름을 출력한다.
2. 길이가 20자보다 긴 이름을 리스트에서 제거한다.
3. 모든 이름의 길이를 더한다.
4. 배열에 길이가 0인 항목이 있으면 불리언 플래그를 true로 설정한다.

A)

1. for문 아니면  Stream을 사용할 것 같다.

   Stream이 나오지 않았지만 Stream도 좋아 보인다.

```java
//for
for (String name : names) {
    System.out.println(name);
}

//Stream
names.stream().forEach(System.out::println);
```

2. Stream을 사용할 것 같다.

```java
Arrays.stream(lengths)
		.filter(length -> length < 20)
		.toArray();
```

3. Stream을 사용할 것 같다.

```java
names.stream()
		.mapToInt(name -> name.length()).sum();
```

4. 무슨 말인지 잘 모르겠다.

### 연습문제 2

Q) String.charAt(int index) 메서드의 계약을 설명하는 자바독 문서를 작성해 공식 문서와 비교해보자.

A)

```java
/** String의 index번째 문자를 반환한다.
 *
 * @throws IndexOutOfBoundsException 문자열의 범위를 벗어나면 발생한다.
 * @param index 가져오고 싶은 index를 입력받는다.
 */
public char charAt(int index) {
    return chars[index];
}
```

![https://user-images.githubusercontent.com/92966772/154826112-b9ed3817-a194-4197-b87f-c790e7cd9359.png](https://user-images.githubusercontent.com/92966772/154826112-b9ed3817-a194-4197-b87f-c790e7cd9359.png)

![https://user-images.githubusercontent.com/92966772/154826146-3bbddf3f-c4c0-4d8f-9b07-07288b33fc89.png](https://user-images.githubusercontent.com/92966772/154826146-3bbddf3f-c4c0-4d8f-9b07-07288b33fc89.png)

### 연습문제 3

Q) 다음과 같은 메서드가 어떠한 작업을 하는지 추측하고 가독성을 개선하자(자바독 메서드 주석도 추가하자). 이번 문제와 다음 문제의 코드는 온라인 저장소에서 확인할 수 있다.

```java
public static int f(String s, char c) {
    int i = 0, n = 0;
    boolean flag = true;
    while (flag) {
        if (s.charAt(i) == c)
            n++;
        if (i == s.length() -1)
            flag = false;
        else
            i++;
    }
    return n;
}
```

A)

String s에 문자 c가 포함된 개수를 반환하는 메서드다.

메서드명이 마음에 안들지만 더 이상 생각나는게 없네요 ㅎㅎ

```java
public static int countCharContainsString(String s, char c) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == c) {
            count++;
        }
    }
    return count;
}
```

### 연습문제 4

Q)

다음 메서드는 (10,000명이 별점을 주고 4,000번 포크한) 깃허브 저장소에 올린 알고리듬 모음에서 발췌했다. 이 메서드는 byte 타입의 인접 행렬로 표현된 그래프에서 너비 우선 방문(breadth-first visit)을 수행한다. 연습문제를 풀기 위해 알고리듬을 이해할 필요는 없으며 i번 노드에서 j번 노드로 향하는 간선이 존재하면 a[i][j]가 1이고 그렇지 않으면 0이라는 점만 알면 된다.

두 단계에 걸쳐 이 메서드의 가독성을 개선하자. 우선 변수 이름과 주석을 수정해 외형적인 변화를 가하자. 다음으로 구조적 변화를 가하자. 모든 변화는 API(파라미터 타입)와 가시적 행위(화면 출력)를 그대로 유지해야 한다.

A)