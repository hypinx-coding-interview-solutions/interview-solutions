package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_7_Substring_Removal {

    public static void main(String[] args) {
        String input = "BABB";
        int expected = 0;
        int result = getMinLength(input);

        TestCaseValidator.validateTestCase("1", expected, result);

        input = "AABBBAB";
        expected = 1;
        result = getMinLength(input);

        TestCaseValidator.validateTestCase("2", expected, result);

    }

    public static int getMinLength(String seq) {
        StringBuilder sb = new StringBuilder(seq);
        boolean found = true;
        while(found) {
            found = false;
            for (int i = 0; i < sb.length() - 1; i++) {
                // Delete either AB or BB from string
                if ((sb.charAt(i) == 'A' && sb.charAt(i + 1) == 'B') || (sb.charAt(i) == 'B' && sb.charAt(i + 1) == 'B')) {
                    sb.delete(i, i + 2);
                    found = true;
                    break;
                }
            }
        }
        return sb.length();
    }
}
