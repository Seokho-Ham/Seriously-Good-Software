package ch2.mission3;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String name;
	private Set<User> directFriend;

	public User(String name) {
		this.name = name;
		directFriend = new HashSet<>();
		directFriend.add(this);
	}

	public void befriend(User other) {
		this.directFriend.add(other);
		other.directFriend.add(this);
	}

	public boolean isDirectFriendOf(User other) {
		return directFriend.contains(other);
	}

	public boolean isIndirectFriendOf(User other) {
		Set<User> indirectFriend = new HashSet<>();

		for (User user : directFriend) {
			indirectFriend.addAll(user.directFriend);
		}

		indirectFriend.removeAll(directFriend);

		return indirectFriend.contains(other);
	}

}
