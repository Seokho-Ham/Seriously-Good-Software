package ch6.boundedset;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoundedSetTest {

    private BoundedSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = new BoundedSet<>(3);
    }

    @Test
    @DisplayName("null 추가 테스트")
    void testAddNull() {
        assertThatThrownBy(() -> set.add(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("빈 set에 추가 테스트")
    void testAddOnEmpty() {
        Integer result = set.add(1);
        assertThat(result)
            .as("wrong return value")
            .isNull();
        assertThat(set.content())
            .as("wrong set content")
            .contains(1);
    }

    @Test
    @DisplayName("요소가 존재하고 꽉 차지 않은 set에 추가 테스트")
    void testAddAbsentOnNonFull() {
        set.add(1);
        Integer result = set.add(2);
        assertThat(result)
            .as("wrong return value")
            .isNull();
        assertThat(set.content())
            .as("wrong set content")
            .contains(1, 2);
    }

    @Test
    @DisplayName("요소가 존재하고 꽉 찬 set에 추가 테스트")
    void testAddAbsentOnFull() {
        set.add(1);
        set.add(2);
        set.add(3);
        Integer result = set.add(4);
        assertThat(result)
            .as("wrong return value")
            .isEqualTo(1);
        assertThat(set.content())
            .as("wrong set content")
            .contains(2, 3, 4);
    }

    @Test
    @DisplayName("요소가 존재하고 꽉 차지 않은 set에 이미 존재하는 요소 추가 테스트")
    void testAddPresentOnNonFull() {
        set.add(2);
        set.add(1);
        Integer result = set.add(2);;
        assertThat(result)
            .as("wrong return value")
            .isNull();
        assertThat(set.content())
            .as("wrong set content")
            .contains(1, 2);
    }

    @Test
    @DisplayName("요소가 존재하고 꽉 찬 set에 이미 존재하는 요소 추가 테스트")
    void testAddPresentOnFull() {
        set.add(1);
        set.add(2);
        set.add(3);
        Integer result = set.add(2);
        assertThat(result)
            .as("wrong return value")
            .isNull();
        assertThat(set.content())
            .as("wrong set content")
            .contains(1, 3, 2);
    }
}
