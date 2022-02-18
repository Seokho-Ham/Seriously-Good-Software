package ch6;

import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch6.InterleaveLists.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class InterleaveListsTest {
    ArrayList<Object> list1;
    ArrayList<Object> list2;

    @BeforeEach
    void beforeEach() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    @Test
    @DisplayName("주어진 두 리스트 중 하나가 null이면 메서드는 NPE를 던진다.")
    void parameterEmptyThrowNPE() {
        assertThrows(NullPointerException.class, () -> interleaveLists(null, list2));
    }

    @Test
    @DisplayName("두 리스트의 길이가 다르면 IAE를 던진다.")
    void twoListNotEqualsSize() {
        list1.add("a");
        list2.add("b");
        list2.add("c");

        assertThrows(IllegalArgumentException.class, () -> interleaveLists(list1, list2));
    }

    @Test
    @DisplayName("두 시르트의 요소가 번갈아 저장된 새로운 List를 리턴한다.")
    void returnTwoListAlternateSaveList() {
        list1.add("a");
        list1.add("b");
        list2.add("c");
        list2.add("d");

        List<Object> result = interleaveLists(list1, list2);
        List<Object> totalList = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());

        for (Object o : totalList) {
            assertThat(result).contains(o);
        }
    }
}
