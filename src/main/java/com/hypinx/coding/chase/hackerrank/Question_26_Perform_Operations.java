package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_26_Perform_Operations {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(9,8,7,6,5,4,3,2,1,0));
        List<List<Integer>> operations = List.of(
                List.of(0,9),
                List.of(4,5),
                List.of(3,6),
                List.of(2,7),
                List.of(1,8),
                List.of(0,9)
        );

        List<Integer> result = performOperations(input, operations);

        TestCaseValidator.validateTestCase("1", input, result);
    }

    private static List<Integer> performOperations(List<Integer> arr, List<List<Integer>> operations) {
        for (int i = 0; i < operations.size(); i++) {
            List<Integer> operation = operations.get(i);
            int leftPtr = operation.get(0), rightPtr = operation.get(1);

            while (leftPtr < rightPtr) {
                int left = arr.get(leftPtr), right = arr.get(rightPtr);
                arr.set(leftPtr, right);
                arr.set(rightPtr, left);
                leftPtr++;
                rightPtr--;
            }
        }

        return arr;
    }
}
