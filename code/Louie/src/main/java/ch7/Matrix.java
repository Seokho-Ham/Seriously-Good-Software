package ch7;

import java.util.Arrays;

public class Matrix {

    /** Transposes a square matrix
     *
     * @param matrix  a matrix
     * @throws IllegalArgumentException if the given matrix is not square
     */
    public static void transpose(double[][] matrix) {
        if (isNotSquare(matrix)){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /** Checks whether a given matrix is square-shaped
     *
     * @param matrix a matrix
     * @return {@code true} if the given matrix is square
     */
    public static boolean isNotSquare(double[][] matrix) {
        for (double[] row: matrix) {
            if (row.length != matrix.length) {
                return true;
            }
        }
        return false;
    }

    public static void main(String ... args) {
        double matrix[][] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        transpose(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
