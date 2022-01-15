package uml;

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
