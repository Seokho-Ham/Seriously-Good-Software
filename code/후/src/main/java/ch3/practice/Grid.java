package ch3.practice;

public class Grid {

    private int residualPower;

    public Grid(int power) {
        this.residualPower = power;
    }

    public int residualPower() {
        return this.residualPower;
    }

    private boolean isOverloaded(int power) {
        return this.residualPower < power;
    }

    public void setResidualPower(int power) throws IllegalArgumentException {
        if (isOverloaded(power)) {
            throw new IllegalArgumentException("잔류 전력이 충분하지 않아 과부하가 걸렸습니다.");
        }
        this.residualPower += power;
    }
}
