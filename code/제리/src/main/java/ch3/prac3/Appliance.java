package ch3.prac3;

public class Appliance {

	private final int requiredPower;
	private Grid grid;
	private boolean isOn;

	public Appliance(int requiredPower) {
		if (requiredPower < 0) {
			throw new IllegalArgumentException("음수의 전력 요구량은 존재할 수 없습니다.");
		}
		this.requiredPower = requiredPower;
		this.isOn = false;
	}

	public void plugInto(Grid grid) {
		if (this.grid == null) {
			this.grid = grid;
		} else if (this.grid != grid) {
			off();
			this.grid = grid;
		}
	}

	public void on() {
		if (!isOn) {
			this.grid.connected(requiredPower);
			isOn = true;
		} else {
			System.out.println("이미 켜져 있습니다.");
		}
	}

	public void off() {
		if (isOn) {
			this.grid.disConnected(requiredPower);
			isOn = false;
		} else {
			System.out.println("이미 꺼져 있습니다.");
		}
	}


}
