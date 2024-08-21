package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question_9_Equal_Price {

    public static void main(String[] args) {
        List<Integer> price = Arrays.asList(2);
        List<Integer> query = List.of(8,4,3,10,6);
        List<Long> expected = Arrays.asList(6L, 2L, 1L, 8L, 4L);
        List<Long> result = countMinimumOperations(price, query);

        TestCaseValidator.validateTestCase("1", expected, result);

        price = Arrays.asList(2,5,1);
        query = List.of(8,4,3);
        expected = Arrays.asList(16L, 6L, 5L);
        result = countMinimumOperations(price, query);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    public static List<Long> countMinimumOperations(List<Integer> price, List<Integer> query) {
        int n = price.size();
        Collections.sort(price);

        List<Long> result = new ArrayList<>();
        long[] prefix = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + price.get(i - 1);
        }

        for (int value : query) {
            int i = Collections.binarySearch(price, value);
            if (i < 0) i = -(i + 1);
            result.add(1L * value * (2 * i - n) + prefix[n] - 2 * prefix[i]);
        }

        return result;
    }
}
