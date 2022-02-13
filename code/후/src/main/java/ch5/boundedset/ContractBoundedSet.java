package ch5.boundedset;

import java.util.ArrayList;
import java.util.List;

public class ContractBoundedSet<T> extends BoundedSet<T> {

    public ContractBoundedSet(int maxSize) {
        super(maxSize);
    }

    public ContractBoundedSet(ContractBoundedSet<T> other) {
        super(other);
    }

    @Override
    public void add(T elem) {
        // 1. 사전 조건 검사
        if (elem == null) {
            throw new NullPointerException();
        }
        // 2. 사후 조건 검사를 위한 데이터 저장
        ContractBoundedSet<T> copy = null;
        assert (copy = new ContractBoundedSet<>(this)) != null;
        // 3. 실제 작업 수행
        if (data.size() == MAX_SIZE) {
            data.poll();
        }
        data.remove(elem);
        data.offer(elem);
        // 4. 사후 조건 검사
        assert postAdd(copy, elem) : "add failed its post-condition!";
    }

    private boolean postAdd(ContractBoundedSet<T> oldSet, T newElement) {
        if (!data.getLast().equals(newElement)) {
            return false;
        }
        List<T> copyOfCurrent = new ArrayList<>(data);
        copyOfCurrent.remove(newElement);
        oldSet.data.remove(newElement);
        if (oldSet.data.size() == MAX_SIZE) {
            oldSet.data.poll();
        }
        return oldSet.data.equals(copyOfCurrent);
    }

    @Override
    public boolean contains(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        return data.contains(elem);
    }
}
