package ch3.intstatistics;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuickSelectIntStatsTest {

    @Test
    void 삽입() {
        QuickSelectIntStats qs = new QuickSelectIntStats();
        qs.insert(2);
        qs.insert(10);
        qs.insert(11);
        qs.insert(20);
        qs.insert(100);
        assertThat(qs.getNumbers()).hasSize(5)
            .contains(2, 10, 11, 20, 100);
        assertThat(qs.getTotal()).isEqualTo(143);
    }

    @Test
    void 평균() {
        QuickSelectIntStats qs = new QuickSelectIntStats();
        qs.insert(2);
        qs.insert(10);
        qs.insert(11);
        qs.insert(20);
        qs.insert(100);
        assertThat(qs.getAverage()).isEqualTo(28.6);
    }

    @Test
    void 중앙값_홀수() {
        QuickSelectIntStats qs = new QuickSelectIntStats();
        qs.insert(2);
        qs.insert(10);
        qs.insert(11);
        qs.insert(20);
        qs.insert(100);
        assertThat(qs.getMedian()).isEqualTo(11);
    }

    @Test
    void 중앙값_짝수() {
        QuickSelectIntStats qs = new QuickSelectIntStats();
        qs.insert(2);
        qs.insert(10);
        qs.insert(11);
        qs.insert(20);
        assertThat(qs.getMedian()).isEqualTo(10.5);
    }

    @Test
    void 중앙값_비어있는() {
        QuickSelectIntStats qs = new QuickSelectIntStats();
        Throwable thrown = catchThrowable(() -> qs.getMedian());
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("리스트가 비어있습니다.");
    }
}
