package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question_25_Get_Min_Operations {

    public static void main(String[] args) {
        List<Long> input = List.of(8L);
        List<Integer> expected = List.of(4);
        List<Integer> result = getMinOperations(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static List<Integer> getMinOperations(List<Long> kValues) {
        Map<Long, Integer> cache = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (Long k : kValues) {
            result.add(minOperationsFromKtoZero(k, cache));
        }
        return result;
    }

    private static int minOperationsFromKtoZero(long k, Map<Long, Integer> cache) {
        if (cache.containsKey(k)) return cache.get(k);
        int operations = 0;
        while (k > 0) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k -= 1;
            }
            operations++;
        }
        cache.put(k, operations);
        return operations;
    }
}
