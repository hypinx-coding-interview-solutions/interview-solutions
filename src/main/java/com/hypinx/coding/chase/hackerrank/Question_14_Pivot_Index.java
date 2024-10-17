package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_14_Pivot_Index {

    public static void main(String[] args) {
        List<Integer> input = List.of(1,2,3,3);
        int expected = 2;
        int result = balancedSum(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = List.of(1,7,3,6,5,6);
        expected = 3;
        result = balancedSum(input);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static int balancedSum(List<Integer> arr) {
        int[] dpLeft = new int[arr.size() + 1];
        int[] dpRight = new int[arr.size() + 1];
        dpLeft[0] = 0;
        dpRight[dpRight.length - 1] = 0;
        buildLeft(arr, dpLeft);
        buildRight(arr, dpRight);


        if (arr.size() <= 1) return 0;

        int total = 0, minIndex = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (dpLeft[i] == dpRight[i + 1]) {
                return i;
            }
            total += arr.get(i);
        }
        return minIndex;
    }

    private static void buildLeft(List<Integer> arr, int[] dp) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
            dp[i + 1] = sum;
        }
    }

    private static void buildRight(List<Integer> arr, int[] dp) {
        int sum = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            sum += arr.get(i);
            dp[i] = sum;
        }
    }
}
