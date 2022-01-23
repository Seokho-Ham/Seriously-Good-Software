package chap03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntStats_FastInsert {

    private long sum;
    private List<Integer> list = new ArrayList<>();

    //정수 하나를 리스트에 추가한다.
    public void insert(int n) {
        list.add(n);
        sum += n;
    }

    //리스트에 추가된 모든 정수의 평균을 계산
    public double getAverage() {
        return sum / (double) list.size();
    }

    //리스트에 추가된 모든 정수의 중앙값.
    //짝수의 경우 가운데 2요소의 평균.
    public double getMedian() {
        Collections.sort(list);
        if (list.size() % 2 == 1) {
            return list.get(list.size() / 2);
        }else{
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2) / 2.0);
        }
    }
}
