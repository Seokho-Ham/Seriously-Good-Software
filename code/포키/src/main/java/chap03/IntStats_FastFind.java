package chap03;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntStats_FastFind {
    private long sum;
    private List<Integer> list = new ArrayList<>();

    //정수 하나를 리스트에 추가한다.
    public void insert(int n) {
        int i = 0;
        for (Integer k : list) {
            if (k >= n) break;
            i++;
        }
        list.add(i, n);
        sum += n;
    }

    //리스트에 추가된 모든 정수의 평균을 계산
    public double getAverage() {
        return sum / (double) list.size();
    }

    //리스트에 추가된 모든 정수의 중앙값.
    //짝수의 경우 가운데 2요소의 평균.
    public double getMedian() {
        if (list.size() < 1) {
            throw new NoSuchElementException("리스트에 데이터가 없습니다.");
        }
        if (list.size() % 2 == 1) {
            return list.get(list.size() / 2);
        } else {
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2) / 2.0);
        }
    }
}
