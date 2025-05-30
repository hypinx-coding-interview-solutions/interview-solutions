package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question76 {

    public static void main(String[] args) {
        String input = "abcdaaae";
        int expected = 3;
        int result = solution(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static int solution(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            char c = s.charAt(i + 2);

            if (a != b && b != c && a != c) {
                count++;
            }
        }

        return count;
    }
}
