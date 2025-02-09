package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question62 {

    public static void main(String[] args) {
        String input = "aXA";
        int expected = 1;
        int result = solution(input);
        TestCaseValidator.validateTestCase("1", expected, result);

        input = "";
        expected = 0;
        result = solution(input);
        TestCaseValidator.validateTestCase("2", expected, result);

        input = "abCXccc";
        expected = 2;
        result = solution(input);
        TestCaseValidator.validateTestCase("3", expected, result);
    }

    public static int solution(String text) {
        int result = 0;
        text = text.toLowerCase();
        if (text.length() < 3) return result;

        int p1 = 0, p2 = 2;

        while (p2 < text.length()) {
            if (text.charAt(p1++) == text.charAt(p2++)) {
                result++;
            }
        }

        return result;
    }
}
