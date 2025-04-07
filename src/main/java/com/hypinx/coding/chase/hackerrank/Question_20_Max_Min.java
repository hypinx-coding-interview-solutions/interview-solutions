package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Question_20_Max_Min {

    public static void main(String[] args) {
        List<Integer> input = List.of(1,2,3,1,2);
        int k = 1;
        int expected = 3;
        int result = maxMin(input, k);
        TestCaseValidator.validateTestCase("1", expected, result);

        input = List.of(1,1,1);
        k = 2;
        expected = 1;
        result = maxMin(input, k);
        TestCaseValidator.validateTestCase("1", expected, result);

        input = List.of(2,5,4,6,8);
        k = 3;
        expected = 4;
        result = maxMin(input, k);
        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int maxMin(List<Integer> arr, int k) {
        if (arr == null || arr.size() == 0 || k <= 0 || k > arr.size())
            return -1;

        Deque<Integer> deque = new ArrayDeque<>();
        int maxOfMins = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            // Remove indices that are out of this window
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Maintain increasing order in deque
            while (!deque.isEmpty() && arr.get(deque.peekLast()) >= arr.get(i)) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // When we have a complete window
            if (i >= k - 1) {
                int currentMin = arr.get(deque.peekFirst());
                maxOfMins = Math.max(maxOfMins, currentMin);
            }
        }

        return maxOfMins;
    }
}
