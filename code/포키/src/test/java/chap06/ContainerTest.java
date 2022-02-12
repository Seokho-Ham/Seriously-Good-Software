package chap06;

import chap02.Container;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ContainerTest {
    private Container a,b;

    @BeforeEach
    public void setUp() {
        a = new Container();
        b = new Container();
    }

    @Test
    public void testNewContainerIsEmpty(){
        assertThat(a.getAmount()).isEqualTo(0);
    }

    //------------------------------------------------
    //addWater

    @Test
    public void testAddValidNegativeToConnected() {
        a.connectTo(b);
        a.addWater(10);
        a.addWater(-4);

        assertThat(a.getAmount()).isEqualTo(3);
    }

    @Test
    public void testAddPositiveToIsolated() {
        a.addWater(1);

        assertThat(a.getAmount()).isEqualTo(1);
    }

    @Test
    public void testAddZeroToIsolated() {
        a.addWater(0);

        assertThat(a.getAmount()).isEqualTo(0);
    }
    @Test
    public void testAddValidNegativeToIsolated() {
        a.addWater(10.5);
        a.addWater(-2.5);

        assertThat(a.getAmount()).isEqualTo(8);
    }
    @Test
    public void testAddInvalidNegativeToIsolated() {
        assertThatThrownBy(()-> a.addWater(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    //------------------------------------------------
    //connectTo

    @Test
    public void testConnectOtherOneOne() {
        a.connectTo(b);
        a.addWater(2);

        assertThat(a.getAmount()).isEqualTo(1);
    }

    @Test
    public void testConnectOtherTwoOne() {
        Container c = new Container();
        a.connectTo(b);
        a.connectTo(c);
        a.addWater(3);

        assertThat(a.getAmount()).isEqualTo(1);
    }

    @Test
    public void testConnectOtherOneTwo() {
        Container c = new Container();
        b.connectTo(c);
        a.connectTo(b);
        a.addWater(3);

        assertThat(a.getAmount()).isEqualTo(1);
    }
}
