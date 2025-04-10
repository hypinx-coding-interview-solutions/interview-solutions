package com.hypinx.coding.meta.coding;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_11_Balance_Parentheses {

    public static int minAddToMakeValid(String s) {
        int openCount = 0;  // Tracks unmatched opening '('
        int closeCount = 0; // Tracks unmatched closing ')'

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                openCount++;  // Unmatched '(' encountered
            } else if (ch == ')') {
                if (openCount > 0) {
                    openCount--;  // Found a matching '(' for this ')'
                } else {
                    closeCount++; // No matching '(', so increment closeCount
                }
            }
        }

        // The sum of unmatched '(' and ')' gives the minimum insertions required
        return openCount + closeCount;
    }

    public static void main(String[] args) {
        String input = "(((";
        int result = minAddToMakeValid(input);
        int expected = 3;

        TestCaseValidator.validateTestCase("1", expected, result);

        input = "(()";
        result = minAddToMakeValid(input);
        expected = 1;

        TestCaseValidator.validateTestCase("2", expected, result);

        input = "(())";
        result = minAddToMakeValid(input);
        expected = 0;

        TestCaseValidator.validateTestCase("3", expected, result);

        input = ")(";
        result = minAddToMakeValid(input);
        expected = 2;

        TestCaseValidator.validateTestCase("4", expected, result);
    }
}
