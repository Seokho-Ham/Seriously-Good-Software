package ch3.speed1;

import java.util.HashSet;
import java.util.Set;

public class Group {

	double amountPerContainer;
	Set<Container> members;

	Group(Container c) {
		members = new HashSet<>();
		members.add(c);
	}
}
