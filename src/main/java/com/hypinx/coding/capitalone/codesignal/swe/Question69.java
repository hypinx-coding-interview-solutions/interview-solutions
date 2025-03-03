package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;

public class Question69 {

    // Directions for diagonal neighbors: (row, col)
    private static final int[][] DIAGONALS = {
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public static void main(String[] args) {
        int[][] bubbles = {
                {1, 1, 1, 4, 3},
                {4, 1, 2, 3, 3},
                {1, 5, 1, 1, 2},
                {4, 3, 2, 2, 4}
        };

        int[][] operations = {
                {1, 1},
                {3, 3},
                {2, 2},
                {3, 0}
        };

        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 4, 3},
                {0, 5, 0, 3, 3},
                {4, 3, 2, 1, 4}
        };

        int[][] result = solution(bubbles, operations);

        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }

        TestCaseValidator.validateTestCase("1", Arrays.deepEquals(expected, result));

    }

    public static int[][] solution(int[][] bubbles, int[][] operations) {
        int rows = bubbles.length;
        int cols = bubbles[0].length;

        // Process each operation
        for (int[] op : operations) {
            int r = op[0], c = op[1];

            if (bubbles[r][c] != 0) { // If not already empty
                int color = bubbles[r][c];
                popBubbles(bubbles, r, c, color, rows, cols);
                applyGravity(bubbles, rows, cols);
            }
        }

        return bubbles;
    }

    // DFS to remove bubbles of the same color diagonally
    private static void popBubbles(int[][] board, int r, int c, int color, int rows, int cols) {
        if (r < 0 || c < 0 || r >= rows || c >= cols || board[r][c] != color) return;

        board[r][c] = 0; // Remove bubble
        for (int[] d : DIAGONALS) {
            popBubbles(board, r + d[0], c + d[1], color, rows, cols);
        }
    }

    // Apply gravity: Shift non-empty bubbles downward
    private static void applyGravity(int[][] board, int rows, int cols) {
        for (int col = 0; col < cols; col++) {
            int writePos = rows - 1; // Bottom-most available position

            for (int row = rows - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    board[writePos][col] = board[row][col];
                    if (writePos != row) {
                        board[row][col] = 0;
                    }
                    writePos--;
                }
            }
        }
    }
}
