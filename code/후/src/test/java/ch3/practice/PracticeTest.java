package ch3.practice;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PracticeTest {

    @Test
    void runExampleScenario() {
        Appliance tv = new Appliance(150);
        Appliance radio = new Appliance(30);
        Grid grid = new Grid(3000);

        tv.plugInto(grid);
        radio.plugInto(grid);
        assertThat(grid.residualPower()).isEqualTo(3000);
        tv.on();
        assertThat(grid.residualPower()).isEqualTo(2850);
        radio.on();
        assertThat(grid.residualPower()).isEqualTo(2820);
    }
}
