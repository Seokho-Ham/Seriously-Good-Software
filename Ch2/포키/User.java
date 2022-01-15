import java.util.HashSet;
import java.util.Set;

public class User {
    private String userName;
    private Set<User> directConnectedGroup;
    private Set<User> indirectConnectedGroup;

    public User(String userName) {
        this.userName = userName;
        directConnectedGroup = new HashSet<>();
        indirectConnectedGroup = new HashSet<>();
    }

    public void befriend(User other) {
        if (!isDirectFriendOf(other)) {
            directConnectedGroup.add(other);
            other.directConnectedGroup.add(this);
        }
    }

    public boolean isDirectFriendOf(User other) {
        return directConnectedGroup.contains(other);
    }
    public boolean isIndirectFriendOf(User other) {
        //현재 directFriend를 순환한다.
        //순환하는 과정에서 other이 directfriend알 경우 true, 아니면 false
        for (User friend : directConnectedGroup) {
            if (friend.isDirectFriendOf(other)) {
                return true;
            }
        }
        return false;
    }
}


//요구사항
// 이름을 담는 변수가 있다. 이름을 매개변수로 받는 생성자를 만들것.
// 친구 관계를 맺는 메서드가 있다. 친구 관계는 대칭적이다.
// 직접 친구인지 간접친구인지 판별하는 메서드가 있다.