package ch5.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InterleaveLists {

    public static <T> List<T> interleaveLists(List<? extends T> a, List<? extends T> b) {
        if (a == null || b == null) {
            throw new NullPointerException();
        }
        if (a.size() != b.size()) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            result.add(a.get(i));
            result.add(b.get(i));
        }
        assert interleaveCheckPost(a, b, result);
        return result;
    }

    private static boolean interleaveCheckPost(List<?> a, List<?> b, List<?> result) {
        if (result.size() != a.size() + b.size()) {
            return false;
        }
        boolean odd = true;
        Iterator<?> ia = a.iterator();
        Iterator<?> ib = b.iterator();
        for (Object elem : result) {
            if (odd && elem != ia.next()) {
                return false;
            }
            if (!odd && elem != ib.next()) {
                return false;
            }
            odd = !odd;
        }
        return true;
    }
}
