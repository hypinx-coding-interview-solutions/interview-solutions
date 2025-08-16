package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.List;

public class Question_29_Signal_Filtering {

    public static void main(String[] args) {
        List<Integer> frequencies = List.of(8, 15, 14, 16, 21);
        List<List<Integer>> filterRanges = List.of(
                List.of(10, 17),
                List.of(13, 15),
                List.of(13, 17)
        );

        long expected = 2;
        long result = countSignals(frequencies, filterRanges);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static long countSignals(List<Integer> frequencies, List<List<Integer>> filterRanges) {
        int result = 0;

        int maxStart = Integer.MIN_VALUE, minEnd = Integer.MAX_VALUE;
        for (List<Integer> filter : filterRanges) {
            int start = filter.get(0), end = filter.get(1);
            if (start > maxStart) maxStart = start;
            if (end < minEnd) minEnd = end;
        }

        for (int frequency : frequencies) {
            if (frequency >= maxStart && frequency <= minEnd) result++;
        }

        return result;
    }
}
