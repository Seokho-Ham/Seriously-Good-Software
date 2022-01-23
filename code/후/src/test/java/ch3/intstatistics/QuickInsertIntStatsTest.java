package ch3.intstatistics;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuickInsertIntStatsTest {

    @Test
    void 삽입() {
        QuickInsertIntStats qi = new QuickInsertIntStats();
        qi.insert(2);
        qi.insert(10);
        qi.insert(11);
        qi.insert(20);
        qi.insert(100);
        assertThat(qi.getNumbers()).hasSize(5)
            .contains(2, 10, 11, 20, 100);
        assertThat(qi.getTotal()).isEqualTo(143);
    }

    @Test
    void 평균() {
        QuickInsertIntStats qi = new QuickInsertIntStats();
        qi.insert(2);
        qi.insert(10);
        qi.insert(11);
        qi.insert(20);
        qi.insert(100);
        assertThat(qi.getAverage()).isEqualTo(28.6);
    }

    @Test
    void 중앙값_홀수() {
        QuickInsertIntStats qi = new QuickInsertIntStats();
        qi.insert(2);
        qi.insert(10);
        qi.insert(11);
        qi.insert(20);
        qi.insert(100);
        assertThat(qi.getMedian()).isEqualTo(11);
    }

    @Test
    void 중앙값_짝수() {
        QuickInsertIntStats qi = new QuickInsertIntStats();
        qi.insert(2);
        qi.insert(10);
        qi.insert(11);
        qi.insert(20);
        assertThat(qi.getMedian()).isEqualTo(10.5);
    }

    @Test
    void 중앙값_비어있는() {
        QuickInsertIntStats qi = new QuickInsertIntStats();
        Throwable thrown = catchThrowable(() -> qi.getMedian());
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("리스트가 비어있습니다.");
    }
}
