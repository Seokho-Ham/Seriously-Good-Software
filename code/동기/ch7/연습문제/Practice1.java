package code.동기.ch7.연습문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice1 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        double[] lengths = new double[10];

        // 리스트의 모든 이름을 출력한다.
        for (String name : names) {
            System.out.println(name);
        }

        // 길이가 20자보다 긴 이름을 리스트에서 제거한다.
        Iterable<String> iterable = names.iterator();

        // 모든 이름의 길이를 더한다.
        double totalLength = 0;
        for (double length : lengths) {
            totalLength += length;
        }

        double totalLength = Arrays.stream(lengths).sum();


        // 배열에 길이가 0인 항목이 있으면 불리언 플래그를 true로 설정한다.
        boolean containsZero = false;
        for (double length : lengths) {
            if (length == 0) {
                containsZero = true;
                break;
            }
        }

        boolean containsZero = Arrays.stream(lengths).anyMatch(
                length -> length == 0);
        )


    }
}
