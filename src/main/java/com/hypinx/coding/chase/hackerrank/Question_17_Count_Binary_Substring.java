package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_17_Count_Binary_Substring {

    public static void main(String[] args) {
        String input = "001100011";
        int expected = 6;
        int result = getSubstringCount(input);
        TestCaseValidator.validateTestCase("1", expected, result);

        input = "000110";
        expected = 3;
        result = getSubstringCount(input);
        TestCaseValidator.validateTestCase("2", expected, result);

        input = "010101010";
        expected = 8;
        result = getSubstringCount(input);
        TestCaseValidator.validateTestCase("3", expected, result);

    }

    public static int getSubstringCount(String s) {
        int current = 1, previous = 0, result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                current++;
            } else {
                result += Math.min(current, previous);
                previous = current;
                current = 1;
            }
        }

        return result + Math.min(current, previous);
    }

}
