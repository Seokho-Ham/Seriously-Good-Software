package code.포키.src.main.java.chap05;

import java.util.*;

public class BoundedSet<T> {
    private final LinkedList<T> data;
    private final int capacity;

    public BoundedSet(int capacity) {
        this.data = new LinkedList<>();
        this.capacity = capacity;
    }

    public BoundedSet(BoundedSet<T> other) {
        data = new LinkedList<>(other.data);
        capacity = other.capacity;
    }

    //주어진 요소를 크기가 정해진 집합에 추가한다.
    //용량 초과 시 가장 오래 된 요소를 제거해야한다.
    //이미 존재하는 요소를 추가하면 해당 요소를 가장 최신 요소의 자리로 이동해야한다.

    //사전 조건 : null이 아니다
    //페널티 : NullPointerException 던지기

    public void add(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        BoundedSet<T> copy = null;
        assert (copy = new BoundedSet<>(this)) != null;

        data.remove(elem);
        if (data.size() == capacity) {
            data.removeFirst();
        }
        data.addLast(elem);

        assert postAdd(copy, elem) : "실패!!!";
    }
    public void add_v2(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }
        data.remove(elem);
        if (data.size() == capacity) {
            data.removeFirst();
        }
        data.addLast(elem);

        assert checkInvariants() : "실패!!!";
    }


    private boolean postAdd(BoundedSet<T> copy, T elem) {
        if(data.getLast().equals(elem)) return false;

        List<T> copyOfCurrent = new ArrayList<>(data);
        copyOfCurrent.remove(elem);
        copy.data.remove(elem);
        if (copy.data.size() == capacity) {
            copy.data.removeFirst();
        }
        return copy.data.equals(copyOfCurrent);
    }

    private boolean checkInvariants() {
        if (data.size() > capacity) {
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

    // 크기가 정해진 집합에 주어진 요소가 존재하면 true를 리턴한다.
    //사전 조건 : null이 아니다.
    //페널티 : NullPointerException 던지기
    public boolean contains(T elem) {
        return data.contains(elem);
    }
}

//요소의 삽입 순서를 기억한다.
// 객체 생성 시 정해지는 최대 크기인 용량이 존재한다.