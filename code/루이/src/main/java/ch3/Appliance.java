package ch3;

public class Appliance {
    private int useEnergy;
    private Grid grid;
    private boolean power;

    public Appliance(int useEnergy) {
        this.useEnergy = useEnergy;
    }

    public void plugInto(Grid grid) {
        this.grid = grid;
    }

    public void on() {
        gridNullCheck();
        if (!power) {
            power = true;
            grid.on(useEnergy);
        }
    }

    public void off() {
        gridNullCheck();
        if (power) {
            power = true;
            grid.off(useEnergy);
        }
    }

    private void gridNullCheck() {
        if (grid == null) {
            throw new RuntimeException("그리드를 연결해 주세요");
        }
    }

    public static void main(String...args) {
        Appliance tv = new Appliance(150), radio = new Appliance(30);
        Grid grid = new Grid(3000);

        tv.plugInto(grid);
        radio.plugInto(grid);
        System.out.println(grid.residualPower());
        tv.on();
        System.out.println(grid.residualPower());
        radio.on();
        System.out.println(grid.residualPower());
    }
}
