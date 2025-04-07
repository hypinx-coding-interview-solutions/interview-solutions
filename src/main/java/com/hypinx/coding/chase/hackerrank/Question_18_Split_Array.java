package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_18_Split_Array {

    public static void main(String[] args) {
        List<Integer> input = List.of(-3, -2, 10, 20, -30);
        int expected = 2;
        int result = splitIntoTwo(input);
        TestCaseValidator.validateTestCase("1", expected, result);

        input = List.of(10, -5, 6);
        expected = 1;
        result = splitIntoTwo(input);
        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static int splitIntoTwo(List<Integer> arr) {
        int leftSum = 0, rightSum = 0;

        for (int num : arr) {
            rightSum += num;
        }

        int ways = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            leftSum += arr.get(i);
            rightSum -= arr.get(i);
            if (leftSum > rightSum) {
                ways++;
            }
        }

        return ways;
    }

}
