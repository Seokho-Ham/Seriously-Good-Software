package sns;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String name;
    private Set<User> friends;

    public User(String name) {
        this.name = name;
        this.friends = new HashSet<>();
    }

    public void befriend(User other) {
        friends.add(other);
        other.friends.add(this);
    }

    public boolean isDirectFriendOf(User other) {
        return friends.contains(other);
    }

    public boolean isIndirectFriendOf(User other) {
        Set<User> friendsOfFriends = new HashSet<>();
        friends.forEach(f -> {
            friendsOfFriends.addAll(f.friends);
        });
        friendsOfFriends.removeAll(friends);
        return friendsOfFriends.contains(other);
    }
}
