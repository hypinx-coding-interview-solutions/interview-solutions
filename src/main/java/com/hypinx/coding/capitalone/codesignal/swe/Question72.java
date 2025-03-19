package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Solution works for only a few test cases
 */
public class Question72 {

    public static void main(String[] args) {
        int length = 7;
        int[][] queries = new int[][]{
                {1, 2}, {0, 2}, {3, 5}, {3, 2}, {2, 2}, {6, 1}, {1, 3}
        };

        int[] expected = new int[]{0, 1, 1, 1, 3, 3, 1};
        int[] result = solution(length, queries);
        TestCaseValidator.validateTestCase("1", expected, result);

        length = 5;
        queries = new int[][]{
                {0,1}, {0,2}, {1,2}, {1,1}
        };
        expected = new int[]{0, 0, 1, 0};
        result = solution(length, queries);
        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int[] solution(int length, int[][] queries) {
        Map<Integer, Integer> painted = new HashMap<>();
        TreeSet<Integer> modifiedPositions = new TreeSet<>();
        int[] result = new int[queries.length];
        int consecutivePairs = 0;

        for (int q = 0; q < queries.length; q++) {
            int coord = queries[q][0];
            int newColor = queries[q][1];

            // Find left and right neighbors
            Integer left = modifiedPositions.lower(coord);
            Integer right = modifiedPositions.higher(coord);

            // Check the current color at coord
            Integer oldColor = painted.getOrDefault(coord, -1);

            // Decrease consecutive pairs if overwriting an existing color that was part of a pair
            if (oldColor != -1) {
                if (left != null && painted.getOrDefault(left, -1) == oldColor) consecutivePairs--;
                if (right != null && painted.getOrDefault(right, -1) == oldColor) consecutivePairs--;
            }

            // Apply the new color
            painted.put(coord, newColor);
            modifiedPositions.add(coord);

            // Increase consecutive pairs if new paint forms new pairs
            if (left != null && painted.getOrDefault(left, -1) == newColor) consecutivePairs++;
            if (right != null && painted.getOrDefault(right, -1) == newColor) consecutivePairs++;

            // Store the count after this query
            result[q] = consecutivePairs;
        }

        return result;
    }
}
