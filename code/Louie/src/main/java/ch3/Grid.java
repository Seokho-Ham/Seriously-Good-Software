package ch3;

public class Grid {
    private int maxEnergy;
    private int nowEnergy;

    public Grid(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.nowEnergy = maxEnergy;
    }

    public int residualPower() {
        return nowEnergy;
    }

    public void on(int useEnergy) {
        if (useEnergy > nowEnergy) {
            throw new RuntimeException("전기가 부족합니다. 사용하지 않는 전자장치를 꺼주세요");
        }
        nowEnergy -= useEnergy;
    }

    public void off(int useEnergy) {
        if (maxEnergy > nowEnergy + useEnergy) {
            throw new RuntimeException("전기가 부족합니다. 사용하지 않는 전자장치를 꺼주세요");
        }
        nowEnergy += useEnergy;
    }
}
