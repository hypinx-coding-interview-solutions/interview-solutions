package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_10_Circular_Printer {

    public static void main(String[] args) {
        String input = "BZA";
        long expected = 4;
        long result = getTime(input);

        TestCaseValidator.validateTestCase("1", expected, result);
    }

    public static long getTime(String s) {
        char start = 'A';
        long time = 0;

        for (char c : s.toCharArray()) {
            int rightSpin = Math.abs(c - start);
            int leftSpin = 26 - rightSpin;
            time += Math.min(rightSpin, leftSpin);
            start = c;
        }

        return time;
    }
}
