가독성의 재료

1. 구조적 특징
    - 프로그램 실행에 영향을 미치는 특징
        - 아키텍처 수준 : 하나 이상의 클래스와 관련 있는 특징
        - 클래스 수준 : 한 클래스와 그 안에 포함된 모든 메서드와 관련 있는 특징
        - 메서드 수준 : 한 메서드와 관련 있는 특징
2. 외부적 특징
    - 프로그램 실행에 영향을 미치지 않는 특징
    - 주석, 공백, 변수 명명 규칙 등

<br>

구조적 가독성 특징

<img width="541" alt="스크린샷 2022-02-19 09 47 16" src="https://user-images.githubusercontent.com/81368630/154796151-c660b6a1-237d-42ef-ac3e-4460a9cc0527.png">

외부적 가독성 특징

<img width="423" alt="스크린샷 2022-02-19 09 51 29" src="https://user-images.githubusercontent.com/81368630/154796153-c28476b8-5550-42aa-b417-ff9a1bf0f03d.png">

자바독을 이용한 클래스 헤더 문서화

<img width="415" alt="스크린샷 2022-02-19 15 23 22" src="https://user-images.githubusercontent.com/81368630/154796158-f1a1e277-e667-4e30-9595-b95439936a76.png">


자바독 호환되는 HTML 예시

<img width="411" alt="스크린샷 2022-02-19 15 27 17" src="https://user-images.githubusercontent.com/81368630/154796159-e467afb9-7610-45e1-9085-3247705652f7.png">


<br>

난독화기 전 후 비교

[https://www.preemptive.com/obfuscation/](https://www.preemptive.com/obfuscation/)


<br>

**돌발 퀴즈 1**

Q) 가독성의 영향을 받는 품질 속성은 무엇일까?

유지보수성

책 : 신뢰성 && 유지보수성

<br>

**돌발 퀴즈 2**

Q) n개의 정수를 포함하는 배열을 0부터 n - 1 까지 초기화한다면 어떠한 루프를 사용해야 할까?

```java
for (int i = 0; i < n; i++) {
	arr[i] = i;
}
```

<br>

**돌발 퀴즈 3**

Q) private 메서드의 동작을 설명하는 메서드는 명세 주석일까, 구현 주석일까?

구현 주석

<br>

**돌발 퀴즈 4**

Q) Employee 클래스에서 월급을 저장하는 필드의 이름으로, salary, s, monthlySalary, employeeMonthlySalary 중 어느 이름이 좋을까?

monthlySalary

<br>

**돌발 퀴즈 5**

Q) 어떠한 public 메서드가 클래스 불변 조건을 위반한다면 AssertionError를 던진다고 가정해보자. 이 사실을 메서드의자바독 문서에 언급해야 할까?

~~Yes!!~~

책 : AssertionError는 assert에 대한 에러라서 내부적인 로직에 대한 에러라서 자바독 문서에 언급할 필요가 없다.
