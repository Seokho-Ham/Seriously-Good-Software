package chap03;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private int maxEnergy;
    private int remainingEnergy;


    public Grid(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.remainingEnergy = maxEnergy;
    }

    public int getRemainingEnergy() {
        return remainingEnergy;
    }

    public void useEnergy(int energy) {
        remainingEnergy -= energy;
    }

    public void restoreEnergy(int energy) {
        remainingEnergy += energy;
    }

    public int residualPower() {
        return remainingEnergy;
    }

}
