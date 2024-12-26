package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question_16_Equalize {

    public static void main (String[] args) {
        List<Long> power = new ArrayList<>();
        power.addAll(List.of(1L,2L,3L,4L,5L));
        long expected = 6;
        long result = equalize(power);

        TestCaseValidator.validateTestCase("1", expected, result);

        power = new ArrayList<>();
        power.addAll(List.of(10L, 20L, 30L, 40L, 50L, 51L, 52L, 53L, 54L, 55L));
        expected = 115;
        result = equalize(power);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static long equalize(List<Long> power) {
        if (power == null || power.isEmpty()) return 0;

        // Sort list to find the median
        Collections.sort(power);

        int size = power.size();
        long median = power.get(size / 2);

        // Calculate the min cost to make all elements equal to the median
        long cost = 0;
        for (long num : power) {
            cost += Math.abs(num - median);
        }

        return cost;
    }
}
