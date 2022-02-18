package ch6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InterleaveLists {

    public static List<Object> interleaveLists(List<Object> list1, List<Object> list2) {
        Objects.requireNonNull(list1);
        Objects.requireNonNull(list2);

        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("두 리스트의 크기가 다릅니다.");
        }

        List<Object> result = new ArrayList<>();
        for (int i = 0; i < list1.size() + list2.size(); i++) {
            if (i % 2 == 0) {
                result.add(list1.get(i / 2));
            } else {
                result.add(list2.get(i / 2));
            }
        }
        return result;
    }
}
