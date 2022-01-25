public class UseCase {
    public static void main(String[] args) {
        Grid grid = new Grid(3000);

        Appliance tv = new Appliance(150);
        Appliance radio = new Appliance(30);

        tv.plugInto(grid);
        radio.plugInto(grid);
        System.out.println(grid.residualPower());

        tv.on();
        System.out.println(grid.residualPower());

        radio.on();
        System.out.println(grid.residualPower());

        tv.off();
        System.out.println(grid.residualPower());

        radio.off();
        System.out.println(grid.residualPower());

        Appliance limit = new Appliance(3100);
        limit.plugInto(grid);
        limit.on();
    }
}
