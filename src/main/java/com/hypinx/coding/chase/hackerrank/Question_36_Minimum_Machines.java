package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;
import java.util.List;

public class Question_36_Minimum_Machines {

    public static void main(String[] args) {
        List<Integer> start = List.of(1,8,3,9,6);
        List<Integer> end = List.of(7,9,6,14,7);
        int expected = 3;
        int result = getMinMachines(start, end);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int getMinMachines(List<Integer> start, List<Integer> end) {
        int n = start.size();
        long[] s = new long[n];
        long[] e = new long[n];

        for (int i = 0; i < n; i++) {
            s[i] = start.get(i);
            e[i] = end.get(i);
        }

        Arrays.sort(s);
        Arrays.sort(e);

        int machines = 0;
        int endIdx = 0;

        for (int i = 0; i < n; i++) {
            // Inclusive end: can reuse only if start time is strictly greater than earliest end
            if (s[i] > e[endIdx]) {
                endIdx++; // reuse a freed machine
            } else {
                machines++; // need a new machine
            }
        }

        return machines;
    }
}
