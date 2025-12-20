package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_33_Change_Ads {

    public static void main(String[] args) {
        int base10 = 50;
        int expected = 13;
        int resultOne = changeAdsSolutionOne(base10);
        int resultTwo = changeAdsSolutionTwo(base10);


        TestCaseValidator.validateTestCase("1", expected, resultOne);
        TestCaseValidator.validateTestCase("2", expected, resultTwo);
    }

    public static int changeAdsSolutionOne(int base10) {
        // Edge case
        if (base10 == 0) return 1;

        // Count number of leading zeros
        int highestBit = 31 - Integer.numberOfLeadingZeros(base10);

        // From highest bit down to 0 use 1s
        int cover = (1 << highestBit + 1) - 1;

        // XOR
        return base10 ^ cover;
    }

    public static int changeAdsSolutionTwo(int base10) {
        String bin = Integer.toBinaryString(base10); // no leading zeros
        StringBuilder flipped = new StringBuilder(bin.length());

        for (int i = 0; i < bin.length(); i++) {
            flipped.append(bin.charAt(i) == '0' ? '1' : '0');
        }

        return Integer.parseInt(flipped.toString(), 2);
    }
}
