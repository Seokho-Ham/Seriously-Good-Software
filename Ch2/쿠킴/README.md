## 요약, 정리 

2장 레퍼런스 구현 중 메모리 주제에 대해 글을 작성 했습니다.   
[자바 객체의 메모리 크기는 얼마일까?](https://kukim.tistory.com/59)

## 퀴즈

### 퀴즈 1
Q) 스마트폰의 연락처를 저장한다면 어떠한 컬렉션 인터페이스를 사용할 것인가?  
A) TreeSet : 중복 허용하지 않고 지정된순서(가나다)로, 조회가 빠른 컬렉션을 사용한다  

### 퀴즈 3
Q) android.graphics.Rect 클래스는 int 타입의 public 필드 4개를 포함한다. Rect 객체는 몇 바이트를 차지할까?  
A) 객체헤더12 + int 4바이트 * 4 = 28, 패딩을 맞춰서 32바이트  


### 문제 1
```java
public class IdentityMatrix {
    public static int[][] identityMatrix(int n) {
        int[][] result = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) {
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }
}


// 개선 코드
// 위와 비교했을 때 O(n)으로 시간 복잡도로 보인다.
// 하지만 사실 new int[n][n] 을 만들 떄 초기값을 주기 때문에 이때 이미 O(n^2)이다.
// 따라서 위 코드보다 효율을 좀 더 좋을 수 있지만, 비슷하다.
public class IdentityMatrixSolved {
    public static int[][] identityMatrix(int n) {
        int[][] result = new int[n][n];
        for (int i=0; i<n; i++) {
            result[i][i] = 1;
        }
        return result;
    }
}
```

### 문제 2
Q) java.util.linkedList<T> 클래스는 T 타입 객체의 참조를 포함하는 이중 연결리스트를 구현한다. 소스 코드를 확인 후 요로 개수가 n일 때 LinkedList의 크기를 바이트 단위로 평가하라  

A)  

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    transient int size = 0;

    /**
     * Pointer to first node.
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     */
    transient Node<E> last;

}


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
```
- LinkedList = 기본(오버헤드 12 + 멤버변수(size, first, last = 3 * 4바이트) + 상속된(AbstractSequentialList 에서) modCount 4바이트) = 28바이트

- LinkedList에 연결된 Node들 = 오버헤드12 + 멤버변수(item, next, prev = 3 * 4바이트) = 24바이트

- Total = 28 바이트  + n* 24 바이트

### 문제 3

A)  
```java
import java.util.*;

// 연습문제 ch2의 3번 , DFS로 풀이
public class User {
    private String name;
    private Set<User> directFriends = new HashSet<>();

    private User() {

    }

    public static User createUserWithName(String name) {
        User user = new User();
        user.name = name;

        return user;
    }

    public void befriend(User other) {
        directFriends.add(other);
        other.directFriends.add(other);
    }

    public boolean isDirectFriendOf(User other) {
        boolean result = directFriends.contains(other);
        return result;
    }

    public boolean isIndirectFriendOf(User other) { 
        Set<User> visited = new HashSet<>();
        Stack<User> stack = new Stack<>();

        stack.push(this); // DFS : stack 활용
        while (Objects.nonNull(stack)) {
            User user = stack.pop();
            if (visited.add(user)) {
                stack.addAll(user.directFriends);
            }

            if (user.equals(other)) {
                return true;
            }

        }
        return false;
    }


    public static void main(String[] args) {
        User user1 = User.createUserWithName("피터");
        User user2 = User.createUserWithName("피터");
        User user3 = User.createUserWithName("메이");
        User user4 = User.createUserWithName("MJ");

        user1.befriend(user2);
        user2.befriend(user3);

        System.out.println(user1.isDirectFriendOf(user2));
        System.out.println(user1.isIndirectFriendOf(user3));
        System.out.println(user1.isDirectFriendOf(user4));
        System.out.println(user1.isDirectFriendOf(user4));

    }
}
```