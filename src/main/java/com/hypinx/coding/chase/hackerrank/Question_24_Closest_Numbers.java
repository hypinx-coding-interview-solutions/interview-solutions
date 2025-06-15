package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question_24_Closest_Numbers {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(6);
        input.add(2);
        input.add(4);
        input.add(10);
        List<List<Integer>> expected = List.of(List.of(2,4), List.of(4,6));
        List<List<Integer>> result = closestNumbers(input, false);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    /**
     * This is the actual function which is given by Hackerrank but it doesn't return and only
     * expects to print. We have an overloaded method which returns the list for verification.
     */
    public static void closestNumbers(List<Integer> numbers) {
        Collections.sort(numbers);

        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i=0; i < numbers.size() - 1; i++) {
            int diff = numbers.get(i + 1) - numbers.get(i);
            if (diff < minDiff) {
                minDiff = diff;
                pairs.clear();
                pairs.add(Arrays.asList(numbers.get(i), numbers.get(i + 1)));
            } else if (diff == minDiff) {
                pairs.add(Arrays.asList(numbers.get(i), numbers.get(i + 1)));
            }
        }

        for (List<Integer> pair : pairs) {
            System.out.println(pair.get(0) + " " + pair.get(1));
        }
    }

    /**
     * This method is just used for verifying results. Not the signature used in Hackerrank
     */
    public static List<List<Integer>> closestNumbers(List<Integer> numbers, boolean print) {
        Collections.sort(numbers);

        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < numbers.size() - 1; i++) {
            int diff = numbers.get(i + 1) - numbers.get(i);
            if (diff < minDiff) {
                minDiff = diff;
                pairs.clear();
                pairs.add(Arrays.asList(numbers.get(i), numbers.get(i + 1)));
            } else if (diff == minDiff) {
                pairs.add(Arrays.asList(numbers.get(i), numbers.get(i + 1)));
            }
        }

        if (print) {
            for (List<Integer> pair : pairs) {
                System.out.println(pair.get(0) + " " + pair.get(1));
            }
        }

        return pairs;
    }
}
