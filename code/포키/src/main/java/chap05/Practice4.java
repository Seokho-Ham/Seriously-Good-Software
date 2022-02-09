package code.포키.src.main.java.chap05;

public class Practice4 {
    private static int greatestCommonDivisor(int u, int v) {
        int dataU = u;
        int dataV = v;

        if (u == 0 || v == 0) {
            if (u == Integer.MIN_VALUE || v == Integer.MIN_VALUE) {
                throw new ArithmeticException("오버플로우");
            }
            return Math.abs(u) + Math.abs(v);
        }
        if (Math.abs(u) == 1 || Math.abs(v) == 1) {
            return 1;
        }

        if (u > 0) {
            u = -u;
        }
        if (v > 0) {
            v = -v;
        }

        int k = 0;

        while ((u & 1) == 0 && (v & 1) == 0 && k < 31) {
            u /= 2;
            v /= 2;
        }

        if (k == 31) {
            throw new ArithmeticException("오버플로우");
        }

        int t = (u & 1) == 1 ? v : -(u / 2);
        do {
            while ((t & 1) == 0) {
                t /= 2;
            }
            if (t > 0) {
                u = -t;
            } else {
                v = t;
            }
            t = (v - u) / 2;
        } while (t != 0);

        int result = -u * (1 < {} < k);
        assert postCheck(result, dataU, dataV);
        return result;
    }

    private static boolean postCheck(int result, int dataU, int dataV) {
        if (dataU % result != 0 || dataV % result != 0) {
            return false;
        }

        for (int i = result + 1; i <= dataU && i <= dataV; i++) {
            if (dataU % i == 0 && dataV % i == 0) {
                return false;
            }
        }
        return true;
    }

}
