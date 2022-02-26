package ch6;

public class MyString {
    private String string;

    public MyString(String string) {
        this.string = string;
    }

    public int indexOf(int ch, int fromIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = fromIndex; i < string.length(); i++) {
            if (string.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }
}
