package com.hypinx.coding.chase.superday.algorithm;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_5_Balance_Parentheses {

    public static void main(String[] args) throws IllegalArgumentException {
        String s = "(()))";
        int expected = 1;
        int result = getMin(s);

        TestCaseValidator.validateTestCase("1", expected, result);

        s = "))((";
        expected = 4;
        result = getMin(s);

        TestCaseValidator.validateTestCase("2", expected, result);
    }

    // Time Complexity: O(N) - Linear - only one pass on the input string is needed
    // Space Complexity: O(1) - Constant - only 2 variables needed to keep track
    public static int getMin(String s) throws IllegalArgumentException {
        // Edge cases: If s is null or empty string, return 0
        if (s == null || s.isEmpty()) return 0;

        int open = 0, insertions = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;
            } else if (ch == ')') {
                if (insertions > 0) {
                    // Match an open parentheses from open
                    insertions--;
                } else {
                    // No open parentheses found from before
                    open++;
                }
            } else {
                // If the character is not either ( or ) throw an exception
                throw new IllegalArgumentException("Invalid character in input string. Only ( and ) allowed");
            }
        }

        return (open + insertions);
    }

}
