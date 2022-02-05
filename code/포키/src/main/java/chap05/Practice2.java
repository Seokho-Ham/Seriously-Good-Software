package code.포키.src.main.java.chap05;

import java.util.ArrayList;
import java.util.List;

public class Practice2 {
    public static <T> List<T> interleaveLists(List<T> l1, List<T> l2) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException();
        }
        if (l1.size() != l2.size()) {
            throw new IllegalArgumentException();
        }

        List<T> result = new ArrayList<>();

        for (int i = 0; i < l1.size(); i++) {
            result.add(l1.get(i));
            result.add(l2.get(i));
        }
        assert postCheck(result, l1,l2) : "실패!!!";
        return result;
    }

    private static <T> boolean postCheck(List<T> result, List<T> l1, List<T> l2) {
        boolean bool = true;
        for (int i = 0; i < result.size(); i++) {
            if (i % 2 == 0) {
                bool = l1.contains(result.get(i));
            }else{
                bool = l2.contains(result.get(i));
            }
            if(!bool) return false;
        }
        return true;
    }
}
