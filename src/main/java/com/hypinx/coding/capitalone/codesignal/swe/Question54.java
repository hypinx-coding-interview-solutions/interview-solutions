package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;

public class Question54 {

    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        matrix[0] = new int[]{1,2,3};
        matrix[1] = new int[]{4,5,6};
        matrix[2] = new int[]{7,8,9};

        String[] commands = {
                "swapRows 0 2",
                "swapColumns 1 2",
                "reverseRow 0",
                "reverseColumn 2",
                "rotate90Clockwise"
        };

        int[][] expected = new int[3][3];
        expected[0] = new int[]{1,4,8};
        expected[1] = new int[]{3,6,9};
        expected[2] = new int[]{7,5,2};

        int[][] result = solution(matrix, commands);

        TestCaseValidator.validateMultiDimensionalArrays(expected, result);
    }

    public static int[][] solution(int[][] matrix, String[] commands) {
        for (String command : commands) {
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "swapRows":
                    swapRows(matrix, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "swapColumns":
                    swapColumns(matrix, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "reverseRow":
                    reverseRow(matrix, Integer.parseInt(parts[1]));
                    break;
                case "reverseColumn":
                    reverseColumn(matrix, Integer.parseInt(parts[1]));
                    break;
                case "rotate90Clockwise":
                    matrix = rotate90Clockwise(matrix);
                    break;
            }
        }
        return matrix;
    }

    // Swap two rows in the matrix
    private static void swapRows(int[][] matrix, int r1, int r2) {
        int[] temp = matrix[r1];
        matrix[r1] = matrix[r2];
        matrix[r2] = temp;
    }

    // Swap two columns in the matrix
    private static void swapColumns(int[][] matrix, int c1, int c2) {
        for (int i = 0; i < matrix.length; i++) {
            int temp = matrix[i][c1];
            matrix[i][c1] = matrix[i][c2];
            matrix[i][c2] = temp;
        }
    }

    // Reverse the elements in a specific row
    private static void reverseRow(int[][] matrix, int row) {
        int left = 0;
        int right = matrix[row].length - 1;
        while (left < right) {
            int temp = matrix[row][left];
            matrix[row][left] = matrix[row][right];
            matrix[row][right] = temp;
            left++;
            right--;
        }
    }

    // Reverse the elements in a specific column
    private static void reverseColumn(int[][] matrix, int col) {
        int top = 0;
        int bottom = matrix.length - 1;
        while (top < bottom) {
            int temp = matrix[top][col];
            matrix[top][col] = matrix[bottom][col];
            matrix[bottom][col] = temp;
            top++;
            bottom--;
        }
    }

    // Rotate the matrix 90 degrees clockwise
    private static int[][] rotate90Clockwise(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] rotated = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }
}
