package ch5.boundedset;

import java.util.HashSet;
import java.util.Set;

public class InvariantBoundedSet<T> extends BoundedSet<T> {

    public InvariantBoundedSet(int maxSize) {
        super(maxSize);
    }

    @Override
    public void add(T elem) {
        // 1. 사전 조건 검사
        if (elem == null) {
            throw new NullPointerException();
        }
        // 2. 실제 작업 수행
        if (data.size() == MAX_SIZE) {
            data.poll();
        }
        data.remove(elem);
        data.offer(elem);
        // 3. 불변 조건 검사
        assert checkInvariants() : "add broke an invariant!";
    }

    private boolean checkInvariants() {
        if (data.size() > MAX_SIZE) {
            return false;
        }
        Set<T> elements = new HashSet<>();
        for (T element : data) {
            if (!elements.add(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        return data.contains(elem);
    }
}
