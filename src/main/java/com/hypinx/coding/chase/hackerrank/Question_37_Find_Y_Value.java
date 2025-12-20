package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_37_Find_Y_Value {

    public static void main(String[] args) {
        int bits = 4;
        int maxSet = 1;
        String x = "1011";
        String expected = "0100";
        String result = findYValue(bits, maxSet, x);

        TestCaseValidator.validateTestCase("1", expected.equals(result));
    }

    public static String findYValue(int bits, int maxSet, String x) {
        char[] y = new char[bits];
        int remaining = maxSet;

        for (int i = 0; i < bits; i++) {
            char xi = x.charAt(i);

            if (xi == '1') {
                // y=0 makes XOR bit 1 for free
                y[i] = '0';
            } else { // xi == '0'
                if (remaining > 0) {
                    // spend a 1 in y to make XOR bit 1
                    y[i] = '1';
                    remaining--;
                } else {
                    // can't spend more 1s, XOR bit becomes 0
                    y[i] = '0';
                }
            }
        }
        return new String(y);
    }
}
