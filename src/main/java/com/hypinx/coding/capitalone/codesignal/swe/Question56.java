package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question56 {

    public static void main(String[] args) {
        int[][] operations = new int[][]{{1,1,1}};
        boolean[] expected = new boolean[]{true};
        boolean[] result = solution(operations);

        TestCaseValidator.validateTestCase("1", Arrays.equals(expected, result));

        operations = new int[][]{
            {0, 1, 3},
            {0, 4, 2},
            {1, 3, 4},
            {1, 3, 2}
        };
        expected = new boolean[]{true, false};
        result = solution(operations);

        TestCaseValidator.validateTestCase("2", Arrays.equals(expected, result));
    }

    public static boolean[] solution(int[][] operations) {
        List<int[]> rectangles = new ArrayList<>(); // Store all rectangles created
        List<Boolean> results = new ArrayList<>(); // Store results for [1, a, b] operations

        // Iterate through all operations
        for (int[] operation : operations) {
            if (operation[0] == 0) {
                // Create and save a rectangle
                rectangles.add(new int[]{operation[1], operation[2]});
            } else if (operation[0] == 1) {
                // Check if all rectangles fit in the box of size a × b
                int boxA = operation[1];
                int boxB = operation[2];
                boolean allFit = true;

                // Check each rectangle
                for (int[] rectangle : rectangles) {
                    int w = rectangle[0];
                    int h = rectangle[1];

                    // Check if the rectangle fits either normally or rotated
                    if (!((w <= boxA && h <= boxB) || (h <= boxA && w <= boxB))) {
                        allFit = false;
                        break;
                    }
                }

                // Store the result
                results.add(allFit);
            }
        }

        // Convert the list of results to a boolean array
        boolean[] resultArray = new boolean[results.size()];
        for (int i = 0; i < results.size(); i++) {
            resultArray[i] = results.get(i);
        }

        return resultArray;
    }
}
