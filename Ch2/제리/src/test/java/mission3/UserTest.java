package mission3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	@DisplayName("직접친구 테스트")
	void directTest() {
		User userA = new User("a");
		User userB = new User("b");
		User userC = new User("c");

		userA.befriend(userB);

		assertTrue(userA.isDirectFriendOf(userB));

		assertFalse(userA.isDirectFriendOf(userC));

	}

	@Test
	@DisplayName("간접친구 테스트")
	void inDirectTest() {
		User userA = new User("a");
		User userB = new User("b");
		User userC = new User("c");

		userA.befriend(userB);

		userC.befriend(userB);

		assertTrue(userA.isDirectFriendOf(userB));

		assertFalse(userA.isDirectFriendOf(userC));

		assertTrue(userA.isIndirectFriendOf(userC));

		assertFalse(userA.isIndirectFriendOf(userB));

	}

}