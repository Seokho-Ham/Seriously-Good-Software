package ch6.practice;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IndexOfTest {

    private final static String TESTME = "test me";

    @Test
    public void testNominal() {
        int result = TESTME.indexOf((int) 't', 2);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void testNegativeIndex() {
        int result = TESTME.indexOf((int) 't', -2);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testZeroCharAndOverflowingIndex() {
        int result = TESTME.indexOf(0, TESTME.length() + 10);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void testNegativeCharAndZeroIndex() {
        int result = TESTME.indexOf(-1, 0);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void testEmptyString() {
        int result = "".indexOf((int) 't', 0);
        assertThat(result).isEqualTo(-1);
    }
}
