package com.hypinx.coding.chase.hackerrank;

import com.hypinx.coding.misc.TestCaseValidator;

public class Question_33_Change_Ads {

    public static void main(String[] args) {
        int base10 = 50;
        int expected = 13;
        int result = changeAds(base10);

        TestCaseValidator.validateTestCase("1", expected, result);

    }

    public static int changeAds(int base10) {
        // Edge case
        if (base10 == 0) return 1;

        // Count number of leading zeros
        int highestBit = 31 - Integer.numberOfLeadingZeros(base10);

        // From highest bit down to 0 use 1s
        int cover = (1 << highestBit + 1) - 1;

        // XOR
        return base10 ^ cover;
    }
}
