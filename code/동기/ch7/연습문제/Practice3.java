package code.동기.ch7.연습문제;

public class Practice3 {

    public static int countOccrrences(String s, char c) {

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }

        return count;
    }
}
