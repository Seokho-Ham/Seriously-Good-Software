package ch7.exercises;

public class Exercise2 {
    private char[] chars;

    /** String의 index번째 문자를 반환한다.
     *
     * @throws IndexOutOfBoundsException 문자열의 범위를 벗어나면 발생한다.
     * @param index 가져오고 싶은 index를 입력받는다.
     */
    public char charAt(int index) {
        return chars[index];
    }

    public Exercise2(char[] chars) {
        this.chars = chars;
    }

}
