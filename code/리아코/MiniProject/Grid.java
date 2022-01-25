public class Grid {
    private int mxPower;
    private int inUse;

    public Grid(int power) {
        this.mxPower = power;
    }
    public int residualPower() {
        return mxPower - inUse;
    }
    void supplyPower(int power) {
        if (inUse + power > mxPower) {
            throw new IllegalArgumentException("Maximum power exceeded.");
        }
        inUse += power;
    }
}