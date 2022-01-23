package chap03;

public class Main {

    public static void main(String[] args) throws Exception{
        Appliance tv = new Appliance(150);
        Appliance radio = new Appliance(30);
        Appliance dd = new Appliance(3001);
        Grid grid = new Grid(3000);

        tv.plugIn(grid);
        radio.plugIn(grid);
        dd.plugIn(grid);




        System.out.println(grid.residualPower());
        tv.on();
        System.out.println(grid.residualPower());
        radio.on();
        System.out.println(grid.residualPower());
        radio.off();
        System.out.println(grid.residualPower());

        dd.on();
        System.out.println(grid.residualPower());
    }

}
