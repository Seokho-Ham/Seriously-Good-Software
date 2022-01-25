import java.util.Objects;

public class Appliance {
    private Grid grid;
    private int required;
    private boolean isOn;

    public Appliance(int power) {
        this.required = power;
    }

    public void plugInto(Grid obj) {
        if (Objects.isNull(grid)) {
            grid = obj;
        }
        if (isOn) {
            grid.supplyPower(required);
        }
    }

    public void on() {
        if (Objects.isNull(grid) || isOn) {
            return;
        }
        grid.supplyPower(required);
        isOn = true;
    }

    public void off() {
        if (Objects.isNull(grid) && !isOn) {
            return;
        }
        grid.supplyPower(-required);
        isOn = false;
    }
}