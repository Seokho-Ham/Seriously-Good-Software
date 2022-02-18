package ch6;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reference.Container;

class UnitTests {

    private Container a, b;

    @BeforeEach
    void setUp() {
        a = new Container();
        b = new Container();
    }

    @Test
    void testNewContainerIsEmpty() {
        assertThat(a.getAmount())
            .as("new container is not empty")
            .isEqualTo(0);
    }

    @Test
    @DisplayName("고립된 수조에 양수만큼 물 추가")
    void testAddPositiveToIsolated() {
        a.addWater(1);
        assertThat(a.getAmount())
            .as("should be 1")
            .isEqualTo(1);
    }

    @Test
    @DisplayName("고립된 수조에 0만큼 물 추가")
    void testAddZeroIsolated() {
        a.addWater(0);
        assertThat(a.getAmount())
            .as("should be 0")
            .isEqualTo(0);
    }

    @Test
    @DisplayName("고립된 수조에 유효한 음수만큼 물 추가")
    void testAddValidNegativeToIsolated() {
        a.addWater(10.5);
        a.addWater(-2.5);
        assertThat(a.getAmount())
            .as("should be 8")
            .isEqualTo(8);
    }

    @Test
    @DisplayName("고립된 수조에 유효하지 않은 음수만큼 물 추가")
    void testAddInvalidNegativeToIsolated() {
        assertThatThrownBy(() -> a.addWater(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("크기가 1인 수조에 크기가 1인 수조 연결")
    void testConnectOtherOneOne() {
        a.connectTo(b);
        a.addWater(2);
        assertThat(a.getAmount())
            .as("should be 1")
            .isEqualTo(1);
    }

    @Test
    @DisplayName("크기가 2+인 수조에 크기가 1인 수조 연결")
    void testConnectOtherTwoOne() {
        Container c = new Container();
        a.connectTo(b);
        a.connectTo(c);
        a.addWater(3);
        assertThat(a.getAmount())
            .as("should be 1")
            .isEqualTo(1);
    }

    @Test
    @DisplayName("크기가 1인 수조에 크기가 2+인 수조 연결")
    public void testConnectOtherOneTwo() {
        Container c = new Container();
        b.connectTo(c);
        a.connectTo(b);
        a.addWater(3);
        assertThat(a.getAmount())
            .as("should be 1")
            .isEqualTo(1);
    }
}
