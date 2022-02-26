package ch7.exercises;

import java.util.stream.IntStream;

public class Exercise3 {

    public static int countCharContainsString(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
}
