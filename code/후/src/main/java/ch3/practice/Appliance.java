package ch3.practice;

public class Appliance {

    private int powerConsumption;
    private boolean isOn;
    private Grid connectedGrid;

    public Appliance(int powerConsumption) {
        if (powerConsumption < 0) {
            throw new IllegalArgumentException("전자장치는 반드시 양수의 전력을 소비합니다.");
        }
        this.powerConsumption = powerConsumption;
        this.isOn = false;
    }

    public void plugInto(Grid grid) {
        if (this.connectedGrid != grid) {
            off();
        }
        this.connectedGrid = grid;
    }

    public void on() {
        if (!isOn) {
            this.isOn = true;
            this.connectedGrid.setResidualPower(-powerConsumption);
        }
    }

    public void off() {
        if (isOn) {
            this.isOn = false;
            this.connectedGrid.setResidualPower(powerConsumption);
        }
    }
}
