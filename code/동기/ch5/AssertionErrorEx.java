package code.동기.ch5;

public class AssertionErrorEx {

    public static void main(String[] args) {
        boolean condition = false;
//        assert condition : "Error message!";

        if (!condition) {
            throw new AssertionError("Error message!!!!!!");
        }
    }
}
