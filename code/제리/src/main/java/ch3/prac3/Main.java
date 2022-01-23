package ch3.prac3;

public class Main {

	public static void main(String[] args) {
		Appliance tv = new Appliance(3100);
		Appliance radio = new Appliance(30);
		Grid grid = new Grid(3000);

		tv.plugInto(grid);
		radio.plugInto(grid);

		System.out.println(grid.residualPower());

		tv.on();

		System.out.println(grid.residualPower());

		radio.on();
		System.out.println(grid.residualPower());

		tv.plugInto(grid);

		System.out.println(grid.residualPower());


	}

}
