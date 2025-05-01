package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_16_Round_Trip_Between_Two_Cities {

    public static void main(String[] args) {

        int[] D = new int[]{10, 8, 9, 11, 7};
        int[] R = new int[]{8, 8, 10, 7, 9};
        int[] expected = new int[]{1,3};
        int[] result = findMinRoundTripCost(D, R);
        TestCaseValidator.validateTestCase("1", expected, result);

        D = new int[]{10, 8, 9, 11, 7, 2};
        R = new int[]{8, 8, 10, 7, 9, 3};
        expected = new int[]{5,5};
        result = findMinRoundTripCost(D, R);
        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int[] findMinRoundTripCost(int[] D, int[] R) {
        if (D == null || R == null || D.length != R.length || D.length == 0) {
            throw new IllegalArgumentException("Invalid input.");
        }

        int minDepartureIndex = 0;
        int minCost = Integer.MAX_VALUE;
        int bestI = 0, bestJ = 0;

        for (int j = 0; j < D.length; j++) {
            // Evaluate (j, j)
            int cost = D[j] + R[j];
            if (cost < minCost) {
                minCost = cost;
                bestI = j;
                bestJ = j;
            }

            // Evaluate best previous departure (i < j)
            if (j > 0) {
                int costWithPrevious = D[minDepartureIndex] + R[j];
                if (costWithPrevious < minCost) {
                    minCost = costWithPrevious;
                    bestI = minDepartureIndex;
                    bestJ = j;
                }
            }

            // Update minDeparture for future days
            if (D[j] < D[minDepartureIndex]) {
                minDepartureIndex = j;
            }
        }

        return new int[]{bestI, bestJ};
    }
}
