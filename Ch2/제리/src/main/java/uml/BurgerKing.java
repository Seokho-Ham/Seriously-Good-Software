package uml;

public class BurgerKing {

	public static void main(String[] args) {
		Burger burger = new Burger(new ChickenPatty());
		burger.eat();
	}

}
