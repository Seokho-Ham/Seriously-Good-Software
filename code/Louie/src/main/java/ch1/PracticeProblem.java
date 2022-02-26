package ch1;

public class PracticeProblem {

    public static int[][] identityMatrix(int n) { // O(n^2)
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }

    public static int[][] identityMatrix2(int n) { // O(n)
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }
        return result;
    }
}
