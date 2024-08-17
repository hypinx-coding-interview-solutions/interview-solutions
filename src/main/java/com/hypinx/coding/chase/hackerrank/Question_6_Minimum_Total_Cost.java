package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;

public class Question_6_Minimum_Total_Cost {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1,2,3,1,2);
        int expected = 5;
        int result = findMinimumCost(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = Arrays.asList(1,1,1,1);
        expected = 0;
        result = findMinimumCost(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    /**
     * Only 6 out of 15 test cases pass. Times out
     */
    public static int findMinimumCost(List<Integer> arr) {
        int n = arr.size();
        int totalCost = 0;

        for (int i = n - 1; i > 0; i--) {
            if (!arr.get(i).equals(arr.get(i-1))) {
                int diff = arr.get(i) - arr.get(i - 1);
                totalCost += Math.abs(diff);

                for (int j = 0; j < i; j++) {
                    arr.set(j, arr.get(j) + diff);
                }
            }
        }

        return totalCost;
    }

    public static int findMinimumCostV2(List<Integer> arr) {
        return -1;
    }
}
