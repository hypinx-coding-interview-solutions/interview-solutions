package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question75 {

    public static void main(String[] args) {
        String input = "456";
        int expected = 3;
        int result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = "6666";
        expected = 10;
        result = solution(input);

        TestCaseValidator.validateTestCase("2", expected, result);

        input = "303";
        expected = 5;
        result = solution(input);

        TestCaseValidator.validateTestCase("3", expected, result);

    }

    public static int solution(String alienCode) {
        int count = 0;

        for (int i = 0; i < alienCode.length(); i++) {
            for (int j = i + 1; j < alienCode.length() + 1; j++) {
                String substring = alienCode.substring(i, j);
                // Skip substrings starting with 0
                if (substring.charAt(0) == '0' && substring.length() > 1) continue;

                int current = Integer.parseInt(substring);
                if (current % 3 == 0) count++;
            }
        }

        return count;
    }
}
