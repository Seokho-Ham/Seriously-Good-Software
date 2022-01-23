package ch3.prac3;

public class Grid {

	private int remainPower;

	public Grid(int remainPower) {
		this.remainPower = remainPower;
	}

	public int residualPower() {
		return remainPower;
	}

	public void connected(int power) {
		if (remainPower < power) {
			throw new IllegalArgumentException("전원을 킬 수 없습니다.");
		}
		remainPower -= power;
	}

	public void disConnected(int power) {
		remainPower += power;
	}

}
