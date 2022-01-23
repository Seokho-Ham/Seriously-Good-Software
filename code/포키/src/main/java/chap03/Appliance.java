package chap03;

import java.util.Objects;

public class Appliance {
    private int energy;
    private boolean power;
    private Grid connectedGrid;

    public Appliance(int useEnergy) {
        this.energy = useEnergy;
        this.power = false;
    }

    public void plugIn(Grid grid) {
        if (connectedGrid!= null && connectedGrid != grid) {
            connectedGrid.restoreEnergy(energy);
        }
        connectedGrid = grid;
    }

    public void on() throws Exception {
        if (connectedGrid.getRemainingEnergy() < energy) {
            throw new IllegalArgumentException("전력 부족");
        }
        if (power) {
            System.out.println("이미 켜져있는 상태입니다.");
            return;
        }
        connectedGrid.useEnergy(energy);
        power = true;
    }

    public void off() {
        if (!power) {
            System.out.println("이미 꺼져있는 상태입니다.");
            return;
        }
        power = false;
        connectedGrid.restoreEnergy(energy);
    }
}
