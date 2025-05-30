package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

/*
    Question has not been tested in codesignal
 */
public class Question78 {

    public static void main(String[] args) {
        char[][] box = {
                {'#', '#', '-', '-', '-', '-', '-', '-'},
                {'#', '#', '#', '-', '-', '-', '-', '-'},
                {'#', '#', '#', '-', '-', '#', '-', '-'}
        };

        char[][] result = solution(box);

        char[][] expected = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '#'},
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '#', '#'},
                {'#', '#', '#'},
                {'#', '#', '#'}
        };

        System.out.println("Actual Result:");
        printBox(result);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static char[][] solution(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;

        // Step 1: Rotate 90° clockwise
        char[][] rotated = new char[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - i - 1] = box[i][j];
            }
        }

        // Step 2: Apply gravity to each row (each column in rotated matrix)
        for (int i = 0; i < cols; i++) {
            int emptyRow = rows - 1;
            for (int j = rows - 1; j >= 0; j--) {
                if (rotated[i][j] == '*') {
                    emptyRow = j - 1;
                } else if (rotated[i][j] == '#') {
                    if (j != emptyRow) {
                        rotated[i][emptyRow] = '#';
                        rotated[i][j] = '-';
                    }
                    emptyRow--;
                }
            }
        }

        // Step 3: Flip rows to match expected orientation
        for (int i = 0; i < rotated.length / 2; i++) {
            char[] temp = rotated[i];
            rotated[i] = rotated[rotated.length - 1 - i];
            rotated[rotated.length - 1 - i] = temp;
        }

        return rotated;
    }

    public static void printBox(char[][] box) {
        for (char[] row : box) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
