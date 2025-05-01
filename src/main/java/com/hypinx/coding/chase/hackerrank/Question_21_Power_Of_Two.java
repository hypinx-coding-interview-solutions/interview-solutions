package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question_21_Power_Of_Two {

    public static void main(String[] args) {
        List<Integer> input = List.of(1,3,8,12,16);
        List<Integer> expected = List.of(1,0,1,0,1);
        List<Integer> result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static List<Integer> solution(List<Integer> input) {

        List<Integer> result = new ArrayList<>();
        Set<Integer> values = new HashSet<>();

        for (int el : input) {
            boolean isPower;

            if (values.contains(el)) {
                isPower = true;
            } else {
                isPower = isPowerOfTwo(el);
            }

            if (isPower) {
                result.add(1);
                values.add(el);
            } else {
                values.add(0);
            }
        }
        return result;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        if (n <= 2) return true;
        if (n % 2 == 1) return false;

        return isPowerOfTwo(n / 2);
    }
}
