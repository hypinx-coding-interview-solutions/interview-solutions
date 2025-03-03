package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.TreeSet;

public class Question68 {

    public static void main(String[] args) {
        int[][] operations = {
                {1, 2},
                {1, 6},
                {2, 4, 2},
                {2, 5, 2},
                {2, 1, 1},
                {2, 1, 2}
        };

        String expected = "1010";
        String result = solution(operations);

        TestCaseValidator.validateTestCase("1", expected.equals(result));
    }

    public static String solution(int[][] operations) {
        TreeSet<Integer> obstacles = new TreeSet<>();
        StringBuilder result = new StringBuilder();

        for (int[] op : operations) {
            if (op[0] == 1) {
                // Place an obstacle at position x
                obstacles.add(op[1]);
            } else if (op[0] == 2) {
                // Check if a block can be placed at x with given size
                int x = op[1];
                int size = op[2];
                int leftBound = x - (size - 1);
                int rightBound = x + (size - 1);

                // Use ceiling() to find the first obstacle in the range
                Integer firstObstacle = obstacles.ceiling(leftBound);

                // If an obstacle exists within [leftBound, rightBound], return "0"
                if (firstObstacle != null && firstObstacle <= rightBound) {
                    result.append("0");
                } else {
                    result.append("1");
                }
            }
        }

        return result.toString();
    }
}
